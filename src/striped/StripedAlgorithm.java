package striped;

import org.apache.commons.lang3.time.StopWatch;
import shared.Algorithm;
import shared.Matrix;
import shared.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StripedAlgorithm implements Algorithm {

    final Matrix firstMatrix;
    final Matrix secondMatrix;
    final int threadsCount;
    final StopWatch timer;

    public StripedAlgorithm(Matrix firstMatrix, Matrix secondMatrix, int threadsCount, StopWatch timer) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.threadsCount = threadsCount;
        this.timer = timer;
    }


    @Override
    public Result algorithm() {
        int[][] result = new int[firstMatrix.length()][secondMatrix.length()];
        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);

        List<Future<int[]>> list = new ArrayList<>();

        for (int i = 0; i < firstMatrix.length(); i++) {
            StripedAlgorithmCallable worker = new StripedAlgorithmCallable(firstMatrix.getRow(i), secondMatrix);
            Future submit = executor.submit(worker);
            list.add(submit);
        }

        for (int i = 0; i < list.size(); i++) {
            try {
                result[i] = list.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();


        return new Result(result);
    }

    @Override
    public void printResult(Result result, long time) {
        result.matrix.print();

        System.out.println("Time: " + time + " milliseconds with thread count: " + threadsCount);

    }

    @Override
    public long run(boolean print) {
        if (print) System.out.println("\n Striped Algorithm:\n");
        this.timer.start();
        final Result result = algorithm();
        this.timer.stop();
        final long time = this.timer.getTime();
        if (print) printResult(result, time);
        timer.reset();
        return time;
    }
}