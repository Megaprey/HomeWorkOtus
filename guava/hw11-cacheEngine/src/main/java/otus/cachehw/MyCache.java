package otus.cachehw;

import otus.references.ReferenceDemo;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author sergey
 * created on 14.12.18.
 */
public class MyCache<K, V> implements HwCache<K, V> {
//Надо реализовать эти методы
    Map<String, V> cache = new WeakHashMap<>();


    @Override
    public void put(K key, V value) {
        cache.put(String.valueOf(key), value);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        ReferenceQueue<HwListener<K, V>> refQueue = new ReferenceQueue<>();
        new PhantomReference<>(listener, refQueue);

        new Thread(
                () -> {
                    try {
                        logger.info("Waiting for object cleaning...");
                        refQueue.remove();
                        logger.info("Object cleaned");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        ).start();
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {

    }
}
