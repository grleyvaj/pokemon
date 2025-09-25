# pokemon
Expone un servicio SOAP que ofrece métodos relacionados con Pokémon. Internamente, el servicio actúa como un adaptador y consume la API pública de [PokeAPI](https://pokeapi.co/api/v2/pokemon/) , transformando las respuestas REST en operaciones accesibles vía SOAP.



### Setup
Se han configurado dos perfiles uno para trabajar con la BD h2 y otro con mysql:

Para activar `mysql`
```bash
  mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```
Para activar `h2`
```bash
  mvn spring-boot:run -Dspring-boot.run.profiles=h2
```
La variable de entorno `ENV_PORT` ha sido configurada para arrancar por el `8081`

Use esta ENV para acceder a la consola h2, disponible en: http://localhost:8081/h2-console

![img.png](images/h2-console.png)


### Documentación del servicio expuesto
Endpoints que expone SpringDoc
- JSON del OpenAPI (documentación en crudo): http://localhost:8081/api-docs
- Interfaz de Swagger UI (navegable en el navegador): http://localhost:8081/swagger-ui/index.html
