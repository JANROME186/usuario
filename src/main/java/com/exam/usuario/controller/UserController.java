package com.exam.usuario.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.usuario.model.Credenciales;
import com.exam.usuario.model.Filtro;
import com.exam.usuario.model.Usuario;
import com.exam.usuario.service.DatabaseService;
import com.exam.usuario.template.RespuestaTemplate;

@RestController
public class UserController {
	
	@Autowired
	private DatabaseService DatabaseService;
	
	@PostMapping(value = "/usuario/lista")
	public ResponseEntity<?> listaUsuarios(@RequestBody Filtro filtro){
		return new ResponseEntity<>(DatabaseService.listaUsuarios(filtro), HttpStatus.OK);
	}
	
	@PostMapping(value = "/usuario/filtro")
	public ResponseEntity<?> obtenerUsuarios(@RequestBody Filtro filtro){
		return new ResponseEntity<>(DatabaseService.obtenerUsuario(filtro) , HttpStatus.OK);
	}
	
	@PostMapping(value = "/usuario")
	public ResponseEntity<RespuestaTemplate> altaUsuario(@RequestBody Usuario usuario){
		return new ResponseEntity<>(DatabaseService.altaUsuario(usuario), HttpStatus.OK);
	}
	
	@PutMapping(value = "/usuario")
	public ResponseEntity<RespuestaTemplate> modificacionUsuario(@RequestBody Usuario usuario){
		return new ResponseEntity<>(DatabaseService.modificacionUsuario(usuario), HttpStatus.OK);
	}
	
	@PostMapping(value="/usuario/login")
	public ResponseEntity<RespuestaTemplate> validaUsuario(@RequestBody Credenciales credenciales){
		return new ResponseEntity<>(DatabaseService.loginUsuario(credenciales), HttpStatus.OK);
	}

}
