package pie.intepreter;

import pie.symtab.FunctionSymbol;

/**
 * A function invocation scope; stores arguments, local variables and return
 * value
 */
public class FunctionSpace extends MemorySpace {

  FunctionSymbol functionSymbol; // what function are we executing?
  Object returnValue;

  public FunctionSpace(FunctionSymbol functionSymbol) {
    super(functionSymbol.getName() + " invocation");
    this.functionSymbol = functionSymbol;
  }

  /**
   * @return the returnValue
   */
  public Object getReturnValue() {
    return this.returnValue;
  }

  /**
   * @param returnValue the returnValue to set
   */
  public void setReturnValue(Object returnValue) {
    this.returnValue = returnValue;
  }

}
