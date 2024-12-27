package com.ioc_container;

import com.ioc_container.container.Container;
import com.ioc_container.services.ServiceB;

public class Main {
    public static void main(String[] args) throws Exception {
        Container container = new Container("com.ioc_container.services");
        ServiceB serviceB = container.getBean(ServiceB.class);
        serviceB.execute();
    }
}