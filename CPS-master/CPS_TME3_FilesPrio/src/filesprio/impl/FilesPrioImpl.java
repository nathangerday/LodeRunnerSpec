package filesprio.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import filesprio.service.FilesPrio;

public class FilesPrioImpl<T> implements FilesPrio<T> {

	//	private List<List<T>> files;
	private Map<Integer,List<T>> files;
	public FilesPrioImpl() {
		super();
	}

	/** Observators **/
	@Override
	public int getSize() {
		int size = 0;
		for (List<T> l : files.values()) {
			size+= l.size();
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

	@Override
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
		return files.get(i).get(k);
	}

	@Override
	public T getPrio(int i){
		// TODO peut-être qu'il faut prendre l'élément 1 de la file i (d'après la spec)
		return files.get(i).get(0);
	}


	/** Constructors **/
	@Override
	public void init() {
		files = new HashMap<Integer, List<T>>();
	}


	/** Operators **/
	@Override
	public void putPrio(int i, T e) {
		List<T> tmp = files.get(i);
		if (tmp == null) {
			tmp = new ArrayList<T>();
		}
		tmp.add(e);
		files.put(i, tmp);
	}

	@Override
	public void put(T e) {
		putPrio(maxPrio(), e);		
	}








}
