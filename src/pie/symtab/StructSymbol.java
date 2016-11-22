package pie.symtab;

import org.antlr.v4.runtime.Token;

import java.util.LinkedHashMap;
import java.util.Map;

public class StructSymbol extends ScopedSymbol implements Scope {

  Map<String, Symbol> fields = new LinkedHashMap<String, Symbol>();

  public StructSymbol(Token startToken, String name, Scope parent) {
    super(startToken, name, parent);
  }

  /** For a.b, only look in a only to resolve b, not up scope tree */
  public Symbol resolveMember(String name) {
    return fields.get(name);
  }

  public Map<String, Symbol> getMembers() {
    return fields;
  }

  public boolean checkIfDefinedAsField(String name) {
    return fields.containsKey(name);
  }

  public String toString() {
    String fieldsStr = "";
    String[] memebers =
        fields.keySet().toArray(new String[fields.keySet().size()]);
    for (int i = 0; i < memebers.length; i++)
      fieldsStr += memebers[i] + (i < memebers.length - 1 ? ", " : "");
    return "struct " + name + ":{" + fieldsStr + "}";
  }
}