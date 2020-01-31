# my-barber-app


## Para buildar o projeto (criar o jar):
./gradlew build


## Para rodar a aplicacao (nao precisa buildar antes):
./gradlew bootRun

## JWT
Para utilizar o JWT
1) start a api
2) abra o Postman, ou programa semelhante
3) verifique o link de acesso a api, o padrão é "localhost:8080"
4) configure o Postman para realizar um POST para a URL "localhost:8080/authenticate" passando o json abaixo no Body com a opção "raw" selecionada.
    {"username": "customer", "password": "password"}
5) execute a consulta e verifique o token retornado no header da requisição.