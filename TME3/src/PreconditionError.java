
public class PreconditionError extends Exception{
	public PreconditionError(String message) {
		super("Precondition failed: " + message);
	}
}