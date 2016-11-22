package interpreter;

/**
 * Created by longm on 14/11/16.
 */

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.AssemblerLexer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.StringTokenizer;

public class Interpreter
{

    //CAMPI
    private static final int DEFAULT_OPERAND_STACK_SIZE = 100;
    private static final int DEFAULT_CALL_STACK_SIZE = 1000;
    private int ip;             		// instruction pointer register
    private byte[] code;       			// byte-addressable code memory (memoria codice)
    private int codeSize;
    private Object[] globals;   		// global variable space (memoria globale)
    private Object[] constPool;		    // per memorizzare tutto ci� che non pu� essere memorizzato come un intero di 4 bit (string,float,struc)

    //stack degli operandi, all'interno della quale vengono messi parametri e variabili locali delle funzioni
    private Object[] operands = new Object[DEFAULT_OPERAND_STACK_SIZE];
    private int sp = -1;        		// stack pointer register

    //stack degli stack frame
    private StackFrame[] calls = new StackFrame[DEFAULT_CALL_STACK_SIZE];		// funzioni
    private int fp = -1;        		                                        // frame pointer register
    private FunctionSymbol mainFunction;

    //MAIN
    public static void main(String[] args) throws Exception {
        InputStream input = new FileInputStream(new File("test/fatt.asm"));
        Interpreter interpreter = new Interpreter();
        load(interpreter, input);		// popola i campi dell'interprete
        /*
        inizia l'esecuzione del programma in bytecode, richiama il main del programma ed esegue il suo metodo cpu()
         */
        interpreter.exec();
        interpreter.cleanMemory();
    }

    //  svuotamento della memoria
    private void cleanMemory() {
        mainFunction = null;
        fp = -1;
        calls = null;
        sp = -1;
        operands = null;
        constPool = null;
        globals = null;
        codeSize = 0;
        code = null;
        ip = -1;
    }

    //avvio il programma e popolo i campi dell'interprete
    private static boolean load(Interpreter interp, InputStream input) throws Exception {
        boolean hasErrors = false;
        try {
            AssemblerLexer assemblerLexer = new AssemblerLexer(new ANTLRInputStream(input));
            CommonTokenStream tokens = new CommonTokenStream(assemblerLexer);
            BytecodeAssembler assembler = new BytecodeAssembler(tokens, BytecodeDefinition.instructions);
            assembler.program();

            //recupero i campi riempiti dal metodo program() di AssemblerParser e popolo i campi dell'interprete
            interp.code = assembler.getMachineCode();
            interp.codeSize = assembler.getCodeMemorySize();
            interp.constPool = assembler.getConstantPool();
            interp.mainFunction = assembler.getMainFunction();
            interp.globals = new Object[assembler.getDataSize()];
            hasErrors = assembler.getNumberOfSyntaxErrors()>0;
            System.err.println("There are errors: "+hasErrors);
            System.out.println("");
        }
        finally {
            input.close();
        }
        return hasErrors;
    }

    /** Execute the bytecodes in code memory starting at mainAddr */
    private void exec() throws Exception {
        StackFrame f = new StackFrame(mainFunction, -1);
        calls[++fp] = f;											// richiama il main del programma in bytecode
        ip = mainFunction.address;
        cpu();
    }

    //simulazione del ciclo fetch-decode-execute
    private void cpu() {
        //Object v=null; // some locals to reuse
        int a,b;
        float e,f;
        int addr = 0;
        short opcode = code[ip];
        System.out.println("Starting execution program...");
        while (opcode != BytecodeDefinition.HALT && ip < codeSize) {
            ip++;
            System.out.println("opcode: "+opcode);
            switch (opcode) {
                case BytecodeDefinition.IADD :
                    a = (Integer)operands[sp-1];
                    b = (Integer)operands[sp];
                    sp -= 2;
                    System.out.println("istr: iadd, ops: "+a+", "+b);
                    operands[++sp] = a + b;
                    break;
                case BytecodeDefinition.ISUB :
                    a = (Integer)operands[sp-1];							// prende prende l'operando sotto la cima della stack
                    b = (Integer)operands[sp];								// prende l'operando sulla cima dello stack
                    sp -= 2;												// elimina i due operandi dallo stack
                    System.out.println("istr: isub, ops: "+a+", "+b);
                    operands[++sp] = a - b;									// esegue la sottrazione e salva il risultato sulla cima dello stack
                    break;
                case BytecodeDefinition.IMUL:
                    a = (Integer)operands[sp-1];
                    b = (Integer)operands[sp];
                    sp -= 2;
                    System.out.println("istr: imul, ops: "+a+", "+b);
                    operands[++sp] = a * b;
                    break;
                case BytecodeDefinition.IDIV:
                    a = (Integer)operands[sp-1];
                    b = (Integer)operands[sp];
                    sp -= 2;
                    System.out.println("istr: idiv, ops: "+a+", "+b);
                    operands[++sp] = a / b;
                    break;
                case BytecodeDefinition.FADD :
                    e = (Float)operands[sp-1];
                    f = (Float)operands[sp];
                    sp -= 2;
                    System.out.println("istr: fadd, ops: "+e+", "+f);
                    operands[++sp] = e + f;
                    break;
                case BytecodeDefinition.FSUB :
                    e = (Float)operands[sp-1];
                    f = (Float)operands[sp];
                    sp -= 2;
                    System.out.println("istr: fsub, ops: "+e+", "+f);
                    operands[++sp] = e - f;
                    break;
                case BytecodeDefinition.FMUL:
                    e = (Float)operands[sp-1];
                    f = (Float)operands[sp];
                    sp -= 2;
                    System.out.println("istr: fmul, ops: "+e+", "+f);
                    operands[++sp] = e * f;
                    break;
                case BytecodeDefinition.FDIV:
                    e = (Float)operands[sp-1];
                    f = (Float)operands[sp];
                    sp -= 2;
                    System.out.println("istr: fdiv, ops: "+e+", "+f);
                    operands[++sp] = e / f;
                    break;
                case BytecodeDefinition.ILT :
                    a = (Integer)operands[sp-1];
                    b = (Integer)operands[sp];
                    sp -= 2;
                    System.out.println("istr: ilt, ops: "+a+", "+b);
                    operands[++sp] = a < b;
                    break;
                case BytecodeDefinition.IGT :
                    a = (Integer)operands[sp-1];
                    b = (Integer)operands[sp];
                    sp -= 2;
                    System.out.println("istr: igt, ops: "+a+", "+b);
                    operands[++sp] = a > b;
                    break;
                case BytecodeDefinition.IEQ :
                    a = (Integer)operands[sp-1];
                    b = (Integer)operands[sp];
                    sp -= 2;
                    System.out.println("istr: ieq, ops: "+a+", "+b);
                    operands[++sp] = a == b;
                    break;
                case BytecodeDefinition.FLT :
                    e = (Float)operands[sp-1];
                    f = (Float)operands[sp];
                    sp -= 2;
                    System.out.println("istr: flt, ops: "+e+", "+f);
                    operands[++sp] = e < f;
                    break;
                case BytecodeDefinition.FGT :
                    e = (Float)operands[sp-1];
                    f = (Float)operands[sp];
                    sp -= 2;
                    System.out.println("istr: fgt, ops: "+e+", "+f);
                    operands[++sp] = e > f;
                    break;
                case BytecodeDefinition.FEQ :
                    e = (Float)operands[sp-1];
                    f = (Float)operands[sp];
                    sp -= 2;
                    System.out.println("istr: feq, ops: "+e+", "+f);
                    operands[++sp] = e == f;
                    break;
                case BytecodeDefinition.ITOF :
                    a = (Integer)operands[sp--];
                    operands[++sp] = (float)a;
                    System.out.println("istr: itof, op: "+a);
                    break;
                case BytecodeDefinition.ICONST :
                    operands[++sp] = getIntOperand();
                    System.out.println("istr: iconst, op: "+operands[sp]);
                    break;
                case BytecodeDefinition.SCONST :
                    int constPoolIndex = getIntOperand();
                    operands[sp+1] = constPool[constPoolIndex];
                    sp++;
                    System.out.println("istr: sconst, op: "+operands[sp]);
                    break;
                case BytecodeDefinition.FCONST :
                    int constPoolIndex1 = getIntOperand();
                    operands[++sp] = constPool[constPoolIndex1];
                    System.out.println("istr: fconst, op: "+constPool[constPoolIndex1]);
                    break;
                case BytecodeDefinition.CALL :
                    int funcIndexInConstPool = getIntOperand();						// il parametro del metodo call � un indirizzo della constant pool (dove inizia la funzione)
                    call(funcIndexInConstPool);										// per recuperarlo utilizza il metodo getIntOperand()
                    System.out.println("istr: call, constPoolAddress: "+funcIndexInConstPool);
                    break;
                case BytecodeDefinition.RET :
                    StackFrame fr = calls[fp--];
                    ip = fr.returnAddress;
                    System.out.println("istr: ret to "+ip);
                    break;
                case BytecodeDefinition.BR :
                    ip = getIntOperand();
                    System.out.println("istr: br to "+ip);
                    break;
                case BytecodeDefinition.BRT :
                    addr = getIntOperand();
                    if ( operands[sp--].equals(true) ){
                        ip = addr;
                        System.out.println("istr: brt to "+ip);
                    }
                    break;
                case BytecodeDefinition.BRF :
                    addr = getIntOperand();
                    if ( operands[sp--].equals(false) ){
                        ip = addr;
                        System.out.println("istr: brf to "+ip);
                    }
                    break;
                case BytecodeDefinition.GLOAD :
                    addr = getIntOperand();
                    operands[++sp] = globals[addr];
                    System.out.println("istr: gload "+operands[sp]);
                    break;
                case BytecodeDefinition.GSTORE :
                    addr = getIntOperand();
                    globals[addr] = operands[sp--];
                    System.out.println("istr: gstore "+globals[addr]);
                    break;
                case BytecodeDefinition.LOAD :
                    addr = getIntOperand();
                    operands[++sp] = calls[fp].locals[addr];			// vengono caricati i parametri dalla funzione allo stack di operandi
                    System.out.println("istr: load  "+operands[sp]);
                    break;
                case BytecodeDefinition.STORE :
                    addr = getIntOperand();
                    calls[fp].locals[addr] = operands[sp--];			// vengono passati i parametri alla funzione
                    System.out.println("istr: store "+calls[fp].locals[addr]);
                    break;
                case BytecodeDefinition.PRINT :
                    System.out.println("istr: print "+operands[sp--]);
                    break;
                default :
                    throw new Error("invalid opcode: "+opcode+" at ip="+(ip-1));
            }
            opcode = code[ip];
        }
        System.out.println("End execution program...");
    }

    //passando l'indice, va a recuperare il FunctionSymbol corrispondente
    private void call(int functionConstPoolIndex) {
        FunctionSymbol fs = (FunctionSymbol) constPool[functionConstPoolIndex];
        StackFrame f = new StackFrame(fs, ip);
        calls[++fp] = f;
        ip = fs.address;
    }

    //Gli int sono rappresentati con 4 byte, quindi ogni volta che viene inserito un int, andremo in avanti di 4
    // byte nella code memory
    protected int getIntOperand() {
        int word = BytecodeAssembler.getInt(code, ip);
        ip += 4;
        return word;
    }
}
