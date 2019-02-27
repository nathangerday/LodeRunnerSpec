import java.util.Set;

public interface FilesPrio<T> {
	/* Observators */
	
	public int getSize();
	public boolean isEmpty();
	public Set<Integer> getActivePrios();
	public boolean isActive(int i);
	public int getMaxPrio();
	public int getSizePrio(int i);
	
	//\pre getSizePrio(i) > 0
	public T getPrio(int i);
	
	//\pre getSize() > 0
	public T getElem();
	
	//\pre i \in getActivePrios()
	//\pre k > 0
	//`pre k <= getSizePrios(i)
	public T getElemPrio(int i, int k);

	
	public void init();
	/* Invariants */
	
	
	//\inv getSize() == \Sum( \Forall int i \with (i \in getActivePrios()) getSizePrio(i))
	//\inv  isEmpty() == (getSize() == 0)
	//\inv \Forall int i  ( i \in getActivePrios()) == isActive(i)
	//\inv getMaxPrio() == max(getActivePrios()) \with ( max(E) = \Exists int x \with (x \in E \ union {0}), \ForAll int y \with (y \in E) x >= y )
	//\inv \Forall int i \with (i \in getActivePrios()) getPrio(i) == getElemPrio(i, 1)
	//\inv getElem() == getPrio(getMaxPrio())
	
	//\inv \Forall int i \with (i \in getActivePrios()) { getSizePrio(i) > 0 }
	//\inv \Forall int i \with (i \not \in getActivePrios()) { getSizePrio(i) == 0 }
	//\inv \Forall int i \with (i \in getActivePrios(), \Forall int k \with (k >= 1 \and k <= getSizePrio(i)), { getElemPrio(i, k) != null }
	
	
	/* Constructors */
	//\post getSize() == 0
	
	/* Operators */
	//\pre i >= 0
	//\pre e != null
	//\post isActive(i)@pre \impl getActivePrios() == getActivePrios()@pre
	//\post !isActive(i)@pre \impl getActivePrios() == ( getActivePrios()@pre \Union {i} )
	//\post getSizePrio(i) == getSizePrio(i)@pre + 1
	//\post \Forall int j \with ( j \in ( getActivePrios() \ { i })), getSizePrio(j) == getSizePrio(j)@pre
	//\post getPrio(i) == e
	//\post \Forall int k \with ( k >= 2 \and k <= getSizePrio(i)@pre+1 ), getElemPrio(i, k) == getElemPrio(i, k-1)@pre
	//\post \Forall int j \with ( j \in getActivePrios()@pre \ { i })), \Forall int k \with ( k >= 1 \and k <= getSizePrio(j)@pre ), getElemPrio(j,k) == getElemPrio(j,k)@pre
	public void putPrio(int i, T e);
	
	//\pre e != null
	//\define put(e) = putPrio(e, getMaxPrio())
	public void put(T e);
	
	//\pre getSizePrio(i) > 0
	//\post getSizePrio(i)@pre > 1 \impl getActivePrios() == getActivePrios()@pre
	//\post getSizePrio(i)@pre == 1 \impl getActivePrios() == (getActivePrios()@pre \ {i})
	//\post getSizePrio(i) == getSizePrio(i)@pre - 1
	//\post \Forall int j \with ( j \in ( getActivePrios()@pre \ {i} )), getSizePrio(j) == getSizePrio(j)@pre
	//\post \Forall int k \with ( k >= 1 \and k <= getSizePrio(i) - 1), getElemPrio(i,k) == getElemPrio(i,k)@pre
	//\post \Forall int j \with ( j \in (getActivePrios()@pre \ {i})), \Forall int k \with (k >= 1 \and k < getSizePrio(j)), getElemPrio(j,k) == getElemPrio(j,k)@pre
	public void removePrio(int i);
	
	//\pre getSize() > 0
	//\define remove() = removePrio(getMaxPrio())
	public void remove();
	
	
}
