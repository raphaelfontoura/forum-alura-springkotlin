package br.com.rddev.forum.repository

import br.com.rddev.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long>{

    fun findByEmail(email: String?) : Usuario?
}