package com.meuportifolio.apimedicosspring3.infra.security;

import com.meuportifolio.apimedicosspring3.model.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // recupera o token
        var tokenJWT = recuperarToken(request);

        if(tokenJWT != null){
            // valida se o token tá correto
            var subject = tokenService.getSubject(tokenJWT);
            // pega o email e o usuário dentro do token
            var usuario = usuarioRepository.findByLogin(subject);

            // carrega o objeto usuário no banco de dados, cria o dto que representa o usuário e força a autenticação
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        /*
        Necessário para chamar os próximos filtros na aplicação
         */
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){ //|| authorizationHeader.isEmpty() || !authorizationHeader.startsWith("Bearer")){
            //throw new RuntimeException("TokenJWT inválido ou não enviado no cabeçalho Authorization");
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

}
