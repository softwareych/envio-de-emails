package com.spring.mail.sender.domain;

/*
public record EmailDTO(String[] toUser,
                      String subject,
                      String message) { //POJO: representan los JSON que llegan y convertirlos a un objeto DTO:DataTransfer Object

//el record viene de java 14, donde se definen los atributos como parametros, y esto genera solo los getters, pq serian inmutables


}*/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailDTO{ //POJO: representan los JSON que llegan y convertirlos a un objeto DTO:DataTransfer Object

    private String[] toUser;
    private String subject;
    private String message;

}