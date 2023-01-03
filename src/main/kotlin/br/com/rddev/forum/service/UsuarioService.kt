package br.com.rddev.forum.service

import br.com.rddev.forum.exception.NotFoundException
import br.com.rddev.forum.model.Usuario
import br.com.rddev.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val repository: UsuarioRepository
) : UserDetailsService {

    fun buscarPorId(id: Long): Usuario {
        return repository.findById(id).orElseThrow { NotFoundException("Usuário não encontrado.") }
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = repository.findByEmail(username) ?: throw RuntimeException()
        return UserDetail(usuario)
    }
}
