
public class TestFilePrio {
	public static void main(String[] args) {
		FilesPrio<String> fp = new FilesPrioImpl<>();
		FilesPrio<String> f = new FilesPrioContrat<String>(fp);
		f.init();
		
		f.putPrio(10, "Hello");
		f.putPrio(10, "world");
		f.put("azsduhaze");
		f.put("b  jze");
		f.put("alkjlijze");
		f.put("rthsd");
		f.put("azezrezr");
		f.put("azeaze");
		f.putPrio(20, "truc");
		f.putPrio(20, "machin2");
		f.putPrio(20, "bidule3");
		f.put("T1");
		f.putPrio(10, "T2");
		f.putPrio(5, "TEST3");
		f.removePrio(10);
		f.remove();
		f.removePrio(10);
		f.removePrio(10);
		f.remove();
		f.removePrio(10);
		f.removePrio(10);
		f.remove();
		f.remove();
		f.put("San");
		f.put("Gatsu");
		f.put("No");
		f.remove();
		f.putPrio(20, "Lion");
		f.remove();
		
		
		System.out.println("Final size : " + f.getSize());
		System.out.println("Final set of active prios : " + f.getActivePrios());
		System.out.println("Size of prio 5 : " + f.getSizePrio(5));
		System.out.println("Size of prio 10 : " + f.getSizePrio(10));
		System.out.println("Size of prio 20 : " + f.getSizePrio(20));
		
	}
}
