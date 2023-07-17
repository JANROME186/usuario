package com.exam.usuario.template;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespuestaTemplate {
	
	private String error;
	private String mensaje;
	private Object resultado;

}
