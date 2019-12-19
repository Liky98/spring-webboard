package com.demo.webboard.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

@Deprecated
//@Aspect
//@Component
@Slf4j
public class DbTransaction {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Around("execution(* com.demo..*.insert*(..))")
    public Object insert(ProceedingJoinPoint joinpoint) throws Throwable {
        //공통 기능이 적용되는 메서드가 어떤 메서드인지 출력하기 위해 메서드명을 얻어옴
        String signatureStr = joinpoint.getSignature().toShortString();

        // https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/transaction/TransactionDefinition.html
        // TransactionDefinition.withDefaults()가 Spring 5.2 이상 가능하기 때문에 springboot 2.2로 version up
        TransactionStatus transactionStatus = transactionManager.getTransaction(TransactionDefinition.withDefaults());
        log.info(signatureStr + " 트랜잭션 시작");
        Object savepoint = transactionStatus.createSavepoint();

        //공통기능
        try {
            transactionManager.commit(transactionStatus);
            return joinpoint.proceed(); //핵심 기능 실행
        } catch (Exception e){ // 예외 발생 롤백
            transactionStatus.rollbackToSavepoint(savepoint);
            log.debug("Error 발생. Rollback | stack trace : ", e);
            throw e;
        } finally {
            //공통기능
            log.info(signatureStr + " 트랜잭션 정상 종료");
        }
    }

    @Around("execution(* com.demo..*.update*(..))")
    public Object update(ProceedingJoinPoint joinpoint) throws Throwable {
        //공통 기능이 적용되는 메서드가 어떤 메서드인지 출력하기 위해 메서드명을 얻어옴
        String signatureStr = joinpoint.getSignature().toShortString();
        log.info(signatureStr + " 트랜잭션 시작");

        //공통기능
        try {
            return joinpoint.proceed(); //핵심 기능 실행
        } finally {
            //공통기능
            log.info(signatureStr + " 트랜잭션 정상 종료");
        }
    }

    @Around("execution(* com.demo..*.delete*(..))")
    public Object delete(ProceedingJoinPoint joinpoint) throws Throwable {
        //공통 기능이 적용되는 메서드가 어떤 메서드인지 출력하기 위해 메서드명을 얻어옴
        String signatureStr = joinpoint.getSignature().toShortString();
        log.info(signatureStr + " 트랜잭션 시작");

        //공통기능
        try {
            return joinpoint.proceed(); //핵심 기능 실행
        } finally {
            //공통기능
            log.info(signatureStr + " 트랜잭션 정상 종료");
        }
    }
}
