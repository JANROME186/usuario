package com.exam.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.exam.usuario.model.Filtro;
import com.exam.usuario.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	@Query("select u from Usuario u where u.nombre like :#{#filtro.nombre}")
	List<Usuario> findAllByNombre(Filtro filtro);
	
	@Query("select u from Usuario u where u.fechaAlta > :#{#filtro.fechaAltaInicial} AND u.fechaAlta <= :#{#filtro.fechaAltaFinal}")
	List<Usuario> findAllByFechas(Filtro filtro);

}
