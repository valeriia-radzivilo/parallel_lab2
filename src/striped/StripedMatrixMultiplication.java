package striped;

import shared.Matrix;
import shared.Result;

public class StripedMatrixMultiplication {
    public static Result multiplyMatrices(Matrix matrixA, Matrix matrixB) {
        if (matrixA.getSizeAxis1() != matrixB.getSizeAxis0()) {
            throw new IllegalArgumentException("Розміри матриць несумісні для множення.");
        }

        int rowsA = matrixA.getSizeAxis0();
        int colsA = matrixA.getSizeAxis1();
        int colsB = matrixB.getSizeAxis1();

        int[][] result = new int[rowsA][colsB];

        Thread[] threads = new Thread[rowsA];
        for (int i = 0; i < rowsA; i++) {
            threads[i] = new Thread(new Worker(i, matrixA, matrixB, result));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return new Result(result);
    }

    static class Worker implements Runnable {
        private final int row;
        private final Matrix matrixA;
        private final Matrix matrixB;
        private final int[][] result;

        Worker(int row, Matrix matrixA, Matrix matrixB, int[][] result) {
            this.row = row;
            this.matrixA = matrixA;
            this.matrixB = matrixB;
            this.result = result;
        }

        @Override
        public void run() {
            for (int j = 0; j < matrixB.getSizeAxis1(); j++) {
                int sum = 0;
                for (int k = 0; k < matrixA.getSizeAxis1(); k++) {
                    sum += matrixA.matrix[row][k] * matrixB.matrix[k][j];
                }
                result[row][j] = sum;
            }
        }
    }

}