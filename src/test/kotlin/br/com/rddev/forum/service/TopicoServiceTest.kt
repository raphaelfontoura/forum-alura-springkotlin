package br.com.rddev.forum.service

import br.com.rddev.forum.exception.NotFoundException
import br.com.rddev.forum.mapper.TopicoFormMapper
import br.com.rddev.forum.mapper.TopicoViewMapper
import br.com.rddev.forum.model.Topico
import br.com.rddev.forum.model.TopicoTest
import br.com.rddev.forum.model.TopicoViewTest
import br.com.rddev.forum.repository.TopicoRepository
import io.mockk.every
import org.junit.jupiter.api.Test
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicoServiceTest {

    val topicos = PageImpl(listOf(TopicoTest.build()))

    val paginacao: Pageable = mockk()

    val topicoRepository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
        every { findAll(paginacao) } returns topicos
    }
    val topicoViewMapper: TopicoViewMapper = mockk {
        every { map(any()) } returns TopicoViewTest.build()
    }
    val topicoFormMapper: TopicoFormMapper = mockk()

    val topicoService = TopicoService(
        topicoRepository, topicoViewMapper, topicoFormMapper
    )

    @Test
    fun `deve listar topicos a partir do nome do curso`() {

        topicoService.listar("Kotlin avancado", paginacao)

        verify(exactly = 1) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 0) { topicoRepository.findAll(paginacao) }
    }

    @Test
    fun `deve listar todos os topicos quando o nome do curso for nulo`() {
        topicoService.listar(null, paginacao)

        verify(exactly = 0) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 1) { topicoRepository.findAll(paginacao) }
    }

    @Test
    fun `deve retornar NotFoundException quando topico nao for achado`() {
        every { topicoRepository.findById(99L) } returns Optional.empty()

        val result = assertThrows<NotFoundException> {
            topicoService.buscarPorId(99L)
        }

        assertThat(result.message).isEqualTo("Tópico não encontrado")
    }

    @Test
    fun `deve retornar um Topico quando topico for achado`() {
        val topico = TopicoTest.build()
        val slot = slot<Topico>()
        every { topicoRepository.findById(1L) } returns Optional.of(topico)
        every { topicoViewMapper.map(capture(slot)) } returns TopicoViewTest.build()

        val result = topicoService.buscarPorId(1L)

        assertThat(result.titulo).isEqualTo(topico.titulo)
        assertThat(slot.captured.titulo).isEqualTo(topico.titulo)
        assertThat(slot.captured.mensagem).isEqualTo(topico.mensagem)
        assertThat(slot.captured.status).isEqualTo(topico.status)
    }
}