package br.com.cabeleireiro.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cabeleireiro.domain.Cabeleireiro;
import br.com.cabeleireiro.domain.Role;
import br.com.cabeleireiro.repository.CabeleireiroRepository;
import br.com.cabeleireiro.repository.RoleRepository;
import br.com.cabeleireiro.repository.filter.CabeleireiroFilterAtualiza;

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

	public Cabeleireiro encontrarCabeleireiroPorId(Long id) {
		return cabeleireiroRepository.findById(id).orElse(null);
	}

	public Cabeleireiro encontrarCabeleireiroPorEmail(String email) {
		return cabeleireiroRepository.findByEmail(email);
	}

	@Transactional
	public void desativarCabeleireiro(Long id) {
		Cabeleireiro cabeleireiroEncontrado = encontrarCabeleireiroPorId(id);
		cabeleireiroEncontrado.setAtivo(0);
		cabeleireiroRepository.desativarCabelereiro(cabeleireiroEncontrado.getAtivo(), id);
	}

	@Transactional
	public void ativarCabeleireiro(String email) {
		Cabeleireiro cabeleireiroEncontrado = encontrarCabeleireiroPorEmail(email);
		cabeleireiroEncontrado.setAtivo(1);
		cabeleireiroRepository.ativarCabeleireiro(cabeleireiroEncontrado.getAtivo(), email);
	}

	@Transactional
	public void atualizarCabeleireiro(Long id, CabeleireiroFilterAtualiza cabeleireiroFilterAtualiza) {
		Cabeleireiro cabeleireiroEncontrado = encontrarCabeleireiroPorId(id);

		cabeleireiroEncontrado.setNomeEstabelecimento(cabeleireiroFilterAtualiza.getNomeEstabelecimento());
		cabeleireiroEncontrado.setCnpj(cabeleireiroFilterAtualiza.getCnpj());
		cabeleireiroEncontrado.setEmail(cabeleireiroFilterAtualiza.getEmail());
		cabeleireiroEncontrado.setTelefone(cabeleireiroFilterAtualiza.getTelefone());
		cabeleireiroEncontrado.getEndereco().setBairro(cabeleireiroFilterAtualiza.getEndereco().getBairro());
		cabeleireiroEncontrado.getEndereco().setRua(cabeleireiroFilterAtualiza.getEndereco().getRua());
		cabeleireiroEncontrado.getEndereco().setComplemento(cabeleireiroFilterAtualiza.getEndereco().getComplemento());
		cabeleireiroEncontrado.getEndereco().setNumero(cabeleireiroFilterAtualiza.getEndereco().getNumero());
		cabeleireiroEncontrado.getEndereco().setCidade(cabeleireiroFilterAtualiza.getEndereco().getCidade());
		cabeleireiroEncontrado.getEndereco().setCep(cabeleireiroFilterAtualiza.getEndereco().getCep());
		cabeleireiroEncontrado.getEndereco().setRegiao(cabeleireiroFilterAtualiza.getEndereco().getRegiao());
		cabeleireiroEncontrado.setValorAdulto(cabeleireiroFilterAtualiza.getValorAdulto());
		cabeleireiroEncontrado.setValorInfantil(cabeleireiroFilterAtualiza.getValorInfantil());

		cabeleireiroRepository.setCabeleireiroInfoById(cabeleireiroEncontrado.getNomeEstabelecimento(),
				cabeleireiroEncontrado.getCnpj(), cabeleireiroEncontrado.getEndereco().getRua(),
				cabeleireiroEncontrado.getEndereco().getBairro(), cabeleireiroEncontrado.getEndereco().getCidade(),
				cabeleireiroEncontrado.getEndereco().getCep(), cabeleireiroEncontrado.getEndereco().getNumero(),
				cabeleireiroEncontrado.getEndereco().getComplemento(), cabeleireiroEncontrado.getEmail(),cabeleireiroEncontrado.getEndereco().getRegiao()
				,cabeleireiroEncontrado.getValorAdulto(),cabeleireiroEncontrado.getValorInfantil(), id);
	}

	public Cabeleireiro salvarCabeleireiro(Cabeleireiro cabeleireiro) {
		System.out.println(cabeleireiro);
		cabeleireiro.setSenha(bCryptPasswordEncoder.encode(cabeleireiro.getSenha()));
		cabeleireiro.setAtivo(1);
		Role usuarioRole = roleRepository.findByNome("CABELEIREIRO");
		cabeleireiro.setRoles(new HashSet<Role>(Arrays.asList(usuarioRole)));
		System.err.println(usuarioRole);
		return cabeleireiroRepository.save(cabeleireiro);
	}

}
