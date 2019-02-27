import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class FilesPrioImplBug<T> implements FilesPrio<T> {
	private int size;
	private Map<Integer, LinkedList<T>> filep;
	
	public FilesPrioImplBug() {
		this.init();
	}
	
	public void init() {
//		this.size = 1; //BUG 1 Taille init differente de 0
		this.size = 0;
		this.filep = new HashMap<>();
	}
	
	@Override
	public int getSize() {
//		return this.size + 1; //BUG 2 Retourne mauvaisse taille
		return this.size;
	}

	@Override
	public boolean isEmpty() {
//		return this.size > 0; //BUG 3 retourne !isEmpty()
		return this.size == 0;
	}

	@Override
	public Set<Integer> getActivePrios() {
//		Set<Integer> res = new HashSet<>(filep.keySet());
//		res.add(new Random().nextInt());
//		return res; //BUG 4 Retourne un set des priorite active avec une valeur non active
		return filep.keySet();
	}

	@Override
	public boolean isActive(int i) {
		return filep.containsKey(i);
	}

	@Override
	public int getMaxPrio() {
		if(this.size == 0) {
			return 0;
		}
		return Collections.max(this.getActivePrios());
//		return getSizePrio(Collections.min(this.getActivePrios())); //BUG 5 Retourne une priorite differente de max prio
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
		return filep.get(i).get(k-1);
	}

	@Override
	public void putPrio(int i, T e) {
		if(filep.containsKey(i)) {
			filep.get(i).addFirst(e);
//			filep.get(i).addLast(e); //BUG 6 Ajout au mauvais endroit dans la liste
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
//			filep.get(i).removeLast();
			filep.get(i).removeFirst(); //BUG 7 Supprime au mauvais endroit dans la liste
		}
		this.size--;
	}

	@Override
	public void remove() {
		removePrio(getMaxPrio());
		
	}

}
