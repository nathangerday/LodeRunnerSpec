
public class PostconditionError extends Error{
	public PostconditionError(String message) {
		super("Postcondition failed: " + message);
	}
}