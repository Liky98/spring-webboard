# spring-webboard
스프링부트로 구축한 기본 사이트
<br/><br/>
## SKILL
* 개발도구 : IntelliJ
* Java : jdk 1.8
* Framework : SpringBoot 2.2
* Build : Gradle 5.6
* In-Memory DB : [H2](https://blog.naver.com/myh814/221684433033)
* Mapper : mybatis
* Front-end : [jsp](https://blog.naver.com/myh814/221684419549), [bootstrap admin](https://startbootstrap.com/templates/sb-admin/)
<br/><br/>

## BUILD
처음에 jar 파일로 빌드해서 실행을 했었는데, jsp 경로를 찾지 못했다.<br/>
경로를 바꿔보고 삽질을 한 결과, springboot가 jar로 된 빌드 파일은 더이상 jsp를 지원하지 않는다고 한다.
[상세](https://blog.naver.com/myh814/221683685426) <br/>
> ### 실행방법 1
>> 1. spring-webboard.war를 다운받는다.
>> 2. 터미널에서 `java -jar spring-webboard.war` 을 실행한다.
>> 3. `http://localhost:8080`으로 접속한다.
> ### 실행방법 2
>> 1. [git파일](https://github.com/moonsiri/spring-webboard)을 다운받는다.
>> 2. 개발툴에 import 하여 실행한다. (단, [Lombok](https://blog.naver.com/myh814/221504225671)설정해줘야함)
> ### 실행방법 3
>> 1. [git파일](https://github.com/moonsiri/spring-webboard)을 다운받는다.
>> 2. [build.gradle](build.gradle) 파일에서 `// war로 빌드 시` 부분의 주석을 제거한다.
>> 3. war로 빌드 후 실행한다.

<br/>
<hr/>
<br/>

## [ERD](src/main/resources/schema.sql)
![erd](./src/main/webapp/img/erd.png)
(Change column name of BOARD TABLE form "NAME" to "BOARD_NAME")

#### [USER](src/main/resources/data.sql)
계정은 다음과 같다.

|ROLE|USER_ID|PASSWORD|
|---|---|---|
|ADMIN|admin|1234|
|USER|user1|1234|
|USER|user2|1234|
|USER|user3|1234|
|USER|user4|1234|
|USER|user5|1234|
|USER|user6|1234|

#### script encoding
data.sql을 사용할때 한글깨짐이 발생한다. [상세](https://blog.naver.com/myh814/221684494896) <br/>
application.yml에 다음과 같이 설정을 추가한다.
```
spring:
  datasource:
    sql-script-encoding: UTF-8
```
<br/>

## [REST API](https://blog.naver.com/myh814/221684474038)
|ROLE|ACTION|URI|Method|
|---|---|---|---|
|Anonymous|메인 페이지|/|GET|
|Anonymous|로그인|/login|GET|
|Anonymous|Password 찾기 화면|/pw|GET|
|Anonymous|Password 찾기|/pw|POST|
|Anonymous|게시글 목록 화면|/board/{boardNo}|GET|
|Anonymous|게시글 상세 화면|/board/{boardNo}/{postNo}|GET|
|USER|게시글 작성 화면|/board/{boardNo}/post|GET|
|USER|게시글 작성|/board/{boardNo}/post|POST|
|USER|게시글 수정 화면|/board/{boardNo}/post/{postNo}|GET|
|USER|게시글 수정|/board/{boardNo}/post/{postNo}|PUT|
|USER|게시글 삭제|/board/{boardNo}/post/{postNo}|DELETE|
|USER|사용자 페이지|/user|GET|
|USER|사용자 정보 수정|/user|POST|
|ADMIN|관리자 페이지|/admin|GET|
|ADMIN|게시판 관리 화면|/admin/board|GET|
|ADMIN|게시판 생성|/admin/board|POST|
|ADMIN|게시판 삭제|/admin/board/{boardNo}|DELETE|

#### 로그인 화면
![로그인화면](./src/main/webapp/img/login.jpg)

#### 게시글 목록 화면
![게시글목록화면](./src/main/webapp/img/boardList.jpg)

#### 게시글 상세 화면
![게시글상세화면](./src/main/webapp/img/postView.jpg)

#### 사용자 페이지 - 사용자 정보 수정
![사용자페이지](./src/main/webapp/img/userView.jpg)

#### 관리자페이지 - 게시판 관리 화면
![관리자페이지](./src/main/webapp/img/adminView.jpg)

<br/>

## [Spring Security](src/main/java/com/demo/webboard/config/security)
```java
@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    // ...
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeRequests()    // 권한요청 처리 설정 메서드
                .antMatchers("/board/**/post/**").hasAnyRole("USER", "ADMIN")    // post CUD 권한은 ROLE_USER에게만 존재
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")   // 사용자 페이지는 ROLE_USER에게만 존재
                .antMatchers("/admin/**").hasRole("ADMIN")   // 관리자 페이지는 ROLE_ADMIN에게만 존재
                .anyRequest().permitAll()   // 다른 요청은 누구든지 접근 가능
                .and()
            .formLogin()
                .loginPage("/login").permitAll()    // 로그인 기본 url
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
                .and()
            .logout()
                .logoutSuccessUrl("/").permitAll()
                .and()
            .csrf()
                .ignoringAntMatchers("/h2-console/**")
                .disable(); // GET메소드는 문제가 없는데 POST메소드만 안되서 CSRF 비활성화 시킴
    }
    // ...
}
```
#### [ROLE](https://blog.naver.com/myh814/221684510475)
|ROLE_NO|ROLE|
|---|---|
|1|ADMIN|
|2|USER|

#### [Password Encoding](src/main/java/com/demo/webboard/config/security/WebAuthenticationProvider.java)
```
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
```
#### Find Password - [Create temporary password](src/main/java/com/demo/webboard/main/service/impl/UserServiceImpl.java)
```
    private String getRamdomPassword() {
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '^', '&' };

        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        int len = charSet.length;
        for (int i=0; i<10; i++) {
//            idx = (int) (len * Math.random());
            idx = sr.nextInt(len);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
            sb.append(charSet[idx]);
        }

        return sb.toString();
    }
```
<br/>


## SiteMesh Filter
[SiteMeshFilter.java](src/main/java/com/demo/webboard/config/sitemesh/SiteMeshFilter.java)
```
public class SiteMeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        final String emptyDeco = "/WEB-INF/decorators/emptyDecorator.jsp";

        builder
            .addTagRuleBundle(new Sm2TagRuleBundle())
            .addDecoratorPath("/user*", emptyDeco)
            .addDecoratorPath("/admin*", emptyDeco)
            .addDecoratorPath("/*", "/WEB-INF/decorators/decorator.jsp")
                .addExcludedPath("/error*")
                .addExcludedPath("/login*")
                .addExcludedPath("/pw*")
                .addExcludedPath("/**/*List")
                .addExcludedPath("/**/list/**")
                .addExcludedPath("/favicon.ico");
    }
}
```

ServletInitializer.java
```
@Configuration
public class ServletInitializer extends SpringBootServletInitializer {
    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setOrder(Ordered.LOWEST_PRECEDENCE);
        filter.setFilter(new SiteMeshFilter()); //sitemesh 필터

        return filter;
    }
}
```
<br/>

## Spring Validator
일단 테스트로 BoardName만 validation 처리함.<br/>
BoardVO.java
```
@Data
public class BoardVO extends Paging {

    private Long boardNo;

    @NotEmpty
    @Length(max=255)
    private String boardName;
```
Controller.java
```
    @PostMapping("/board")
    @ResponseBody
    public Map<String, Object> createBoardMap(@RequestBody BoardVO boardVO, BindingResult bindingResult) throws Exception {
         if (bindingResult.hasErrors()) {
            // validation처리
        }
// ...
```
<br/>

## [Transaction](https://blog.naver.com/myh814/221742963874)
@Transactional Annotation 사용<br/>
[DatabaseConfiguration.java](src/main/java/com/demo/webboard/config/DatabaseConfiguration.java)
