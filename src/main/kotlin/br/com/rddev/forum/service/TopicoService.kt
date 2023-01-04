package br.com.rddev.forum.service

import br.com.rddev.forum.dto.AtualizacaoTopicoForm
import br.com.rddev.forum.dto.NovoTopicoForm
import br.com.rddev.forum.dto.TopicoPorCategoriaDto
import br.com.rddev.forum.dto.TopicoView
import br.com.rddev.forum.exception.NotFoundException
import br.com.rddev.forum.mapper.Mapper
import br.com.rddev.forum.mapper.TopicoFormMapper
import br.com.rddev.forum.mapper.TopicoViewMapper
import br.com.rddev.forum.model.Curso
import br.com.rddev.forum.model.Topico
import br.com.rddev.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate
import javax.persistence.EntityManager

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Tópico não encontrado"
) {

    fun listar(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<TopicoView> {
        val topicos = if (nomeCurso == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }
        return topicos.map { topico ->
            topicoViewMapper.map(topico)
        }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = repository
            .findById(id)
            .orElseThrow { NotFoundException(notFoundMessage) }

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = repository.findById(form.id).orElseThrow { NotFoundException(notFoundMessage) }
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        topico.dataAlteracao = LocalDate.now()

        return topicoViewMapper.map(topico)

    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return repository.relatorio()
    }
}