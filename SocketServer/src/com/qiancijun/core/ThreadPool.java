package com.qiancijun.core;

/**
 * @author Xu Chun
 * @Desc 线程池
 * @Time 2021/4/25 20:48
 */

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    private int cpu = Runtime.getRuntime().availableProcessors(); // CPU核数
    private ThreadPoolExecutor pool; // 自定义线程池
    private static volatile ThreadPool instance;

    public ThreadPool() {
        pool = new ThreadPoolExecutor(
                2,
                cpu + 1,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public ThreadPoolExecutor getPool() {
        return pool;
    }

    // 双重检查的单例模式
    public static ThreadPool getInstance() {
        if (instance == null) {
            synchronized (ThreadPool.class) {
                if (instance == null) {
                    try {
                        instance = new ThreadPool();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }
}
