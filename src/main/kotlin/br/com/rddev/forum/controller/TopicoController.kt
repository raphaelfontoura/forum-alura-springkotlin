package br.com.rddev.forum.controller

import br.com.rddev.forum.model.Curso
import br.com.rddev.forum.model.Topico
import br.com.rddev.forum.model.Usuario
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topicos")
class TopicoController {

    @GetMapping
    fun listar(): List<Topico> {
        val curso = Curso(id = 1, nome = "Kotlin", categoria = "Programação")
        val usuario = Usuario(id = 1, nome = "Cicrano", email = "cicrano@email.com")
        val topicoList = listOf(
            Topico(
                id = 1,
                titulo = "titulo 1",
                mensagem = "dúvida sobre spring",
                curso = curso,
                autor = usuario
            ),
            Topico(
                id = 2,
                titulo = "titulo 2",
                mensagem = "dúvida sobre kotlin",
                curso = curso,
                autor = usuario
            ),
            Topico(
                id = 3,
                titulo = "titulo 3",
                mensagem = "dúvida sobre kotlin com spring",
                curso = curso,
                autor = usuario
            )
        )
        return topicoList

    }
}