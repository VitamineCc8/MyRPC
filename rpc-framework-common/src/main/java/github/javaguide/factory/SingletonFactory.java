package github.javaguide.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取单例对象的工厂类
 *这段代码实现了一个单例工厂模式，它提供了一个方法getInstance，该方法接受一个Class类型的参数，返回一个该类型的实例。如果之前已经创建了该类型的实例，则直接返回之前创建的实例；否则，创建一个新的实例，并存储在一个ConcurrentHashMap中，以备下次使用。
 *具体来说，代码中的ConcurrentHashMap维护了一个从类型名到实例的映射。getInstance方法首先检查这个映射是否包含给定类型的实例。如果是，则直接从映射中获取并返回该实例。如果不是，则通过反射创建一个新的实例，并将其添加到映射中。
 *这个代码中使用了Java的泛型，使得返回的实例类型与输入的类型一致。此外，使用了ConcurrentHashMap，保证了线程安全性。同时，使用了computeIfAbsent方法，可以保证在并发访问下不会出现重复创建对象的情况，从而避免了线程安全问题。
 *
 *
 */
public final class SingletonFactory {
    private static final Map<String, Object> OBJECT_MAP = new ConcurrentHashMap<>();

    private SingletonFactory() {
    }

    public static <T> T getInstance(Class<T> c) {
        if (c == null) {
            throw new IllegalArgumentException();
        }
        String key = c.toString();
        if (OBJECT_MAP.containsKey(key)) {
            return c.cast(OBJECT_MAP.get(key));
        } else {
            return c.cast(OBJECT_MAP.computeIfAbsent(key, k -> {
                try {
                    return c.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }));
        }
    }
}
