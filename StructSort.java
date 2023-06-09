// Hrag Bankian (expand comments for answers to theory questions)
// 40245363
// COMP 352 - Assignment 2
// June 2, 2023

/*

Q2) Theory: Qualify This Structuring
How did the structuring pass you performed, specifically the reversals chosen, affect swaps and comparisons?
Was anything else affected? Answer in less than 100 words.

The structuring pass reduced the number of swaps, but increased the number of comparisons. With the given inputs, 
implementing the structuring pass gave 48 swaps and 72 compares overall. Without it, we would have 54 swaps and 
65 compares. The structuring pass reduced the number of swaps by 6, but increased the number of comparisons by 7. 
Considering that swaps are more expensive than comparisons, it is fair to assume that 6 swaps are more expensive 
than 7 comparisons, which means that the structuring pass increased the overall efficiency of the program. The 
structuring pass "eased" the insertion sort's job.


Q3) Theory: Size of Runs
How do you feel the size of the specific runs you recorded (ASCENDING order of length 4) impacted
performance? Answer in less than 100 words.

Considering we wanted to sort numbers in descending order, ascending runs are going to worsen performance because 
they are opposite to what we are looking for. The more ascending runs we have, the more swaps will be required. 
The higher the length of those ascending runs, the more swaps will be required. More swaps means worse performance. 
This means that an ascending run of length 4 is worse for performance than an ascending run with a length lower than 
4, but it is better for performance than an ascending run with a length higher than 4. 


Q4) Theory: Doubly Linked Lists
What would implementing this as a Doubly Linked List do? How would the specified structuring affect
results? Answer in less than 100 words.

Overall, an array offers a better performance than a Linked List, but the difference in performance would be slim. This 
is because to access the nth element, with a Linked List, the time complexity would be O(n), while with an array, it 
would be O(1). However, this difference in time complexity balances out when considering that inserting an element in a 
Linked List has a time complexity of O(1), while in an array, it has a time complexity of O(n).The structuring would "ease" 
the insertion sort's job, but implementing the insertion sort without the structuring would be overall more efficient.

*/

package ca.concordia.comp352;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StructSort {

	public static void main(String[] args) {
	
		Scanner sc = null;
		int size = 0;

		try {
			sc = new Scanner(new FileInputStream(args[0]));
		}
		catch (FileNotFoundException e){
			System.out.println("File not found!");
		}
		
		while (sc.hasNextInt()) {
			sc.nextInt();
			size++;
		}
		
		sc.close();
		
		try {
			sc = new Scanner(new FileInputStream(args[0]));
		}
		catch (FileNotFoundException e){
			System.out.println("File not found!");
		}
		
		int[] array = new int[size];
		
		for (int i = 0; i < size; i++) {
			array[i] = sc.nextInt();
		}
		
		sc.close();
		
		for (int i = 0; i < size; i++) {
			System.out.print(array[i] + " ");
		}
		
		int length4 = 0;
		int reversals = 0;
		int structuringCompares = -1;
		int overallCompares = 0;
		int swaps = 0;
		for (int i = 0; i < size; i++) {
			structuringCompares++;
			int length = 1;
			boolean ascending = false;
			int tempIndex = 0;
			int tempLength = 0;
			if (i == 0 || array[i] > array[i-1]) {
				while (i < size-1 && array[i] < array[i+1]) {
					structuringCompares++;
					ascending = true;
					length++;
					i++;
				}
				if (length == 4) {
					length4++;
				}
				tempLength = length;
				tempIndex = i;
			}
			if (ascending) {
				reversals++;
				int tempValue = 0;
					for (int j = 0; j < length/2; j++) {
						tempValue = array[i-tempLength+1];
						array[i-tempLength+1] = array[tempIndex];
						array[tempIndex] = tempValue;
						tempLength--;
						tempIndex--;
						swaps++;
					}
			}
		}
		
		
		for (int i = 1; i < size; i++) {
			int tempValue = array[i];
			int j = i-1;
			while (j >= 0 && array[j] < tempValue) {
				overallCompares++;
				swaps++;
				array[j+1] = array[j];
				j--;
			}
			if (j >= 0 && array[j] > tempValue)
				overallCompares++;
			array[j+1] = tempValue;
		}
		
		overallCompares += structuringCompares;
		
		System.out.println("\nWe sorted in DEC order");
		System.out.println("We counted " + length4 + " INCR run of length 4");
		System.out.println("We performed " + reversals + " reversals of runs in INCR order");
		System.out.println("We performed " + structuringCompares + " compares during structuring");
		System.out.println("We performed " + overallCompares + " compares overall");
		System.out.println("We performed " + swaps + " swaps overall");
		for (int i = 0; i < size ; i++) {
			System.out.print(array[i] + " ");
		}
		
	}
}