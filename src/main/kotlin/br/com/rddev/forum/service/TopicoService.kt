package br.com.rddev.forum.service

import br.com.rddev.forum.dto.NovoTopicoForm
import br.com.rddev.forum.dto.TopicoView
import br.com.rddev.forum.mapper.Mapper
import br.com.rddev.forum.mapper.TopicoFormMapper
import br.com.rddev.forum.mapper.TopicoViewMapper
import br.com.rddev.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicos: List<Topico> = emptyList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper
) {

    fun listar(): List<TopicoView> {
        return topicos.map { topico ->
            topicoViewMapper.map(topico)
        }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos
            .first { topico -> topico.id == id }

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm) {
        val topico = topicoFormMapper.map(form)
        topico.id = topicos.size + 1L
        topicos = topicos.plus(topico)
    }
}