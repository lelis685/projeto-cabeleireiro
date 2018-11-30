package br.com.cabeleireiro.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cabeleireiro.domain.Role;
import br.com.cabeleireiro.domain.Usuario;
import br.com.cabeleireiro.repository.RoleRepository;
import br.com.cabeleireiro.repository.UsuarioRepository;

@Service
public class UsuarioServico  {
	
    private UsuarioRepository usuarioRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private RoleRepository roleRepository;

	@Autowired
	public UsuarioServico(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			RoleRepository roleRepository) {
		this.usuarioRepository = usuarioRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleRepository = roleRepository;
	}
	
	public Usuario encontrarUsuarioPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public Usuario salvarUsuario(Usuario usuario) {
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
		usuario.setAtivo(1);
		Role usuarioRole = roleRepository.findByRole("USUARIO");
		usuario.setRoles(new HashSet<Role>(Arrays.asList(usuarioRole)));
		return usuarioRepository.save(usuario);
	}
	
	
	
	
	
	
}
