package test;

import java.util.ArrayList;

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
		
		int[] mat1D = {9,5,6,0,0,0,0,0,2,3,4,0,9,8,0,0,6,6,6,0,0,0,0,7,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		
	
		int[][] mat2 = Zig_Zag(8);
		
		//System.out.println(numberOfZeros(mat1D, 10));

		//System.out.println(indexOfEOB(mat1D));

		//print2d(mat2);
		//System.out.println();
		//zigzag(mat2);
		
		//System.out.println(listAC(mat1D).toString());
		
		ArrayList<int[]> listAC = listAC(mat1D);
		
		for(int[] temp : listAC){
			
			for (int i = 0; i < temp.length; i++) {
				System.out.print(temp[i]+" ");
			}
			System.out.println();
			
		}
		
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
	
	//https://rosettacode.org/wiki/Zig-zag_matrix (a été modifier pour print plutot que remplir)
	//Print un array en zigzag, array 2d doit etre symétrique
	public static void  Zig_Zag2(int[][] data) {
		
		int i = 1;
		int j = 1;
		for (int element = 0; element < data.length * data.length; element++) {
			
			System.out.print(data[i-1][j-1] + " ");
			
			if ((i + j) % 2 == 0) {
				// Even stripes
				if (j < data.length)
					j++;
				else
					i += 2;
				if (i > 1)
					i--;
			} else {
				// Odd stripes
				if (i < data.length)
					i++;
				else
					j += 2;
				if (j > 1)
					j--;
			}
		}

	}
	
	public static int[] Zig_ZagtoArray(int[][] data) {
		
		int[] mat = new int [data.length*data.length];
		
		int i = 1;
		int j = 1;
		for (int element = 0; element < data.length * data.length; element++) {
			
			mat[element] = data[i-1][j-1];
			
			if ((i + j) % 2 == 0) {
				// Even stripes
				if (j < data.length)
					j++;
				else
					i += 2;
				if (i > 1)
					i--;
			} else {
				// Odd stripes
				if (i < data.length)
					i++;
				else
					j += 2;
				if (j > 1)
					j--;
			}
		}
		
		return mat;

	}


	
	public static void print2d(int[][] mat){
		
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				
				System.out.print(mat[i][j]+ " ");
				
			}
			System.out.println();
		}
		
	}
	
	public static void print1D(int[] mat) {
		for (int i = 0; i < mat.length; i++) {
			System.out.print(mat[i]+" ");
		}
	}
	
	//return the EOB de l'array
	public static int indexOfEOB(int[] mat){
		
		int index = -1;
		
		for (int i = mat.length-1; i >= 0; i--) {
			
			if (mat[i]!=0) {
				
				index=i;
				index=index+1;
				break;
				
			}
			
		}
		
		return index;
		
	}
	
	public static ArrayList<int[]> listAC (int[] mat){
		
		ArrayList<int[]> listAc = new ArrayList<>();
		
		
		
		int indexEOB = indexOfEOB(mat);
		
		//System.out.println(indexEOB);
		//System.out.println(mat.length);
		
		for (int i = 1; i < indexEOB ; i++) {
			
			if (mat[i]!=0) {
				
				int[] arrayAc = {0,mat[i]};
				listAc.add(arrayAc);
				

			}else{
				
				int[] arrayAC2 = new int [2];
				arrayAC2[0] = numberOfZeros(mat, i);
				arrayAC2[1] = mat[i+arrayAC2[0]];
				i=i+numberOfZeros(mat, i);
				listAc.add(arrayAC2);

			}
			
			
		}
		
		if (indexEOB != mat.length) {
			
			int[] arrayEOB = {0,0};
			listAc.add(arrayEOB);
			
		}
		
		return listAc;
		
		
	}
	
	public static int numberOfZeros(int[] mat,int index){
		
		int numZero = 0;
		
		for (int i = index; i < mat.length; i++) {
			
			if(mat[i]!=0){
				
				break;
				
			}
			
			numZero++;
			
		}
		
		return numZero;
		
	}

}
