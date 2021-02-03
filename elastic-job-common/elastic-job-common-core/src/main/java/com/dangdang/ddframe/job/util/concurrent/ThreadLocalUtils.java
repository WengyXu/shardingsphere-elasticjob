package com.dangdang.ddframe.job.util.concurrent;

import java.util.HashMap;
import java.util.Map;

public final class ThreadLocalUtils {
    
    private ThreadLocalUtils() {

    }
    
    private static ThreadLocal<Map<String, Object>> local;

    static {
        local = new ThreadLocal(){
            @Override
            public Map initialValue(){
                return new HashMap<>();
            }
        };
    }

    public static Map<String, Object> getAll() {
        return new HashMap<>(local.get());
    }
    
    public static <T> T put(String key, T value) {
        local.get().put(key, value);
        return value;
    }

    public static void remove(String key) {
        local.get().remove(key);
    }

    public static void clear() {
        local.remove();
    }

    public static <T> T get(String key) {
        return ((T) local.get().get(key));
    }

    public static <T> T getAndRemove(String key) {
        try {
            return get(key);
        } finally {
            remove(key);
        }
    }
    
}