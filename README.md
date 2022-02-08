
# Pure Java Spark Api

Um projeto de uma api utilizando da linguagem java, saindo um pouco do padrão de 
Spring, e utilizando uma biblioteca chamada Spark para construir nossa api. 



## Bibliotecas Utilizadas

**Para Construção das Rotas :** [Spark](https://sparkjava.com/)

**Para Conexão com o Banco :** [My Batis](https://mybatis.org/mybatis-3/)

**Para Logging :** [SLF4J](https://www.slf4j.org/)

**Para Serialização de Deserialização de JSON:** [Gson](https://github.com/google/gson)

**Banco de Dados Utilizado :** MY SQL

**Há mais algumas libs utilizadas no arquivo pom.xml, mas são destinadas a testes.**
## Documentação da API

#### Retorna todos os usuários

```http
  GET /myapi/v1/users
```



#### Retorna um usuário. 

```http
  GET /myapi/v1/users/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do usuário que você quer |

#### Adiciona um usuário. 

```http
  POST /myapi/v1/users
```

#### Usuário :

| Atributo   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `name`      | `string` | **Obrigatório**. O nome do usuário |
| `email`      | `string` | **Obrigatório**. O email do usuário |
| `password`      | `string` | **Obrigatório**. A senha do usuário |



#### Altera um usuário. 

```http
  PUT /myapi/v1/users/${id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do usuário que você quer |

#### Usuário :

| Atributo   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `name`      | `string` | **Opcional**. O nome do usuário |
| `email`      | `string` | **Opcional**. O email do usuário |
| `password`      | `string` | **Opcional**. A senha do usuário |


#### Deleta um usuário. 

```http
  DELETE /myapi/v1/users/${id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do usuário que você quer 



## Requisitos

Para rodar essa api é necessário :

- Java versão 11 ou superior.
- Maven versão 3.8 ou superior.
- Uma instancia do MYSQL rodando na sua máquina.
 
    
## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/MaidanaGustavo/pure-java-spark-api.git
```

Entre no diretório do projeto

```bash
  cd pure-java-spark-api
```
 * Abra o seu editor de código de preferência;
 * Vá até src/main/resources;
 * No arquivo jdbc.properties altere o host do seu banco de dados, o usuario e a senha;
 * Acesse seu banco de dados e crie o database e tabela conforme está no arquivo sql em src/database;

#### Com tudo configurado podemos instalar as dependencias e rodar o projeto.
 No diretório do projeto, rode o seguinte comando :

 ```bash
  mvn clean compile assembly:single &&java -cp target/spk-1-jar-with-dependencies.jar spk.App
```




#### Após rodar o comando, no seu terminal :
![App Screenshot](https://iili.io/0xrqyN.png)

*********************************************


#### A aplicação será iniciada em : http://localhost:4567


## Referência

 - [Spark Java](https://sparkjava.com/)
 - [My Batis](https://mybatis.org/mybatis-3/)
 - [SLF4J](https://www.slf4j.org/)
 - [Gson](https://github.com/google/gson)
 

## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/MaidanaGustavo)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/gustavo-r%C3%ADbolis-maidana-b034811b7/)
[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/gustavomaidana_)

