package com.int403.jabong.gifviewer2.bumptech.load.engine.bitmap_recycle;


import com.int403.jabong.gifviewer2.bumptech.util.Util;

import java.util.Queue;

abstract class BaseKeyPool<T extends Poolable> {
    private static final int MAX_SIZE = 20;
    private final Queue<T> keyPool = Util.createQueue(MAX_SIZE);

    protected T get() {
        T result = keyPool.poll();
        if (result == null) {
            result = create();
        }
        return result;
    }

    public void offer(T key) {
        if (keyPool.size() < MAX_SIZE) {
            keyPool.offer(key);
        }
    }

    protected abstract T create();
}
