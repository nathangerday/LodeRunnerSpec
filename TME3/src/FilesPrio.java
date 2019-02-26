import java.util.Set;

public interface FilesPrio<T> {
	/* Observators */
	
	public int getSize();
	public boolean isEmpty();
	public Set<Integer> getActivePrios();
	public boolean isActive(int prio);
	public int getMaxPrio();
	public int getSizePrio(int prio);
	
	//\pre getSizePrio() > 0
	public T getPrio(int prio);
	
	//\pre getSize() > 0
	public T getElem();
	
	//\pre i \in getActivePrios()
	//\pre k > 0
	//`pre k < getSizePrios(i)
	public T getElemPrio(int i, int k);

		
	/* Invariants */
	//TODO Comment/ou définir \sum et \max ?
	//		Utiliser define 
	
	//\inv \Forall int i \with (i \in getActivePrios()) { getSize() == (\sum getSizePrio(i))}
	//\inv  isEmpty() == (getSize() == 0)
	//\inv \Forall int i ( i \in getActivePrio()) == isActive(i)
	//\inv getMaxPrio() == \max getActivePrios()
	//\inv getPrio(i) == getElemPrio(i, 1)
	//\inv getElem() == getPrio(getMaxPrio())
	
	//\inv \Forall int i \with (i \in getActivePrios()) { getSizePrio(i) > 0}
	//\inv \Forall int i \with (i \not-in getActivePrios()) { getSizePrio(i) == 0}
	//\inv \Forall int i \with (i \in getActivePrios()
	//	   \Forall int k \with (k \in [1..getSizePrio(i)]
	// 	   { getElemPrio(i, k) != null }
	
	
	/* Constructors */
	//\pre getSize() == 0
	
	/* Operators */
	//\pre i >= 0
	//\pre e != null
	//\post isActive(i) \impl getActivePrios() == getActivePrios()@pre
	//\post !isActive(i) \impl (getActivePrios() \Union i) == getActivePrios()@pre
	//\post sizePrio(i) == sizePrio(i)@pre + 1
	//\post \Forall int j \with ( j \in ( getActivePrios() \ { i }), getSizePrio(i) == getSizePrio(j)@pre
	//\post prio(i) == e
	//\post \Forall int k \with ( k \in [2..getSizePrio(i)@pre+1] ), getElemPrio(i, k) == getElemPrio(i, k-1)@pre
	//\post \Forall int j \with ( j \in getActivePrios()@pre \ { i })
	//		\Forall int k \with ( k \in [1..getSizePrio(j)@pre]
	//		getElemPrio(j,k) == getElemPrio(j,k)@pre
	public void putPrio(int i, T e);
	
	//\pre e != null
	//\def putPrio(e, getMaxPrio())
	public void put(T e);
	
	//\pre getSizePrio(i) > 0
	public void removePrio(int i);
	
	//\pre getSize() > 0
	public void remove();
	
	
}
