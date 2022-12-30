package br.com.rddev.forum.service

import br.com.rddev.forum.dto.NovoTopicoForm
import br.com.rddev.forum.dto.TopicoView
import br.com.rddev.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicos: List<Topico> = emptyList(),
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
) {

    fun listar(): List<TopicoView> {
        return topicos.map { topico ->
            TopicoView(
                id = topico.id,
                titulo = topico.mensagem,
                mensagem = topico.mensagem,
                status = topico.status,
                dataCriacao = topico.dataCriacao
            )
        }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos
            .first { topico -> topico.id == id }

        return TopicoView(
            id = topico.id,
            titulo = topico.mensagem,
            mensagem = topico.mensagem,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )
    }

    fun cadastrar(dto: NovoTopicoForm) {
        val topico = Topico(
            id = topicos.size + 1L,
            titulo = dto.titulo,
            mensagem = dto.mensagem,
            curso = cursoService.buscarPorId(dto.idCurso),
            autor = usuarioService.buscarPorId(dto.idAutor)
        )
        topicos = topicos.plus(topico)
    }
}