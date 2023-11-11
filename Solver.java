import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Solver {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("List Dimensions: length height");
	
		int l = sc.nextInt();
		int h = sc.nextInt();
		

		String [][] grid = new String[l][h];

		System.out.println("Please Enter Node Identity from left to right, top to bottom, with spaces.  Mark empty spaces with a period, '.'");
		
		sc.nextLine();
		String [] input = sc.nextLine().split(" ");
		ArrayList <String> refinedInput = new ArrayList<String>(Arrays.asList(input));

		while(refinedInput.contains(".")) refinedInput.remove(".");

		try {
			verify(input,refinedInput,l,h);
			
			populateGrid(grid, input);
			displayGrid(grid);

		} catch (Exception e){
		}

	}

	public static void displayGrid(String[][] grid){
		for(int x = 0; x < grid.length; x++){
			for(int y = 0; y < grid[0].length; y++){
				System.out.print(" " + grid[x][y] + " ");
			}
			System.out.println();
		}
	}

	public static void populateGrid(String[][] grid, String[] input){
		int counter = 0;

		for(int x = 0; x < grid.length; x++){
			for(int y = 0; y<grid[0].length; y++){
				String temp = input[counter++];
				if(!temp.equals(".")) grid[x][y] = temp;	
			}
		}	 
	}

	public static void verify(String[] in, ArrayList<String> refinedInput,int l, int h) throws Exception{
		
		if (!(matchPairTest(refinedInput) && sizeTest(in, refinedInput,l,h))) throw new Exception("Grid Generation Error");
	}	

	public static boolean sizeTest(String[] in, ArrayList<String> refinedInput, int l, int h){
		//Check if Grid is filled
		if(l*h!=in.length){ 
			System.out.println("Improper number of nodes.");
			return false;
		}

		//Check if odd number of nodes
		int inSize = refinedInput.size();
		if(inSize%2!=0) { 
			System.out.println("Odd number of nodes");
			return false;
		}

		//Check if node limit is exceeded for grid size
		int numberOfNodes = inSize/2;
		if(Math.floor(l*h/2)<inSize) {
			System.out.println("Too many nodes"); 
			return false;
		}
		

		return true;
	}

	public static boolean matchPairTest(ArrayList<String> in){

		while(in.size()!=0){
			String temp = in.get(0);
			in.remove(temp);
			if(!in.remove(temp)){ 
				System.out.println("Irregular Pairing");
				return false;
			}

			if(in.remove(temp)){
				System.out.println("Exceed Pair Limit");
				return false;
			}
		}

		return true;	
	}

}

