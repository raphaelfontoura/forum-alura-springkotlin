package br.com.rddev.forum.mapper

import br.com.rddev.forum.dto.NovoTopicoForm
import br.com.rddev.forum.model.Topico
import br.com.rddev.forum.service.CursoService
import br.com.rddev.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
): Mapper<NovoTopicoForm, Topico>{
    override fun map(form: NovoTopicoForm): Topico {
        return Topico(
            titulo = form.titulo,
            mensagem = form.mensagem,
            curso = cursoService.buscarPorId(form.idCurso),
            autor = usuarioService.buscarPorId(form.idAutor)
        )
    }

}
