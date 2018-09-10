package project3;

import java.util.ArrayList;

public class WayFinder{
	/**
	 * Initialize some of the variables;
	 */
	static String[] input;
	static int count;
	static boolean[] visited;
	static ArrayList<String[]> solution;

	/**
	 * The main function: This includes input validation, before passing the input into the recursive step;
	 * The function also includes the "wrap-up" output.
	 * @param args command line arguments of the puzzle.
	 */
	public static void main (String [] args) {
		count = 0;
		solution = new ArrayList<String[]>();
		input = new String[args.length];
		visited = new boolean[input.length];
		for (int i = 0; i < args.length; i++) {
			if (Integer.parseInt(args[i]) < 0) {
				System.out.println("ERROR: the puzzle values have to be positive integers.");
				return;
			}
			if (Integer.parseInt(args[i]) > 99) {
				System.out.println("ERROR: the puzzle values have to be smaller than 100.");
				return;
			}
			if (i == args.length - 1 && !args[i].equals("0")) {
				System.out.println("ERROR: the puzzle must end with value 0");
				return;
			}
			input[i] = args[i];
		}
		stepSolver(0);
		if (count == 0) {
			System.out.println("No way through the puzzle.");
			return;
		}
		System.out.print("There are " + Integer.toString(count) + " ways through the puzzle.\n");
	}

	
	 
	

	/**
	 * The main recursive function that is used to solve the puzzle;
	 * The base case is when the pointer reaches the final step, print out the solution;
	 * The algorithm includes: trying each step with a further right and left recursive step, and clean up if 
	 * the recursive step did not reach the base case;
	 * @param pointer the step we are currently at; To initialize any puzzle, the first input is 0;
	 */
	public static void stepSolver(int pointer){
		if (pointer == input.length - 1) {
			printSolution();
			count++;
			return;
		}
		int rightPointer = pointer + Integer.parseInt(input[pointer]);
		int leftPointer = pointer - Integer.parseInt(input[pointer]);
		if (leftPointer > 0 && leftPointer < input.length) {
			if (!visited[leftPointer]) {
				visited[leftPointer] = true;
				String[] temp = input.clone();
				temp[pointer] += "L";
				solution.add(temp);
				stepSolver(leftPointer);
				visited[leftPointer] = false;
				solution.remove(solution.size()-1);
			}
		}
		if (rightPointer > 0 && rightPointer < input.length) {
			if (!visited[rightPointer]) {
				visited[rightPointer] = true;
				String[] temp = input.clone();
				temp[pointer] += "R";
				solution.add(temp);
				stepSolver(rightPointer);
				visited[rightPointer] = false;
				solution.remove(solution.size()-1);
			}
		}
		return;
	}
	
	/**
	 * This method is used to determine whether the output consists of pure numbers;
	 * This method is only used within the printSolution function, so that the alignment could be correctly adjusted.
	 * @param str the String we are going to test
	 * @return true if the String consists of pure numbers; false if not;
	 */
	public static boolean isNumber(String str){
		for (int i = 0; i < str.length(); i++){
			if (!Character.isDigit(str.charAt(i))){
				return false;
				}
			}
		return true;
	}
	
	/**
	 * This method is applied to print out a solution, if the stepSolve function finds one.
	 * It produces a String, with alignments and commas already set, and print a line of that string.
	 * It also includes the brackets
	 */
	public static void printSolution() {
		for (int i = 0; i < solution.size(); i++) {
			String output = "[";
			for (int j = 0; j < solution.get(i).length; j++) {
				if (j != 0) {
					output += " ";
				}
				if (isNumber(solution.get(i)[j])) {
					if (Integer.parseInt(solution.get(i)[j]) < 10) {
						output += " ";
					}
					output += solution.get(i)[j];
					output += " ,";
				}
				else{
					String temp = solution.get(i)[j].substring(0,solution.get(i)[j].length()-1);
					if (Integer.parseInt(temp) < 10) {
						output += " ";
					}
					output += temp;
					output +=  solution.get(i)[j].charAt(solution.get(i)[j].length()-1) + ",";
				}
			}
			output = output.substring(0,output.length()-1);
			output += "]";
			System.out.println(output);
		}
		System.out.println();
	}
}
		
	