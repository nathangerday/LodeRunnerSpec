import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class FilesPrioImpl<T> implements FilesPrio<T> {
	private int size;
	private Map<Integer, LinkedList<T>> filep;
	
	public FilesPrioImpl() {
		this.size = 0;
		this.filep = new HashMap<>();
	}
	
	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public Set<Integer> getActivePrios() {
		return filep.keySet();
	}

	@Override
	public boolean isActive(int i) {
		return filep.containsKey(i);
	}

	@Override
	public int getMaxPrio() {
		if(this.size == 0) {
			return 0; //TODO Pas de specif sur ce que retourne maxPrio quand vide ???
		}
		return Collections.max(this.getActivePrios());
	}

	@Override
	public int getSizePrio(int i) {
		if(filep.containsKey(i)) {
			return filep.get(i).size();
		}else {
			return 0;
		}
	}

	@Override
	public T getPrio(int i) {
		return getElemPrio(i, 1);
	}

	@Override
	public T getElem() {
		return getPrio(getMaxPrio());
	}

	@Override
	public T getElemPrio(int i, int k) {
		return filep.get(i).get(k-1); //Les valeurs donnees commencent a 1
	}

	@Override
	public void putPrio(int i, T e) {
		if(filep.containsKey(i)) {
			filep.get(i).addFirst(e);
		}else {
			LinkedList<T> tmp = new LinkedList<>();
			tmp.addFirst(e);
			filep.put(i, tmp);
		}
		this.size++;
		
	}

	@Override
	public void put(T e) {
		putPrio(getMaxPrio(), e);
	}

	@Override
	public void removePrio(int i) {
		if(filep.get(i).size() == 1) {
			filep.remove(i);
		}else {
			filep.get(i).removeLast();
		}
		this.size--;
	}

	@Override
	public void remove() {
		removePrio(getMaxPrio());
		
	}

}
