package com.bluesoft.excercise2;

public class Messenger {

    private TemplateEngine templateEngine;
    private MailServer mailServer;

    public Messenger(MailServer mailServer, TemplateEngine templateEngine) {
        this.mailServer = mailServer;
        this.templateEngine = templateEngine;
    }

    public void sendMessage(Client client, Template template) {
        String body = templateEngine.prepareMessageBody(template, client);
        mailServer.send(client.getEmailAddress(), body);
    }
}
