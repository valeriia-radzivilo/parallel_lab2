package shared;

public interface Algorithm {


    Result algorithm();

    void printResult(Result result, long time);

    long run(boolean print);

}