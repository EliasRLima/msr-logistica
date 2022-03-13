package com.algaworks.algalog.api.model.input;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaInput {

	@NotNull
	@Valid
	private ClienteIdInput cliente;
	
	@NotNull
	@Valid
	private DestinatarioInput destinatario;
	
	@NotNull
	private BigDecimal taxa;
	
}
