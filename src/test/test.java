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
		
	

		int[][][] image = PPMReaderWriter.readPPMFile("/Users/am37580/lena.ppm");
		
		int height = image[0].length;
		int width = image[0][0].length;
		
		image = rgbToYUV(image);
		
		ArrayList<int[][]> list8x8 = getListEightByEight(image);
		
		image = getImageFromList8x8(list8x8, height, width);
		
		image = yuvToRGB(image);
		
		PPMReaderWriter.writePPMFile("/Users/am37580/test.jpg", image);
		
	}
	

	
	//https://rosettacode.org/wiki/Zig-zag_matrix
	//Rempli une matrice 2d symétrique en zizag partant de 0 a int size
	public static int[][] Zig_Zag(int size) {
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
	//Print un array2D en zigzag, array 2d doit etre symétrique
	public static void  printZig_Zag(int[][] data) {
		
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
	
	/*
	 * rempli un array 1D en zigzagant la matrice 2D
	 * ensuite returne le array 1D
	 * 
	 * Grandement inspiré de cette source
	 * source: https://rosettacode.org/wiki/Zig-zag_matrix
	 */
	public static int[] zig_ZagMatrixToArray(int[][] data) {
		
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
	

	
	//pas tester encore
	public static boolean isArrayIdentical(int[] mat1, int[] mat2){
		
		if (mat1.length!=mat2.length) {
			return false;
		}
		
		boolean identical = true;
		
		for (int i = 0; i < mat1.length; i++) {
			if (mat1[i]!=mat2[i]) {
				identical = false;
				break;
			}
		}
		
		return identical;
		
	}
	
	/**
	 * 
	 * Classe qui permet de returner une matrice 2d 8X8 d'une image en array3D.
	 * Les point de depart commence en haut a gauche. Par exemple, si les
	 * offsetV et offetH == 0. l'array 2d sera créé à partir de ce point, il
	 * parcourera 8 element horizontal et 8 element vertical de l'array3D et
	 * retournera les valeurs dans un array 2D.
	 * 
	 * @param imageMatrix
	 *            L'array 3D de l'image
	 * @param offsetV
	 *            Le point de départ vertical
	 * @param offsetH
	 *            Le point de départ horizontal
	 * @param color
	 *            La couleur sur laquelle en veut effectuer notre array 2D (0 =
	 *            R ou Y, 1 = G ou U,...)
	 * @return
	 */
	public static int[][] getEightByEight(int[][][] imageMatrix, int offsetV, int offsetH, int color) {

		int lenght = 8;

		int[][] eightByEghtMatrix = new int[lenght][lenght];

		for (int i = 0; i < lenght; i++) {

			for (int j = 0; j < lenght; j++) {

				eightByEghtMatrix[i][j] = imageMatrix[color][offsetV + i][offsetH + j];

			}

		}

		return eightByEghtMatrix;

	}

	public static ArrayList<int[][]> getListEightByEight(int[][][] imageMatrix) {

		ArrayList<int[][]> list2Dmatrix = new ArrayList<>();

		for (int i = 0; i < imageMatrix.length; i++) {

			for (int j = 0; j < imageMatrix[0].length; j = j + 8) {

				for (int k = 0; k < imageMatrix[0][0].length; k = k + 8) {

					list2Dmatrix.add(getEightByEight(imageMatrix, j, k, i));

				}
			}
		}

		return list2Dmatrix;

	}
	
	public static int[][][] getImageFromList8x8(ArrayList<int[][]> list8x8, int height, int width) {

		int[][][] image = new int [3][height][width];
		
		for (int i = 0; i < list8x8.size()-1; i++) {
			
			if (i<=(list8x8.size()/3)-1) {
				
				for (int j = 0; j < height/8; j=j+8) {
					for (int k = 0; k < width/8; k=k+8) {
						
						System.out.println(list8x8.get(i).length);
						System.out.println(i);
						System.out.println(j+" "+k);
						putEightByEight(list8x8.get(i), height, width, 0, image);
						
						
					}
				}
				
			}else if (i>(list8x8.size()/3)-1 && i<=(list8x8.size()/3*2)-1) {
				
				for (int j = 0; j < height/8; j=j+8) {
					for (int k = 0; k < width/8; k=k+8) {
						
						putEightByEight(list8x8.get(i), height, width, 1, image);
						
					}
				}
				
			}else{
				
				for (int j = 0; j < height/8; j=j+8) {
					for (int k = 0; k < width/8; k=k+8) {
						
						putEightByEight(list8x8.get(i), height, width, 2, image);
						
					}
				}
				
			}
			
		}
		
		return image;


	}
	
	public static int[][][] putEightByEight(int[][] matrix8x8, int offsetV, int offsetH, int color,int[][][] image) {

		int lenght = 8;


		for (int i = 0; i < lenght; i++) {

			for (int j = 0; j < lenght; j++) {

				image[color][offsetV + i][offsetH+j] = matrix8x8[i][j];

			}

		}

		return image;

	}
	
	public static int[][][] rgbToYUV(int[][][] matRGB) {

		int imageHeight = matRGB[0].length;
		int imageWidth = matRGB[0][0].length;

		int[][][] matYUV = new int[3][imageHeight][imageWidth];

		// Valeurs pour convertion
		int constante = 128;

		double yr = 0.299;
		double yg = 0.587;
		double yb = 0.114;

		double cbr = -0.168736;
		double cbg = 0.331264;
		double cbb = 0.5;

		double crr = 0.5;
		double crg = 0.418688;
		double crb = 0.081312;

		for (int i = 0; i < imageHeight; i++) {

			for (int j = 0; j < imageWidth; j++) {

				matYUV[0][i][j] = (int) (yr * matRGB[0][i][j] + yg * matRGB[1][i][j] + yb * matRGB[2][i][j]);

				matYUV[1][i][j] = (int) (cbr * matRGB[0][i][j] - cbg * matRGB[1][i][j] + cbb * matRGB[2][i][j]
						+ constante);

				matYUV[2][i][j] = (int) (crr * matRGB[0][i][j] - crg * matRGB[1][i][j] - crb * matRGB[2][i][j]
						+ constante);
			}

		}

		return matYUV;

	}
	
	public static int[][][] yuvToRGB(int[][][] matYUV) {

		int imageHeight = matYUV[0].length;
		int imageWidth = matYUV[0][0].length;

		int[][][] matRGB = new int[3][imageHeight][imageWidth];

		// Formules prisent de :
		// http://softpixel.com/~cwright/programming/colorspace/yuv/
		for (int i = 0; i < imageHeight; i++) {
			for (int j = 0; j < imageWidth; j++) {

				int[] matTemp = new int[3];

				// Regle le probleme du noir/bleu
				// Quand y = 0 ca fuck tout alors on met 1 a la place ca semble
				// régler le probleme
				if (matYUV[PPMReaderWriter.Y][i][j] == 0) {
					matYUV[PPMReaderWriter.Y][i][j] = 1;
				}

				matTemp[0] = (int) (matYUV[PPMReaderWriter.Y][i][j] + 1.4075 * (matYUV[PPMReaderWriter.V][i][j] - 128));
				matTemp[1] = (int) (matYUV[PPMReaderWriter.Y][i][j] - 0.3455 * (matYUV[PPMReaderWriter.U][i][j] - 128)
						- (0.7169 * (matYUV[PPMReaderWriter.V][i][j] - 128)));
				matTemp[2] = (int) (matYUV[PPMReaderWriter.Y][i][j] + 1.78409 * (matYUV[PPMReaderWriter.U][i][j] - 128));
				// 1.17790 valeur qui fonctionnait pas
				// 1.78409 Ceci est la bonne valeur je l'ai calculer avec la
				// TI(entk ca marche bien a date)

				for (int ii = 0; ii < matTemp.length; ii++) {

					if (matTemp[ii] > 255) {
						matTemp[ii] = 255;
					}

					if (matTemp[ii] < 0) {
						matTemp[ii] = 0;
					}

				}

				matRGB[PPMReaderWriter.R][i][j] = matTemp[0];
				matRGB[PPMReaderWriter.G][i][j] = matTemp[1];
				matRGB[PPMReaderWriter.B][i][j] = matTemp[2];

			}
		}

		return matRGB;

	}

}
