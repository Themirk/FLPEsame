package pie.intepreter;

import pie.symtab.StructSymbol;

public class StructInstance extends MemorySpace {

  StructSymbol structSymbol; // the corresponding struct symbol 

  public StructInstance(StructSymbol struct) {
    super(struct.getName() + " instance");
    this.structSymbol = struct;
  }

  public StructSymbol getStructSymbol() {
    return structSymbol;
  }

  public String toString() {
    StringBuilder buf = new StringBuilder();
    buf.append("struct ");
    buf.append(structSymbol.getName());
    buf.append("{");
    boolean first = true;
    for (String fieldName : structSymbol.getMembers().keySet()) {
      Object v = members.get(fieldName);
      if (!first)
        buf.append(", ");
      else
        first = false;
      buf.append(fieldName);
      buf.append('=');
      buf.append(v);
    }
    buf.append("}");
    return buf.toString();
  }
}