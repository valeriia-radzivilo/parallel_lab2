package striped;

import shared.Matrix;

import java.util.concurrent.Callable;

public class StripedAlgorithmCallable implements Callable<int[]> {
    private final int[] row;
    private final Matrix secondMatrix;

    public StripedAlgorithmCallable(int[] row, Matrix secondMatrix) {
        this.row = row;
        this.secondMatrix = secondMatrix;
    }

    @Override
    public int[] call() {
        int[] result = new int[secondMatrix.length()];

        for (int j = 0; j < secondMatrix.length(); j++) {
            for (int i = 0; i < row.length; i++) {
                result[j] += row[i] * secondMatrix.getRow(i)[j];
            }
        }
        return result;
    }
}