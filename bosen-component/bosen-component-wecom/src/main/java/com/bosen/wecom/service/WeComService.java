package com.bosen.wecom.service;

import cn.hutool.json.ObjectMapper;
import com.bosen.wecom.config.WeComConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2025/4/27
 */
@Service
public class WeComService {

    @Resource
    private RestTemplate restTemplate;

    private final String TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

    @Resource
    private WeComConfig weComConfig;

    @Resource
    private ObjectMapper objectMapper;

    private static final int MAX_RETRY = 3;

    public String getAccessToken() {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={corpid}&corpsecret={corpsecret}";

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("corpid", weComConfig.getCorpId());
        uriVariables.put("corpsecret", weComConfig.getCorpSecret());

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class, uriVariables);
        Map<String, Object> body = response.getBody();

        if (body != null && body.get("errcode").equals(0)) {
            return (String) body.get("access_token");
        } else {
            throw new RuntimeException("获取AccessToken失败");
        }
    }

    public void sendTextMessage(String content, String... toUser) {
        String accessToken = getAccessToken();
        String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + accessToken;

        Map<String, Object> request = new HashMap<>();
        request.put("touser", String.join("|", toUser));
        request.put("msgtype", "text");
        request.put("agentid", weComConfig.getAgentId());

        Map<String, String> text = new HashMap<>();
        text.put("content", content);
        request.put("text", text);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
        Map<String, Object> body = response.getBody();

        if (body == null || !body.get("errcode").equals(0)) {
            throw new RuntimeException("发送消息失败");
        }
    }

//    private void sendPostRequest(String url, Object data) throws IOException {
//        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.setHeader("Content-Type", "application/json");
//
//            String json = objectMapper.writeValueAsString(data);
//            httpPost.setEntity(new StringEntity(json, StandardCharsets.UTF_8));
//
//            HttpResponse response = httpClient.execute(httpPost);
//            String responseBody = EntityUtils.toString(response.getEntity());
//
//            Map<String, Object> result = objectMapper.readValue(responseBody, HashMap.class);
//            if (!result.get("errcode").equals(0)) {
//                throw new RuntimeException("发送消息失败: " + result.get("errmsg"));
//            }
//        }
//    }

    public void sendNewsMessage(String title, String description, String url, String picUrl, String... toUser)
            throws IOException {
        String accessToken = getAccessToken();
        String apiUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + accessToken;

        Map<String, Object> message = new HashMap<>();
        message.put("touser", String.join("|", toUser));
        message.put("msgtype", "news");
        message.put("agentid", weComConfig.getAgentId());

        Map<String, Object> news = new HashMap<>();

        List<Map<String, String>> articles = new ArrayList<>();
        Map<String, String> article = new HashMap<>();
        article.put("title", title);
        article.put("description", description);
        article.put("url", url);
        article.put("picurl", picUrl);
        articles.add(article);

        news.put("articles", articles);
        message.put("news", news);

//        sendPostRequest(apiUrl, message);
    }

    public void sendMarkdownMessage(String content, String... toUser) throws IOException {
        String accessToken = getAccessToken();
        String apiUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + accessToken;

        Map<String, Object> message = new HashMap<>();
        message.put("touser", String.join("|", toUser));
        message.put("msgtype", "markdown");
        message.put("agentid", weComConfig.getAgentId());

        Map<String, String> markdown = new HashMap<>();
        markdown.put("content", content);
        message.put("markdown", markdown);

//        sendPostRequest(apiUrl, message);
    }

    public void sendWithRetry(String content, String... toUser) {
        int retryCount = 0;
        while (retryCount < MAX_RETRY) {
            try {
                this.sendTextMessage(content, toUser);
                return;
            } catch (Exception e) {
                retryCount++;
                if (retryCount == MAX_RETRY) {
                    throw new RuntimeException("消息发送失败，重试次数已达上限", e);
                }
                try {
                    Thread.sleep(1000 * retryCount); // 指数退避
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}