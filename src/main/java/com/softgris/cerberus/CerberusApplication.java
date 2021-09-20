package com.softgris.cerberus;

import com.softgris.cerberus.di.Autowired;
import com.softgris.cerberus.di.Injector;
import com.softgris.cerberus.di.test.TestController;
import com.softgris.cerberus.di.test.TestService;
import com.softgris.cerberus.di.test.TestServiceImplOne;
import com.softgris.cerberus.di.test.TestServiceImplTwo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CerberusApplication {

  public static void main(String[] args) {
    Injector.startApplication(CerberusApplication.class);
    Injector.getService(TestController.class).display();
    SpringApplication.run(CerberusApplication.class, args);
  }

}
