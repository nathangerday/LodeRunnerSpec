package filesprio.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import filesprio.service.FilesPrio;

public class FilesPrioImplBug<T> implements FilesPrio<T> {

	private Map<Integer, List<T>> files;


	/** Observators **/
	@Override
	public int getSize() {
		int size = 0;
		for (List<T> l : files.values()) {
			size += l.size();
		}
		return size; 
	}

	@Override
	public boolean isEmpty() {
		return getSize() == 0;
	}

	@Override
	public Set<Integer> activePrios() {
		return files.keySet(); 
	}

	public boolean isActivePrio(int numero){
		return activePrios().contains(numero);
	}

	@Override
	public int maxPrio(){
		Set<Integer> activePrios = activePrios();
		int maxPrio = -1;
		for(int i : activePrios){
			if(i > maxPrio)
				maxPrio = i;
		}
		return maxPrio;
	}
	
	@Override
	public int sizePrio(int numero){
		int sizePrio;
		if (isActivePrio(numero)) {
			sizePrio = files.get(numero).size();
		}
		else {
			sizePrio = 0;
		}
		return sizePrio;
	}

	@Override
	public T getElem(int i, int k){
		return null;
	}

	@Override
	public T getPrio(int i){
		return null;
	}


	/** Constructors **/
	@Override
	public void init() {
		files = new HashMap<Integer,List<T>>();	
	}




	/** Operators **/
	@Override
	public void putPrio(int i, T e) {
		//TODO BUG ICI
		List<T> tmp = files.get(i+1);
		if (tmp == null) {
			tmp = new ArrayList<T>();
		}
		tmp.add(e);
		files.put(i+1, tmp);
	}

	@Override
	public void put(T e) {
		putPrio(maxPrio(), e);		
	}

}

