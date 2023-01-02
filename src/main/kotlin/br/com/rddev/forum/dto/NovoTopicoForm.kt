package br.com.rddev.forum.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NovoTopicoForm(
    @field:NotEmpty(message = "Título não pode estar vazio")
    @field:Size(min = 3, max = 100, message = "Título deve ter entre 3 e 100 caracteres")
    val titulo: String,
    //data class necessário @field para assumir na construção
    // a anotação vai para o parâmetro do construtor em vez do atributo.
    // link: https://medium.com/@demisgomes/implementando-um-custom-validator-no-kotlin-df90313bd17
    @field:NotBlank
    val mensagem: String,
    @field:NotNull
    val idCurso: Long,
    @field:NotNull
    val idAutor: Long
)
