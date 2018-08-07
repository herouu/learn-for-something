package top.alertcode.trainhigh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.alertcode.trainhigh.mapper")
public class TrainHighApplication {

  public static void main(String[] args) {
    SpringApplication.run(TrainHighApplication.class, args);
  }
}
