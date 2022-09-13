package SnippetOA.snippet_oa.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import SnippetOA.snippet_oa.wrapper.ApiWrapper;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ApiExceptionHandler{

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApiWrapper handleEntityNotFound(Exception e, WebRequest request) {
		ApiWrapper apiWrapper = new ApiWrapper();
		apiWrapper.setSuccess(false);
		apiWrapper.setErrorCode(-1);
		apiWrapper.setMessage(e.getMessage());
		return apiWrapper;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiWrapper handleAllException(Exception e, WebRequest request) {
		ApiWrapper apiWrapper = new ApiWrapper();
		apiWrapper.setSuccess(false);
		apiWrapper.setErrorCode(-1);
		apiWrapper.setMessage(e.getMessage());
		return apiWrapper;
	}
	
	@ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ApiWrapper handleIndexOutOfBoundsException(Exception e, WebRequest request) {
		ApiWrapper apiWrapper = new ApiWrapper();
		apiWrapper.setSuccess(false);
		apiWrapper.setErrorCode(-1);
		apiWrapper.setMessage(e.getMessage());
		return apiWrapper;
	}

}
