package com.ioc_container.services;

import com.ioc_container.annotations.Component;
import com.ioc_container.annotations.Inject;

@Component
public class ServiceB {

    @Inject
    private ServiceA serviceA;

    public void execute() {
        this.serviceA.sayHello();
        System.out.println("ServiceB executed successfully !");
    }
}
