import fox.FoxAlgorithm;
import org.apache.commons.lang3.time.StopWatch;
import shared.Matrix;
import striped.StripedAlgorithm;

import java.util.Arrays;


public class Main {

    static final int MATRIX_SIZE = 100;
    static final int[] THREADS_EXP = new int[]{MATRIX_SIZE / 10, MATRIX_SIZE / 5, MATRIX_SIZE / 3, MATRIX_SIZE / 2, MATRIX_SIZE};
    static final int[] MATRIX_SIZES_LIST = new int[]{MATRIX_SIZE / 10, MATRIX_SIZE, MATRIX_SIZE * 5, MATRIX_SIZE * 10, MATRIX_SIZE * 20};

    public static void main(String[] args) {
        final Matrix firstMatrix = new Matrix(MATRIX_SIZE, MATRIX_SIZE);
        final Matrix secondMatrix = new Matrix(MATRIX_SIZE, MATRIX_SIZE);
        firstMatrix.generateRandomMatrix();
        secondMatrix.generateRandomMatrix();

        System.out.println("\nFirst matrix: \n");
        firstMatrix.print();
        System.out.println("\nSecond matrix: \n");
        secondMatrix.print();


        int threadCount = 12;

        StopWatch timer = StopWatch.create();

//        regularStripedAndFox(threadCount, firstMatrix, secondMatrix, timer);
        checkMatrixSizes(timer, threadCount);
//        checkThreadsNumber(firstMatrix, secondMatrix, timer);

    }

    private static void regularStripedAndFox(int threadCount, Matrix firstMatrix, Matrix secondMatrix, StopWatch timer) {

        final StripedAlgorithm stripedAlgorithm = new StripedAlgorithm(firstMatrix, secondMatrix, threadCount, timer);
        stripedAlgorithm.run(true);

        final FoxAlgorithm foxAlgorithm = new FoxAlgorithm(timer, threadCount, firstMatrix, secondMatrix);
        foxAlgorithm.run(true);
    }

    private static void checkMatrixSizes(StopWatch timer, int threadCount) {
        long minTimeStripped = -1;
        long minTimeStrippedSize = 0;
        long minTimeFox = -1;
        long minTimeFoxSize = 0;
        for (int size : MATRIX_SIZES_LIST) {
            final Matrix firstMatrix = new Matrix(size, size);
            final Matrix secondMatrix = new Matrix(size, size);
            firstMatrix.generateRandomMatrix();
            secondMatrix.generateRandomMatrix();

            final StripedAlgorithm stripedAlgorithm = new StripedAlgorithm(firstMatrix, secondMatrix, threadCount, timer);
            final long timeStripped = stripedAlgorithm.run(false);

            final FoxAlgorithm foxAlgorithm = new FoxAlgorithm(timer, threadCount, firstMatrix, secondMatrix);
            final long timeFox = foxAlgorithm.run(false);

            System.out.println("Size: " + size + " Stripped finished in " + timeStripped + " millisec" + ";  Fox finished in " + timeFox + " millisec" + " with " + threadCount + " threads");
            if (minTimeStripped == -1 && minTimeFox == -1) {
                minTimeStripped = timeStripped;
                minTimeFox = timeFox;
                minTimeFoxSize = size;
                minTimeStrippedSize = size;
            }
            if (timeStripped < minTimeStripped) {
                minTimeStripped = timeStripped;
                minTimeStrippedSize = size;
            }
            if (timeFox < minTimeFox) {
                minTimeFox = timeFox;
                minTimeFoxSize = size;
            }
        }
        System.out.println("Results: ");
        System.out.println("\nStripped: ");
        System.out.println("Best time " + minTimeStripped + " milliseconds with " + minTimeStrippedSize + " size");
        System.out.println("\nFox: ");
        System.out.println("Best time " + minTimeFox + " milliseconds with " + minTimeFoxSize + " size");

    }

    private static void checkThreadsNumber(Matrix firstMatrix, Matrix secondMatrix, StopWatch timer) {

        long minTimeStrippedMilliseconds = -1;
        int minTimeStrippedThreads = -1;

        for (int threadNumber : THREADS_EXP) {


            final StripedAlgorithm stripedAlgorithm = new StripedAlgorithm(firstMatrix, secondMatrix, threadNumber, timer);
            final long time = stripedAlgorithm.run(false);
            System.out.println("Striped with " + threadNumber + " threads took " + time + " milliseconds");
            if ((minTimeStrippedThreads == -1 && minTimeStrippedMilliseconds == -1) || time < minTimeStrippedMilliseconds) {
                minTimeStrippedMilliseconds = time;
                minTimeStrippedThreads = threadNumber;
            }


        }

        long minTimeFoxMilliseconds = 0;
        int minTimeFoxThreads = 0;

        for (int threadNumber : Arrays.stream(THREADS_EXP).toArray()) {


            final FoxAlgorithm foxAlgorithm = new FoxAlgorithm(timer, threadNumber, firstMatrix, secondMatrix);
            final long time = foxAlgorithm.run(false);
            System.out.println("Fox with " + threadNumber + " threads took " + time + " milliseconds");
            if ((minTimeFoxMilliseconds == 0 && minTimeFoxThreads == 0) || time < minTimeFoxMilliseconds) {
                minTimeFoxMilliseconds = time;
                minTimeFoxThreads = threadNumber;
            }

        }


        System.out.println("Results: ");
        System.out.println("\nStripped: ");
        System.out.println("Best time " + minTimeStrippedMilliseconds + " milliseconds with " + minTimeStrippedThreads + " threads");
        System.out.println("\nFox: ");
        System.out.println("Best time " + minTimeFoxMilliseconds + " milliseconds with " + minTimeFoxThreads + " threads");


    }


}