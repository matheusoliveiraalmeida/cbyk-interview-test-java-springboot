## APP

```docker-compose build``` para construção das imagens.
```docker-compose up``` para iniciar os containers.

Ao inicializar a aplicação a mesma já roda as migrations dentro do resources.

## Swagger

http://localhost:8080/swagger-ui/index.html#/
username: user
pass: password

### Postman

```shell 

curl --location --request GET 'http://localhost:8080/api/contas/1' \
--header 'accept: */*' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
--header 'Cookie: JSESSIONID=6E78550B5F5A46CD74886268BFA6802D; JSESSIONID=6E78550B5F5A46CD74886268BFA6802D' 

```