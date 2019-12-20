# spring-webboard
스프링부트로 구축한 기본 사이트
<br/><br/>



## xml로 Transaction aop 
[context-datasource.xml](src/main/resources/context-datasource.xml)<br/>
[context-mybatis-mapper.xml](src/main/resources/context-mybatis-mapper.xml)<br/>
[Application.java](src/main/java/com/demo/webboard/Application.java)<br/>
```java
@ImportResource({ "classpath:context-datasource.xml", "classpath:context-mybatis-mapper.xml" })
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class,
		TransactionAutoConfiguration.class}) // https://github.com/WOWHans/decoration/issues/1
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
```
