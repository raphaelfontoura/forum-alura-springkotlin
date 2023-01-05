package br.com.rddev.forum.service

import br.com.rddev.forum.model.Resposta
import br.com.rddev.forum.repository.RespostaRepository
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val respostaRepository: RespostaRepository
) {

    fun salvar(resposta: Resposta) = respostaRepository.save(resposta)
}