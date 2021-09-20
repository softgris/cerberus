package com.softgris.cerberus.di;

import org.reflections.Reflections;

import javax.management.RuntimeErrorException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Injector {
    private Map<Class<?>, Class<?>> diMap;
    private Map<Class<?>, Object> applicationScope;

    private static Injector injector;

    public Injector() {
        diMap = new HashMap<>();
        applicationScope = new HashMap<>();
    }

    public static void startApplication(Class<?> mainClass) {
        try {
            synchronized (Injector.class) {
                if (injector == null) {
                    injector = new Injector();
                    injector.initFramework(mainClass);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initFramework(Class<?> mainClass) throws IOException, ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?>[] classes = ClassLoaderUtil.getClasses(mainClass.getPackageName());
        Reflections reflections = new Reflections(mainClass.getPackageName());
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(Bean.class);

        for (Class<?> implementationClass : types) {
            Class<?>[] interfaces = implementationClass.getInterfaces();

            if (interfaces.length == 0) {
                diMap.put(implementationClass, implementationClass);
            } else {
                for (Class<?> iface : interfaces) {
                    diMap.put(implementationClass, iface);
                }
            }
        }

        for (Class<?> classz : classes) {
            if (classz.isAnnotationPresent(Bean.class)) {
                Object classInstance = classz.getDeclaredConstructor().newInstance();
                applicationScope.put(classz, classInstance);
                InjectionUtil.autowire(this, classz, classInstance);
            }
        }
    }

    public static <T> T getService(Class<T> classz) {
        try {
            return injector.getBeanInstance(classz);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private <T> T getBeanInstance(Class<T> interfaceClass) throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        return (T) getBeanInstance(interfaceClass, null, null);
    }

    public <T> Object getBeanInstance(Class<T> interfaceClass, String fieldName, String qualifier)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> implementationClass = getImplementationClass(interfaceClass, fieldName, qualifier);

        if (applicationScope.containsKey(implementationClass)) {
            return applicationScope.get(implementationClass);
        }

        synchronized (applicationScope) {
            Object service = implementationClass.getDeclaredConstructor().newInstance();
            applicationScope.put(implementationClass, service);
            return service;
        }
    }

    private Class<?> getImplementationClass(Class<?> interfaceClass, String fieldName, String qualifier) {
        Set<Map.Entry<Class<?>, Class<?>>> implementationClasses = diMap.entrySet().stream()
                .filter(entry -> entry.getValue() == interfaceClass).collect(Collectors.toSet());
        String errorMessage = "";

        if (implementationClasses == null || implementationClasses.size() == 0) {
            errorMessage = "No implementation found for interface " + interfaceClass.getName();
        } else if (implementationClasses.size() == 1) {
            Optional<Map.Entry<Class<?>, Class<?>>> entry = implementationClasses.stream().findFirst();
            if (entry.isPresent()) {
                return entry.get().getKey();
            }
        } else {
            final String findBy = (qualifier == null || qualifier.trim().length() == 0)
                    ? fieldName : qualifier;
            Optional<Map.Entry<Class<?>, Class<?>>> entry = implementationClasses.stream()
                    .filter(param -> param.getKey().getSimpleName().equalsIgnoreCase(findBy)).findAny();

            if (entry.isPresent()) {
                return entry.get().getKey();
            } else {
                errorMessage = "There are " + implementationClasses.size() + " implementations of the interface "
                        + interfaceClass.getName() + ". Expected single implementation of make use of @Qualifier" +
                        " to specify the implementation needed.";
            }
        }

        throw new RuntimeErrorException(new Error(errorMessage));
    }
}
