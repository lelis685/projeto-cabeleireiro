package br.com.cabeleireiro.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.cabeleireiro.domain.Cabeleireiro;
import br.com.cabeleireiro.domain.Role;
import br.com.cabeleireiro.domain.Usuario;
import br.com.cabeleireiro.repository.RoleRepository;
import br.com.cabeleireiro.repository.UsuarioRepository;
import br.com.cabeleireiro.repository.filter.CabeleireiroFilter;
import br.com.cabeleireiro.repository.filter.UsuarioFilter;

@Service
public class UsuarioServico {

	private UsuarioRepository usuarioRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private RoleRepository roleRepository;

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	public UsuarioServico(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			RoleRepository roleRepository) {
		this.usuarioRepository = usuarioRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleRepository = roleRepository;
		System.out.println("UsuarioServico");

	}

	public Usuario encontrarUsuarioPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public Usuario encontrarUsuarioPorId(Long id) {
		System.out.println("encontrarUsuarioPorId");
		return usuarioRepository.findById(id).orElse(null);
	}
	
	

	@Transactional
	public void desativarUsuario(Long id) {
		Usuario usuarioEncontrado = encontrarUsuarioPorId(id);
		usuarioEncontrado.setAtivo(0);
		usuarioRepository.desativarUsuario(usuarioEncontrado.getAtivo(),id);
	}
	
	
	@Transactional
	public void ativarUsuario(String email) {
		Usuario usuarioEncontrado = encontrarUsuarioPorEmail(email);
		usuarioEncontrado.setAtivo(1);
		usuarioRepository.ativarUsuario(usuarioEncontrado.getAtivo(),email);
	}
	
	
	
	@Transactional
	public void atualizarUsuario(Long id,UsuarioFilter usuarioFilter) {
		Usuario usuarioEncontrado = encontrarUsuarioPorId(id);
	
		usuarioEncontrado.setNome(usuarioFilter.getNome());
		usuarioEncontrado.setSobreNome(usuarioFilter.getSobreNome());
		usuarioEncontrado.setDataNascimento(usuarioFilter.getDataNascimento());
		usuarioEncontrado.setEmail(usuarioFilter.getEmail());
		usuarioEncontrado.setCelular(usuarioFilter.getCelular());
		
		System.out.println(id);
		
		usuarioRepository.setUsuarioInfoById(usuarioEncontrado.getNome(), usuarioEncontrado.getSobreNome(), 
				usuarioEncontrado.getDataNascimento(), usuarioEncontrado.getCelular(), id);
	}
	

	public Usuario salvarUsuario(Usuario usuario) {
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
		usuario.setAtivo(1);
		Role usuarioRole = roleRepository.findByNome("USUARIO");
		usuario.setRoles(new HashSet<Role>(Arrays.asList(usuarioRole)));
		return usuarioRepository.save(usuario);
	}

	public List<CabeleireiroFilter> filtrar(CabeleireiroFilter cabeleireiroFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cabeleireiro> criteria = builder.createQuery(Cabeleireiro.class);
		Root<Cabeleireiro> root = criteria.from(Cabeleireiro.class);

		Predicate[] predicates = restricoesQuery(cabeleireiroFilter, builder, root);
		criteria.where(predicates);
	
		TypedQuery<Cabeleireiro> query = manager.createQuery(criteria);

		List<CabeleireiroFilter> cf = criaListaCabeleireiroFilter(query);

		return cf;
	}

	private List<CabeleireiroFilter> criaListaCabeleireiroFilter(TypedQuery<Cabeleireiro> query) {
		List<CabeleireiroFilter> cf = new ArrayList<>();
		// copiar só campos necessarios
		for (int i = 0; i < query.getResultList().size(); i++) {
			CabeleireiroFilter cc = new CabeleireiroFilter();
			cc.setNomeEstabelecimento(query.getResultList().get(i).getNomeEstabelecimento());
			cc.setBairro(query.getResultList().get(i).getEndereco().getBairro());
			cc.setCep(query.getResultList().get(i).getEndereco().getCep());
			cc.setCidade(query.getResultList().get(i).getEndereco().getCidade());
			cc.setNumero(query.getResultList().get(i).getEndereco().getNumero());
			cc.setRua(query.getResultList().get(i).getEndereco().getRua());
			cf.add(cc);
		}
		return cf;
	}

	public Predicate[] restricoesQuery(CabeleireiroFilter cabeleireiroFilter, CriteriaBuilder builder,
			Root<Cabeleireiro> root) {

		// lista de restricoes
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(cabeleireiroFilter.getNomeEstabelecimento())) {
			predicates.add(builder.like(builder.lower(root.get("nomeEstabelecimento")),
					 "%"+ cabeleireiroFilter.getNomeEstabelecimento().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(cabeleireiroFilter.getBairro())) {
			predicates.add(builder.like(builder.lower(root.get("endereco").get("bairro")),
					"%" + cabeleireiroFilter.getBairro().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(cabeleireiroFilter.getRua())) {
			predicates.add(builder.like(builder.lower(root.get("endereco").get("rua")),
					"%" + cabeleireiroFilter.getRua().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(cabeleireiroFilter.getCidade())) {
			predicates.add(builder.like(builder.lower(root.get("endereco").get("cidade")),
					"%" + cabeleireiroFilter.getCidade().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(cabeleireiroFilter.getCep())) {
			predicates.add(builder.like(builder.lower(root.get("endereco").get("cep")),
					cabeleireiroFilter.getCep()));
		}

		if (!StringUtils.isEmpty(cabeleireiroFilter.getNumero())) {
			predicates.add(builder.equal(root.get("endereco").get("numero"), cabeleireiroFilter.getNumero()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);

	}

}
