# Instructivo de la Aplicación

Este documento describe cómo ejecutar y probar la aplicación, así como las operaciones que se pueden realizar a través de la API.

> **Observación** : 
> Consideración al compilar con maven, el proyecto se encuentra desarrollado con java 17, 
> compilarlo con otra versión podría ocasionar errores

  
--- 

## Ejecutar el Programa con Maven

Se debe compilar con maven con el siguiente comando : 

```
mvn clean package
```

Al finalizar se encuentra un jar generado que se puede ejecutar con: 

```
java -jar target/bci-0.0.1-SNAPSHOT.jar
```

---

## Ejecutar el Programa con Docker

La aplicación incluye un archivo `Dockerfile` y un archivo `docker-compose.yml`. Si tienes Docker instalado en tu máquina, puedes ejecutar la aplicación con el siguiente comando:

```bash
docker-compose up
```

Para bajar la aplicación 


```bash
docker-compose down
```

---

## Prueba de la aplicación

Para probar la API, puedes utilizar Swagger, que proporciona una interfaz gráfica para interactuar con los recursos disponibles.

> **Observación** : 
> En caso de utilizar otra aplicación ejemplo: Postman, se debera agregar una autenticación de tipo "Basic Auth" con username "bci" y password "1234" 

## Swagger Ui

La documentación interactiva de la API está disponible en http://localhost:8080/swagger-ui/index.html#/. Aquí podrás ver todos los endpoints y realizar pruebas directamente desde el navegador.

---

## Endpoints disponibles

## 1. GET /users

Retorna la lista de los usuarios guardados en la base de datos

### Response status:

200 - OK

### Response example
```json
[
  {
    "name": "Will Smith",
    "email": "willSmith@gmail.com",
    "password": "pass",
    "phones": [
      {
        "number": 1,
        "cityCode": 1,
        "countryCode": 57
      }
    ],
    "id": "8d602934-21ce-4494-9189-719a275d1d65",
    "created": "2024-12-18T09:44:42.728918-03:00",
    "modified": null,
    "lastLogin": "2024-12-18T09:44:42.729922-03:00",
    "token": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJKV1RiY2kiLCJzdWIiOiJXaWxsIFNtaXRoIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTczNDUyNTg4MiwiZXhwIjoxNzM0NTI2NDgyfQ.w6mWD2wj5qX9hgMjm21YS7aboxfFNTXlhF81bbldGYI",
    "active": true
  }
]
```

## 2. POST /users
 
Guarda un usuario en la base de datos

### Request example

```json
{
  "name": "Will Smith",
  "email": "willSmith@gmail.com",
  "password": "pass",
  "phones": [
    {
      "number": "1",
      "cityCode": "1",
      "countryCode": "57"
    }
  ]
}
```

## Response status:

201 - CREATED

## Response example
```json
{
  "name": "Will Smith",
  "email": "willSmith@gmail.com",
  "password": "pass",
  "phones": [
    {
      "number": 1,
      "cityCode": 1,
      "countryCode": 57
    }
  ],
  "id": "7e412a78-5cff-4e17-949d-767ee586d76f",
  "created": "2024-12-18T09:39:27.2817946-03:00",
  "modified": null,
  "lastLogin": "2024-12-18T09:39:27.2817946-03:00",
  "token": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJKV1RiY2kiLCJzdWIiOiJXaWxsIFNtaXRoIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTczNDUyNTU2NywiZXhwIjoxNzM0NTI2MTY3fQ.xz3O_WeS6lu5aLqztVyx9yHS2YqaHUnBFIdHEND1H7Q",
  "active": true
}
```

## 3. PUT /users/{id}

Modifica o guarda un usuario en la base de datos

### Request example

Path-Variable : {id} del usuario a modificar

Body
```json
{
  "name": "Will Moon",
  "email": "willMoon@gmail.com",
  "password": "pass",
  "phones": [
    {
      "number": "2",
      "cityCode": "2",
      "countryCode": "234"
    }
  ]
}
```

### Response status:

200 - OK

### Response example
```json
{
  "name": "Will Moon",
  "email": "willMoon@gmail.com",
  "password": "pass",
  "phones": [
    {
      "number": 2,
      "cityCode": 2,
      "countryCode": 234
    }
  ],
  "id": "8d602934-21ce-4494-9189-719a275d1d65",
  "created": "2024-12-18T09:44:42.728918-03:00",
  "modified": "2024-12-18T09:54:47.3857538-03:00",
  "lastLogin": "2024-12-18T09:44:42.729922-03:00",
  "token": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJKV1RiY2kiLCJzdWIiOiJXaWxsIFNtaXRoIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTczNDUyNTg4MiwiZXhwIjoxNzM0NTI2NDgyfQ.w6mWD2wj5qX9hgMjm21YS7aboxfFNTXlhF81bbldGYI",
  "active": true
}
```


## 4. DELETE /user/{id}


### Response status:

204 - No content
