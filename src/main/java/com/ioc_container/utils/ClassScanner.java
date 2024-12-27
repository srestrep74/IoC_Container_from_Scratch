package com.ioc_container.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class ClassScanner {
    public static List<Class<?>> getClasses(String basePackage) throws Exception {
        String path = basePackage.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);
        List<Class<?>> classes = new ArrayList<>();

        while (resources.hasMoreElements()){
            URL resource = resources.nextElement();
            File directory = new File(resource.toURI());
            for (File file : Objects.requireNonNull(directory.listFiles())){
                if(file.getName().endsWith(".class")) {
                    String className = basePackage + '.' + file.getName().replace(".class", "");
                    classes.add(Class.forName(className));
                }
            }
        }

        return classes;
    }
}
