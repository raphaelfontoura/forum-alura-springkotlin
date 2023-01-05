package br.com.rddev.forum.config

import br.com.rddev.forum.security.JWTLoginFilter
import br.com.rddev.forum.security.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JWTUtil
) : WebSecurityConfigurerAdapter(){

    override fun configure(http: HttpSecurity?) {
        http?.
            csrf()?.disable()?.
            authorizeRequests()?.
            antMatchers("/topicos")?.hasAuthority("LEITURA_ESCRITA")?.
            antMatchers(HttpMethod.POST, "/login")?.permitAll()?.
            anyRequest()?.authenticated()

        http?.addFilterBefore(JWTLoginFilter(authManager = authenticationManager(), jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
        http?.addFilterBefore(JwtAuthenticationFilter(jwtUtil= jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
        http?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(web: WebSecurity?) {
        web?.ignoring()?.antMatchers("/swagger-ui/**", "/v3/api-docs/**")
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptPasswordEncoder())
    }
}