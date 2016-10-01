public class Location {
	// index of Block in DynamicArray.arrayofBlocks
	protected final int blockIndex;
	// index of element in the arrayOfElements in the Block
	protected final int elementIndex;

	// Workhorse constructor. Initialize variables.
	public Location(int blockIndex, int elementIndex) {
		this.blockIndex = blockIndex;// setting blockindex (qualifying left with
										// this.)
		this.elementIndex = elementIndex;// setting elem (qualifying left with
											// this.)
	}

	// Returns blockIndex
	public int getBlockIndex() {
		return blockIndex;
	}

	// returns elementIndex
	public int getElementIndex() {
		return elementIndex;
	}

	// Create a pretty representation of the Location for debugging.
	// Example:
	// blockIndex:2 elementIndex:1
	protected String toStringForDebugging() {
		// simple String so no string builder
		return "blockIndex:" + blockIndex + " elementIndex:" + elementIndex;
	}
}
