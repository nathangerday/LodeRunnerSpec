
public class InvariantError extends Exception{
	public InvariantError(String message) {
		super("Invariant failed: " + message);
	}
}