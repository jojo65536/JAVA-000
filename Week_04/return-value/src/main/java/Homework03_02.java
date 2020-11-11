import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Homework03_02 {

    /**
     * 方法02
     * 使用线程池submit(Callable<T> task)
     * 183ms
     *
     * @param args
     */
    public static void main(String[] args) {

        ExecutorService exec = Executors.newFixedThreadPool(1);

        long start = System.currentTimeMillis();

        Integer result = null;


        try {
            result = exec.submit(() -> {
                return sum();
            }).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        exec.shutdown();
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
}


