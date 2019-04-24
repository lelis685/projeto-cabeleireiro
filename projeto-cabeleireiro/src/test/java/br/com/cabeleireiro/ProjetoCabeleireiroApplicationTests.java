package br.com.cabeleireiro;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cabeleireiro.service.CabeleireiroServico;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjetoCabeleireiroApplicationTests {
	
	@Autowired
	private CabeleireiroServico cabeleireiroServico;

	
	
	@Test
	public void testeCabeleireiroNaoExiste() {
		assertNull(cabeleireiroServico.encontrarCabeleireiroPorId(42L));
	}
	
	@Test
	public void testeCabeleireiroExiste() {
		assertNotNull(cabeleireiroServico.encontrarCabeleireiroPorId(1L));
	}
	
	
	@Test
	public void testeCabeleireiroIguais() {
		assertSame(cabeleireiroServico.encontrarCabeleireiroPorId(11L), 
				cabeleireiroServico.encontrarCabeleireiroPorId(11L)
				);
	}
	
	

}
