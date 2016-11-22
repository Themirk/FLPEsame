package interpreter;

/**
 * Created by longm on 14/11/16.
 */
import parser.AssemblerParser;

public class BytecodeDefinition {

    public static final int INT = AssemblerParser.INT,
            FLOAT = AssemblerParser.FLOAT,
            STRING = AssemblerParser.STRING,
            LABEL = AssemblerParser.ID,
            FUN = AssemblerParser.FUNC;

    public static class Instruction {
        String name;
        int operand;
        public Instruction(String name) {
            this(name, 0);
        }
        public Instruction(String name, int a) {
            this.name = name;
            this.operand = a;
        }
    }

    // INSTRUCTION BYTECODES
    public static final short
    //integer operators
    IADD = 1, ISUB = 2, IMUL = 3, IDIV = 4,
    //float operators
    FADD = 5, FSUB = 6, FMUL = 7, FDIV = 8,
    //integer comparison
    ILT = 9, IGT = 10, IEQ = 11,
    //float comparison
    FLT = 12, FGT = 13, FEQ = 14,
    //int to float converter
    ITOF = 15,
    //add int constant to stack
    ICONST = 16,
    //add string constant to stack
    SCONST = 17,
    //add float constant to stack
    FCONST = 18,
    //function operators
    CALL = 19, RET = 20,
    //branch
    BR = 21, BRT = 22, BRF = 23,
    //global
    GLOAD = 24, GSTORE = 25,
    //local
    LOAD = 26, STORE = 27,
    //system
    PRINT = 28, HALT = 29;


    /** Used for assembly/disassembly; describes instruction set */
    public static Instruction[] instructions = new Instruction[] {
            null, // <INVALID>
            new Instruction("iadd"),
            new Instruction("isub"),
            new Instruction("imul"),
            new Instruction("idiv"),
            new Instruction("fadd"),
            new Instruction("fsub"),
            new Instruction("fmul"),
            new Instruction("fdiv"),
            new Instruction("ilt"),
            new Instruction("igt"),
            new Instruction("ieq"),
            new Instruction("flt"),
            new Instruction("fgt"),
            new Instruction("feq"),
            new Instruction("itof"),
            new Instruction("iconst", INT),
            new Instruction("sconst", STRING),
            new Instruction("fconst", FLOAT),
            new Instruction("call", FUN),
            new Instruction("ret"),
            new Instruction("br", LABEL),
            new Instruction("brt", LABEL),
            new Instruction("brf", LABEL),
            new Instruction("gload", INT),
            new Instruction("gstore", INT),
            new Instruction("load", INT),
            new Instruction("store", INT),
            new Instruction("print"),
            new Instruction("halt")     // index is the opcode
    };
}
