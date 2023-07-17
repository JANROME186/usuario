package com.exam.usuario.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name="usuario")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	@Id
	private String login;
	private String password;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Float cliente;
	private String email;
	private Date fechaAlta;
	private Date fechaBaja;
	private char status;
	private Float intentos;
	private Date fechaRevocado;
	private Date fechaVigencia;
	private Integer noAcceso;
	private Integer area;
	private Date fechaModificacion;

}
