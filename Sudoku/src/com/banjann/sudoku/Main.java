package com.banjann.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	private static Integer[][] matrix = {
            {0, 3, 0, 0, 0, 0, 0, 7, 0},
            {0, 0, 8, 0, 0, 4, 0, 0, 0},
            {7, 6, 4, 0, 0, 0, 5, 3, 0},
            {6, 0, 0, 1, 0, 7, 2, 0, 0},
            {4, 0, 7, 5, 6, 0, 9, 1, 3},
            {9, 1, 0, 0, 4, 2, 0, 0, 0},
            {0, 0, 0, 0, 2, 0, 0, 4, 0},
            {0, 0, 9, 0, 5, 0, 0, 0, 6},
            {0, 0, 0, 0, 0, 3, 0, 0, 0}
			};

	public static void main(String[] args) {
		if (bruteForce()) {
			printSolution();
		} else {
			System.out.println("no solution!");
		}
	}

	private static boolean bruteForce() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (matrix[r][c] == 0) {
					for (int digit = 1; digit <= 9; digit++) {
						if (isValid(digit, r, c)) {
							matrix[r][c] = digit;
							if (bruteForce()) {
								return true;
							} else {
								matrix[r][c] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isValid(int digit, int r, int c) {
		List<Integer> row = Arrays.asList(matrix[r]);

		ArrayList<Integer> column = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++) {
			column.add(matrix[i][c]);
		}

		ArrayList<Integer> submatrixAsList = new ArrayList<Integer>();
		int subMatrix_r = 0;
		int subMatrix_c = 0;
		if (3 <= r && r < 6) {
			subMatrix_r = 3;
		}
		if (r >= 6) {
			subMatrix_r = 6;
		}
		if (3 <= c && c < 6) {
			subMatrix_c = 3;
		}
		if (c >= 6) {
			subMatrix_c = 6;
		}
		for (int i = subMatrix_r; i < subMatrix_r + 3; i++) {
			for (int j = subMatrix_c; j < subMatrix_c + 3; j++) {
				submatrixAsList.add(matrix[i][j]);
			}
		}

		return !row.contains(digit) && !column.contains(digit) && !submatrixAsList.contains(digit);
	}

	private static void printSolution() {
		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[r].length; c++) {
				System.out.print(matrix[r][c]);
				if (c != 8) {
					System.out.print(" ");
				}
			}
			System.out.println();
			System.out.println("-----------------");
		}
	}
}
