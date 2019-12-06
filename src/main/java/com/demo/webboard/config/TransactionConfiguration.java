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

@Aspect
@Configuration
public class TransactionConfiguration {
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.demo.**.*Impl.*(..))";
    private static final int TX_METHOD_TIMEOUT = 3;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        Properties txAttributes = new Properties();

        List<RollbackRuleAttribute> rollbackRules = new ArrayList<>();
        rollbackRules.add(new RollbackRuleAttribute(Exception.class));

        DefaultTransactionAttribute readOnlyAttribute = new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED);
        readOnlyAttribute.setReadOnly(true);
        readOnlyAttribute.setTimeout(TX_METHOD_TIMEOUT);

        RuleBasedTransactionAttribute writeAttribute = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);
        writeAttribute.setTimeout(TX_METHOD_TIMEOUT);

        // PROPAGATION_REQUIRED,ISOLATION_DEFAULT,timeout_3,readOnly
        String readOnlyTransactionAttributesDefinition = readOnlyAttribute.toString();
        // PROPAGATION_REQUIRED,ISOLATION_DEFAULT,timeout_3,-java.lang.Exception
        String writeTransactionAttributesDefinition = writeAttribute.toString();

        txAttributes.setProperty("select*", readOnlyTransactionAttributesDefinition);
        txAttributes.setProperty("insert*", "-java.lang.Exception");
        txAttributes.setProperty("update*", writeTransactionAttributesDefinition);
        txAttributes.setProperty("delete*", writeTransactionAttributesDefinition);

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
