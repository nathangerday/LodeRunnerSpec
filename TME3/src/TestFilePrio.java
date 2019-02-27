
public class TestFilePrio {
	public static void main(String[] args) {
		FilesPrio<String> fp = new FilesPrioImplBug<>();
		FilesPrio<String> f = new FilesPrioContrat<String>(fp);
		f.init();
		
		System.out.println(f.getActivePrios());
		System.out.println(f.getSize());
		
		f.putPrio(10, "Hello");
		f.putPrio(10, "world");
		f.put("azsduhaze");
		f.put("b  jze");
		f.put("alkjlijze");
		f.put("rthsd");
		f.put("azezrezr");
		f.put("azeaze");
		f.putPrio(20, "Cruel");
		f.putPrio(20, "Cruel2");
		f.putPrio(20, "Cruel3");
		f.put("T1");
		f.putPrio(10, "T2");
		f.putPrio(5, "TEST3");
		f.removePrio(10);
		f.removePrio(10);
		f.removePrio(10);
		f.removePrio(10);
		f.removePrio(10);
		f.removePrio(10);
		f.removePrio(10);
		f.removePrio(10);
		f.removePrio(10);
		
		System.out.println(f.getSize());
		System.out.println(f.getActivePrios());
		System.out.println(f.getSizePrio(5));
		System.out.println(f.getSizePrio(10));
		System.out.println(f.getSizePrio(20));
		
	}
}
