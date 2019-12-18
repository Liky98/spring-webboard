package com.demo.webboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@ImportResource({ "classpath:context-datasource.xml", "classpath:context-mybatis-mapper.xml" })
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class,
		TransactionAutoConfiguration.class}) // https://github.com/WOWHans/decoration/issues/1
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
