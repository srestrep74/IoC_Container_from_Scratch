package com.ioc_container.container;

import com.ioc_container.annotations.Component;
import com.ioc_container.utils.ClassScanner;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container {

    private final Map<Class<?>, Object> beans = new HashMap<>();

    public Container(String basePackage) throws Exception {
        this.scanAndInstantiate(basePackage);
        this.resolveDependencies();
    }

    private void scanAndInstantiate(String basePackage) throws Exception {
        List<Class<?>> classes = ClassScanner.getClasses(basePackage);
        for(Class<?> clazz : classes){
            if(clazz.isAnnotationPresent(Component.class)) {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                this.beans.put(clazz, instance);
            }
        }
    }

    private void resolveDependencies() throws Exception {
        for(Object bean : this.beans.values()){
            Field[] fields = bean.getClass().getDeclaredFields();
            for(Field field : fields){
                Class<?> dependencyType = field.getType();
                Object dependency = this.beans.get(dependencyType);
                if(dependency == null){
                    throw new RuntimeException("Unsatisfied dependency: " + dependencyType);
                }
                field.setAccessible(true);
                field.set(bean, dependency);
            }
        }
    }

    public <T> T getBean(Class<T> beanType) {
        return beanType.cast(this.beans.get(beanType));
    }
}
