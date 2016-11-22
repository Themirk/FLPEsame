package pie.symtab;

import org.antlr.v4.runtime.Token;

public abstract class ScopedSymbol extends Symbol implements Scope {

  Scope enclosingScope;

  public ScopedSymbol(Token startToken, String name, Scope enclosingScope) {
    super(startToken, name, enclosingScope); // enclosing scope is the defining scope
    this.enclosingScope = enclosingScope;
  }

  public Symbol resolve(String name) {
    Symbol s = getMembers().get(name);
    if (s != null)
      return s;
    // if not here, check any parent scope
    if (getParentScope() != null) {
      return getParentScope().resolve(name);
    }
    return null; // not found
  }

  public void define(Symbol sym) {
    getMembers().put(sym.name, sym);
    sym.scope = this; // track the scope in each symbol
  }

  public Scope getParentScope() {
    return getEnclosingScope();
  }

  public Scope getEnclosingScope() {
    return enclosingScope;
  }

  public String getScopeName() {
    return name;
  }

}