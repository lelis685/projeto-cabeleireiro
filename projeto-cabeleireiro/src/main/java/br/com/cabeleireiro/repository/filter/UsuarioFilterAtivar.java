package br.com.cabeleireiro.repository.filter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UsuarioFilterAtivar {

	@Email(message = "*Por favor preencha um email válido")
	@NotEmpty(message = "*Por favor preencha seu email")
	private String email;
	
	private Long id;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
}
