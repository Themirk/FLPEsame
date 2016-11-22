package pie.symtab;

import org.antlr.v4.runtime.Token;

public class Symbol { // A generic programming language symbol 

  String name; // all symbols have a name
  Scope scope; // all symbols know what scope contains them
  Token startToken;

  // for variable and function symbols
  public Symbol(Token startToken, String name, Scope definingScope) {
    this.name = name;
    this.scope = definingScope;
    this.startToken = startToken;
  }

  public String getName() {
    return name;
  }

  public Scope getScope() {
    return this.scope;
  }

  public Token getStartToken() {
    return startToken;
  }

  public String toString() {
    String s = "";
    if (scope != null)
      s = scope.getScopeName() + ".";
    return s + getName();
  }

}
