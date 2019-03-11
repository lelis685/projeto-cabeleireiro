package br.com.cabeleireiro.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cabeleireiro.domain.Cabeleireiro;
import br.com.cabeleireiro.domain.Role;
import br.com.cabeleireiro.repository.CabeleireiroRepository;
import br.com.cabeleireiro.repository.RoleRepository;

@Service
public class CabeleireiroServico {


    private CabeleireiroRepository cabeleireiroRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private RoleRepository roleRepository;
	
	@Autowired
	public CabeleireiroServico(CabeleireiroRepository cabeleireiroRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
		super();
		this.cabeleireiroRepository = cabeleireiroRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleRepository = roleRepository;
	}
	
	public Cabeleireiro encontrarCabeleireiroPorEmail(String email) {
		return cabeleireiroRepository.findByEmail(email);
	}
	
	
	public Cabeleireiro salvarCabeleireiro(Cabeleireiro cabeleireiro) {
		cabeleireiro.setSenha(bCryptPasswordEncoder.encode(cabeleireiro.getSenha()));
		cabeleireiro.setAtivo(1);
		Role usuarioRole = roleRepository.findByNome("CABELEIREIRO");
		cabeleireiro.setRoles(new HashSet<Role>(Arrays.asList(usuarioRole)));
		return cabeleireiroRepository.save(cabeleireiro);
	}
	
	
	
}
