package com.exam.usuario.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exam.usuario.model.Credenciales;
import com.exam.usuario.model.Filtro;
import com.exam.usuario.model.Usuario;
import com.exam.usuario.repository.UsuarioRepository;
import com.exam.usuario.template.RespuestaTemplate;
import com.exam.usuario.util.DateUtils;

@Component
public class UsuarioDatabesService implements DatabaseService, DateUtils{
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public RespuestaTemplate listaUsuarios(Filtro filtro) {
		List<Usuario> usuarios;
		String mensaje="";
		String error="";
		if(Objects.nonNull(filtro.getNombre()) && !filtro.getNombre().isEmpty()) {
			filtro.setNombre("%"+filtro.getNombre()+"%");
			usuarios = usuarioRepository.findAllByNombre(filtro); 
			mensaje = "success";
		} else if(Objects.nonNull(filtro.getFechaAltaInicial()) && 
				  Objects.nonNull(filtro.getFechaAltaFinal())) {
			usuarios = usuarioRepository.findAllByFechas(filtro);
			mensaje = "success";
		} else {
			usuarios = usuarioRepository.findAll();
			mensaje = "success";
			error = "Filtros no especificados";
		}
		
		return RespuestaTemplate.builder().error(error).mensaje(mensaje).resultado(usuarios).build();
	}

	@Override
	public RespuestaTemplate obtenerUsuario(Filtro filtro) {
		String mensaje="";
		String error="";
		Optional<Usuario> usuario = usuarioRepository.findById(filtro.getLogin());
		if(!usuario.isPresent()) {
			mensaje = "error";
			error="No se encontro al usuario";
		}
		return RespuestaTemplate.builder().error(error).mensaje(mensaje).resultado(usuario.get()).build();
	}

	@Override
	public RespuestaTemplate altaUsuario(Usuario usuario) {
		String mensaje="success";
		String error="";
		Usuario usuarioH2 = usuarioRepository.save(usuario);
		return RespuestaTemplate.builder().error(error).mensaje(mensaje).resultado(usuarioH2).build();
	}

	@Override
	public RespuestaTemplate modificacionUsuario(Usuario usuario) {
		String mensaje="success";
		String error="";
		Usuario usuarioH2 = usuarioRepository.save(usuario);
		return RespuestaTemplate.builder().error(error).mensaje(mensaje).resultado(usuarioH2).build();
	}

	@Override
	public RespuestaTemplate loginUsuario(Credenciales credenciales) {
		String mensaje="";
		String error="";
		Optional<Usuario> usuarioLogin = usuarioRepository.findById(credenciales.getLogin());
		if(usuarioLogin.isPresent()) {
			if(usuarioLogin.get().getPassword().equals(
					Base64.encodeBase64String(
							credenciales.getPassword().getBytes()))) {
				if(!usuarioLogin.get().getFechaVigencia().before(this.fechaSystema())) {
					switch(usuarioLogin.get().getStatus()) {
					case 'A':
						mensaje = "success";
						break;
					case 'B':
						mensaje = "error";
						error = "Usuario dado de baja.";
						break;
					case 'R':
						mensaje = "error";
						error = "Usuario revocado.";
						break;
					}
				} else {
					mensaje = "error";
					error = "El usuario ha expirado";
				}
			} else {
				mensaje="error";
				error="Usuario/Contraseña incorrectos";
			}
		} else {
			mensaje = "error";
			error = "No existe el usuario.";
		}
		
		return RespuestaTemplate.builder().error(error).mensaje(mensaje).resultado(null).build();
	}

}
