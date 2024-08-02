<div align = "center"> 
  
  <h1 align = "center">API para el Envío de Emails</h1>

  ![Static Badge](https://img.shields.io/badge/Version-1.0-cyan)
  ![Static Badge](https://img.shields.io/badge/Lenguaje-Java%2017-yellow)
  ![Static Badge](https://img.shields.io/badge/Spring%20Boot-3.3.1-fuchsia)
  ![Static Badge](https://img.shields.io/badge/MAVEN-green)

  ![Portada para Facebook Email Marketing Minimalista  Azul](https://github.com/user-attachments/assets/fe430fd6-402b-4550-a689-7167ae5b9598)
  
</div>

# Conceptos

* Se usa la librería Java Mail Sender
* Para el envío de emails se debe configurar la clave del correo a través de un cifrado para aumentar la seguridad, a fin de no usar la contraseña personal (Ver MailConfiguration.class), sigue estos pasos:
  * Debes ir a la Verificación en 2 pasos de tu email en la Administración de tu cuenta, obtienes los códigos y activas.
  * Luego anda a Contraseñas de Aplicaciones, ingresas el nombre que desees (Ej.: nombre de tu app), con ello se genera una contraseña para conectarse a tu cuenta (con permiso limitado), esta sería la contraseña a colocar en tu propertie
  * Puedes eliminar dicha contraseña cuando desees

# Objetivo
Esta API es un ejercicio para el envío de email con Maven que sirve de estudios y referencia.

# Pruebas

![Static Badge](https://img.shields.io/badge/Postman-curl-orange?link=curl%20--location%20--request%20POST%20'http%3A%2F%2Flocalhost%3A8080%2Fsumar%3FnumberA%3D4%26numberB%3D3') 
curl --location --request POST 'http://localhost:8080/sumar?numberA=4&numberB=3'


>[!TIP]
>
> Revisar los comentarios dentro del código para ayudarte a entender
>

## Desarrollador

[Yenny Chipamo](https://www.linkedin.com/in/yenny-chipamo/)
