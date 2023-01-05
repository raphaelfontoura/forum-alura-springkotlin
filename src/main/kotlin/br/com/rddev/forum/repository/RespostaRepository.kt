package br.com.rddev.forum.repository

import br.com.rddev.forum.model.Resposta
import org.springframework.data.jpa.repository.JpaRepository

interface RespostaRepository : JpaRepository<Resposta, Long>