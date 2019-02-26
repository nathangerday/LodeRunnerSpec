
public class TestFilePrio {
	public static void main(String[] args) {
		FilesPrio<String> f = new FilesPrioImpl<>();
		System.out.println(f.getActivePrios());
		System.out.println(f.getSize());
		
		f.putPrio(10, "Hello");
		f.putPrio(10, "world");
		f.putPrio(20, "Cruel");
		System.out.println(f.getActivePrios());
		System.out.println(f.getSize());
		
		System.out.println(f.getElem());
		f.removePrio(20);
		System.out.println(f.getElem());
		System.out.println(f.getSizePrio(30));
		
	}
}
