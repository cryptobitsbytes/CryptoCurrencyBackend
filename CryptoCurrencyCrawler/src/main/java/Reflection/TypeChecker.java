package Reflection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lennard on 22-8-2017.
 */
public class TypeChecker {

    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

    public static boolean isCollection(Class<?> objectClass)
    {
        return Collection.class.isAssignableFrom(objectClass);
    }

    public static boolean isNotCollection(Class<?> objectClass)
    {
        return !isCollection(objectClass);
    }
    public static boolean isWrapperType(Class<?> objectClass)
    {
        return WRAPPER_TYPES.contains(objectClass);
    }
    public static boolean isNotWrapperType(Class<?> objectClass)
    {
        return !isWrapperType(objectClass);
    }

    private static Set<Class<?>> getWrapperTypes()
    {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(String.class);
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        return ret;
    }
}
