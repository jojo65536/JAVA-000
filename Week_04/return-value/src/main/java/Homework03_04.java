import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Homework03_04 {

    /**
     * 方法04
     * 使用线程池submit(Runnable task)
     * 103ms
     *
     * @param args
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        ExecutorService exec = Executors.newFixedThreadPool(1);

        final Integer[] result = {null};
        
        try {
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    result[0] = sum();
                }
            }).get();
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


