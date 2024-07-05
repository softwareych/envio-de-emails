package com.spring.mail.sender.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter
@Setter //aquí el DTO si lo necesita, pq recibe un form-data, no un Json, y el form-data si lo necesita
@AllArgsConstructor
@NoArgsConstructor
public class EmailFileDTO { //Solo para los correos con adjuntos

    private String[] toUser;
    private String subject;
    private String message;
    private MultipartFile file; //el multipartfile es para el mecanismo con el que spring maneja los archivos
    //importante: el multipartfile debe configurarse en el properties, para activarlo y definir tamaños
}
