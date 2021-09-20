package com.softgris.cerberus.di.test;

import com.softgris.cerberus.di.Autowired;
import com.softgris.cerberus.di.Bean;
import com.softgris.cerberus.di.Qualifier;

@Bean
public class TestControllerImpl implements TestController {

    @Autowired
    @Qualifier("testServiceImplOne")
    private TestService testServiceImplOne;

    @Autowired
    @Qualifier("testServiceImplTwo")
    private TestService testServiceImplTwo;

    public void display() {
        testServiceImplOne.test();
        testServiceImplTwo.test();
    }
}
