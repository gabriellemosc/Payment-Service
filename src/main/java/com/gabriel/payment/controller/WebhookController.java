package com.gabriel.payment.controller;


import com.gabriel.payment.dto.WebhookRequest;
import com.gabriel.payment.service.WebhookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final WebhookService webhookService;

    public WebhookController(WebhookService webhookService){
        this.webhookService = webhookService;
    }

    @PostMapping
    public ResponseEntity<Void> receiveWebhook(@RequestBody WebhookRequest request){

        webhookService.process(
             request               //get new status
        );

        return ResponseEntity.ok().build(); //return 200
    }

}
