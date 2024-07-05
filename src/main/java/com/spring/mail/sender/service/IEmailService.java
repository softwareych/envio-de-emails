package com.spring.mail.sender.service;

import java.io.File;

public interface IEmailService {

    void sendEmail(String[] toUser, String subject, String message); //para enviar email (para, asunto, cuerpo)

    void sendEmailWithFile(String[] toUser, String subject, String message, File file); //envío de emails con adjunto (java.io)



}
