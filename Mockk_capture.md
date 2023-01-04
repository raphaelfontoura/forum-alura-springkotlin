### Para saber mais: usando capture

Um recurso bastante interessante que podemos utilizar do mockK é a função de capturar. Por exemplo, no teste deve listar tópicos a partir do nome do curso, sabemos que o método `findByCursoNome`, do repositório, devolve um `Page<Topico>`; porém nós não garantimos que o objeto `Topico` retornado está correto. Não conseguimos simplesmente pegar o retorno do método `listar(“Kotlin avancado”, paginacao)`, pois no momento do retorno, o objeto `Topico` já foi convertido para `TopicoView`. Então, como garantir? Nesse cenário podemos utilizar o *capture*. Para criar um capturing nós utilizamos uma função chamada `slot()`.

```kotlin
val slot = slot<Topico>()
```
A grande questão agora é: em que momento no teste conseguimos recuperar o objeto `Topico`? A função map de `TopicoViewMapper` recebe e o transforma em um `TopicoView`. Como temos acesso a este recurso no nosso teste, podemos capturar o objeto a partir dele:

```kotlin
every { topicoViewMapper.map(capture(slot)) } returns TopicoViewTest.build()
```

Uma vez que temos o capture, podemos utilizar o AssertJ e fazer asserções para garantir que o objeto estava correto:

```kotlin
val topico = TopicoTest.build()
assertThat(slot.captured.titulo).isEqualTo(topico.titulo)
assertThat(slot.captured.mensagem).isEqualTo(topico.mensagem)
assertThat(slot.captured.status).isEqualTo(topico.status)
```

Como podemos ver, este recurso é muito interessante para validação de informações que não temos acesso no escopo do teste.