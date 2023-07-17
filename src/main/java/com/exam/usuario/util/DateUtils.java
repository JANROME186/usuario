package com.exam.usuario.util;

import java.util.Date;

public interface DateUtils {
	
	default Date fechaSystema() {
		return new Date();
	}

}
