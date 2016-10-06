package com.javarush.test.level34.lesson08.bonus01;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();

    public V getByKey(K key, Class<V> clazz) throws Exception {

        if (cache.get(key) != null) return cache.get(key);

        Constructor<V> constructor = clazz.getDeclaredConstructor(key.getClass());

        cache.put(key,  constructor.newInstance(key));
        return cache.get(key);
    }

    public boolean put(V obj)
    {
        try
        {
            Method method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);
            K key = (K) method.invoke(obj);
            cache.put(key, obj);
            return true;
        }
        catch (NoSuchMethodException e)
        {

        }
        catch (IllegalAccessException e)
        {

        }
        catch (InvocationTargetException e)
        {

        }
        return false;
    }

    public int size() {
        return cache.size();
    }
}
