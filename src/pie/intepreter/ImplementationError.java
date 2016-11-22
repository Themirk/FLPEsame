package pie.intepreter;

public class ImplementationError extends RuntimeException {

  public ImplementationError() {
    super();
  }

  public ImplementationError(String message) {
    super(message);
  }

}
