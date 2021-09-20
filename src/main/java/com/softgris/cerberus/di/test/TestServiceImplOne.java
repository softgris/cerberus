package com.softgris.cerberus.di.test;

import com.softgris.cerberus.di.Bean;

@Bean
public class TestServiceImplOne implements TestService {
    @Override
    public void test() {
        System.out.println("Service One");
    }
}
