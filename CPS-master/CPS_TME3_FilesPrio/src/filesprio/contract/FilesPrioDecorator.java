package filesprio.contract;

import java.util.Set;

import filesprio.service.FilesPrio;

public class FilesPrioDecorator<T> implements FilesPrio<T> {

	private FilesPrio<T> filesPrio;


	public FilesPrioDecorator(FilesPrio<T> filesPrio) {
		this.filesPrio = filesPrio;
	}

	/** Observators **/
	@Override
	public int getSize() throws Exception {
		return filesPrio.getSize();
	}

	@Override
	public boolean isEmpty() throws Exception {
		return filesPrio.isEmpty();	
	}

	@Override
	public Set<Integer> activePrios() throws Exception {
		return filesPrio.activePrios();
	}

	@Override
	public boolean isActivePrio(int numero) throws Exception{
		return filesPrio.isActivePrio(numero);
	}

	@Override
	public int maxPrio() throws Exception {
		return filesPrio.maxPrio();
	}

	@Override
	public int sizePrio(int numero) throws Exception {
		return filesPrio.sizePrio(numero);
	}

	@Override
	public T getElem(int i, int k) throws Exception{
		return filesPrio.getElem(i, k);
	}

	@Override
	public T getPrio(int i) throws Exception{
		return filesPrio.getPrio(i);
	}



	/** Constructors **/
	@Override
	public void init() throws Exception {
		filesPrio.init();
	}


	/** Operators **/
	@Override
	public void putPrio(int i, T e) throws Exception {
		filesPrio.putPrio(i, e);
	}

	@Override
	public void put(T e)  throws Exception {
		filesPrio.put(e);		
	}




}
