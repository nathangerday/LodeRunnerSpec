
public class PreconditionError extends Error{
	public PreconditionError(String message) {
		super("Precondition failed: " + message);
	}
}