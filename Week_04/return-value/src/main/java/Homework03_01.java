public class Homework03_01 {

    /**
     * 方法01
     * 在main线程中join
     * 178ms
     *
     * @param args
     */
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        final long[] result = new long[1];

        Thread aThread = new Thread(() -> {
            result[0] = sum();
        });

        aThread.start();
        try {
            aThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("异步计算结果为：" + result[0]);

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


