package com.exam.usuario.service;

import org.springframework.stereotype.Service;

import com.exam.usuario.model.Credenciales;
import com.exam.usuario.model.Filtro;
import com.exam.usuario.model.Usuario;
import com.exam.usuario.template.RespuestaTemplate;

@Service
public interface DatabaseService {
	
	RespuestaTemplate listaUsuarios(Filtro filtro);
	
	RespuestaTemplate obtenerUsuario(Filtro filtro);
	
	RespuestaTemplate loginUsuario(Credenciales credenciales);
	
	RespuestaTemplate altaUsuario(Usuario usuario);
	
	RespuestaTemplate modificacionUsuario(Usuario usuario);

}
