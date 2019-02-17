
package filesprio.service;

import java.util.Set;
//TODO tester getElem et getPrio avant de continuer

public interface FilesPrio<T> {

	/** Observators **/
	public int getSize() throws Exception;
	public boolean isEmpty() throws Exception;
	public Set<Integer> activePrios() throws Exception;
	public boolean isActivePrio(int numero) throws Exception;
	public int maxPrio() throws Exception;
	public int sizePrio(int numero) throws Exception;
	// \pre i \in activePrios()
	// \pre 0 < k < sizePrio(i)
	public T getElem(int i, int k) throws Exception;
	// \pre sizePrio(i) > 0
	public T getPrio(int i) throws Exception;

	/** Constructors **/
	// \post size() == 0
	public void init() throws Exception;


	/** Operators **/
	// \pre i >= 0
	// \pre e!=null
	// \post isActivePrio(i) \implies activePrios() = activePrios()@Pre
	// \post (!isActivePrio(i)) \implies activePrios() = activePrios()@Pre \\union i
	// \post sizePrio(i) == sizePrio(i)@pre + 1
	public void putPrio(int i, T e) throws Exception;


	// \pre put(e) require e != null;
	// \post put(e) = putPrio(e,maxPrio(P))
	public void put(T e) throws Exception;



	/** Invariants **/
	// \inv getSize() == somme sizePrio(i) \forall i in activePrios()
	// \inv isEmpty() == (getSize() == 0)
	// \inv isActivePrio(i) == i in activePrios()
	// \inv maxPrio = max(i) \forall i in activePrios()
	// \inv getPrio(i) == getPrio(i, 1)


}
