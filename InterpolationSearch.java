// Name: Noah Clark
// Class: CS 4306/4
// Term: Fall 2022
// Instructor: Dr. Haddad
// Assignment: 2

// Algorithm Design Block:
/* Problem Statement:
 * There is a sorted array of size 1024 and we are asked to find the index for the provided key (value) if it exists within the array.
 * To achieve this while also maintaining a time complexity of log(log(n)), we need to develop a decrease and conquer algorithm.  
 * Therefore, our algorithm must mimic the way we search for a name in a telephone book.
*/

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

/* Algorithm Pseudo-code Syntax:
 * 
 * interpolationSearchMethod returns void given integer-array Values and integer key
 * {
 * 		//Creating necessary variables for tracking the current indicies when splitting the array
 * 		leftMost = 0
 * 		rightMost = length of array Values
 * 
 * 		//While loop with conditions that ensure it runs only if it is possible that the key exists in the array.
 * 		while (leftMost is less than rightMost) and (Values[leftMost] < Values[position]) and (Values[position] < Values[rightMost]):
 * 			//Setting index for position which will either find the key or be the point by which the array is split.
 * 			position = leftMost + ((key-values[leftMost]) * (rightMost - leftMost))/(Values[rightMost]-Values[leftMost])
 * 
 * 			IF (Values[position] is equal to the key):
 * 				//Updates the variables within class interpolationSearch
 * 				Index = position
 * 				Found = true
 * 			else:
 * 				//Divides the array either above or below the current position depending on the value of the key
 * 				Division++
 * 				if (Value[position] is less than the key):
 * 					leftMost = position+1
 * 				else if (Value[position] is greater than the key):
 * 					rightMost = position-1
 * }
*/

// Implementation Section:
// This class named implements the required variables and methods to execute interpolation search.
// It uses three main variables: Boolean found, int Index, and int Divisions.
// These three variables are used to indicate the number of divisions, if the key was found, and at what index the key is located.
// The class then uses a constructor which takes in an array and key. The constructor defaults to setting the variables to False, -1, and 0.
// This constructor calls the interpolationSearchMethod discussed above and passes the required variables.
// This method then updates the three variables declared within the class as needed.
// Lastly, the class implements a getter for each of the primary variables since they are declared as private within the class.
//

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
