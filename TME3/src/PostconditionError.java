
public class PostconditionError extends Exception{
	public PostconditionError(String message) {
		super("Postcondition failed: " + message);
	}
}