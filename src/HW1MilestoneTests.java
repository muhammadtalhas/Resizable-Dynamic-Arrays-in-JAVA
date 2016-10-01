// CHANGELOG: 

/* To run them on the command line, make sure that the junit-310.jar
   is in the project directory.
 
   demo$ javac -cp .:junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .:junit-cs310.jar HW1MilestoneTests   # run tests
 
   On Windows replace : with ; (colon with semicolon)
   demo$ javac -cp .;junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .;junit-cs310.jar HW1MilestoneTests   # run tests
*/

import org.junit.*;
import org.junit.Test; // fixes some compile problems with annotations
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class HW1MilestoneTests{
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("HW1MilestoneTests");
  }
  
  // @Test(timeout=1000,expected=SomeException.class)

  public static void failFmt(String fmt, Object... args){
    fail(String.format(fmt,args));
  }

  static String printArray(Object[] array) {
		StringBuilder B = new StringBuilder();
		B.append("[ ");
		for(Object o: array)
			B.append(o + " ");
		B.append("]");
		return B.toString();
  }


  // Location_Tests


  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Location_construct1() {
  // Check construct Location
     Location l1 = new Location(1,0);
  
     if(! (l1.blockIndex==1 && l1.elementIndex == 0)){
       failFmt("Location constructor:\n"+
          "Expect: + l1.blockIndex==1 l1.elementIndex==0\n" +
          "Actual: l1.blockIndex==" + l1.blockIndex + " l1.elementIndex==" + l1.elementIndex);
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Location_getBlockIndex1(){
   // test getBlockIndex()
     Location l1 = new Location(1,0);
  
     if(l1.getBlockIndex()!=1){
       failFmt("Location getBlockIndex:\n"+
          "Expect: l1.getBlockIndex==1\n" +
          "Actual: l1.getBlockIndex==" + l1.getBlockIndex());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Location_getElementIndex1(){
   // test getElementIndex()
     Location l1 = new Location(1,0);
  
     if(l1.getElementIndex()!=0){
       failFmt("Location getElementIndex:\n"+
          "Expect: l1.getElementIndex==0\n" +
          "Actual: l1.getElementIndex==" + l1.getElementIndex());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Location_toStringForDebugging1(){
   // test toStringForDebugging()
   // output expected to be:
   // blockIndex:2 elementIndex:1
   // Before we check the output, we stip away the white space, resulting in
   // blockIndex:2elementIndex:1
   // So this ignores minor spacing differences between the actual and expected Strins
     Location l1 = new Location(1,0);
     String result = l1.toStringForDebugging().replaceAll("\\s+",""); // For example, "A B" becomes "AB", so does "A   B"
  
     if(!(result.equals("blockIndex:1elementIndex:0"))){
       failFmt("Location toStringForDebugging:\n"+
          "Expect: l1.toStringForDebugging = blockIndex:1 elementIndex:0\n" +
          "Actual: l1.toStringForDebugging = " + l1.toStringForDebugging());
     }
  }


  // SuperBlock_Tests


  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void SuperBlock_construct1() {
  // Check construct SuperBlock
     SuperBlock s1 = new SuperBlock(1,2,4,0);
  
     if(! (s1.number==1 && s1.maxNumberOfDataBlocks == 2 && s1.maxNumberOfElementsPerBlock==4 && s1.currentNumberOfDataBlocks == 0)){
       failFmt("SuperBlock constructor:\n"+
          "Expect:s1.number==1  s1.maxNumberOfDataBlocks==2  s1.maxNumberOfElementsPerBlock==4  s1.currentNumberOfDataBlocks == 0" +
          "Actual: s1.number==" + s1.number + " s1.maxNumberOfDataBlocks==" + s1.maxNumberOfDataBlocks
               + " s1.maxNumberOfElementsPerBlock==" + s1.maxNumberOfElementsPerBlock
               + " s1.currentNumberOfDataBlocks==" + s1.currentNumberOfDataBlocks);
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void SuperBlock_getNumber1(){
   // test getNumber()
     SuperBlock s1 = new SuperBlock(1,2,4,0);
  
     if(s1.getNumber()!=1){
       failFmt("SuperBlock getNumber:\n"+
          "Expect: +s1.getNumber==1\n" +
          "Actual: s1.getNumber==" + s1.getNumber());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void SuperBlock_getMaxNumberOfDataBlocks1(){
   // test getMaxNumberOfDataBlocks()
     SuperBlock s1 = new SuperBlock(1,2,4,0);
  
     if(s1.getMaxNumberOfDataBlocks()!=2){
       failFmt("SuperBlock getMaxNumberOfDataBlocks:\n"+
          "Expect: +s1.getMaxNumberOfDataBlocks==2\n" +
          "Actual: s1.getMaxNumberOfDataBlocks==" + s1.getMaxNumberOfDataBlocks());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void SuperBlock_getMaxNumberOfElementsPerBlock1(){
   // test getMaxNumberOfElementsPerBlock()
     SuperBlock s1 = new SuperBlock(1,2,4,0);
  
     if(s1.getMaxNumberOfElementsPerBlock()!=4){
       failFmt("SuperBlock getMaxNumberOfElementsPerBlock:\n"+
          "Expect: +s1.getMaxNumberOfElementsPerBlock==4\n" +
          "Actual: s1.getMaxNumberOfElementsPerBlock==" + s1.getMaxNumberOfElementsPerBlock());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void SuperBlock_getCurrentNumberOfDataBlocks1(){
   // test getCurrentNumberOfDataBlocks()
     SuperBlock s1 = new SuperBlock(1,2,4,0);
  
     if(s1.getCurrentNumberOfDataBlocks()!=0){
       failFmt("SuperBlock getCurrentNumberOfDataBlocks:\n"+
          "Expect: s1.getCurrentNumberOfDataBlocks==0\n" +
          "Actual: s1.getCurrentNumberOfDataBlocks==" + s1.getCurrentNumberOfDataBlocks());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void SuperBlock_incrementCurrentNumberOfDataBlocks1(){
   // test incrementCurrentNumberOfDataBlocks()
     SuperBlock s1 = new SuperBlock(1,2,4,0);
     s1.incrementCurrentNumberOfDataBlocks();
  
     if(s1.getCurrentNumberOfDataBlocks()!=1){
       failFmt("SuperBlock incrementCurrentNumberOfDataBlocks:\n"+
          "Expect: +s1.getCurrentNumberOfDataBlocks==1\n" +
          "Actual: s1.getCurrentNumberOfDataBlocks==" + s1.getCurrentNumberOfDataBlocks());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void SuperBlock_decrementCurrentNumberOfDataBlocks1(){
   // test decrementCurrentNumberOfDataBlocks()
     SuperBlock s1 = new SuperBlock(1,2,4,1);
     s1.decrementCurrentNumberOfDataBlocks();
  
     if(s1.getCurrentNumberOfDataBlocks()!=0){
       failFmt("SuperBlock decrementCurrentNumberOfDataBlocks:\n"+
          "Expect: +s1.getCurrentNumberOfDataBlocks==0\n" +
          "Actual: s1.getCurrentNumberOfDataBlocks==" + s1.getCurrentNumberOfDataBlocks());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void SuperBlock_toStringForDebugging1(){
   // test toStringForDebugging()
   // output expected to be:
   // - maxNumberOfDataBlocks:2    
   // - maxNumberOfElementsPerBlock:4
   // - currentNumberOfDataBlocks:1
   // Before we check the output, we stip away the white space, resulting in
   // -maxNumberOfDataBlocks:2-maxNumberOfElementsPerBlock:4-currentNumberOfDataBlocks:1
   // So this ignores minor spacing differences between the actual and expected Strings

     SuperBlock s1 = new SuperBlock(1,2,4,1);
     String result = s1.toStringForDebugging().replaceAll("\\s+",""); // For example, "A B" becomes "AB", so does "A   B"
  
     if(!(result.equals("-maxNumberOfDataBlocks:2-maxNumberOfElementsPerBlock:4-currentNumberOfDataBlocks:1"))){
       failFmt("SuperBlock toStringForDebugging:\n"+
          "Expect: s1.toStringForDebugging = -maxNumberOfDataBlocks:2-maxNumberOfElementsPerBlock:4-currentNumberOfDataBlocks:1\n" +
          "Actual: l1.toStringForDebugging = " + result);
     }
  }


  // Block_Tests


  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Block_construct1() {
  // Check construct Block
     Block<String> b1 = new Block<String>(1,2);
  
     if(! (b1.number==1 && b1.capacity == 2 && b1.size==0)){
       failFmt("Block constructor:\n"+
          "Expect:b1.number==1 b1.capacity == 2 b1.size==0" +
          "Actual: b1.number==" + b1.number + " b1.capacity==" + b1.capacity
               + " b1.size==" + b1.size);
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Block_getNumber1(){
   // test getCapacity()
    Block<String> b1 = new Block<String>(1,2);
  
     if(b1.getNumber()!=1){
       failFmt("Block getNumber:\n"+
          "Expect: +b1.getNumber==1\n" +
          "Actual: b1.getNumber==" + b1.getNumber());
     }
  }


  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Block_getCapacity1(){
   // test getCapacity()
    Block<String> b1 = new Block<String>(1,2);
  
     if(b1.getCapacity()!=2){
       failFmt("Block getCapacity:\n"+
          "Expect: +b1.getCapacity==2\n" +
          "Actual: b1.getCapacity==" + b1.getCapacity());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Block_grow1(){
   // test grow()
     Block<String> b1 = new Block<String>(1,2);
     b1.grow();
  
     if(b1.size()!=1){
       failFmt("Block grow:\n"+
          "Expect: +b1.size==1\n" +
          "Actual: b1.size==" + b1.size());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Block_grow2(){
   // test grow()
     Block<String> b1 = new Block<String>(1,2);
     b1.grow();
	  b1.grow();
  
     if(b1.size()!=2){
       failFmt("Block grow:\n"+
          "Expect: +b1.size==2\n" +
          "Actual: b1.size==" + b1.size());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Block_size1(){
   // test size()
     Block<String> b1 = new Block<String>(1,2);
  
     if(b1.size()!=0){
       failFmt("Block size:\n"+
          "Expect: +b1.size==0\n" +
          "Actual: b1.size==" + b1.size());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Block_shrink1(){
   // test shrink()
     Block<String> b1 = new Block<String>(1,2);
     b1.grow();
	  b1.shrink();
  
     if(b1.size()!=0){
       failFmt("Block shrink:\n"+
          "Expect: +b1.size==0\n" +
          "Actual: b1.size==" + b1.size());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Block_shrink2(){
   // test shrink()
     Block<String> b1 = new Block<String>(1,2);
     b1.grow();
	  b1.grow();
     b1.shrink();
     b1.shrink();
  
     if(b1.size()!=0){
       failFmt("Block shrink:\n"+
          "Expect: +b1.size==0\n" +
          "Actual: b1.size==" + b1.size());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Block_get_setElement1(){
   // test get/setElement()
     Block<String> b1 = new Block<String>(1,2);
     b1.grow();
	  b1.setElement(0,"A");
     String result = b1.getElement(0);
  
     if(!(result.equals("A"))){
       failFmt("Block setElement:\n"+
          "Expect: +b1.getElement(0)==A\n" +
          "Actual: b1.getElement(0)==" + b1.getElement(0));
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Block_get_setElement2(){
   // test get/setElement()
     Block<String> b1 = new Block<String>(1,2);
     b1.grow();
     b1.grow();
	  b1.setElement(0,"A");
	  b1.setElement(1,"B");
     String result = b1.getElement(1);
  
     if(!(result.equals("B"))){
       failFmt("Block setElement:\n"+
          "Expect: +b1.getElement(1)==B\n" +
          "Actual: b1.getElement(1)==" + b1.getElement(1));
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Block_shrink3(){
   // test shrink() sets last element to null
     Block b1 = new Block(1,2);
     b1.grow();
	  b1.grow();
	  b1.setElement(0,"A");
	  b1.setElement(1,"B");
     b1.shrink(); // set Element in position 1 to null
     b1.shrink(); //set Element in position 0 to null
  
     if(b1.size()!=0){
       failFmt("Block shrink:\n"+
          "Expect: +b1.size==0\n" +
          "Actual: b1.size==" + b1.size());
     }
     if(b1.arrayOfElements[1]!=null){
       failFmt("Block shrink:\n"+
          "Expect: b1.arrayOfElements[1]==null" +
          "Actual: b1.arrayOfElements[1]==" + (b1.arrayOfElements[1]));
     }
     if(b1.arrayOfElements[0]!=null){
       failFmt("Block shrink:\n"+
          "Expect: b1.arrayOfElements[0]==null" +
          "Actual: b1.arrayOfElements[0]==" + (b1.arrayOfElements[0]));
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Block_toString1(){
   // test toString()
     Block<String> b1 = new Block<String>(1,2);
     b1.grow();
	  b1.setElement(0,"A");
     String result = b1.toString().replaceAll("\\s+","");
  
     if(!(result.equals("A"))){
       failFmt("Block toString:\n"+
          "Expect: +b1.toString()==A\n" +
          "Actual: b1.toString()==" + b1.toString());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Block_toString2(){
   // test toString()
   // output expected to be:
   // A B
   // Before we check the output, we stip away the white space, resulting in
   // AB
   // So this ignores  spacing differences between "A B" and "A   B" etc

     Block<String> b1 = new Block<String>(1,2);
     b1.grow();
     b1.grow();
	  b1.setElement(0,"A");
	  b1.setElement(1,"B");
     String result = b1.toString().replaceAll("\\s+",""); // "A B" becomes "AB", so does "A   B"
  
     if(!(result.equals("AB"))){
       failFmt("Block toString:\n"+
          "Expect: +b1.toString()==AB\n" +
          "Actual: b1.toString()==" + b1.toString());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void Block_toStringForDebugging1(){
   // test toStringForDebugging()
   // output expected to be:
   // AB
   // -capacity=2 size=2
   // Before we check the output, we stip away the white space, resulting in
   // AB-capacity=2size=2
   // So this ignores minor spacing differences between expected and actual result
   
     Block<String> b1 = new Block<String>(1,2);
     b1.grow();
     b1.grow();
	  b1.setElement(0,"A");
	  b1.setElement(1,"B");
     String result = b1.toStringForDebugging().replaceAll("\\s+",""); 
     // Strips spaces, for example
     // "A B" becomes "AB", so does "A   B"
  
     if(!(result.equals("AB-capacity=2size=2"))){
       failFmt("Block toStringForDebugging:\n"+
          "Expect: +b1.toStringForDebugging()==AB-capacity=2size=2\n" +
          "Actual: b1.toStringForDebugging()==" + result);
     }
  }

  // DynamicArray_Tests

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void DynamicArray_construct1() {
  // Check construct DynamicArray
     DynamicArray<String> d1 = new DynamicArray<String>();
  
     if(! (d1.sizeOfArrayOfBlocks==1 && d1.size == 0 && d1.numberOfEmptyDataBlocks==1
           && d1.numberOfNonEmptyDataBlocks==0 && d1.numberOfDataBlocks == 1 && d1.indexOfLastNonEmptyDataBlock==-1
           && d1.indexOfLastDataBlock==0 && d1.numberOfSuperBlocks == 1 && d1.lastSuperBlock.getNumber()==0)){
       failFmt("DynamicArray constructor:\n"+
          "Expect: d1.sizeOfArrayOfBlocks==1 d1.size == 0 d1.numberOfEmptyDataBlocks==1 " +
           "d1.numberOfNonEmptyDataBlocks==0  d1.numberOfDataBlocks == 1  d1.indexOfLastNonEmptyDataBlock==-1 " +
           "d1.indexOfLastDataBlock==0  d1.numberOfSuperBlocks == 1  d1.lastSuperBlock.getNumber==0" +
           "Actual: d1.sizeOfArrayOfBlocks== " + d1.sizeOfArrayOfBlocks + " d1.size == " + d1.size + " d1.numberOfEmptyDataBlocks==" +
            d1.numberOfEmptyDataBlocks + " d1.numberOfNonEmptyDataBlocks== " + d1.numberOfNonEmptyDataBlocks + " d1.numberOfDataBlocks== " + d1.numberOfDataBlocks + " d1.indexOfLastNonEmptyDataBlock==" +
            d1.indexOfLastNonEmptyDataBlock + " d1.indexOfLastDataBlock==" + d1.indexOfLastDataBlock + " d1.numberOfSuperBlocks==" + d1.numberOfSuperBlocks + " d1.lastSuperBlock.getNumber()" + 
            d1.lastSuperBlock.getNumber());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void DynamicArray_getBlock1(){
   // test getBlock()
    DynamicArray<String> d1 = new DynamicArray<String>();
    Block b = d1.getBlock(0);

    if(b.getNumber()!=0){
       failFmt("DynamicArray getBlock:\n"+
          "Expect: +b.getNumber==0\n" +
          "Actual: b.getNumber==" + b.getNumber());
     }
 
    if(b.getCapacity()!=1){
       failFmt("DynamicArray getBlock:\n"+
          "Expect: +b.getCapacity==1\n" +
          "Actual: b.getCapacity==" + b.getCapacity());
    }
    if(b.size()!=0){
       failFmt("DynamicArray getBlock:\n"+
          "Expect: +b.size==0n" +
          "Actual: b.size==" + b.size());
    }
  }

 
  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void DynamicArray_size1(){
   // test size()
     DynamicArray<String> d1 = new DynamicArray<String>();
  
     if(d1.size()!=0){
       failFmt("DynamicArray size:\n"+
          "Expect: +d1.size==0\n" +
          "Actual: d1.size==" + d1.size());
     }
  }

  @SuppressWarnings("unchecked")
  @Test(timeout=1000) public void DynamicArray_grow1(){
   // test grow() on empty DynamicArray
     DynamicArray<String> d1 = new DynamicArray<String>();
     d1.grow(); // grow one space in Block0
 
    Block b = d1.getBlock(0);

    if(b.getNumber()!=0){
       failFmt("DynamicArray grow:\n"+
          "Expect: +b.getNumber==0\n" +
          "Actual: b.getNumber==" + b.getNumber());
     }
 
    if(b.getCapacity()!=1){
       failFmt("DynamicArray grow:\n"+
          "Expect: +b.getCapacity==1\n" +
          "Actual: b.getCapacity==" + b.getCapacity());
    }
    if(b.size()!=1){
       failFmt("DynamicArray grow:\n"+
          "Expect: +b.size==1n" +
          "Actual: b.size==" + b.size());
    }
  }

}

