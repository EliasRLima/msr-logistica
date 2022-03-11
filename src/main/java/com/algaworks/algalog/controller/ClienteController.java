package com.algaworks.algalog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class ClienteController {
	
	//@Autowired //substituido pelo lombok onde é iniciado construtor com o clienteRepository por meio do AllArgsConstructor
	private ClienteRepository clienteRepository;

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/clientes/{nome}")
	public List<Cliente> listar(@PathVariable String nome) {
		return clienteRepository.findByNome(nome);
	}
}
