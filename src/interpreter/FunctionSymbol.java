package interpreter;

/**
 * Created by longm on 14/11/16.
 */
public class FunctionSymbol {

    protected int address = -1;
    protected int nargs = 0;
    protected String name;
    protected int nlocals = 0;

    public FunctionSymbol(String name, int args, int locals, int ip) {
        this.name = name;
        this.nargs = args;
        this.nlocals = locals;
        this.address = ip;
    }

    public FunctionSymbol(String id) {
        this.name = id;
    }

}
