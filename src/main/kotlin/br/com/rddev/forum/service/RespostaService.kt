package br.com.rddev.forum.service

import br.com.rddev.forum.dto.RespostaForm
import br.com.rddev.forum.mapper.RespostaFormMapper
import br.com.rddev.forum.model.StatusTopico
import br.com.rddev.forum.repository.RespostaRepository
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val respostaRepository: RespostaRepository,
    private val emailService: EmailService,
    private val mapper: RespostaFormMapper
) {

    fun salvar(respostaDto: RespostaForm) {
        val resposta = mapper.map(respostaDto)
        resposta.topico.status = StatusTopico.NAO_SOLUCIONADO
        respostaRepository.save(resposta)
        val emailAutor = resposta.topico.autor.email
        emailService.notificar(emailAutor)
    }
}