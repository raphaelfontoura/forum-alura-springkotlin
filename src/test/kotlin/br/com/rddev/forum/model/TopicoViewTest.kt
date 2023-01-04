package br.com.rddev.forum.model

import br.com.rddev.forum.dto.TopicoView
import java.time.LocalDate
import java.time.LocalDateTime

object TopicoViewTest {

    fun build() = TopicoView(
        id = 1L,
        titulo = "Kotlin Basico",
        mensagem = "Aprendendo kotlin basico",
        status = StatusTopico.NAO_RESPONDIDO,
        dataCriacao = LocalDateTime.now(),
        dataAlteracao = LocalDate.now()
    )
}