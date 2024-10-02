package com.example.altrieserciziee.tools;

import com.example.altrieserciziee.entities.User;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailgunSender {

    private String apiKey;
    private String domainName;

    public MailgunSender(@Value("${mailgun.apikey}")String apiKey, @Value("${mailgun.domainname}")String domainName) {
        this.apiKey = apiKey;
        this.domainName = domainName;
    }

    public void sendRegistrationEmail(User recipient){
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/"+ this.domainName + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", "kuroakuma@libero.it")
                .queryString("to", recipient.getEmail())
                .queryString("subject", "Registrazione completata con successo!")
                .queryString("html", "<html><body><h1>Benvenuto " + recipient.getName() + "!</h1>"
                        + "<p>Complimenti per esserti registrato con successo alla nostra piattaforma.</p>"
                        + "</body></html>")
                .asJson();

        System.out.println(response.getBody());
    }


}