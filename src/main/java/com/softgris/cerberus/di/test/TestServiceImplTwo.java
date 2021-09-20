package com.softgris.cerberus.di.test;

import com.softgris.cerberus.di.Bean;

@Bean
public class TestServiceImplTwo implements TestService {
    @Override
    public void test() {
        System.out.println("Service Two");
    }
}
