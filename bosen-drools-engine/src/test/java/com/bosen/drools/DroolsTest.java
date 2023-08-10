package com.bosen.drools;

import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/10
 */
public class DroolsTest {

    @Test
    public void test() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
