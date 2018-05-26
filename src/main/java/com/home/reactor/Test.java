package com.home.reactor;

public class Test {
    private static final int SIZE = 5;

    public static void main(String[] args) {
        printSpiral();
        System.out.println("----------------------------------------------");
        printSpiral2();
        System.out.println("----------------------------------------------");
        printDiagonals();
    }

    private static void printDiagonals() {
        int[][] matrix = new int[SIZE][SIZE];
        int value = 1;

        int col = 0;
        int row = SIZE - 1;
        while (value <= SIZE * SIZE) {

            for (int i = 0; i < SIZE - Math.max(row, col); ++i) {
                matrix[row + i][col + i] = value++;
            }

            if (row > 0) {
                row--;
            } else {
                col++;
            }
        }

        print(matrix);
    }

    private static void printSpiral() {
        int[][] matrix = new int[SIZE][SIZE];

        int minRow, minCol, maxCol, maxRow;

        maxRow = maxCol = SIZE - 1;
        minRow = minCol = 0;

        int value = 1;

        while (value <= SIZE * SIZE) {
            for (int i = minCol; i <= maxCol; ++i) {
                matrix[minRow][i] = value++;
            }
            for (int i = minRow + 1; i <= maxRow; ++i) {
                matrix[i][maxCol] = value++;
            }
            for (int i = maxCol - 1; i >= minCol; --i) {
                matrix[maxRow][i] = value++;
            }
            for (int i = maxRow - 1; i > minRow; --i) {
                matrix[i][minCol] = value++;
            }

            minCol++;
            minRow++;
            maxCol--;
            maxRow--;

        }

        print(matrix);
    }

    private static void printSpiral2() {
        int[][] matrix = new int[SIZE][SIZE];

        int value = 1;
        int offset = 0;
        while (value <= SIZE * SIZE) {
            for (int i = offset; i < SIZE - offset; ++i) {
                matrix[offset][i] = value++;
            }
            for (int i = offset + 1; i < SIZE - offset; ++i) {
                matrix[i][SIZE - 1 - offset] = value++;
            }
            for (int i = SIZE - 2 - offset; i >= offset; --i) {
                matrix[SIZE - 1 - offset][i] = value++;
            }
            for (int i = SIZE - 2 - offset; i > offset; --i) {
                matrix[i][offset] = value++;
            }
            offset++;
        }

        print(matrix);
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
