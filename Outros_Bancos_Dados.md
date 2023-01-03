### Para saber mais: Outros bancos de dados

Na API, utilizamos o banco de dados H2 e vimos como ter acesso a ele via interface gráfica fornecida pelo Spring Boot.

No entanto, é totalmente possível utilizar outros bancos de dados na API, como o MySQL, PostgreSQL, dentre outros, bastando para isso substituir no pom.xml a dependência do H2 e as configurações no arquivo application.yml.

Por exemplo, para utilizar o MySQL, basta substituir a dependência do H2 no pom.xml para:

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

E alterar as configurações no application.yml para:

```yaml
spring:
  datasource:
    driverClassName: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost/forum
    username: root
    password: root
  jpa:
    database-platform: org.hibernate.dialect.MySQL8Dialect
```

Lembrando de substituir o username e password de acordo com as configurações de seu MySQL.