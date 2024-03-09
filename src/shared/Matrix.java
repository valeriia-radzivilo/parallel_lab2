package shared;

public class Matrix {
    private final int rows;
    private final int columns;
    public int[][] matrix;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new int[columns][rows];
    }

    public Matrix(int[][] matrix) {
        this.rows = matrix.length;
        this.columns = matrix[0].length;
        this.matrix = matrix;
    }


    public void print() {
        for (int i = 0; i < this.columns; i++) {
            for (int j = 0; j < this.rows; j++) {
                System.out.printf(this.matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int length() {
        return this.matrix.length;
    }


    public int[] getRow(int index) {
        return this.matrix[index];
    }


    public void generateRandomMatrix() {
        for (int i = 0; i < this.columns; i++) {
            for (int j = 0; j < this.rows; j++) {
                this.matrix[i][j] = (int) (Math.random() * 10);
            }
        }
    }

    public int getRowsLength() {
        return this.rows;
    }

    public int getColumnsLength() {
        return this.columns;
    }
}