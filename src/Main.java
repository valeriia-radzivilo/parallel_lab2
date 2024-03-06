import shared.Matrix;
import shared.Result;
import striped.StripedMatrixMultiplication;


public class Main {
    public static void main(String[] args) {
        final Matrix firstMatrix = new Matrix(2, 2);
        final Matrix secondMatrix = new Matrix(2, 2);
        firstMatrix.generateRandomMatrix();
        secondMatrix.generateRandomMatrix();

        System.out.println("\nFirst matrix: \n");
        firstMatrix.print();
        System.out.println("\nSecond matrix: \n");
        secondMatrix.print();

        System.out.println("Striped algorithm:");
        startStripedMatrixAlgorithm(firstMatrix, secondMatrix);

    }


    private static void startStripedMatrixAlgorithm(Matrix A, Matrix B) {

        Result result = StripedMatrixMultiplication.multiplyMatrices(A, B);
        System.out.println("\nResult: \n");
        result.getMatrix().print();

    }
}