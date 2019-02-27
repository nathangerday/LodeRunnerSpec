
public class TestFilePrio {
	public static void main(String[] args) {
		FilesPrio<String> fp = new FilesPrioImpl<>();
		FilesPrio<String> f = new FilesPrioContrat<String>(fp);
		System.out.println(f.getActivePrios());
		System.out.println(f.getSize());
		
		f.putPrio(10, "Hello");
		f.putPrio(10, "world");
		f.putPrio(20, "Cruel");
		f.put("T1");
		f.putPrio(10, "T2");
		f.putPrio(5, "TEST3");
		f.remove();
		System.out.println(f.getActivePrios());
		System.out.println(f.getSize());
		
		System.out.println(f.getElem());
		f.removePrio(20);
		System.out.println(f.getElem());
		System.out.println(f.getSizePrio(30));
		
	}
}
