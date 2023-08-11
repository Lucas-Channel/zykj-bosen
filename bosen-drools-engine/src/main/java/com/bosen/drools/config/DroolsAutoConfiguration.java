package com.bosen.drools.config;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/10
 */
@Configuration
@ConditionalOnClass({ KieServices.class, KieContainer.class })
public class DroolsAutoConfiguration {
//    @Bean
//    public KieContainer kieContainer() {
//        return KieServices.Factory.get().getKieClasspathContainer();
//    }
//
//    @Bean
//    public KieSession kieSession() {
//        return kieContainer().newKieSession();
//    }
}
