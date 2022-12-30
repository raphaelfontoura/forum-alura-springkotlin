package br.com.rddev.forum.service

import br.com.rddev.forum.model.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(
    private var cursos: List<Curso>
) {

    init {
        cursos = listOf(
            Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programação"
            ),
            Curso(
                id = 2,
                nome = "Java",
                categoria = "Programação"
            )
        )
    }

    fun buscarPorId(id: Long): Curso {
        return cursos.first {curso -> curso.id == id}
    }
}
