package com.extensao.senac.backend.servicos;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.extensao.senac.backend.dto.UsuarioCompletoOutput;
import com.extensao.senac.backend.dto.UsuarioOutput;
import com.extensao.senac.backend.dto.inputs.UsuarioAtualizadoInput;
import com.extensao.senac.backend.dto.inputs.UsuarioInput;
import com.extensao.senac.backend.jwt.TokenService;
import com.extensao.senac.backend.modelos.Anexo;
import com.extensao.senac.backend.modelos.Usuario;
import com.extensao.senac.backend.repositorios.UsuarioRepositorio;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AnexoServico anexoServico;

    public UsuarioOutput cadastrarUsuario(UsuarioInput usuario) {
        Optional<Usuario> usuarioResult =
                usuarioRepositorio.findByEmail(usuario.getEmail());
        if(usuarioResult.isPresent()) {
            throw new RuntimeException("Usuário já cadastrado!");
        }

        Usuario usuarioPersist = new Usuario();
        usuarioPersist.setEmail(usuario.getEmail());
        usuarioPersist.setNome(usuario.getNome());
        usuarioPersist.setSenha(passwordEncoder.encode(usuario.getSenha()));

        Usuario usuarioFinal = usuarioRepositorio.save(usuarioPersist);

        UsuarioOutput retorno = new UsuarioOutput();
        retorno.setEmail(usuarioFinal.getEmail());
        retorno.setNome(usuarioFinal.getNome());
        retorno.setToken(tokenService.gerarToken(usuarioFinal));

        return retorno;

    }

    public UsuarioOutput loginUsuario(UsuarioInput usuario) {
        Optional<Usuario> resultUsuario =
                usuarioRepositorio.findByEmail(usuario.getEmail());
        if(resultUsuario.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        Usuario usuarioLogin = resultUsuario.get();

        if(passwordEncoder.matches(usuario.getSenha(), usuarioLogin.getSenha())) {
            UsuarioOutput response = new UsuarioOutput();
            response.setEmail(usuarioLogin.getEmail());
            response.setNome(usuarioLogin.getNome());
            response.setToken(tokenService.gerarToken(usuarioLogin));

            return response;
        }

        throw new RuntimeException("Senha invalida!");
    }

    public UsuarioCompletoOutput atualizarPerfil(UsuarioAtualizadoInput atualizaUsuario, Usuario usuario) throws RuntimeErrorException {
        if(atualizaUsuario.getNome() != null && atualizaUsuario.getNome().length() > 0){
            usuario.setNome(atualizaUsuario.getNome());
        }
        if(atualizaUsuario.getFotoPerfil() != null && atualizaUsuario.getFotoPerfil().length() > 0){
            Anexo fotoPerfil = anexoServico.buscarAnexo(atualizaUsuario.getFotoPerfil());
            usuario.setFotoPerfil(fotoPerfil);
        }
        UsuarioCompletoOutput usuarioOutput = new UsuarioCompletoOutput();
        usuarioOutput.setEmail(usuario.getEmail());
        usuarioOutput.setNome(usuario.getNome());
        if(usuario.getFotoPerfil() != null ){
            usuarioOutput.setFotoPerfil(usuario.getFotoPerfil().getNomeAnexo());
        }
        usuarioRepositorio.save(usuario);
        return usuarioOutput;
    }
}