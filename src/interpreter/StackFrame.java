package interpreter;

/**
 * Created by longm on 14/11/16.
 */
class StackFrame
{
    int returnAddress;  // the instruction following the call
    Object[] locals;    // holds parameters and local variables

    StackFrame(FunctionSymbol sym, int returnAddress)
    {
        this.returnAddress = returnAddress;
        this.locals = new Object[sym.nargs+sym.nlocals];		// crea un array con n posizioni (nr di argomenti della funzione + nr di variabili locali)
    }
}
