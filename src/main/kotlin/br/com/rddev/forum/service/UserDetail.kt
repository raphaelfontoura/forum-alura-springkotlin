package br.com.rddev.forum.service

import br.com.rddev.forum.model.Usuario
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(private val usuario: Usuario) : UserDetails {
    override fun getAuthorities() = usuario.role

    override fun getPassword(): String = usuario.password

    override fun getUsername(): String = usuario.email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}