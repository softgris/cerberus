package com.softgris.cerberus.di;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class InjectionUtil {

    private InjectionUtil() {}

    public static void autowire(Injector injector, Class<?> classz, Object classInstance) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Set<Field> fields = findFields(classz);

        for (Field field : fields) {
            String qualifier = field.isAnnotationPresent(Qualifier.class)
                    ? field.getAnnotation(Qualifier.class).value() : null;
            Object fieldInstance = injector.getBeanInstance(field.getType(), field.getName(), qualifier);
            field.set(classInstance, fieldInstance);
            // Recursively autowire all dependencies of dependencies
            autowire(injector, fieldInstance.getClass(), fieldInstance);
        }
    }

    private static Set<Field> findFields(Class<?> classz) {
        Set<Field> set = new HashSet<>();

        while (classz != null) {
            for (Field field : classz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    set.add(field);
                }
            }

            classz = classz.getSuperclass();
        }

        return set;
    }
}
