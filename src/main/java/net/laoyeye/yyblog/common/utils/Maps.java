package net.laoyeye.yyblog.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 快捷操作Map相关工具
 */
@SuppressWarnings("unchecked")
public final class Maps {
    enum MapType {
        /**
         * 生成Map的类型
         */
        ConcurrentHashMap,
        HashMap,
        TreeMap
    }

	public static <K, V> HashMap<K, V> hashMap(Object... keyValues) {
        return (HashMap<K, V>) map(MapType.HashMap, keyValues);
    }

    public static <K, V> ConcurrentHashMap<K, V> concurrentHashMap(Object... keyValues) {
        return (ConcurrentHashMap<K, V>) map(MapType.HashMap, keyValues);
    }

    public static <K, V> TreeMap<K, V> treeMap(Object... keyValues) {
        return (TreeMap<K, V>) map(MapType.HashMap, keyValues);
    }

    private static <K, V> Map<K, V> map(MapType mapType, Object... keyValues) {
        int length = keyValues.length;
        if (length % 2 == 0) {
            Map<K, V> map;
            if (mapType.equals(MapType.ConcurrentHashMap)) {
                map = new ConcurrentHashMap<>(length / 2);
            } else if (mapType.equals(MapType.HashMap)) {
                map = new HashMap<>(length / 2);
            } else if (mapType.equals(MapType.TreeMap)) {
                map = new TreeMap<>();
            } else {
                throw new RuntimeException("不支持的Map类型！");
            }
            for (int i = 1; i < keyValues.length; i = i + 2) {
                //noinspection unchecked
                K key = (K) keyValues[i - 1];
                //noinspection unchecked
                V value = (V) keyValues[i];
                map.put(key, value);
            }
            return map;
        } else {
            throw new RuntimeException("键值对数目不匹配！");
        }
    }
}
