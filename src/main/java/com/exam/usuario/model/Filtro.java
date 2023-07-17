package com.exam.usuario.model;

import java.util.Date;

import lombok.Data;

@Data
public class Filtro {
	
	private String login;
	
	private String nombre;
	
	private Date fechaAltaInicial;
	
	private Date fechaAltaFinal;
	
	private char Status; 

}
