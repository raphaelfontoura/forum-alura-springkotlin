### Configurando o Redis

Baixando a imagem do Redis.
```bash
$ docker pull redis:latest
```

Rodando o Redis.
```shell
$ docker run -d -p 6379:6379 --name redis-local redis:latest
```