package com.spring.mail.sender.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {

    @Value("${email.sender}") //con ello toma el valor del propertie
    private String emailUser;

    @Value("${email.password}") //con ello toma el valor del propertie
    private String password;

    @Bean
    public JavaMailSender getJavaMailSender(){

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl(); //ayuda a configurar las propiedadesdel enviador de correos, calse que interactua con nuestro email para enviar

        mailSender.setHost("smtp.gmail.com"); //en el host del proveedor del email, se pone el protocolo de todos los correos: smtp, si es con gmail sería así
        mailSender.setPort(587); //usa por defecto 587
        mailSender.setUsername(emailUser); //se pone el correo electronico
        mailSender.setPassword(password); //iría la contraseña del email, oj oen producción deben poner este valor en otra parte para no exponer este dato sensible

        Properties props = mailSender.getJavaMailProperties(); //objeto Properties, para unas adicionales
        props.put("mail.transport.protocol", "smtp"); //cual es el protocolo que usaremos
        props.put("mail.smtp.auth", "true"); //habilita la autenticación
        props.put("mail.smtp.starttls.enable", "true"); // es la mas importante, pq habilita el cifrado entre la comunicacion de esta app y el proveedor, esto aumenta la seguridad
        props.put("mail.debug","true"); //para que en la consola imprima información de por donde va, es para desarrollo, en produccion ponerlo en false

        return mailSender;
    }
}
