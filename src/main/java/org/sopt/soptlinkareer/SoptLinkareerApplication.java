package org.sopt.soptlinkareer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SoptLinkareerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SoptLinkareerApplication.class, args);
  }
}
