package pie.symtab;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import pie.parser.PieBaseListener;
import pie.parser.PieParser.FunctionDefinitionContext;
import pie.parser.PieParser.ProgramContext;
import pie.parser.PieParser.StructDefinitionContext;

public class ScopePrinter extends PieBaseListener {

  ParseTreeProperty<Scope> scopes = new ParseTreeProperty<Scope>();
  private String indent = "";
  private final String TAB = "  ";

  public ScopePrinter(ParseTreeProperty<Scope> scopes) {
    super();
    this.scopes = scopes;
  }

  private void decIndentation() {
    indent = indent.substring(TAB.length());
  }

  private void incrIndentation() {
    indent = indent + TAB;
  }

  private void printScope(ParserRuleContext ctx) {
    Scope scope = scopes.get(ctx);
    System.out.println(indent + "-- Scope " + scope.getScopeName()); // print symbol table of global scope
    System.out.println(indent + "   Symbol table: " + scope.getMembers());
  }

  @Override
  public void enterProgram(ProgramContext ctx) {
    printScope(ctx);
    incrIndentation();
  }

  @Override
  public void enterStructDefinition(StructDefinitionContext ctx) {
    printScope(ctx);
    incrIndentation();
  }

  @Override
  public void exitStructDefinition(StructDefinitionContext ctx) {
    decIndentation();
  }

  @Override
  public void enterFunctionDefinition(FunctionDefinitionContext ctx) {
    printScope(ctx);
    incrIndentation();
  }

  @Override
  public void exitFunctionDefinition(FunctionDefinitionContext ctx) {
    decIndentation();
  }

}
