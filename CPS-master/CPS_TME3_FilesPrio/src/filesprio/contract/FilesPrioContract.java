package filesprio.contract;

import java.util.HashSet;
import java.util.Set;

import filesprio.service.FilesPrio;

public class FilesPrioContract<T> extends FilesPrioDecorator<T> {

	public FilesPrioContract(FilesPrio<T> files) {
		super(files);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getSize() throws Exception {
		checkInvariant();
		int size = super.getSize();
		checkInvariant();
		return size;
	}

	@Override
	public void init() throws Exception {
		super.init();
		if(super.getSize() != 0) {
			throw new Exception("\\post init : size != 0");
		}
	}

	@Override
	public boolean isEmpty() throws Exception {
		checkInvariant();
		final boolean isEmpty = super.isEmpty();
		checkInvariant();
		return isEmpty;
	}

	@Override
	public Set<Integer> activePrios() throws Exception {
		checkInvariant();
		final Set<Integer> activePrios = super.activePrios();
		checkInvariant();
		return activePrios;
	}

	@Override
	public boolean isActivePrio(int numero) throws Exception{
		checkInvariant();
		final boolean isActivePrio = super.isActivePrio(numero);
		checkInvariant();
		return isActivePrio;
	}

	@Override
	public int maxPrio() throws Exception{
		checkInvariant();
		final int maxPrio = super.maxPrio();
		checkInvariant();
		return maxPrio;
	}


	@Override
	public int sizePrio(int numero) throws Exception {
		checkInvariant();
		final int sizePrio = super.sizePrio(numero);
		checkInvariant();
		return sizePrio;
	}

	@Override
	public T getElem(int i, int k) throws Exception{
		checkInvariant();
		T elem = super.getElem(i, k);
		checkInvariant();
		return elem;
	}

	public T getPrio(int i) throws Exception{
		checkInvariant();
		T elem = super.getPrio(i);
		checkInvariant();
		return elem;
	}


	@Override
	public void putPrio(int i, T e) throws Exception {
		checkInvariant();
		// \pre i >= 0
		if (i < 0) {
			throw new Exception("\\pre putPrio : i < 0");
		}
		// \pre e != null
		if (e == null) {
			throw new Exception("\\pre putPrio : -> e == null");
		}
		final int sizePre = super.getSize();
		Set<Integer> activePriosPre = super.activePrios();
		super.putPrio(i, e);

		// \post sizePrio(i) == sizePrio(i)@pre + 1
		if (getSize() != sizePre + 1) {
			throw new Exception("\\post putPrio : getSize() != getSize@Pre + 1");
		}

		// \post isActivePrio(i) \implies activePrios() = activePrios()@Pre
		if (super.isActivePrio(i)) {
			if (!(activePriosPre.containsAll(super.activePrios()) 
					&& super.activePrios().containsAll(activePriosPre))) {
				throw new Exception("\\post isActivePrio(i) => activePrios() != activePrios()@Pre");
			}
		}

		// \post (!isActivePrio(i)) \implies activePrios() = activePrios()@Pre union i
		if (!super.isActivePrio(i)) {
			Set<Integer> activePriosPreUnion = new HashSet<Integer>(activePriosPre);
			activePriosPreUnion.add(i);
			if (!(activePriosPreUnion.containsAll(super.activePrios()) 
					&& super.activePrios().containsAll(activePriosPreUnion))) {
				throw new Exception("\\post isActivePrio(i) => activePrios() != activePrios()@Pre \\union i");
			}
		}
		checkInvariant();
	}

	@Override
	public void put(T e) throws Exception {
		checkInvariant();
		if (e == null) {
			throw new Exception("\\pre put : e == null");
		}
		super.put(e);
		checkInvariant();		
	}

	public void checkInvariant() throws Exception {
		// \inv getSize() == somme sizePrio(i) \forall i in activePrios()
		int totalSize = 0;
		for (int i : super.activePrios()) {
			totalSize += super.sizePrio(i);
		}
		if (super.getSize() != totalSize) {
			throw new Exception("\\inv getSize() != somme sizePrio(i) \\forall i in activePrios()");
		}

		// \inv isEmpty() == (getSize() == 0)
		if (super.isEmpty() != (super.getSize() == 0)) {
			throw new Exception("\\inv isEmtpy() != getSize() == 0");
		}

		// \inv isActivePrio(i) == i in activePrios()
		final Set<Integer> activePrios = super.activePrios();
		for (int i : activePrios) {
			if (super.activePrios().contains(i) != super.isActivePrio(i))  {
				throw new Exception("\\inv isActivePrio(i) != i in activePrios())");
			}
		}

		// \inv maxPrio = max(i) \forall i in activePrios()
		int max = -1;
		for (int i : activePrios) {
			if (i > max) {
				max = i;
			}
		}
		if (super.maxPrio() != max) {
			throw new Exception("\\inv maxPrio() != max(activePrios())");
		}



	}


}
