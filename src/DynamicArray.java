import java.lang.Math;

class DynamicArray<T> {
	protected Object[] arrayOfBlocks;
	protected final int DEFAULTCAPACITY = 4;
	protected int sizeOfArrayOfBlocks; // number of Blocks in arrayOfBlocks
	protected int size; // number of elements in DynamicArray
	protected int numberOfEmptyDataBlocks;
	protected int numberOfNonEmptyDataBlocks;
	protected int numberOfDataBlocks;
	protected int indexOfLastNonEmptyDataBlock;
	protected int indexOfLastDataBlock;
	protected int numberOfSuperBlocks;
	protected SuperBlock lastSuperBlock; // right-most SuperBlock

	// Workhorse constructor. Initialize variables, create the array
	// and the last SuperBlock, which represents SB0.
	@SuppressWarnings("rawtypes")
	DynamicArray() {
		arrayOfBlocks = new Object[DEFAULTCAPACITY];// set up array with default
													// size
		arrayOfBlocks[0] = new Block(0, 1);// set first block to an empty block
		size = 0;// no elements so size is 0
		sizeOfArrayOfBlocks = 1;// One block exists so set to 1
		numberOfEmptyDataBlocks = 1;// Block is empty upon creation so 1
		numberOfNonEmptyDataBlocks = 0;// No data means all empty so 0
		numberOfDataBlocks = 1;// One block exists at this point
		indexOfLastNonEmptyDataBlock = -1;// This does not exist so well set -1
		indexOfLastDataBlock = 0;// First position will hold new block
		numberOfSuperBlocks = 1;// Only 1 exists
		// New Super with
		// number=0,maxDataBlock=1,maxElemPerBlock=1,currentNumbOfDatablocks=1
		lastSuperBlock = new SuperBlock(0, 1, 1, 1);
	}

	protected Location locate(int index) {
		int r = index + 1;// Computing r
		double k = log2Floor(r); // k=floor(log2(r))
		int p = 2 * (power2(log2Floor(r) / 2) - 1);// p=2(2^floor(k/2) -1)
		if (((int) k & 1) == 1) {
			// If k is odd
			p = (p + power2(log2Floor(r) / 2));// add on 2^floor(k/2)
		}
		int mask = log2Ceil(r) / 2; // computing mask as ceil(k/2)
		int e = r & maskOfN(mask);// extracting bits and resolving e

		// Shifting over r to the right by mask, extracting by new calculation
		// of MaskOfN
		int b = (r >> mask) & maskOfN(log2Floor(r) / 2);

		// p+b is block index
		// e is element index
		return new Location(p + b, e);
	}

	// Returns the Block at position i in arrayOfBlocks.
	// Target complexity: O(1)
	@SuppressWarnings("unchecked")
	protected Block<T> getBlock(int index) {
		return (Block<T>) arrayOfBlocks[index];// simple lookup from index. May
												// return null if blank
	}

	// Returns the element at position i in the DynamicArray.
	// Throws IllegalArgumentException if index < 0 or
	// index > size -1;
	// Target complexity: O(1)
	public T get(int i) {
		if (i < 0 || i > size() - 1) {
			// avoids out of bounds and nullpointers
			IllegalArgumentException e = new IllegalArgumentException();
			throw e;
		}
		Location location = locate(i);// use our locate to find where index is
		Block<T> block = getBlock(location.getBlockIndex());// extract block by index
		return (T) block.arrayOfElements[location.getElementIndex()];// return by
																// elmIndex

	}

	// Sets the value at position i in DynamicArray to x.
	// Throws IllegalArgumentException if index < 0 or
	// index > size -1;
	// Target complexity: O(1)
	public void set(int index, T x) {
		if (index < 0 || index > size() - 1) {
			// avoids out of bounds and nullpointers
			IllegalArgumentException e = new IllegalArgumentException();
			throw e;
		}
		Location location = locate(index);// locate block and index of location
		Block<T> block = getBlock(location.getBlockIndex());// extract ref to block
		block.arrayOfElements[location.getElementIndex()] = x;// set it

	}

	// Allocates one more spaces in the DynamicArray. This may
	// require the creation of a Block and the last SuperBlock may change.
	// Also, expandArray is called if the arrayOfBlocks is full when
	// a Block is created.
	// Called by add.
	// Target complexity: O(1)

	protected void grow() {
		Block<T> refLastBlock = getBlock(indexOfLastDataBlock); // This will get
																// a refrence to
																// the last
																// block in the
																// array
		Block<T> newBlock;// will hold new block. Garbage collector will clean
							// if
							// it is not used later on. THANKS JAVA!

		// First lets check if the array itself is full AND if the last block is
		// full
		if (indexOfLastDataBlock == arrayOfBlocks.length - 1 && refLastBlock.size() == refLastBlock.getCapacity()) {
			// If it is, we will double the size of the array
			expandArray();
		}

		// Now we're sure that the array has enough space for more blocks, we
		// can go ahead and check to see if the last block is full
		if (refLastBlock.size() == refLastBlock.getCapacity()) {
			// We may need to create a new superBlock, so lets extract some data
			// from the last superBlock

			int sNum = lastSuperBlock.getNumber();
			int sMAxNumberOfDataBlocks = lastSuperBlock.getMaxNumberOfDataBlocks();
			int sMaxNumberOfElementsPerBlock = lastSuperBlock.getMaxNumberOfElementsPerBlock();
			int sCurrentNumberOfDataBlocks = lastSuperBlock.getCurrentNumberOfDataBlocks();

			// If super block is full, we will need a new one after this
			// process. So lets creaet it
			if (sCurrentNumberOfDataBlocks == sMAxNumberOfDataBlocks) {
				// We need to see if the next superBlock will be even, or odd
				if ((sNum + 1 & 1) == 0) { // even
					int newNum = sNum + 1;// new Number
					int newMaxNumberOfDataBlocks = sMAxNumberOfDataBlocks * 2; // Multiply
																				// by
																				// two
																				// for
																				// new
																				// superblock
					int newMaxNumberOfElementsPerBlock = sMaxNumberOfElementsPerBlock; // Same
																						// as
																						// last
																						// super

					// Create a new super block with above parameters
					SuperBlock newSuper = new SuperBlock(newNum, newMaxNumberOfDataBlocks,
							newMaxNumberOfElementsPerBlock, 0);

					// set it as last one
					lastSuperBlock = newSuper;
					numberOfSuperBlocks++;// book keeping
				} else {
					int newNum = sNum + 1; // new Number
					int newMaxNumberOfDataBlocks = sMAxNumberOfDataBlocks;// Same
																			// as
																			// last
																			// super
					int newMaxNumberOfElementsPerBlock = sMaxNumberOfElementsPerBlock * 2;// Multiply
																							// by
																							// two
																							// for
																							// new
																							// superblock

					// Create a new super block with above parameters
					SuperBlock newSuper = new SuperBlock(newNum, newMaxNumberOfDataBlocks,
							newMaxNumberOfElementsPerBlock, 0);

					// set it as last one
					lastSuperBlock = newSuper;
					numberOfSuperBlocks++;// book keeping
				}

			}

			// Since we know that the block was full, we need a new one, here we
			// set it up with the parameters required
			newBlock = new Block<T>(refLastBlock.getNumber() + 1, lastSuperBlock.getMaxNumberOfElementsPerBlock());

			arrayOfBlocks[numberOfDataBlocks] = newBlock;// added to end of
															// array

			// Grow the new block
			newBlock.grow();

			// Now some book keeping
			numberOfNonEmptyDataBlocks++;// New block was created but it isnt
											// empty
			indexOfLastNonEmptyDataBlock = newBlock.number;// new blocks number
															// is the last index
			numberOfDataBlocks++;// another block was added
			sizeOfArrayOfBlocks++;// size of array was increased
			indexOfLastDataBlock++;// last datablock was moved by one

			// superblock book keeping
			lastSuperBlock.incrementCurrentNumberOfDataBlocks();// Last super
																// has a new
																// block

		} else {
			// If the last block isnt full, we just need to grow the block

			// If the block originally has a size of zero, we need to do some
			// extra bookkeeping
			if (refLastBlock.size() == 0) {
				// After this grow...
				numberOfEmptyDataBlocks--;// ...block will no longer be empty
				numberOfNonEmptyDataBlocks++;// ...and will become 'non empty'
			}
			refLastBlock.grow();// Grow the block
			// index of last nonEmpty block need to be set
			indexOfLastNonEmptyDataBlock = refLastBlock.getNumber();
		}

	}

	// Grows the DynamicArray by one space, increases the size of the
	// DynamicArray, and sets the last element to x.
	// Target complexity: O(1)
	public void add(T x) {
		this.grow();// Grow will handle if there is no room
		Location location = locate(size);// easy way to find last spot
		int extractedBlock = location.getBlockIndex();// last block
		int extractedElem = location.getElementIndex();// last open spot
		Block<T> insertBlock = getBlock(extractedBlock);// ref to target block
		insertBlock.setElement(extractedElem, x);// setting index in block
		size++;// bookkeeping- grow size of array
	}

	// Write a null value to the last element, shrinks the DynamicArray by one
	// space, and decreases the size of the DynamicArray. A Block may be
	// deleted and the last SuperBlock may change.
	// Also, shrinkArray is called if the arrayOfBlocks is less than or equal
	// to a quarter full when a Block is deleted.
	// Throws IllegalStateException if the DynamicArray is empty when remove is
	// called.
	// Target complexity: O(1)
	public void remove() {
		if (size == 0) {
			// Nothing to remove is no elements
			IllegalStateException e = new IllegalStateException();
			throw e;
		}
		// -Get last block
		Location location = locate(size - 1);// very last elem being found
		int extractedBlock = location.getBlockIndex();// ref to last block
		Block<T> refLastBlock = getBlock(extractedBlock);
		refLastBlock.shrink();// shrink the block
		size--;// array has one less elem
		if (refLastBlock.size() == 0) {
			// if Block is now empty, some book keeping
			numberOfEmptyDataBlocks++;// one new empty block
			numberOfNonEmptyDataBlocks--;// one less non empty block
			indexOfLastNonEmptyDataBlock--;// non empty block has moved back 1
		}

		// determining if block should be deleted
		if (numberOfEmptyDataBlocks == 2) {
			// If 2 empty blocks exist...
			arrayOfBlocks[indexOfLastDataBlock] = null;// Set last one to null
			indexOfLastDataBlock--;// last block has moved back
			sizeOfArrayOfBlocks--;// lost a block
			numberOfEmptyDataBlocks--;// one empty block gone
			numberOfDataBlocks--;// number of block has gone down too
			// --Bookkeeping-super
			lastSuperBlock.decrementCurrentNumberOfDataBlocks();// need to let super know
														// it lost a block
		}

		// determining if superblock needs to change
		if (lastSuperBlock.getCurrentNumberOfDataBlocks() == 0) {
			// superblock now has nothing to be super for :(

			// getting relevent current superblock info
			int sNum = lastSuperBlock.getNumber();
			int sMAxNumberOfDataBlocks = lastSuperBlock.getMaxNumberOfDataBlocks();
			int sMaxNumberOfElementsPerBlock = lastSuperBlock.getMaxNumberOfElementsPerBlock();

			// determining oddness of new super
			if ((sNum - 1 & 1) == 0) {
				// if even..
				int nNum = lastSuperBlock.getNumber() - 1;// decrease number
				// same as last
				int nMAxNumberOfDataBlocks = sMAxNumberOfDataBlocks;
				// half of last
				int nMaxNumberOfElementsPerBlock = sMaxNumberOfElementsPerBlock / 2;
				// New super presumed full
				int nCurrentNumberOfDataBlocks = nMAxNumberOfDataBlocks;

				// new super being set with new values
				lastSuperBlock = new SuperBlock(nNum, nMAxNumberOfDataBlocks, nMaxNumberOfElementsPerBlock,
						nCurrentNumberOfDataBlocks);
				numberOfSuperBlocks--;// lost a super
			} else {
				// if odd..
				int nNum = lastSuperBlock.getNumber() - 1;// decrease number
				// half of last
				int nMAxNumberOfDataBlocks = sMAxNumberOfDataBlocks / 2;
				// same as last
				int nMaxNumberOfElementsPerBlock = sMaxNumberOfElementsPerBlock;
				// New super presumed full
				int nCurrentNumberOfDataBlocks = nMAxNumberOfDataBlocks;
				// new super being set with new values
				lastSuperBlock = new SuperBlock(nNum, nMAxNumberOfDataBlocks, nMaxNumberOfElementsPerBlock,
						nCurrentNumberOfDataBlocks);
				numberOfSuperBlocks--;// lost a super
			}

			// Were assuming here that if last superblock has changed then a
			// block has been removed. Hence, there is a possibility that we may
			// need to call shrinkArray()

			// 0-1 fullness of array
			double percentFull = (double) size / (double) arrayOfBlocks.length;// 0-1
																				// fullness
			double oneFourth = 0.25;// constant 1/4
			if (percentFull <= oneFourth) {
				// If less than 1/4 full call shrink array
				shrinkArray();
			}
		}

	}

	// Decreases the length of the arrayOfBlocks by half. Create a new
	// arrayOfBlocks and copy the Blocks from the old one to this new array.
	protected void shrinkArray() {
		if (arrayOfBlocks.length / 2 >= DEFAULTCAPACITY) {
			// we dont want to go below the default capacity so checking that

			// new array half as big
			Object[] arrayOfBlocksNew = new Object[arrayOfBlocks.length / 2];
			for (int i = 0; i < numberOfDataBlocks; i++) {
				// copy all old data to new (copying addresses to block)
				arrayOfBlocksNew[i] = arrayOfBlocks[i];
			}
			// set array to new one, old is lost to Garbage collector
			arrayOfBlocks = arrayOfBlocksNew;
		}
	}

	// Doubles the length of the arrayOfBlocks. Create a new
	protected void expandArray() {
		// new array double sized
		Object[] arrayOfBlocksNew = new Object[arrayOfBlocks.length * 2];
		for (int i = 0; i < numberOfDataBlocks; i++) {
			// copy all old data to new (copying addresses to block)
			arrayOfBlocksNew[i] = arrayOfBlocks[i];
		}
		// set array to new one, old is lost to Garbage collector
		arrayOfBlocks = arrayOfBlocksNew;
	}

	// Returns the size of the DynamicArray which is the number of elements that
	// have been added to it with the add(x) method but not removed. The size
	// does not correspond to the capacity of the array.
	public int size() {
		return size;
	}

	// Returns the log base 2 of n
	protected static double log2(int n) {
		return (Math.log(n) / Math.log(2));
	}

	// Returns a mask of N 1 bits; this code is provided below and can be used
	// as is
	protected int maskOfN(int N) {
		int POW2ToN = 1 << N; // left shift 1 N places; e.g., 1 << 2 = 100 = 4
		int mask = POW2ToN - 1; // subtract 1; e.g., 1002 – 12 = 0112 = 3
		// Integer.toString(mask,2); // a String with the bits of mask
		return mask;
	}

	// Create a pretty representation of the DynamicArray. This method should
	// return string formatted similarly to ArrayList
	// Examples: [], [X], [A, B, C, D]
	//
	// Target Complexity: O(N)
	// N: number of elements in the DynamicArray
	public String toString() {
		// building string starting wth bracket
		StringBuilder returnStr = new StringBuilder("[");
		for (int i = 0; i < size(); i++) {
			// for all elements...
			returnStr.append(get(i));// get the elm and append
			if (i != size() - 1) {
				// if this isnt the last elem, append a comma
				returnStr.append(",");
			}
		}
		returnStr.append("]");// append closing bracket
		return returnStr.toString();// return string

	}

	// Create a pretty representation of the DynamicArray for debugging
	// Example:
	// DynamicArray: A B
	// numberOfDataBlocks: 2
	// numberOfEmptyDataBlocks: 0
	// numberOfNonEmptyDataBlocks: 2
	// indexOfLastNonEmptyDataBlock: 1
	// indexOfLastDataBlock: 1
	// numberOfSuperBlocks: 2
	// lastSuperBlock: SB1
	// Block0: A
	// - capacity: 1 size: 1
	// Block1: B
	// - capacity: 2 size: 1
	// SB1:
	// - maxNumberOfDataBlocks: 1
	// - numberOfElementsPerBlock: 2
	// - currentNumberOfDataBlocks: 1

	protected String toStringForDebugging() {
		StringBuilder returnStr = new StringBuilder();

		// Printing the elements in the array WITHOUT brackets or commas
		returnStr.append("DynamicArray: ");
		for (int i = 0; i < size(); i++) {
			returnStr.append(get(i));
			if (i != size() - 1) {
				returnStr.append(" ");// dont add last space cause OCD
			}
		}
		// All book keeping data
		returnStr.append("\n");
		returnStr.append("numberOfDataBlocks: " + numberOfDataBlocks + "\n");
		returnStr.append("numberOfEmptyDataBlocks: " + numberOfEmptyDataBlocks + "\n");
		returnStr.append("numberOfNonEmptyDataBlocks: " + numberOfNonEmptyDataBlocks + "\n");
		returnStr.append("indexOfLastNonEmptyDataBlock: " + indexOfLastNonEmptyDataBlock + "\n");
		returnStr.append("indexOfLastDataBlock: " + indexOfLastDataBlock + "\n");
		returnStr.append("numberOfSuperBlocks: " + numberOfSuperBlocks + "\n");
		returnStr.append("lastSuperBlock: SB" + lastSuperBlock.getNumber() + "\n");

		// Here we loop through each block, append the blocks number, then
		// append data in that block. =
		for (int i = 0; i <= indexOfLastDataBlock; i++) {
			returnStr.append("Block" + getBlock(i).getNumber() + ": ");
			returnStr.append(getBlock(i).toString());

			returnStr.append("\n");// append new line
			// append some blov=ck data
			returnStr.append("- capacity= " + getBlock(i).getCapacity() + " " + "size= " + getBlock(i).size() + "\n");

		}
		returnStr.append("\n");// append new line
		// append last superblock number + data
		returnStr.append("SB" + lastSuperBlock.getNumber() + ":" + "\n");
		returnStr.append("- maxNumberOfDataBlocks: " + lastSuperBlock.getMaxNumberOfDataBlocks() + "\n");
		returnStr.append("- maxNumberOfElementsPerBlock: " + lastSuperBlock.getMaxNumberOfElementsPerBlock() + "\n");
		returnStr.append("- currentNumberOfDataBlocks: " + lastSuperBlock.getCurrentNumberOfDataBlocks() + "\n");

		return returnStr.toString();// return the string

	}

	/*
	 * Below are helper methods for faster math.
	 */

	// returns floor(log2(n)
	public static int log2Floor(int n) {
		return bitNum(n) - 1;
	}

	// returns ceil(log2((n)
	public static int log2Ceil(int n) {
		return bitNum(n - 1);
	}

	// returns 2 to the power n
	public static int power2(int n) {
		return 1 << n;
	}

	// returns the number of bits in n ignoring leading 0 bits
	private static int bitNum(int n) {
		int count = 0;
		while (n != 0) {
			n = n >>> 1;
			count++;
		}
		return count;
	}
}
// Rest in Peace Hamza, miss you everyday