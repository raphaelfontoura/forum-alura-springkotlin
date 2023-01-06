package br.com.rddev.forum.mapper

import br.com.rddev.forum.dto.RespostaForm
import br.com.rddev.forum.model.Resposta
import br.com.rddev.forum.repository.TopicoRepository
import br.com.rddev.forum.service.TopicoService
import br.com.rddev.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class RespostaFormMapper(
    private val usuarioService: UsuarioService,
    private val topicoService: TopicoService
): Mapper<RespostaForm, Resposta> {
    override fun map(t: RespostaForm): Resposta {
        return Resposta(
            mensagem = t.mensagem,
            autor = usuarioService.buscarPorId(t.idAutor),
            topico = topicoService.buscarEntidadePorId(t.idTopico),
            solucao = t.solucao
        )
    }
}