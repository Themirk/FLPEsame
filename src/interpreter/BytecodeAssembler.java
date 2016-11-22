package interpreter;
/**
 * Created by longm on 14/11/16.
 */

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import parser.AssemblerParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class BytecodeAssembler extends AssemblerParser {
    //dimensione iniziale del codice
    private static final int INITIAL_CODE_SIZE = 1024;

    //contenitore del set d'istruzioniche l'assemblatore riconoscerà
    private Map<String,Integer> instructionOpcodeMapping = new HashMap<>();

    //label scope
    private Map<String, LabelSymbol> labels = new HashMap<>();

    // nella constant pool verranno salvate tutte le variabili non integer, come string, float
    private List<Object> constPool = new ArrayList<>();

    //instruction point
    private int ip = 0;

    //code memory
    protected byte[] code = new byte[INITIAL_CODE_SIZE];

    // set via .globals
    private int dataSize;
    private FunctionSymbol mainFunction;

    //prende in input uno stream di token passato dal lexer
    BytecodeAssembler(TokenStream lexer, BytecodeDefinition.Instruction[] instructions) {
        super(lexer);
        //dal dizionario associamo il numero dell'istruzione al nome dell'istruzione
        for (int i=1; i<instructions.length; i++) {
            instructionOpcodeMapping.put(instructions[i].name.toLowerCase(),i);
        }
    }

    byte[] getMachineCode() {
        return code;
    }

    int getCodeMemorySize() {
        return ip;
    }

    int getDataSize() {
        return dataSize;
    }

    //restituisce l'indirizzo della funzione
    FunctionSymbol getMainFunction() {
        return mainFunction;
    }

    //genera il codice per un istruzione senza operandi
    protected void gen(Token instrToken)
    {
        String instrName = instrToken.getText();
        //se istruzione letta non è presente nel dizionario, allora non è possibile
        Integer opcodeI = instructionOpcodeMapping.get(instrName);
        if ( opcodeI==null ) {
            System.err.println("line "+instrToken.getLine()+": Unknown instruction: "+instrName);
            return;
        }
        int opcode = opcodeI;
        ensureCapacity(ip+1);
        //aggiunta dell'istruzione al codice
        code[ip++] = (byte)(opcode&0xFF);
        System.out.println("instr name: "+instrName+" code: "+opcodeI);
    }

    //generazione codice per istruzione con un operando
    protected void gen(Token instrToken, Token operandToken) {
        gen(instrToken);
        genOperand(operandToken);
    }

    //permette di capire il tipo dell'ultimo operando, inserendo in v il bytecode dell'operando e scrivendolo in memoria codice
    private void genOperand(Token operandToken)
    {
        String text = operandToken.getText();
        int v = 0;
        switch ( operandToken.getType() )
        { // switch on token type
            case INT :
                v = Integer.valueOf(text);
                break;
            case FLOAT :
                v = getConstantPoolIndex(Float.valueOf(text)); //indice del constant pool
                break;
            case STRING:
                v = getConstantPoolIndex(text);
                break;
            case ID :
                v = getLabelAddress(text);                      //mette l'indirizzo della label
                break;
            case FUNC :
                v = getFunctionIndex(text);                     //controllo che sia presente nella constantPool
                break;
        }
        ensureCapacity(ip+4);                                   // espando l'array se necessario
        writeInt(code, ip, v);                                  // traduce l'operando in bytecode e lo scrive nella memoria codice
        ip += 4;
    }

    //restituisce l'indice dell'oggetto (se presente) nella constant pool, aggiungendolo(se non presente)
    private int getConstantPoolIndex(Object o) {
        if ( constPool.contains(o) )
            return constPool.indexOf(o);
        constPool.add(o);
        return constPool.size()-1;
    }

    Object[] getConstantPool() {
        return constPool.toArray();
    }

    //ricerca di etichette non usate
    protected void checkForUnresolvedReferences() {
        for (String name : labels.keySet()) {
            LabelSymbol sym = labels.get(name);
            if ( !sym.isDefined ) {
                System.err.println("unresolved reference: "+name);
            }
        }
    }

    //indirizzo codice di un'etichetta
    private int getLabelAddress(String labelName) {
        LabelSymbol sym = (LabelSymbol) labels.get(labelName);
        if ( sym==null ) {
            sym = new LabelSymbol(labelName, ip, true);
            sym.isDefined = false;
            sym.addForwardReference(ip);
            labels.put(labelName, sym);
        } else {
            if ( sym.isForwardRef ) {
                sym.addForwardReference(ip);
            } else {
                return sym.address;
            }
        }
        //senza id viene bloccato il programma
        return -1;
    }

    //definizione di nome, n args, n local var, beg func addr
    protected void defineFunction(Token idToken, int args, int locals)
    {
        String name = idToken.getText();
        //FunctionSymbol per i campi della funzione
        FunctionSymbol f = new FunctionSymbol(name, args, locals, ip);
        if ( name.equals("main") )
        {
            mainFunction = f;		// se il nome della funzione è main, il campo mainFunction viene settato con f
        }
        /*durante l'esecuzione l'interprete trova l'inizio della funzione all'indirizzo indicato nella constant pool*/
        setFunAddressInConstPool(f); //ogni volta che non è un int
    }

    //imposta indirizzo del function symbol in constpool
    private void setFunAddressInConstPool(FunctionSymbol f) {
        boolean found = false;
        for(int i=0; i<constPool.size(); i++){
            Object obj = constPool.get(i);
            String name;
            if (obj instanceof FunctionSymbol) {
                name = ((FunctionSymbol) obj).name;
                if (name.equals(f.name+"()")) {
                    constPool.set(i, f);
                    found = true;
                }
            }
        }
        if(found)
            return;
        // se non trovo la funzione allora la aggiungo alla constant pool
        constPool.add(f);
    }

    //return indice constant pool alla quale inizia la funzione
    private int getFunctionIndex(String funName) {
        for (Object obj : constPool) {
            if (obj instanceof FunctionSymbol) {
                //aggiungo parentesi al nome che recupero dal function definition
                String name = ((FunctionSymbol) obj).name + "()";
                if (name.equals(funName)) {
                    //se la trovo controllo se definita
                    if (((FunctionSymbol) obj).address != -1) {
                        //allora è definita e la restituisco
                        return ((FunctionSymbol) obj).address;
                    }
                }
            }
        }
        //se non la trova l'aggiunge con solo il nome
        return getConstantPoolIndex(new FunctionSymbol(funName));
    }

    protected void defineDataSize(int n) {
        dataSize=n;
    }

    protected void defineLabel(Token idToken) {
        String labelName = idToken.getText();
        //System.out.println("label name: "+labelName);
        LabelSymbol sym = (LabelSymbol)labels.get(labelName);
        if ( sym==null ) {
            LabelSymbol csym = new LabelSymbol(labelName, ip, false);
            csym.isDefined = true;
            labels.put(labelName, csym); // add to symbol table
        } else {
            if ( sym.isForwardRef ) {
                // we have found definition of forward
                sym.isDefined = true;
                sym.address = ip;
                sym.resolveForwardReferences(code);
            } else {
                // ridefinizione del simbolo
                System.err.println("line "+idToken.getLine()+": redefinition of symbol "+labelName);
            }
        }
    }

    protected void ensureCapacity(int index) {
        if ( index >= code.length ) { // expand
            int newSize = Math.max(index, code.length) * 2;
            byte[] bigger = new byte[newSize];
            System.arraycopy(code, 0 , bigger, 0, code.length);
            code = bigger;
        }
    }

    public static int getInt(byte[] memory, int index) {
        int b1 = memory[index++]&0xFF; // mask off sign-extended bits
        int b2 = memory[index++]&0xFF;
        int b3 = memory[index++]&0xFF;
        int b4 = memory[index++]&0xFF;
        int word = b1<<(8*3) | b2<<(8*2) | b3<<(8*1) | b4;
        return word;
    }

    //scrive un valore all'interno di un array di byte
    public static void writeInt(byte[] bytes, int index, int value) {
        bytes[index+0] = (byte)((value>>(8*3))&0xFF); // get highest byte
        bytes[index+1] = (byte)((value>>(8*2))&0xFF);
        bytes[index+2] = (byte)((value>>(8*1))&0xFF);
        bytes[index+3] = (byte)(value&0xFF);
    }
}
