import java.util.Collections;
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
		for(int i: new Random().ints(30, 0, getSize()).toArray()) {
			if(getActivePrios().contains(i) != isActive(i)) {
				throw new InvariantError("\\Forall int i  ( i \\in getActivePrios()) == isActive(i)");
			}
		}
		
		
		//\inv getMaxPrio() == max(getActivePrios()) \with ( max(E) = \Exists int x \with (x \in E \ union {0}), \ForAll int y \with (y \in E) x >= y )
		if(getMaxPrio() != Collections.max(getActivePrios())) {
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
		for(int i: new Random().ints(30, 0, getSize()).toArray()) {
			if(activesPrios.contains(i)) {
				continue; // On ne s'interesse pas aux valeurs dans prio
			}
			if(getSizePrio(i) != 0) {
				throw new InvariantError("\\Forall int i \\with (i \\not \\in getActivePrios()) { getSizePrio(i) == 0 }");
			}
		}
		
		
		//\inv \Forall int i \with (i \in getActivePrios(), \Forall int k \with (k >= 1 \and k < getSizePrio(i)), { getElemPrio(i, k) != null }
		for(int i: getActivePrios()) {
			for(int k=1; k<getSizePrio(i);k++) {
				if(getElemPrio(i, k) == null) {
					throw new InvariantError("\\Forall int i \\with (i \\in getActivePrios(), \\Forall int k \\with (k >= 1 \\and k < getSizePrio(i)), { getElemPrio(i, k) != null }");
				}
			}
		}
	}

}
