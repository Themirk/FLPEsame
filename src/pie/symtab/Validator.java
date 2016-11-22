package pie.symtab;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import pie.parser.PieBaseListener;
import pie.parser.PieParser.*;

/**
 * Build the symbol table and check some static properties
 * 
 * @author Mauro Ferrari
 *
 */
public class Validator extends PieBaseListener {

  private GlobalScope globalScope;
  private Scope currentScope;
  private int numberOfErrors;
  // to store associations between a node of the parse-tree
  // and its scope
  private ParseTreeProperty<Scope> scopes;

  public Validator() {
    super();
    this.globalScope = new GlobalScope();
    this.numberOfErrors = 0;
    this.scopes = new ParseTreeProperty<Scope>();
  }

  @Override
  public void enterProgram(ProgramContext ctx) {
    this.currentScope = globalScope;
    scopes.put(ctx, globalScope);
  }

  @Override
  public void enterStructDefinition(StructDefinitionContext ctx) {
    String structName = ctx.ID().getText();
    Symbol sym = currentScope.resolve(structName);
    if (sym != null) // struct and function names cannot overlap
      reportError(ctx.start, "symbol '" + structName + "' already defined as '" + sym.toString()
          + "'");
    else {
      StructSymbol struct = new StructSymbol(ctx.start, structName, currentScope);
      currentScope.define(struct);
      currentScope = struct;
      scopes.put(ctx, struct);
    }
  }

  @Override
  public void exitStructDefinition(StructDefinitionContext ctx) {
    currentScope = currentScope.getEnclosingScope();
  }

  @Override
  public void enterFunctionDefinition(FunctionDefinitionContext ctx) {
    String functName = ctx.ID().getText();
    Symbol sym = currentScope.resolve(functName);
    if (sym != null) // struct and function names cannot overlap
      reportError(ctx.start, "symbol '" + functName + " already defined as '" + sym.toString());
    else {
      FunctionSymbol fun = new FunctionSymbol(ctx.start, functName, currentScope, ctx.block());
      currentScope.define(fun);
      currentScope = fun;
      scopes.put(ctx, fun);
    }
  }

  @Override
  public void exitFunctionDefinition(FunctionDefinitionContext ctx) {
    currentScope = currentScope.getEnclosingScope();
  }

  @Override
  public void exitVardef(VardefContext ctx) {
    String varName = ctx.ID().getText();
    if (currentScope instanceof FunctionSymbol) {
      FunctionSymbol fun = (FunctionSymbol) currentScope;
      if (fun.checkIfDefinedAsFormalParameter(varName))
        reportError(ctx.start, "duplicate argument '" + varName + "' in function '" + fun.getName()
            + "' definition");
      VariableSymbol var = new VariableSymbol(ctx.start, varName, currentScope);
      currentScope.define(var);
    }
    if (currentScope instanceof StructSymbol) {
      StructSymbol struct = (StructSymbol) currentScope;
      if (struct.checkIfDefinedAsField(varName))
        reportError(ctx.start, "duplicate field '" + varName + "' in struct '" + struct.getName()
            + "' definition");
      VariableSymbol var = new VariableSymbol(ctx.start, varName, currentScope);
      currentScope.define(var);
    }
  }

  @Override
  public void enterStatReturn(StatReturnContext ctx) {
    // check whether return occurs in function
    if (!(currentScope instanceof FunctionSymbol))
      reportError(ctx.start, "'return' outside function");
  }

  public ParseTreeProperty<Scope> getScopes() {
    return scopes;
  }

  private void reportError(Token token, String msg) {
    numberOfErrors++;
    MessageManager.error(token, msg);
    throw new SymbolTableBuilderError();
  }

  public int getNumberOfErrors() {
    return numberOfErrors;
  }

}
