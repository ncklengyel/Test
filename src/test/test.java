package test;

public class test {

	public static void main(String[] args) {
		
		int[][] mat = 
					{{1,2,6,7,15,16,28,29},
					 {3,5,8,14,17,27,30,175},
					 {4,9,13,18,26,31,185,175},
					 {10,12,19,25,32,187,187,187},
					 {11,20,24,33,195,188,187,175},
					 {21,23,34,200,200,190,187,175},
					 {22,35,199,200,191,187,187,175},
					 {36,200,200,200,188,185,187,186}};
		
	
		int[][] mat2 = Zig_Zag(8);
		
		print2d(mat2);
		
		
	}
	
	//https://rosettacode.org/wiki/Zig-zag_matrix
	//Rempli une matrice 2d symétrique en zizag partant de 0 a int size
	public static int[][] Zig_Zag(final int size) {
		int[][] data = new int[size][size];
		int i = 1;
		int j = 1;
		for (int element = 0; element < size * size; element++) {
			data[i - 1][j - 1] = element;
			if ((i + j) % 2 == 0) {
				// Even stripes
				if (j < size)
					j++;
				else
					i += 2;
				if (i > 1)
					i--;
			} else {
				// Odd stripes
				if (i < size)
					i++;
				else
					j += 2;
				if (j > 1)
					j--;
			}
		}
		return data;
	}

	public static void diagDown(int mat[][], int position){
		
		int j = position;
		
		for (int i = 0; i < position+1; i++) {
				
				System.out.print(mat[i][j]+" ");
				j=j-1;
			
		}
		
		
	}
	
	public static void diagUp(int mat[][], int position) {

		if (position % 2 == 0) {

			int j = 0;

			for (int i = position; i >= 0; i--) {

				System.out.print(mat[i][j] + " ");
				j = j + 1;

			}

		}else{
			
			int j = position;
			
			for (int i = 0; i < mat.length-position; i++) {
				
				System.out.print(mat[j][i] + " ");
				j = j - 1;
			}
			
		}

	}
	
	public static void print2d(int[][] mat){
		
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				
				System.out.print(mat[i][j]+ " ");
				
			}
			System.out.println();
		}
		
	}

}
