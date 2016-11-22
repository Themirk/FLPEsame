package pie.symtab;

import org.antlr.v4.runtime.Token;

public class VariableSymbol extends Symbol {

  public VariableSymbol(Token startToken, String name, Scope scope) {
    super(startToken, name, scope);
  }

  //  @Override
  //  public String toString() {
  //    return "variable " + super.toString();
  //  }
}
