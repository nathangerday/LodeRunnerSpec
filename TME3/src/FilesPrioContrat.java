import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class FilesPrioContrat<T> extends FilesPrioDecorator<T> {

	public FilesPrioContrat(FilesPrio<T> f) {
		super(f);
	}
	
	public void checkInvariant() {
		//\inv getSize() == \Sum( \Forall int i \with (i \in getActivePrios()) getSizePrio(i))
		int somme = 0;
		for(int i : getActivePrios()) {
			somme += getSizePrio(i);
		}
		if(somme != getSize()) {
			throw new InvariantError("getSize() == \\Sum( \\Forall int i \\with (i \\in getActivePrios()) getSizePrio(i))");
		}
		
		//\inv  isEmpty() == (getSize() == 0)
		if(isEmpty() != (getSize() == 0)) {
			throw new InvariantError("isEmpty() == (getSize() == 0)");
		}
		
		//\inv \Forall int i  ( i \in getActivePrios()) == isActive(i)
		//TODO Choose test method
//		for(int i: getActivePrios()) {
//			if(!isActive(i)) {
//				throw new InvariantError("\\Forall int i  ( i \\in getActivePrios()) == isActive(i)");
//			}
//		}
		for(int i: new Random().ints(30, 0, getSize()+1).toArray()) {
			if(getActivePrios().contains(i) != isActive(i)) {
				throw new InvariantError("\\Forall int i  ( i \\in getActivePrios()) == isActive(i)");
			}
		}
		
		//TODO Ajouter dans tout les fichiers une precondition sur getMaxPrio, tq getSize() > 0
		//\inv getMaxPrio() == max(getActivePrios()) \with ( max(E) = \Exists int x \with (x \in E \ union {0}), \ForAll int y \with (y \in E) x >= y )
		if(getSize() > 0 && getMaxPrio() != Collections.max(getActivePrios())) {
			throw new InvariantError("getMaxPrio() == max(getActivePrios()) \\with ( max(E) = \\Exists int x \\with (x \\in E \\ union {0}), \\ForAll int y \\with (y \\in E) x >= y )");
		}
		
		
		//\inv \Forall int i \with (i \in getActivePrios()) getPrio(i) == getElemPrio(i, 1)
		for(int i : getActivePrios()) {
			if(getSizePrio(i) > 0 && getPrio(i) != getElemPrio(i, 1)) {
				throw new InvariantError("\\Forall int i \\with (i \\in getActivePrios()) getPrio(i) == getElemPrio(i, 1)");
			}
		}
		
		
		//\inv getElem() == getPrio(getMaxPrio())
		if(getSize() > 0 && getSizePrio(getMaxPrio()) > 0 && getElem() != getPrio(getMaxPrio())) {
			throw new InvariantError("getElem() == getPrio(getMaxPrio())");
		}
		
		//\inv \Forall int i \with (i \in getActivePrios()) { getSizePrio(i) > 0 }
		for(int i : getActivePrios()) {
			if(getSizePrio(i) <= 0) {
				throw new InvariantError("\\Forall int i \\with (i \\in getActivePrios()) { getSizePrio(i) > 0 }");
			}
		}
		
		//\inv \Forall int i \with (i \not \in getActivePrios()) { getSizePrio(i) == 0 }
		Set<Integer> activesPrios = getActivePrios();
		for(int i: new Random().ints(30, 0, getSize()+1).toArray()) {
			if(activesPrios.contains(i)) {
				continue; // On ne s'interesse pas aux valeurs dans prio
			}
			if(getSizePrio(i) != 0) {
				throw new InvariantError("\\Forall int i \\with (i \\not \\in getActivePrios()) { getSizePrio(i) == 0 }");
			}
		}
		
		
		//\inv \Forall int i \with (i \in getActivePrios(), \Forall int k \with (k >= 1 \and k <= getSizePrio(i)), { getElemPrio(i, k) != null }
		for(int i: getActivePrios()) {
			for(int k=1; k<=getSizePrio(i);k++) {
				if(getElemPrio(i, k) == null) {
					throw new InvariantError("\\Forall int i \\with (i \\in getActivePrios(), \\Forall int k \\with (k >= 1 \\and k < getSizePrio(i)), { getElemPrio(i, k) != null }");
				}
			}
		}
	}
	
	public void init() {
		//pre : Pas de precondition
		
		//Traitement
		super.init();
		
		//post invariant
		checkInvariant();
		
		//post getSize() == 0
		if(getSize() != 0) {
			throw new PostconditionError("init: getSize() == 0");
		}
	}
	
	public T getPrio(int i) {
		//\pre getSizePrio(i) > 0
		if(getSizePrio(i) <= 0) {
			throw new PreconditionError("getPrio:  getSizePrio(i) > 0 ");
		}
		
		//Traitement
		return super.getPrio(i);
	}
	
	public T getElem() {
		//\pre getSize() > 0
		if(getSize() <= 0) {
			throw new PreconditionError("getElem: getSize() > 0");
		}
		
		return super.getElem();
	}
	
	public T getElemPrio(int i, int k) {
		//\pre i \in getActivePrios()
		if(!getActivePrios().contains(i)) {
			throw new PreconditionError("getElemPrio: i \\in getActivePrios()");
		}
		
		//\pre k > 0
		//`pre k <= getSizePrios(i)
		if(k <= 0 || k > getSizePrio(i)) {
			throw new PreconditionError("getElemPrio: k>0 \\and k<=getSizePrios(i)");
		}
		
		return super.getElemPrio(i, k);
	}

	public void putPrio(int i, T e) {
		//\pre i >= 0
		if(i < 0) {
			throw new PreconditionError("putPrio: i >= 0");
		}
		
		//\pre e != null
		if(e == null) {
			throw new PreconditionError("putPrio: e != null");
		}
		
		//pre invariant
		checkInvariant();
		
		//Captures
		boolean isActive_at_pre = isActive(i);
		Set<Integer> getActivePrios_at_pre = new HashSet<Integer>(getActivePrios());
		
		int getSizePrio_at_pre[] = new int[Math.max(i, getMaxPrio())+1];
		for(int x=0; x<getSizePrio_at_pre.length; x++) {
			getSizePrio_at_pre[x] = getSizePrio(x);
		}
		
		
		ArrayList<ArrayList<T>> getElemPrio_at_pre = new ArrayList<>(Math.max(i, getMaxPrio()+1));
		for(int x=0; x<=getMaxPrio(); x++) {
			getElemPrio_at_pre.add(new ArrayList<T>(getSizePrio(i) + 2));
			getElemPrio_at_pre.get(x).add(null); //For padding to start at 1
			for(int y=1; y <= getSizePrio(x); y++) {
				if(getSizePrio(x) > 0) {
					getElemPrio_at_pre.get(x).add(getElemPrio(x,y));					
				}else {
					getElemPrio_at_pre.get(x).add(null);
				}
			}
		}

		
		//Traitement
		super.putPrio(i,e);
		
		//post invariant
		checkInvariant();
		
		//\post isActive(i)@pre \impl getActivePrios() == getActivePrios()@pre
		if(! (!isActive_at_pre || getActivePrios().equals(getActivePrios_at_pre))) {
			throw new PostconditionError("putPrio: isActive(i)@pre \\impl getActivePrios() == getActivePrios()@pre");
		}
		
		//\post !isActive(i)@pre \impl getActivePrios() == ( getActivePrios()@pre \Union {i} )
		Set<Integer> getActivePrios_at_pre_Union_i = new HashSet<>(getActivePrios_at_pre);
		getActivePrios_at_pre_Union_i.add(i);
		if(! (isActive_at_pre || getActivePrios().equals(getActivePrios_at_pre_Union_i))) {
			throw new PostconditionError("putPrio: !isActive(i)@pre \\impl getActivePrios() == ( getActivePrios()@pre \\Union {i} )");
		}
		
		
		//\post getSizePrio(i) == getSizePrio(i)@pre + 1
		if(getSizePrio(i) != getSizePrio_at_pre[i] + 1) {
			throw new PostconditionError("putPrio: getSizePrio(i) == getSizePrio(i)@pre + 1");
		}
		
		//\post \Forall int j \with ( j \in ( getActivePrios() \ { i })), getSizePrio(j) == getSizePrio(j)@pre
		for(int j : getActivePrios()) {
			if(j == i) {
				continue;
			}
			if(getSizePrio(j) != getSizePrio_at_pre[j]) {
				throw new PostconditionError("putPrio: \\Forall int j \\with ( j \\in ( getActivePrios() \\ { i })), getSizePrio(j) == getSizePrio(j)@pre");
			}
		}
		
		
		
		//\post getPrio(i) == e
		if(getPrio(i) != e) {
			throw new PostconditionError("putPrio: getPrio(i) == e");
		}
		
		
		//\post \Forall int k \with ( k >= 2 \and k <= getSizePrio(i)@pre+1 ), getElemPrio(i, k) == getElemPrio(i, k-1)@pre
		for(int k=2; k<=getSizePrio_at_pre[i]; k++) {
			if(!getElemPrio(i, k).equals(getElemPrio_at_pre.get(i).get(k-1))) {
				throw new PostconditionError("putPrio: \\Forall int k \\with ( k >= 2 \\and k <= getSizePrio(i)@pre+1 ), getElemPrio(i, k) == getElemPrio(i, k-1)@pre");
			}
		}
		
		
		
		//\post \Forall int j \with ( j \in getActivePrios()@pre \ { i })), \Forall int k \with ( k >= 1 \and k <= getSizePrio(j)@pre ), getElemPrio(j,k) == getElemPrio(j,k)@pre
		for(int j : getActivePrios_at_pre) {
			if(j == i) {
				continue;
			}
			for(int k = 1; k <= getSizePrio_at_pre[j]; k++) {
				if(!getElemPrio(j, k).equals(getElemPrio_at_pre.get(j).get(k))) {
					throw new PostconditionError("putPrio: \\Forall int j \\with ( j \\in getActivePrios()@pre \\ { i })), \\Forall int k \\with ( k >= 1 \\and k <= getSizePrio(j)@pre ), getElemPrio(j,k) == getElemPrio(j,k)@pre");
				}
			}
		}
		
		
	}
	
	public void put(T e) {
		//\pre e != null
		if(e == null) {
			throw new PreconditionError("put: e != null");
		}
		
		//pre invariant
		checkInvariant();
		
		//Traitement
		super.put(e);
		
		//post invariant
		checkInvariant();
		
		//\define put(e) = putPrio(e, getMaxPrio())
		
	}
	
	public void removePrio(int i) {
		//\pre getSizePrio(i) > 0
		if(getSizePrio(i) <= 0) {
			throw new PreconditionError("removePrio: getSizePrio(i) > 0");
		}
		
		//pre invariant
		checkInvariant();
		

		//captures
		Set<Integer> getActivePrios_at_pre = getActivePrios();
		
		int getSizePrio_at_pre[] = new int[getMaxPrio()+1];
		for(int x=0; x<getSizePrio_at_pre.length; x++) {
			getSizePrio_at_pre[x] = getSizePrio(x);
		}
		
		ArrayList<ArrayList<T>> getElemPrio_at_pre = new ArrayList<>(Math.max(i, getMaxPrio()+1));
		for(int x=0; x<=getMaxPrio(); x++) {
			getElemPrio_at_pre.add(new ArrayList<T>(getSizePrio(i) + 2));
			getElemPrio_at_pre.get(x).add(null); //For padding to start at 1
			for(int y=1; y <= getSizePrio(x); y++) {
				if(getSizePrio(x) > 0) {
					getElemPrio_at_pre.get(x).add(getElemPrio(x,y));					
				}else {
					getElemPrio_at_pre.get(x).add(null);
				}
			}
		}
		
		//Traitement
		super.removePrio(i);
		
		//post invariant
		checkInvariant();
		
		
		//\post getSizePrio(i)@pre > 1 \impl getActivePrios() == getActivePrios()@pre
		if(! (getSizePrio_at_pre[i] <= 1 || getActivePrios().equals(getActivePrios_at_pre))) {
			throw new PostconditionError("removePrio: getSizePrio(i)@pre > 1 \\impl getActivePrios() == getActivePrios()@pre");
		}
		
		//\post getSizePrio(i)@pre == 1 \impl getActivePrios() == (getActivePrios()@pre \ {i})
		Set<Integer> getActivePrios_at_pre_priv_i = new HashSet<Integer>(getActivePrios_at_pre);
		getActivePrios_at_pre_priv_i.remove(i);
		if(! (getSizePrio_at_pre[i] != 1 || getActivePrios().equals(getActivePrios_at_pre_priv_i))) {
			throw new PostconditionError("removePrio: getSizePrio(i)@pre == 1 \\impl getActivePrios() == (getActivePrios()@pre \\ {i})");
		}
		
		
		//\post getSizePrio(i) == getSizePrio(i)@pre - 1
		if(getSizePrio(i) != getSizePrio_at_pre[i] - 1) {
			throw new PostconditionError("removePrio: getSizePrio(i) == getSizePrio(i)@pre - 1");
		}
		
		//\post \Forall int j \with ( j \in ( getActivePrios()@pre \ {i} )), getSizePrio(j) == getSizePrio(j)@pre
		for(int j : getActivePrios_at_pre) {
			if(j==i) {
				continue;
			}
			if(getSizePrio_at_pre[j] != getSizePrio(j)) {
				throw new PostconditionError("removePrio: \\Forall int j \\with ( j \\in ( getActivePrios()@pre \\ {i} )), getSizePrio(j) == getSizePrio(j)@pre");
			}
		}
		
		
		//\post \Forall int k \with ( k >= 1 \and k <= getSizePrio(i) - 1), getElemPrio(i,k) == getElemPrio(i,k)@pre
		for(int k=1; k<=getSizePrio(i)-1; k++) {
			if(!getElemPrio(i, k).equals(getElemPrio_at_pre.get(i).get(k))) {
				throw new PostconditionError("removePrio: \\Forall int k \\with ( k >= 1 \\and k <= getSizePrio(i) - 1), getElemPrio(i,k) == getElemPrio(i,k)@pre");
			}
		}
		
		
		//\post \Forall int j \with ( j \in (getActivePrios()@pre \ {i})), \Forall int k \with (k >= 1 \and k <= getSizePrio(j)), getElemPrio(j,k) == getElemPrio(j,k)@pre
		for(int j : getActivePrios_at_pre) {
			if(j == i) {
				continue;
			}
			for(int k=1; k<=getSizePrio(j); k++) {
				if(!getElemPrio(j, k).equals(getElemPrio_at_pre.get(j).get(k))) {
					throw new PostconditionError("removePrio: \\Forall int j \\with ( j \\in (getActivePrios()@pre \\ {i})), \\Forall int k \\with (k >= 1 \\and k <= getSizePrio(j)), getElemPrio(j,k) == getElemPrio(j,k)@pre");
				}
			}
		}
		
	}
	
	public void remove() {
		//\pre getSize() > 0
		if(getSize() <= 0) {
			throw new PreconditionError("remove: getSize() > 0");
		}
		
		//pre invariant
		checkInvariant();
		
		
		//Traitement
		super.remove();
		
		//post invariant
		checkInvariant();
		
		//\define remove() = removePrio(getMaxPrio())
		
	}
	
}
