import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Homework03_03 {

    /**
     * 方法03
     * 使用线程池submit(Runnable task, T result)
     * 100ms
     *
     * @param args
     */
    public static void main(String[] args) {

        ExecutorService exec = Executors.newFixedThreadPool(1);

        long start = System.currentTimeMillis();

        final Integer[] result = {null};

        Integer ret = null;

        try {
            ret = exec.submit(new Runnable() {
                @Override
                public void run() {
                    result[0] = sum();
                }
            }, 11).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("异步计算结果为：" + result[0]);

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


