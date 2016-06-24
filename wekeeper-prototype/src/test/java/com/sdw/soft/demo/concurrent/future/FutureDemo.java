package com.sdw.soft.demo.concurrent.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by shangyindong on 2016/6/18.
 */
public class FutureDemo {

    public static void main(String[] args){
        FutureTask<String> future = new FutureTask<String>(new DealData());
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(future);

        try {
            Thread.sleep(2000);
            System.out.println("result:" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class DealData implements Callable{
        @Override
        public Object call() throws Exception {
            Thread.sleep(5000);
            return "Data-" + new Random(10).nextInt();
        }
    }
}
