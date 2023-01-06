package br.com.rddev.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {

    fun notificar(email: String) {
        val message = SimpleMailMessage()

        message.subject = "[Alura] Resposta Recebida"
        message.text = "Olá. A sua dúvida foi respondida. Venha conferir no forum."
        message.setTo(email)

        javaMailSender.send(message)
    }

}