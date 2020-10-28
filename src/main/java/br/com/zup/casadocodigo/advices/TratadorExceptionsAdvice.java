package br.com.zup.casadocodigo.advices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorExceptionsAdvice {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler
	public ResponseEntity<Map<String, String>> trataMethodArgumentNotValidException(
			MethodArgumentNotValidException exception) {

		List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		Map<String, String> hashMap = new HashMap<String, String>();

		globalErrors.forEach(erro -> {
			String message = getErrorMessage(erro);
			hashMap.put("ErroGlobal", message);
		});

		fieldErrors.forEach(error -> {
			String errorMessage = getErrorMessage(error);
			hashMap.put(error.getField(), errorMessage);
		});

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(hashMap);
	}

	@ExceptionHandler
	public ResponseEntity<Map<String, String>> trataParaHttpMessageNotReadableException(
			HttpMessageNotReadableException exception) {

		String erro = exception.getMessage();

		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("ErroGlobal", erro);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hashMap);
	}

	@ExceptionHandler
	public ResponseEntity<Map<String, String>> trataIllegalStateException(IllegalStateException exception) {

		String erro = exception.getMessage();

		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("ErroGlobal", erro);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hashMap);
	}
	
	@ExceptionHandler
	public ResponseEntity<Map<String, String>> trataIllegalArgumentException(IllegalArgumentException exception) {

		String erro = exception.getMessage();

		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("ErroGlobal", erro);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hashMap);
	}

	private String getErrorMessage(ObjectError error) {
		return messageSource.getMessage(error, LocaleContextHolder.getLocale());
	}

}
