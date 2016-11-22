package pie.symtab;

import org.antlr.v4.runtime.Token;
import pie.parser.PieParser.BlockContext;

import java.util.LinkedHashMap;
import java.util.Map;

public class FunctionSymbol extends ScopedSymbol {

  private LinkedHashMap<String, Symbol> formalParameters = new LinkedHashMap<String, Symbol>();
  private BlockContext block;
  
  public FunctionSymbol(Token startToken, String name, Scope enclosingScope, BlockContext block) {
    super(startToken, name, enclosingScope); // enclosing scope is the defining scope
    this.block = block;
  }

  public Map<String, Symbol> getMembers() {
    return formalParameters;
  }

  public int numberOfFormalParameters() {
    return formalParameters.keySet().size();
  }

  public Map<String, Symbol> getFormalParameters() {
    return formalParameters;
  }

  public BlockContext getFunctionCode() {
    return block;
  }

  public boolean checkIfDefinedAsFormalParameter(String name) {
    return formalParameters.containsKey(name);
  }


  public String toString() {
    String formParStr = "";
    String[] formPar =
        formalParameters.keySet().toArray(new String[formalParameters.keySet().size()]);
    for (int i = 0; i < formPar.length; i++)
      formParStr += formPar[i] + (i < formPar.length - 1 ? ", " : "");

    return "function " + name + "(" + formParStr + ")";
  }
}