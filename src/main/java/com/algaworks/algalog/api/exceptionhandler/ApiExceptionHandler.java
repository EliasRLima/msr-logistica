package com.algaworks.algalog.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		    List<Problema.Campo> campos = new ArrayList<>();
		    for(ObjectError erro : ex.getBindingResult().getAllErrors()) {
		    	String nome = ((FieldError) erro).getField();
		    	String msg = messageSource.getMessage(erro, LocaleContextHolder.getLocale());//erro.getDefaultMessage();
		    	campos.add(new Problema.Campo(nome, msg));
		    }
		    
			Problema problema = new Problema();
			problema.setStatus(status.value());
			problema.setDatahora(LocalDateTime.now());
			problema.setTitulo("Um ou mais campo estao invalidos. Faça o preenchimento correto e tente novamente.");
			problema.setCampos(campos);
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}
}