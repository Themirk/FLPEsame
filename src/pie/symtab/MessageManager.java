package pie.symtab;

import org.antlr.v4.runtime.Token;

public class MessageManager {

  public static void error(Token t, String msg) {
    System.err.printf("ERROR @[%d:%d] -- symbol '%s': %s\n", t.getLine(),
        t.getCharPositionInLine(), t.getText(), msg);
  }

  public static void message(Token t, String msg) {
    System.out.printf("@[%d:%d] -- symbol '%s': %s\n", t.getLine(), t.getCharPositionInLine(),
        t.getText(), msg);
  }

}
