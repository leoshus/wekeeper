package com.sdw.soft.demo.concurrent;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by shangyindong on 2016/6/18.
 */
public class ThreadPoolDemo {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 2000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return null;
        }
    });

    @Test
    public void test01(){

    }
}

