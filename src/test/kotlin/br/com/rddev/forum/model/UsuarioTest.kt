package br.com.rddev.forum.model

object UsuarioTest {
    fun build() = Usuario(
        id = 1L,
        nome = "Cicrano",
        email = "cicrano@email.com",
        password = "1234"
    )
}
