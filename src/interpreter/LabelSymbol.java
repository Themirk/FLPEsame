package interpreter;

import java.util.LinkedList;

/**
 * Created by longm on 14/11/16.
 */
class LabelSymbol {

    protected final String name;
    int address;
    private LinkedList<Integer> forwardReferenceAddresses = new LinkedList<Integer>();
    boolean isForwardRef;
    boolean isDefined;

    LabelSymbol(String id, int ip, boolean b) {
        this.name = id;
        this.address = ip;
        this.isForwardRef = b;
    }

    void addForwardReference(int ip) {
        forwardReferenceAddresses.add(ip);
    }

    void resolveForwardReferences(byte[] code) {
        Integer meetingAddress;
        this.isForwardRef = false;
        while(!forwardReferenceAddresses.isEmpty()){
            //indirizzo dove ho incontrato il riferimento alla label
            meetingAddress = forwardReferenceAddresses.removeFirst();

            //vado a scrivere nella word che ho allocato in memoria l'indirizzo riferito alla label
            Byte addressTo = (byte) ((address >> (8 * 3)) & 0xFF);
            code[meetingAddress++] = addressTo;
            addressTo = (byte) ((address >> (8 * 2)) & 0xFF);
            code[meetingAddress++] = addressTo;
            addressTo = (byte) ((address >> (8)) & 0xFF);
            code[meetingAddress++] = addressTo;
            addressTo = (byte) (address & 0xFF);
            code[meetingAddress] = addressTo;
        }
    }

}