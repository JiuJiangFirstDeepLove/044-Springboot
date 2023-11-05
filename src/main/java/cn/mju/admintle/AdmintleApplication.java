package cn.mju.admintle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.mju.admintle.mapper")
public class AdmintleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdmintleApplication.class, args);
    }

}
