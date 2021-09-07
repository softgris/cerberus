package com.softgris.cerberus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CerberusApplication {

  public static void main(String[] args) {
    SpringApplication.run(CerberusApplication.class, args);
  }
}
