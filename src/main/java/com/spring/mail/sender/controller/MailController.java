package com.spring.mail.sender.controller;

import com.spring.mail.sender.domain.EmailDTO;
import com.spring.mail.sender.domain.EmailFileDTO;
import com.spring.mail.sender.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class MailController {

    @Autowired
    private IEmailService emailService;

    @PostMapping("/sendMessage")
    public ResponseEntity<?> receiveRequestEmail(@RequestBody EmailDTO emailDTO){ //recibo un Json, por lo que con el RequestBody le decimos que el json debe ser convertido a DTO de tipo EmailDTO
        /*NOTA: JSON para convertirlo en objeto de java, eso se llama deserialización
        Cuando tenemos un obj de java y queremos convertir a JSON es serializacion
        La librería que usa spring para serializar o dese es Jackson Serializable*/
        System.out.println("*** Mensaje Recibido: " + emailDTO); //aqui no se implementa el metodo toString si el DTO no tiene la anotación Tostring, por lo que imprime es la refrencia en memoria

        emailService.sendEmail(emailDTO.getToUser(), emailDTO.getSubject(), emailDTO.getMessage());

        Map<String, String> response = new HashMap<>();//con ello quitamos el cast del responseentity
        response.put("estado", "Enviado"); //esta es la información que se visualizará cuando el estatus sea ok

        return ResponseEntity.ok(response);
        /*
        * curl --location 'http://localhost:8080/v1/sendMessage' \
--header 'Content-Type: application/json' \
--data-raw '{
    "toUser":[
        "ych.cursos@gmail.com",
        "crisoli27@gmail.com"
    ],
    "subject": "Esto es una prueba de envio con SimpleMailMessage",
    "message": "Hola soy Yenny esto es una prueba. Eliminala"
}'
        * */
    }

    @PostMapping("/sendMessageFile")
    public ResponseEntity<?> receiveRequestEmailWithFile(@ModelAttribute EmailFileDTO emailFileDTO){
        /*Cuando se trabaja con archivos no recibimos JSON, porque no se la llevan bien
        no son compatibles, se usa el mecanismo Form-Data, no como Json, por lo que se usa ModelAttribute
        llega en una secuencia de bytes, se guardan en resources/files */

        try {

            String fileName = emailFileDTO.getFile().getOriginalFilename(); //trae nombre+extensión

            Path path = Paths.get("src/mailk/resources/files/" + fileName); //ruta donde guardaremos el archivo

            Files.createDirectories(path.getParent());//si el directorio no existe, se crea, en caso de que no esté
            Files.copy(emailFileDTO.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING); //copiamos el archivo que entra a la ruta path, con el getInputStream obtenemos la secuencia de bytes, la ruta path, y el standar donde si existe lo reemplaca sino solo lo copia , asi no se repite

            File file = path.toFile(); //hacemos una referencia al archivo, ya que debemos enviar un File al servicio

            emailService.sendEmailWithFile(emailFileDTO.getToUser(), emailFileDTO.getSubject(), emailFileDTO.getMessage(), file);

            Map<String, String> response = new HashMap<>();//con ello quitamos el cast del responseentity
            response.put("estado", "enviado"); //esta es la información que se visualizará cuando el estatus sea ok
            response.put("archivo", fileName);

            return ResponseEntity.ok(response);

        }catch (Exception e){
           throw new RuntimeException("Error al enviar el Email con el archivo. " + e.getMessage());
        }
    }
}
