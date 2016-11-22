package pie.intepreter;

import org.antlr.v4.runtime.Token;

public class ExecutionError extends RuntimeException {

  public ExecutionError(Token token) {
    super(error(token));
  }

  public ExecutionError(Token token, String message) {
    super(error(token,message));
  }

  private static String error(Token t, String msg) {
    return String.format("ERROR @[%d:%d] -- '%s': %s\n", t.getLine(),
        t.getCharPositionInLine(), t.getText(), msg);
  }

  private static String error(Token t) {
    return String.format("ERROR @[%d:%d] -- symbol '%s'", t.getCharPositionInLine(), t.getText());
  }
}
