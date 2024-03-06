package shared;

public class Result {

    public final Matrix matrix;

    public Result(int[][] matrix) {
        this.matrix = new Matrix(matrix);
    }


    public Matrix getMatrix() {
        return matrix;
    }
}