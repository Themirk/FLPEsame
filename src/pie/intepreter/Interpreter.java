package pie.intepreter;

import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;
import pie.parser.PieBaseVisitor;
import pie.parser.PieParser.*;
import pie.symtab.*;

import java.util.Iterator;
import java.util.Stack;

public class Interpreter extends PieBaseVisitor<Object> {

  private ParseTreeProperty<Scope> scopes;
  private GlobalScope globalScope; // global scope 
  private Scope currentScope;
  private MemorySpace globals; // global memory
  private Stack<FunctionSpace> stack; // call stack
  private boolean exitOnReturn; // to manage return instructions

  public Interpreter(ParseTreeProperty<Scope> scopes) {
    super();
    this.scopes = scopes;
    this.globals = new MemorySpace("globals");
    this.stack = new Stack<FunctionSpace>();
    this.exitOnReturn = false;
  }

  @Override
  public Object visitProgram(ProgramContext ctx) {
    this.globalScope = (GlobalScope) scopes.get(ctx);
    this.currentScope = globalScope;
    for (StatementContext stat : ctx.statement())
      visit(stat);
    return null;
  }

  @Override
  public Object visitStatAssigment(StatAssigmentContext ctx) {
    Object value = visit(ctx.expr());
    fieldStore(ctx.field(), value);
    return null;
  }

  public Object fieldStore(FieldContext ctx, Object value) {
    TerminalNode[] fields = ctx.ID().toArray(new TerminalNode[0]);
    String varName = ctx.ID().get(0).getText();
    MemorySpace memSpace = getSpaceWithSymbol(varName);
    if (ctx.ID().size() == 1) {// it is a variable
      if (memSpace == null)
        memSpace = getCurrentSpace(); // create in current space
      memSpace.put(varName, value); // store
    } else {// it is a field
      if (memSpace == null)
        throw new ExecutionError(ctx.start, "undefined variable '" + varName + "'");
      Object varValue = memSpace.get(varName);
      if (!(varValue instanceof StructInstance))
        throw new ExecutionError(ctx.start, varName + " is not a struct");
      else {
        String currentField = null;
        StructInstance structMemSpace = (StructInstance) varValue;
        for (int i = 1; i < fields.length; i++) {
          currentField = fields[i].getText();
          // resolve field name in the struct scope
          Symbol sym = ((Scope) structMemSpace.getStructSymbol()).resolve(currentField);
          if (sym == null)
            throw new ExecutionError(fields[i].getSymbol(), currentField
                + " is not a field of struct '" + structMemSpace.getStructSymbol().getName() + "'");
          // get value
          if (i < fields.length - 1) {
            Object o = structMemSpace.get(currentField);
            if (o == null || !(o instanceof StructInstance))
              throw new ExecutionError(ctx.start, getFieldNameUpTo(fields, i)
                  + " is not a struct instance");
            else
              structMemSpace = (StructInstance) o;
          } else
            structMemSpace.put(currentField, value);
        }
      }
    }
    return null;
  }

  private String getFieldNameUpTo(TerminalNode[] fields, int position) {
    String name = null;
    for (int i = 0; i <= position; i++)
      name = (name == null ? fields[i].getText() : name + "." + fields[i]);
    return name;
  }

  @Override
  public Object visitBlock(BlockContext ctx) {
    Iterator<StatementContext> statements = ctx.statement().iterator();
    while (statements.hasNext() && !exitOnReturn)
      visit(statements.next());
    return null;
  }

  @Override
  public Object visitStatReturn(StatReturnContext ctx) {
    Object returnValue = visit(ctx.expr());
    stack.peek().setReturnValue(returnValue); // store in the function space
    this.exitOnReturn = true;
    return null;
  }

  @Override
  public Object visitStatPrint(StatPrintContext ctx) {
    System.out.println(visit(ctx.expr()));
    return null;
  }

  @Override
  public Object visitStatIf(StatIfContext ctx) {
    Object value = visit(ctx.expr());
    if (value instanceof Boolean) {
      if ((Boolean) value)
        visit(ctx.block(0));
      else if (ctx.block().size() == 2)
        visit(ctx.block(1));
    } else
      throw new ExecutionError(ctx.expr().start, "non boolean expression as condition");
    return null;
  }

  @Override
  public Object visitStaWhile(StaWhileContext ctx) {
    // TODO 
    return super.visitStaWhile(ctx);
  }

  @Override
  public Object visitStatFunctionCall(StatFunctionCallContext ctx) {
    visit(ctx.functionCall());
    return null;
  }

  @Override
  public Object visitFunctionCall(FunctionCallContext ctx) {
    // resolve function symbol
    FunctionSymbol functSym = null;
    {
      String functName = ctx.ID().getText();
      Symbol sym = currentScope.resolve(functName);
      if (sym == null || !(sym instanceof FunctionSymbol))
        throw new ExecutionError(ctx.start, "undefined function '" + functName + "'");
      else
        functSym = (FunctionSymbol) sym;
    }

    // create function space and pass parameters
    Scope savedScope = currentScope; // save the current scope
    currentScope = (Scope) functSym; // update current scope
    // check correct invocation
    if (ctx.expr().size() != functSym.numberOfFormalParameters())
      throw new ExecutionError(ctx.start, "wrong number of arguments for '" + functSym.getName()
          + "'");
    Object[] actualParams = new Object[ctx.expr().size()];
    // get value of actual parameters
    for (int i = 0; i < actualParams.length; i++)
      actualParams[i] = visit(ctx.expr(i));
    // define function space and push it into the stack
    FunctionSpace functionSpace = new FunctionSpace(functSym);
    stack.push(functionSpace);
    // parameters passed by value
    Symbol[] formalParams = functSym.getFormalParameters().values().toArray(new Symbol[0]);
    for (int i = 0; i < formalParams.length; i++)
      functionSpace.put(formalParams[i].getName(), actualParams[i]);
    // exec function code
    visit(functSym.getFunctionCode());
    FunctionSpace fs = stack.pop();
    currentScope = savedScope; // restore saved scope
    exitOnReturn = false; // stop exit on return return action
    return fs.getReturnValue();
  }

  @Override
  public Object visitExprMult(ExprMultContext ctx) {
    Object exp1 = visit(ctx.expr(0));
    Object exp2 = visit(ctx.expr(1));
    int left, right;
    if (exp1 instanceof Integer)
      left = (Integer) exp1;
    else
      throw new ExecutionError(ctx.start, "wrong type: expected int, found "
          + exp1.getClass().getName());
    if (exp2 instanceof Integer)
      right = (Integer) exp2;
    else
      throw new ExecutionError(ctx.start, "wrong type: expected int, found "
          + exp2.getClass().getName());
    switch (ctx.op.getText()) {
    case "*":
      return left * right;
    case "/":
      return left / right;
    default:
      throw new ImplementationError();
    }
  }

  @Override
  public Object visitExprAdd(ExprAddContext ctx) {
    Object exp1 = visit(ctx.expr(0));
    Object exp2 = visit(ctx.expr(1));
    int left, right;
    if (exp1 instanceof Integer)
      left = (Integer) exp1;
    else
      throw new ExecutionError(ctx.start, "wrong type: expected int, found "
          + exp1.getClass().getName());
    if (exp2 instanceof Integer)
      right = (Integer) exp2;
    else
      throw new ExecutionError(ctx.start, "wrong type: expected int, found "
          + exp2.getClass().getName());
    switch (ctx.op.getText()) {
    case "+":
      return left + right;
    case "-":
      return left - right;
    default:
      throw new ImplementationError();
    }
  }

  @Override
  public Object visitExprCondition(ExprConditionContext ctx) {
    Object exp1 = visit(ctx.expr(0));
    Object exp2 = visit(ctx.expr(1));
    int left, right;
    if (exp1 instanceof Integer)
      left = (Integer) exp1;
    else
      throw new ExecutionError(ctx.start, "wrong type: expected int, found "
          + exp1.getClass().getName());
    if (exp2 instanceof Integer)
      right = (Integer) exp2;
    else
      throw new ExecutionError(ctx.start, "wrong type: expected int, found "
          + exp2.getClass().getName());
    switch (ctx.op.getText()) {
    case "<":
      return left < right;
    case "==":
      return left == right;
    default:
      throw new ImplementationError();
    }
  }

  @Override
  public Object visitExprParens(ExprParensContext ctx) {
    return visit(ctx.expr());
  }

  @Override
  public Object visitExprField(ExprFieldContext ctx) {
    return load(ctx.field());
  }

  public Object load(FieldContext ctx) {
    TerminalNode[] fields = ctx.ID().toArray(new TerminalNode[0]);
    String varName = fields[0].getText();
    MemorySpace memSpace = getSpaceWithSymbol(varName);
    if (memSpace == null)
      throw new ExecutionError(ctx.start, "undefined variable '" + varName + "'");
    if (fields.length == 1) // it is a variable
      return memSpace.get(varName); // store
    else {// it is a field
      Object varValue = memSpace.get(varName);
      if (!(varValue instanceof StructInstance))
        throw new ExecutionError(ctx.start, varName + " is not a struct");
      else {
        String currentField = null;
        StructInstance structMemSpace = (StructInstance) varValue;
        for (int i = 1; i < fields.length; i++) {
          currentField = fields[i].getText();
          // resolve field name in the struct scope
          Symbol sym = ((Scope) structMemSpace.getStructSymbol()).resolve(currentField);
          if (sym == null)
            throw new ExecutionError(fields[i].getSymbol(), currentField
                + " is not a field of struct '" + structMemSpace.getStructSymbol().getName() + "'");
          // get value
          Object value = structMemSpace.get(currentField);
          if (i < fields.length - 1) {
            if (value == null || !(value instanceof StructInstance))
              throw new ExecutionError(ctx.start, getFieldNameUpTo(fields, i)
                  + " is not a struct instance");
            else
              structMemSpace = (StructInstance) value;
          } else
            return value;
        }
      }
    }
    return null;
  }

  public Object loadFromField(MemorySpace space, FieldContext ctx) {
    String currentField = null;
    String fullFieldName = null;
    for (TerminalNode field : ctx.ID()) {
      if (space == null)
        throw new ExecutionError(ctx.start, "no struct instace '" + field.getText() + "' defined");
      currentField = ctx.getText();
      fullFieldName = fullFieldName == null ? currentField : fullFieldName + "." + currentField;
      Object o = space.get(currentField);
      if (!(o instanceof StructInstance))
        throw new ExecutionError(ctx.start, fullFieldName + " is not a struct");
      space = (MemorySpace) o;
    }
    return space.get(currentField);
  }

  @Override
  public Object visitExprInt(ExprIntContext ctx) {
    return new Integer(ctx.getText());
  }

  @Override
  public Object visitExprString(ExprStringContext ctx) {
    return new String(ctx.getText());
  }

  @Override
  public Object visitExprStructCreation(ExprStructCreationContext ctx) {
    String structName = ctx.ID().getText();
    Symbol symbol = currentScope.resolve(structName);
    if (symbol == null || !(symbol instanceof StructSymbol))
      throw new ExecutionError(ctx.start, "undefined struct '" + structName + "'");
    return new StructInstance((StructSymbol) symbol);
  }

  @Override
  public Object visitExprFunctionCall(ExprFunctionCallContext ctx) {
    return visit(ctx.functionCall());
  }

  /** Return scope holding id's value; current func space or global. */
  public MemorySpace getSpaceWithSymbol(String id) {
    if (stack.size() > 0 && stack.peek().get(id) != null) // in top stack?
      return stack.peek();
    if (globals.get(id) != null)
      return globals; // in globals?
    return null; // nowhere
  }

  /** Return curfrent space. */
  public MemorySpace getCurrentSpace() {
    if (stack.size() > 0) // in top stack?
      return stack.peek();
    else
      return globals; // nowhere
  }
}
