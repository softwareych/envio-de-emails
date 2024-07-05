package com.spring.mail.sender.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements IEmailService{ //implementar de la interface con sus metodos

    @Value("${email.sender}") //con ello toma el valor del propertie
    private String emailUser;

    @Autowired
    private JavaMailSender javaMailSender; //inyectamos el JavaMailSender del bean de la config
    @Override
    public void sendEmail(String[] toUser, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(); //objeto que ayuda a construir el msj

        mailMessage.setFrom(emailUser); //Quien enviará el correo
        mailMessage.setTo(toUser); //arreglo para poner los destinatarios (o uno)
        mailMessage.setSubject(subject); //titulo del correo
        mailMessage.setText(message);  //cuerpo del correo

        javaMailSender.send(mailMessage); //con esto se envía el correo con lo configurado

    }

    @Override
    public void sendEmailWithFile(String[] toUser, String subject, String message, File file) {

        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage(); //es un objeto diferente el Mime
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name()); //solicita un try/catch, el true es para decir que se enviará un archivo, y el utf8 es para decir la codificación que usará el archivo

            mimeMessageHelper.setFrom(emailUser); //desde
            mimeMessageHelper.setTo(toUser); //hasta (varios)
            mimeMessageHelper.setSubject(subject); // asunto
            mimeMessageHelper.setText(message); //cuerpo
            mimeMessageHelper.addAttachment(file.getName(), file); //l nombre del archivo y luego el archivo

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
/*
curl --location 'http://localhost:8080/v1/sendMessageFile' \
--form 'toUser="ych.cursos@gmail.com, crisoli27@gmail.com"' \
--form 'subject="Esto es una prueba de envio con MimeMessage"' \
--form 'message="Hola soy Yenny esto es una prueba. Eliminala"' \
--form 'file=@"/C:/Users/criso/Downloads/computer-4484282.jpg"'
 */

    }
}
