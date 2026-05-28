package com.company.networkmovers.integration.thirdparty.n8n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class N8nWebhookClient {

    private static final Logger log = LoggerFactory.getLogger(N8nWebhookClient.class);

    private final RestTemplate restTemplate;
    private final String webhookUrl;

    public N8nWebhookClient(@Value("${integration.n8n.webhook-url}") String webhookUrl) {
        this.restTemplate = new RestTemplate();
        this.webhookUrl = webhookUrl;
    }

    /**
     * Triggers the n8n webhook.
     * Currently does not send a custom payload, just an empty JSON body for testing.
     */
    public void triggerWebhook() {
        try {
            log.info("Sending request to n8n webhook at: {}", webhookUrl);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Empty body for now
            Map<String, Object> payload = new HashMap<>();
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    webhookUrl,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("n8n webhook triggered successfully. Status code: {}", response.getStatusCode());
            } else {
                log.warn("n8n webhook returned non-success status code: {}", response.getStatusCode());
            }
        } catch (Exception e) {
            log.error("Failed to call n8n webhook at {}", webhookUrl, e);
        }
    }
}
