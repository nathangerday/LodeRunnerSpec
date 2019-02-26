
public class InvariantError extends Error{
	public InvariantError(String message) {
		super("Invariant failed: " + message);
	}
}