package com.ioc_container.services;

import com.ioc_container.annotations.Component;

@Component
public class ServiceA {
    public void sayHello() {
        System.out.println("Hello from ServiceA !");
    }
}
