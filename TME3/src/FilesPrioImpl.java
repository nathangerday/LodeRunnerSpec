import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class FilesPrioImpl<T> implements FilesPrio<T> {
	private int size;
	private Map<Integer, List<T>> filep;
	
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
	public boolean isActive(int prio) {
		return filep.containsKey(prio);
	}

	@Override
	public int getMaxPrio() {
		if(this.size == 0) {
			return 0; //TODO Pas de specif sur ce que retourne maxPrio quand vide ???
		}
		return Collections.max(this.getActivePrios());
	}

	@Override
	public int getSizePrio(int prio) {
		if(filep.containsKey(prio)) {
			return filep.get(prio).size();
		}else {
			return 0;
		}
	}

	@Override
	public T getPrio(int prio) {
		return getElemPrio(prio, 0); //TODO Check quelle valeur a prendre par defaut
	}

	@Override
	public T getElem() {
		return getPrio(getMaxPrio());
	}

	@Override
	public T getElemPrio(int i, int k) {
		return filep.get(i).get(k);
	}

	@Override
	public void putPrio(int i, T e) {
		if(filep.containsKey(i)) {
			filep.get(i).add(e);
		}else {
			List<T> tmp = new LinkedList<>();
			tmp.add(e);
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
			filep.get(i).remove(0);
		}
		this.size--;
	}

	@Override
	public void remove() {
		removePrio(getMaxPrio());
		
	}

}
