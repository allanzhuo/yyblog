package net.laoyeye;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * 启动入口
 * @author 小卖铺的老爷爷
 * @date 2017年6月8日
 * @website www.laoyeye.net
 */
@SpringBootApplication(scanBasePackages = "net.laoyeye.yyblog")
@EnableTransactionManagement
@MapperScan(basePackages = "net.laoyeye.yyblog.mapper")
@EnableCaching
public class AppStart {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppStart.class, args);
    }
}
