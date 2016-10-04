public class Block<T> {
	protected final int number; // Block number, as in Block1
	protected final T[] arrayOfElements; // Holds actual elements

	// Note that it is not possible to use the code
	// T a[] = new T[size];
	// which leads to Java�s Generic Array Creation error on
	// compilation. Consult the textbook for solution to surmount this
	// minor problem

	// Number of elements that can be stored in this block;
	// this is equal to arrayOfElements.length
	protected final int capacity;

	// Number of spaces that have been allocated for storing elements;
	// initially 0. size <= capacity
	protected int size;

	// Workhorse constructor. Initialize variables and create array.
	@SuppressWarnings("unchecked")
	public Block(int number, int capacity) {
		this.capacity = capacity;// setting capacity (qualified with this.)
		this.number = number;// setting number (qualified with this.)
		this.size = 0;// empty on creation
		arrayOfElements = ((T[]) new Object[capacity]);// create the array of
														// elements

	}

	// Returns Number
	public int getNumber() {
		return number;
	}

	// Returns capacity
	public int getCapacity() {
		return capacity;
	}

	// Returns size
	public int size() {
		return size;
	}

	// Increases size field of the Block to allow additional elements to be
	// added.
	// Does not change the actual size of the array held by the Block
	public void grow() {
		size += 1;
	}

	// Set the last element to null and decrease the space allocated
	// for storing elements. Decreases size.
	public void shrink() {
		arrayOfElements[size - 1] = null;// set last elem to null
		size -= 1;// decrease size
	}

	// Returns the element at position index in arrayOfElements.
	public T getElement(int index) {
		return arrayOfElements[index];// simple lookup at index
	}

	// Sets the value at position i in arrayOfElements to x.
	public void setElement(int i, T x) {
		arrayOfElements[i] = x;// straight set at index of array
	}

	// Create a pretty representation of the Block.
	// Example:
	// A
	public String toString() {
		StringBuilder returnStr = new StringBuilder();// building string
		for (int i = 0; i < size; i++) {
			// loop through each elem
			if (arrayOfElements[i] != null) {
				// if not null, append it
				returnStr.append(arrayOfElements[i] + " ");

			}
		}
		return returnStr.toString();// return string
	}

	// Create a pretty representation of the Block for debugging.
	// Example:
	// A
	// - capacity=1 size=1
	protected String toStringForDebugging() {
		StringBuilder returnStr = new StringBuilder();// building string
		returnStr.append(toString());//append all elems
		returnStr.append("\n");//new line
		returnStr.append("- capacity=");//label
		returnStr.append(capacity);//data
		returnStr.append(" size=");//label
		returnStr.append(size);//data

		return returnStr.toString();//return String
	}

}