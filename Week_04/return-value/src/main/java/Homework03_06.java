import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Homework03_06 {

    /**
     * 方法06
     * 用CompletableFuture
     * 189ms
     *
     * @param args
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Integer result = null;
        try {
            result = CompletableFuture.supplyAsync(() -> sum()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
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


