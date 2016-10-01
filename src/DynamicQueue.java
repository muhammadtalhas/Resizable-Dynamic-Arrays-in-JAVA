import java.util.NoSuchElementException;

public class DynamicQueue<T> {
	protected DynamicArray<T> front; // These fields may be renamed
	protected DynamicArray<T> rear; // The methods getFront() and getRear()
									// return them

	// Return the “front” dynamic array of outgoing elements for final testing
	// Target complexity: O(1)
	protected DynamicArray<T> getFront() {
		return front;
	}

	// Return the “rear” dynamic array of incoming elements for final testing
	// Target complexity: O(1)
	protected DynamicArray<T> getRear() {
		return rear;
	}

	// Workhorse constructor. Initialize variables.
	public DynamicQueue() {
		front = new DynamicArray<T>();
		rear = new DynamicArray<T>();
	}

	// Adds x to the rear of the queue
	// Target complexity: O(1)
	public void enqueue(T x) {
		rear.add(x);
	}

	// Removes and returns the element at the front of the queue
	// Throws NoSuchElementException if this queue is empty.
	// Target complexity: O(n)
	public T dequeue() {

		// First checks if Queue is empty and throws an error is so
		if (isEmpty()) {
			NoSuchElementException e = new NoSuchElementException();
			throw e;
		}
		// If the front is empty...
		if (front.size() == 0) {
			// We need to transfer
			while (rear.size() != 0) {
				// Were gonna loop tell rear is empty
				T item = rear.get(rear.size - 1);// get last item
				front.add(item);// add it to the front
				rear.remove();// remove it from the rear
			} // repeat
		}
		T returnItem = front.get(front.size - 1);// get the last item in front
		front.remove();// remove it
		return returnItem;// return it
	}

	// Returns true if the queue is empty
	public boolean isEmpty() {
		if (front.size == 0 && rear.size == 0) {
			return true;// If both are empty
		} else {
			return false;// else
		}
	}

	// Returns the size of the queue
	public int size() {
		return front.size + rear.size;
	}

	// Create a pretty representation of the DynamicQueue.
	// Example:
	// [A, B, C, D]
	public String toString() {
		if (isEmpty()) {
			// If empty just return empty array
			return "[]";
		}
		StringBuilder returnStr = new StringBuilder();// Gonna build a string
		returnStr.append("[");// start off by adding the first bracket
		if (front.size != 0) {
			// I check the size of front here because if its empty then with my
			// logic, it was adding a comma in an improper place

			// Append front.toString with the brackets removed
			returnStr.append(front.toString().replaceAll("[\\[\\]]", ""));
			returnStr.append(",");// add comma
		}
		// Append rear.toString with the brackets removed
		returnStr.append(rear.toString().replaceAll("[\\[\\]]", ""));
		returnStr.append("]");// append final bracket
		return returnStr.toString();// convert to string and return
	}

	// Create a pretty representation of the DynamicQueue for debugging.
	// Example:
	// front.toString: [A, B]
	// rear.toString: [C, D]
	protected String toStringForDebugging() {
		StringBuilder returnStr = new StringBuilder();// builing a string
		returnStr.append("front.toString: ");// label
		returnStr.append(front.toString());// data
		returnStr.append("\n");// new line
		returnStr.append("rear.toString: ");// label
		returnStr.append(rear.toString());// data
		return returnStr.toString();// return
	}
}