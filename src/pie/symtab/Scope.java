package pie.symtab;

import java.util.Map;

public interface Scope {

  /**
   * Returns the scope name.
   * 
   * @return the name of this scope.
   */
  public String getScopeName();

  /**
   * Returns the scope enclosing this one.
   * 
   * @return the enclosing scope.
   */
  public Scope getEnclosingScope();

  /**
   * Defines <code>sym</code> in this scope.
   * 
   * @param sym the symbol.
   */
  public void define(Symbol sym);

  /** Look up name in this scope or in enclosing scope if not here */
  public Symbol resolve(String name);

  /**
   * Indicate how subclasses store scope members. Allows us to factor out common
   * code in this class.
   */
  public abstract Map<String, Symbol> getMembers();

}
