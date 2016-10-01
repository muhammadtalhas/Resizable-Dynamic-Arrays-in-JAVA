public class SuperBlock {
	protected int number; // as in S0, S1, etc.
	protected int maxNumberOfDataBlocks;
	// number of elements per Block
	protected int maxNumberOfElementsPerBlock;
	// current number of Blocks in this SuperBlock
	protected int currentNumberOfDataBlocks;

	// Workhorse constructor. Initialize variables.
	public SuperBlock(int number, int maxNumberOfDataBlocks, int maxNumberOfElementsPerBlock,
			int currentNumberOfDataBlocks) {
		this.number = number;// setting number(qualifying left side)
		// setting maxNumberOfDataBlocks(qualifying left side)
		this.maxNumberOfDataBlocks = maxNumberOfDataBlocks;
		// setting maxNumberOfElementsPerBlock(qualifying left side)
		this.maxNumberOfElementsPerBlock = maxNumberOfElementsPerBlock;
		// setting currentNumberOfDataBlocks(qualifying left side)
		this.currentNumberOfDataBlocks = currentNumberOfDataBlocks;

	}

	// Returns number.
	public int getNumber() {
		return this.number;
	}

	// Returns maxNumberOfDataBlocks
	public int getMaxNumberOfDataBlocks() {
		return this.maxNumberOfDataBlocks;
	}

	// Returns maxNumberOfElementsPerBlock
	public int getMaxNumberOfElementsPerBlock() {
		return this.maxNumberOfElementsPerBlock;
	}

	// Returns currentNumberOfDataBlocks
	public int getCurrentNumberOfDataBlocks() {
		return this.currentNumberOfDataBlocks;
	}

	// Increments CurrentNumberOfDataBlocks
	public void incrementCurrentNumberOfDataBlocks() {
		this.currentNumberOfDataBlocks++;
	}

	// Decrements currentNumberOfDataBlocks
	public void decrementCurrentNumberOfDataBlocks() {
		this.currentNumberOfDataBlocks = this.currentNumberOfDataBlocks - 1;
	}

	// Create a pretty representation of the SuperBlock for debugging.
	// Example:
	// - maxNumberOfDataBlocks:2
	// - maxNumberOfElementsPerBlock:2
	// - currentNumberOfDataBlocks:1
	protected String toStringForDebugging() {
		// return string with labels and data, each on new line
		return "- maxNumberOfDataBlocks: " + maxNumberOfDataBlocks + "\n" + "- maxNumberOfElementsPerBlock: "
				+ maxNumberOfElementsPerBlock + "\n" + "- currentNumberOfDataBlocks: " + currentNumberOfDataBlocks
				+ "\n";
	}
}