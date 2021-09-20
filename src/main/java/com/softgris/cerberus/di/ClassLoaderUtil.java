package com.softgris.cerberus.di;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassLoaderUtil {

    /*
     * Get all the classes for the given package (e.g.: java.util)
     */
    public static Class<?>[] getClasses(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;

        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();

        // Get all the files and directories in the package
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        // Get all the classes in the files
        List<Class<?>> classes = new ArrayList<>();
        for (File file : dirs) {
            classes.addAll(findClasses(file, packageName));
        }

        return classes.toArray(new Class[classes.size()]);
    }

    public static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();

        if (!directory.exists()) {
            return classes;
        }

        for (File file : directory.listFiles()) {
            // If it's a directory (package), recursively look for files (classes) inside of it
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                // Remove the ".class" to get the class name
                String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
                classes.add(Class.forName(className));
            }
        }

        return classes;
    }
}
