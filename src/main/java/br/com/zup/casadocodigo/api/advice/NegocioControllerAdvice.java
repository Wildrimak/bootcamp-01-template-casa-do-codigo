package br.com.zup.casadocodigo.api.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NegocioControllerAdvice {

	@ExceptionHandler
	public ResponseEntity<Map<String, String>> tratamentoPara(IllegalArgumentException illegalArgumentException) {

		String erro = illegalArgumentException.getMessage();
		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("erro", erro);

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(hashMap);
	}

}
