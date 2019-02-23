
public class FilesPrioDecorator<T> {
	private FilesPrio<T> filep;
	
	public FilesPrioDecorator(FilesPrio<T> f) {
		this.filep = f;
	}
}
