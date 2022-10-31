// Name: Noah Clark
// Class: CS 4306/4
// Term: Fall 2022
// Instructor: Dr. Haddad
// Assignment: 2

//!!!!NOTE!!!!: I'm not sure if the algorithm design block was required for this java file but I created it anyways. Please ignore it if it is not required. 

// Algorithm Design Block:
/* Problem Statement:
 * We are given the class interpolationSearch and need to provide it with the proper inputs. Additionally, we need to create a menu for the user to operate.
 * To achieve this, we need to create a method that will return a sorted integer array (size 1024) of random numbers.
 * Therefore, our algorithm must sort the array before passing it to the InterpolationSearch Class.
 * Additionally, we need to establish a method that will print a working menu for the user.
*/

/* Algorithm Logical Steps:
 * (For RandomDistinct)
 * 1. Initialize the integer array Values with size 1024 and iterate throughout, adding a random number between 1 and 9999 if it is not a duplicate
 * 2. Sort the array from least to greatest
 * 3. Return Values
 * 
 * (For RunIS)
 * 1. Use a do-while and try-catch statement to print the menu
 * 2. Scan for user input, call the appropriate methods, and output the desired information
 * 3. Repeat or exit program when user enters 4
*/

/* Algorithm Pseudo-code Syntax:
 * 
 * RandomDistinct returns integer-array given nothing{
 *		//Create integer array of size 1024
 * 		integer Values[1024]
 * 		integer randomNumber = number from 1 to 9999
 * 
 * 		while currentIndex less than length of Values:
 * 			//Either a for loop or another method for checking duplicate numbers should be inserted here
 * 			if randomNumber is not in the array:
 * 				Values[currentIndex] = randomNumber
 * 				currentIndex = currentIndex+1
 * 				
 * 		
 * 		//This is bubble sort
 * 		while array remains unsorted:
 * 			for i to the length of Values:
 * 				if [i] is greater than [i+1]:
 * 					swap i and i+1
 * 		
 * }
 * 
 * RunIS returns nothing given nothing{
 * 
 * 		while user has not entered 4 (quit):
 * 			print the user menu
 * 			scan for options 1, 2, 3, or 4
 * 			switch statement:
 * 				option 1:
 * 					Values = randomDistinct()
 * 					print(Values) //Only print 30 values per line
 * 				option 2:
 * 					ask user for table size 
 * 					scan user input
 * 				option 3:
 * 					print table 
 * 					create random key
 * 					create new InterpolationSearch object (given Values, key)
 * 					print results and repeat for length of table size
 * 				option 4:
 * 					quit program
 * }
*/

// Implementation Section:
// This class implements the main method and other methods used to call the interpolation search created in the other class.
// The code starts by executing RunIS() in the main method. This executes the menu for the user to navigate. Here are the menu options explained:
// 		Option 1 of the menu will call the method RandomDistinct() and output the values array with 30 of them on each line.
// 		Option 2 will ask the user for a tableSize and set the tableSize variable.
// 		Option 3 will execute the interpolation search code ONLY IF option 1 and 2 have run first.
// 		Option 4 Closes the program
// The entire menu is enclosed within a while loop and try/catch statement to ensure a valid input from the user.
// In addition to the code for printing the menu, this class includes the method RandomDistinct() which creates an array of size 1024 with random numbers from 1 to 9999.
// After creating the random array, it is then sorted.

import java.util.*;

//TestIS class
public class TestIS {

	//Main method
	public static void main(String[] args) {
		//Executes the RunIS method
		RunIS();
	}

	//Method for obtaining a random distinct integer
	public static int[] RandomDistinct() {
		//Initializing random, integer array, and int currentIndex (used for inserting values into the array)
		Random rand = new Random();
		int[] Values = new int[1024];
		int currentIndex = 0;
		
		//While loop to fill array Values with random integers
		while (currentIndex < 1024) {
			//Generates a random integer from 1 to 9999
			int randomValue = rand.nextInt(9999)+1; //Needs the +1 to change the range from 0-9998 to 1-9999
			
			//For loop to check for duplicates
			boolean duplicate = false;
			for (int value : Values) {
				if (value == randomValue) {
					duplicate = true;
				}
			}
			//If duplicates not found, add randomValue to Values at the currentIndex. 
			if(!duplicate) {
				Values[currentIndex] = randomValue;
				currentIndex = currentIndex + 1; //Increments the currentIndex, allowing for next index to be filled.
			}
		}
		
		
		//While loop and boolean used to sort array Values
		boolean unsorted = true;
		while(unsorted) {
			unsorted = false;
			//For the length of the array, if index i is greater than i+1 swap them. While loop will continue this until no more swaps are required (in other words, it is sorted).
			//Small note: I know this is not the most efficient sorting algorithm but I chose it to minimize the complexity so it is more readable.
			for (int i =0; i < Values.length-1; i++) {
				if(Values[i] > Values[i+1]) {
					int temp = Values[i];
					Values[i] = Values[i+1];
					Values[i+1] = temp;
					unsorted = true;
				}
			}
		} //End of while loop
		
		//return integer array Values
		return Values;
	}
	
	//RunIS method, provides the menus and calls the methods which will execute the interpolation search.
	public static void RunIS() {
		//Creates scanner, random, and some of the variables used in the program.
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		boolean exit = false;
		int[] Values = new int[0]; //Set to 0 so that it will not execute if the user fails to initialize the array.
		int userInput;
		int tableSize = -1; //Set to -1 so that it will not execute if the user fails to set a size.
		
		//do while, continues until user enters 4
		do {
			//Try catch statement for if the user inputs an invalid character
			try {
				//Prints menu
				System.out.println("---------------MAIN MENU---------------\n"
						+ "1. Create and display array Values[]\n"
						+ "2. Read output table size\n"
						+ "3. Run algorithm and display outputs\n"
						+ "4. Exit program");
				System.out.println();
				System.out.print("Enter option number: ");
				userInput = scan.nextInt();
				scan.nextLine();
				
				//Switch statement for user input
				switch(userInput) {
				
				//Option 1: Creates and displays array values by calling RandomDistinct and formatting it to 30 values per line
				case 1:
					Values = RandomDistinct();
					for (int i = 0; i < Values.length; i++) {
						//System.out.format("%-6d", Values[i]);
						System.out.print(Values[i] + "\t"); //As per the assignment instructions, "use tabs to separate values". Old format above.
						if(i%30 == 29) {
							System.out.println();
						}
					}
					System.out.println();
					break;
					
				//Option 2: Asks the user for a table size and scans it.
				case 2:
					System.out.print("Enter table size: ");
					tableSize = scan.nextInt();
					scan.nextLine();
					break;
				
				//Option 3: Runs the interpolation relative to the value given by the user for tableSize.
				case 3:
					//If statement to ensure the user initializes array and provides a table size.
					if((tableSize > 0) && (Values.length > 0)) {
						
						//Prints the table labels "Key", "Found", and so on.
						System.out.printf("%-9s %-9s %-9s %-9s ", "  Key", "Found", "Index", "Divisions");
						System.out.println();
						System.out.println("-------------------------------------------");
						
						int totalDivisions = 0; //Tracks total number of divisions, used for calculations at the end
						
						//For loop which executes a new interpolation search on a new key. Does this for as many times as the user set in table size.
						for (int i = 0; i < tableSize; i++) {
							int key = rand.nextInt(9998)+1; //New key generated here
							InterpolationSearch searchMethod = new InterpolationSearch(Values, key); //Interpolation search executed here
							totalDivisions = totalDivisions + searchMethod.getDivisions(); //Adds current divisions to total number of divisions
							System.out.printf("%-9s %-10s %-8s %4s ","  "+key, (searchMethod.getFound() ? "True" : "False"),
									searchMethod.getIndex(), searchMethod.getDivisions()); //Print statement for each new InterpolationSearch object and its results
							System.out.println();
						}
						
						//Calculations for the average divisions and difference
						double avg = (double)totalDivisions/(double)tableSize;
						System.out.println();
						System.out.println("Divisions average: \t"+avg);
						System.out.println("Difference: \t\t" + (3.332-avg));
						
					} else if (Values.length <= 0) {
						//elif statement for when user does not create an array
						System.out.println("Please initialize the array values before running the algorithm.");
					} else {
						//else statement for when user does not set a table size
						System.out.println("Please select a table size greater than 0 before running the algorithm.");
					}
					break;
					
					//Option 4: closes the program by setting boolean exit to true
				case 4:
					exit = true;
					break;
					
					//Default option for when the user does not provide a value number
				default:
					System.out.println("Please input a valid number.");
					break;
				}
				System.out.println();
				
				//End of try
			} catch (Exception e) {
				//Exception statement for when the user provides an invalid input
				System.out.println("Error! Please input a valid character");
				System.out.println();
				scan.nextLine();
			} //End of catch 
			
		} while(!exit);
		//End of while
		scan.close();
	}
}
