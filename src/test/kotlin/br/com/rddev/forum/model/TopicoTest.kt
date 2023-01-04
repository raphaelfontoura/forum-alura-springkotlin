package br.com.rddev.forum.model

object TopicoTest {
    fun build() = Topico(
        id = 1L,
        titulo = "Kotlin Basico",
        mensagem = "Aprendendo kotlin basico",
        curso = CursoTest.build(),
        autor = UsuarioTest.build()
    )
}