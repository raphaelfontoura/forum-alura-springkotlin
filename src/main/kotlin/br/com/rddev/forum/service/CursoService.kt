package br.com.rddev.forum.service

import br.com.rddev.forum.exception.NotFoundException
import br.com.rddev.forum.model.Curso
import br.com.rddev.forum.repository.CursoRepository
import br.com.rddev.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class CursoService(
    private val repository: CursoRepository
) {

    fun buscarPorId(id: Long): Curso {
        return repository.findById(id).orElseThrow {NotFoundException("Curso não encontrado.")}
    }
}
