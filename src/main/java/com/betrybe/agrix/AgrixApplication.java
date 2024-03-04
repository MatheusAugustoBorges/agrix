package com.betrybe.agrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Applicationn main class.
 */
@SpringBootApplication
//@EntityScan("com.betrybe.agrix.models.entities")
//@EnableJpaRepositories("com.betrybe.agrix.models.repositories")
//@ComponentScan("com.betrybe.agrix")
public class AgrixApplication {

  public static void main(String[] args) {
    SpringApplication.run(AgrixApplication.class, args);
  }

}
