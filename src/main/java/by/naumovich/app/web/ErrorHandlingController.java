package by.naumovich.app.web;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import by.naumovich.app.excep.Unauthorized;

public abstract class ErrorHandlingController {

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public void handleNotReadable(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
		resp.setStatus(HttpStatus.BAD_REQUEST.value());
		resp.getOutputStream().write("INVALID_REQUEST_BODY".getBytes(StandardCharsets.UTF_8));
	}

	@ExceptionHandler(Exception.class)
	public void handleGeneral(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
		resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		resp.getOutputStream().write("INTERNAL_SERVER_ERROR".getBytes(StandardCharsets.UTF_8));
	}

	@ExceptionHandler(ValidationException.class)
	public void validationGeneral(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
		resp.setStatus(HttpStatus.BAD_REQUEST.value());
		resp.getOutputStream().write("BAD_REQUEST".getBytes(StandardCharsets.UTF_8));
	}

	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public void validationGeneral1(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
		resp.setStatus(HttpStatus.BAD_REQUEST.value());
		resp.getOutputStream().write("BAD_REQUEST".getBytes(StandardCharsets.UTF_8));
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public void handleNotFound(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
		resp.setStatus(HttpStatus.NOT_FOUND.value());
		resp.getOutputStream().write("ENTITY_DOES_NOT_EXIST".getBytes(StandardCharsets.UTF_8));
	}

	@ExceptionHandler(Unauthorized.class)
	public void handleUnauthorized(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
		resp.setStatus(HttpStatus.UNAUTHORIZED.value());
		resp.getOutputStream().write("UNAUTHORIZED".getBytes(StandardCharsets.UTF_8));
	}
}
