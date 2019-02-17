package filesprio.main;

import filesprio.contract.FilesPrioContract;
import filesprio.impl.FilesPrioImpl;
import filesprio.impl.FilesPrioImplBug;
import filesprio.service.FilesPrio;

public class FilesPrioMain {

	public static void main(String[] args) {
		
		try {
			System.out.println("");
			FilesPrio<Integer> fp = new FilesPrioImpl<Integer>();
			fp.init();
			FilesPrioContract<Integer> fpc = new FilesPrioContract<Integer>(fp);
			fpc.init();
			fpc.getSize();
			fpc.isEmpty();
			fpc.putPrio(2,2);
			fpc.sizePrio(2);
			fpc.isActivePrio(2);
			fpc.activePrios();
			fpc.maxPrio();
			System.out.println("FilesPrioImpl OK");
		} catch (Exception e) {
			System.out.println("/!\\ FilesPrioImpl KO /!\\");
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
		
		try {
			System.out.println("");
			FilesPrio<Integer> fp = new FilesPrioImplBug<Integer>();
			fp.init();
			FilesPrioContract<Integer> fpc = new FilesPrioContract<Integer>(fp);
			fpc.init();
			fpc.getSize();
			fpc.isEmpty();
			fpc.putPrio(2,2);
			fpc.sizePrio(1);
			fpc.isActivePrio(2);
			fpc.activePrios();
			fpc.maxPrio();
			// KO si aucun bug n'a ete implemente
			System.out.println(" /!\\ Probleme FilesPrioImplBug /!\\ Bug non detecte");
			
		} catch (Exception e) {
			// SI un bug a ete implemente et qu'on ne passe pas ici, il y a un probleme
			System.out.println("FilesPrioImplBug OK - Bug detecte");
			System.out.println(e.getMessage());
		}
		//TODO dernier bug dans putPrio
	}
}