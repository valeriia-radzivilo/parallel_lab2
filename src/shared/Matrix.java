package shared;

public class Matrix {
    private final int sizeAxis0;
    private final int sizeAxis1;
    public int[][] matrix;

    public Matrix(int sizeAxis0, int sizeAxis1) {
        this.matrix = new int[sizeAxis0][sizeAxis1];
        this.sizeAxis0 = sizeAxis0;
        this.sizeAxis1 = sizeAxis1;
    }

    public Matrix(int[][] matrix) {
        this.matrix = matrix;
        this.sizeAxis0 = matrix[0].length;
        this.sizeAxis1 = matrix.length;
    }

    public void print() {
        for (int i = 0; i < this.sizeAxis0; i++) {
            for (int j = 0; j < this.sizeAxis1; j++) {
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
        for (int i = 0; i < this.sizeAxis0; i++) {
            for (int j = 0; j < this.sizeAxis1; j++) {
                this.matrix[i][j] = (int) (Math.random() * 10);
            }
        }
    }
}