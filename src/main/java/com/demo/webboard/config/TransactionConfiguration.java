package com.demo.webboard.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Deprecated
//@Aspect
//@Configuration
public class TransactionConfiguration {
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.demo.**.*Impl.*(..))";
    private static final int TX_METHOD_TIMEOUT = 3;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {

        // PROPAGATION_REQUIRED,ISOLATION_DEFAULT,timeout_3,readOnly
        DefaultTransactionAttribute readOnlyAttribute = new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED);
        readOnlyAttribute.setReadOnly(true);
        readOnlyAttribute.setTimeout(TX_METHOD_TIMEOUT);
        String readOnlyTransactionAttributesDefinition = readOnlyAttribute.toString();

        // PROPAGATION_REQUIRED,ISOLATION_DEFAULT,timeout_3,-java.lang.Exception
        List<RollbackRuleAttribute> rollbackRules = new ArrayList<>();
        rollbackRules.add(new RollbackRuleAttribute(Exception.class));
        RuleBasedTransactionAttribute writeAttribute = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);
        writeAttribute.setTimeout(TX_METHOD_TIMEOUT);
        String writeTransactionAttributesDefinition = writeAttribute.toString();

        Properties txAttributes = new Properties();
        txAttributes.setProperty("select*", readOnlyTransactionAttributesDefinition);
        txAttributes.setProperty("insert*", writeTransactionAttributesDefinition);
        txAttributes.setProperty("update*", writeTransactionAttributesDefinition);
        txAttributes.setProperty("delete*", writeTransactionAttributesDefinition);

        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        transactionInterceptor.setTransactionAttributes(txAttributes);
        transactionInterceptor.setTransactionManager(transactionManager);

        return transactionInterceptor;
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
