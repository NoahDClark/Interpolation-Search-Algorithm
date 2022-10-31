
/* Algorithm Logical Steps:
 * Given the array Values and the integer Key:
 * 		1. Create two integers to track the lowest (left most) and highest (right most) value/index. Use these to define the current "size" of the sorted array. Also initialize middle index called position 
 * 		2. Use the equation: L + ((key-A[L]) * (R - L))/(A[R]-A[L]) to find the next position index, which should be in between lowest and highest value. 
 * 		3. If the key equals the value of the current position index, return it. 
 * 			Otherwise depending on the value of the key relative to the position index, divide the array by setting the left most index to position+1 OR right most index to position -1.
 * 		4. After the splitting the array, repeat steps 2 and 3 until either the key is found or the left most is greater than or equal to right most index. (This indicates that the key is not in the array)
 * 
 * Note: for the equation, L = left most index, R = right most index, A = array values, and key = key.
*/

//Creates class
public class InterpolationSearch {
	//Three primary variables used to indicate if it's been found, what index it was found at (if possible; otherwise -1), and how many divisions it took to determine this
	private boolean Found;
	private int Index;
	private int Divisions;
	
	//Constructor which takes in a sorted integer array and an integer key. These
	InterpolationSearch(int[] array, int key){
		//Default values if the search method finds nothing and/or does not execute.
		Found = false;
		Index = -1;
		Divisions = 0;
		interpolationSearchMethod(array, key); //Calls the search method
	}
	
	//Implementation of the interpolation search method
	public void interpolationSearchMethod(int[] Values, int key){
		//Creates three variables; Two are used to track the boundaries of the array (left and right most). Position is used to track the point by which it will divide or find the key.
		int position;
		int leftMost = 0;
		int rightMost = Values.length-1;
		
		//While loop with conditions that ensure that it will stop when it is no longer possible for the key to exist within the array (In other words, when the search is complete it stops).
		while ((leftMost <= rightMost) && (Values[leftMost] <= key) && (key <= Values[rightMost])) {
			//Position index determining it's value through the interpolation equation
			position = leftMost + ( ((key-Values[leftMost])*(rightMost-leftMost))/(Values[rightMost]-Values[leftMost]) );
			
			//Checks if [position] equals key. If so, set the appropriate class variables and stop the while loop (return).
			if (Values[position] == key) {
				Found = true;
				Index = position;
				return;
			//Else, divide the array relative to the position value. If key < [position], left most increases. If key > [position]
			} else {
				Divisions++; //Increases counter for division
				if (Values[position] < key) {
					leftMost = position+1;
				} else {
					rightMost = position-1;
				}
			}
		}
		
	}
	
	//method to get private boolean Found
	public boolean getFound() {
		return Found;
	}
	
	//method to get private int Index
	public int getIndex() {
		return Index;
	}
	
	//method to get private int Divisions
	public int getDivisions() {
		return Divisions;
	}
}
