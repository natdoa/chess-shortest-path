import java.util.Arrays;

public class Solution {

	private static final int LIMIT = 1000000;

	public static void main(String[] args) {
		int input[][] = {	//Example input
				{0,0,0},
				{0,0,1},
				{1,0,0},
				{0,0,0}
		};  
		System.out.println(Solution(input));
	}

	public static int Solution(int[][] chessboardIn) {		
		if(chessboardIn.length<1||chessboardIn.length>LIMIT){
			System.err.println("Dimension size error");
			System.exit(0);
		}
		/*Predefined {X,Y} movements. {2,1},{1,2} are closer to start
		because it is more likely for those grids to be free.*/ 
		int[] movementY = {2, 1, -2, -2, -1, -1,  1,  2 };
		int[] movementX = {1, 2, -1,  1, -2,  2, -2, -1 };
		int x_axis=chessboardIn.length;
		int y_axis=0;
		
		for(int j:chessboardIn[0]){
			y_axis++;
		}
		if(y_axis<1||y_axis>LIMIT){
			System.err.println("Dimension size error");
			System.exit(0);
		}
		
		if(chessboardIn[x_axis-1][y_axis-1]==1){
			System.err.println("Destination blocked");
			System.exit(0);
		}

		/*I copy the input for myself because I need to count the steps and
		if the input has 0 or 1, it becomes problematic for me.*/
		int chessboard[][]=new int[x_axis][y_axis];
		for(int k=0;k<x_axis;k++){
			for(int l=0;l<y_axis;l++){
				if(chessboardIn[k][l]==0)chessboard[k][l]=-1;
				if(chessboardIn[k][l]==1)chessboard[k][l]=-2;
			}
		}
		//starting grid and step count should be 0 in my algorithm.
		chessboard[0][0] = 0;
		int step = 0;	
		boolean finished=false;
		
		/*the algorithm I thought of iterates through the elements and
		checks the step number. If it is the highest, it continues
		iterating from that grid.
		When the right-bottom corner is not -1 anymore, that means 
		the result has been found.*/
		while (true) {
			finished=true;
			for (int x = 0; x < x_axis; x++) {
				for (int y = 0; y < y_axis; y++) {
					if (chessboard[x][y] == step) {
						for (int k = 0; k < 8; k++) {
							int tempx = movementX[k] + x, tempy = movementY[k] + y;
							if (tempx >= 0 && tempx < x_axis && tempy >= 0 && tempy < y_axis) {
								if (chessboard[tempx][tempy] == -1) {									
									chessboard[tempx][tempy] = step + 1;
									//printChessboard(chessboard);
									finished=false;
									if(chessboard[x_axis-1][y_axis-1] != -1){
										return chessboard[x_axis-1][y_axis-1];
									}
								}
							}
						}
					}
				}
			}
			step++;
			if(finished){
				return 0;
			}
		}
	}

	public static void printChessboard(int[][] arrIn){
		for (int[] arr : arrIn) {
			System.out.println(Arrays.toString(arr));
		}
		System.out.println("--------------");
	}
}