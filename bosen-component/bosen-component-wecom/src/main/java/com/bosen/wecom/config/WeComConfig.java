package com.bosen.wecom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2025/4/27
 */
@Configuration
public class WeComConfig {

    /**
     * 企业ID
     */
    @Value("${wecom.corp-id}")
    private String corpId;

    /**
     * 应用ID
     */
    @Value("${wecom.agent-id}")
    private String agentId;

    /**
     * 应用Secret
     */
    @Value("${wecom.corp-secret}")
    private String corpSecret;

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getCorpSecret() {
        return corpSecret;
    }

    public void setCorpSecret(String corpSecret) {
        this.corpSecret = corpSecret;
    }
}
