package br.com.rddev.forum.service

import br.com.rddev.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private var usuarios : List<Usuario> = emptyList()
) {

    init {
        usuarios = listOf(
            Usuario(
                id = 1,
                nome = "Ana",
                email = "ana@email.com"
            ),
            Usuario(
                id = 2,
                nome = "Sophia",
                email = "sophia@email.com"
            )
        )
    }

    fun buscarPorId(id: Long): Usuario {
        return usuarios.first { usuario -> usuario.id == id }
    }
}
