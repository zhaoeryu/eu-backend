package cn.eu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@SpringBootApplication
public class AppServer {
    public static void main(String[] args) {
        SpringApplication.run(AppServer.class, args);
        System.out.println("===============================");
        System.out.println("服务启动成功");
        System.out.println("===============================");
    }
}
