
public class Test {
	public static void main(String args[]) {
		DynamicArray<Integer> d1 = new DynamicArray<Integer>();

		for (int i = 0; i < 20000; i++) {
			d1.add(i);
		}

//		for (int i = 0; i < 5000; i++) {
//			d1.remove();
//		}
		for (int i = 0; i < 20000; i++) {
			d1.add(i);
		}
//		for (int i = 0; i < 15000; i++) {
//			d1.remove();
//		}

		System.out.println(d1.toStringForDebugging());

	}
}
