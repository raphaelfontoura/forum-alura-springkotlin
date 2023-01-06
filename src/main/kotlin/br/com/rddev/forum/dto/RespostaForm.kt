package br.com.rddev.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class RespostaForm(
    @field:NotEmpty
    val mensagem: String,
    @field:NotNull
    val idAutor: Long,
    @field:NotNull
    val idTopico: Long,
    val solucao: Boolean
)
