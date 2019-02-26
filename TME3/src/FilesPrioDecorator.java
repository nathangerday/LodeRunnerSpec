import java.util.Set;

public class FilesPrioDecorator<T> implements FilesPrio<T>{
	private FilesPrio<T> delegates;
	
	protected FilesPrioDecorator(FilesPrio<T> f) {
		this.delegates = f;
	}

	public int getSize() {
		return delegates.getSize();
	}

	public boolean isEmpty() {
		return delegates.isEmpty();
	}

	public Set<Integer> getActivePrios() {
		return delegates.getActivePrios();
	}

	public boolean isActive(int i) {
		return delegates.isActive(i);
	}

	public int getMaxPrio() {
		return delegates.getMaxPrio();
	}

	public int getSizePrio(int i) {
		return delegates.getSizePrio(i);
	}

	public T getPrio(int i) {
		return delegates.getPrio(i);
	}

	public T getElem() {
		return delegates.getElem();
	}

	public T getElemPrio(int i, int k) {
		return delegates.getElemPrio(i, k);
	}

	public void putPrio(int i, T e) {
		delegates.putPrio(i, e);
	}

	public void put(T e) {
		delegates.put(e);
	}

	public void removePrio(int i) {
		delegates.removePrio(i);
	}

	public void remove() {
		delegates.remove();
	}

	
}
