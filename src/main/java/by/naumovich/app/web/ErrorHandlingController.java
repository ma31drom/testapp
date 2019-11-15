package by.naumovich.app.web;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import by.naumovich.app.excep.BrandNotFoundException;
import by.naumovich.app.excep.CarNotFoundException;
import by.naumovich.app.excep.CarPicNotFoundException;
import by.naumovich.app.excep.EntityExistsException;
import by.naumovich.app.excep.Unauthorized;
import by.naumovich.app.excep.UserNotFoundException;

public abstract class ErrorHandlingController {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleNotReadable(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
        resp.setStatus(HttpStatus.BAD_REQUEST.value());
        resp.getOutputStream()
            .write("INVALID_REQUEST_BODY".getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public void handleNoBrand(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
        resp.setStatus(HttpStatus.NOT_FOUND.value());
        resp.getOutputStream()
            .write("BRAND_NOT_FOUND".getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler(CarNotFoundException.class)
    public void handleNoCar(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
        resp.setStatus(HttpStatus.NOT_FOUND.value());
        resp.getOutputStream()
            .write("CAR_NOT_FOUND".getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public void handleNoUser(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
        resp.setStatus(HttpStatus.NOT_FOUND.value());
        resp.getOutputStream()
            .write("USER_NOT_FOUND".getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler(CarPicNotFoundException.class)
    public void handleNoCarPic(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
        resp.setStatus(HttpStatus.NOT_FOUND.value());
        resp.getOutputStream()
            .write("PICTURE_NOT_FOUND".getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler(Exception.class)
    public void handleGeneral(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
        resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        resp.getOutputStream()
            .write("INTERNAL_SERVER_ERROR".getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler(ValidationException.class)
    public void validationGeneral(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
        resp.setStatus(HttpStatus.BAD_REQUEST.value());
        resp.getOutputStream()
            .write("BAD_REQUEST".getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public void validationGeneral1(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
        resp.setStatus(HttpStatus.BAD_REQUEST.value());
        resp.getOutputStream()
            .write("BAD_REQUEST".getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public void handleNotFound(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
        resp.setStatus(HttpStatus.NOT_FOUND.value());
        resp.getOutputStream()
            .write("ENTITY_DOES_NOT_EXIST".getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler(Unauthorized.class)
    public void handleUnauthorized(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
        resp.setStatus(HttpStatus.UNAUTHORIZED.value());
        resp.getOutputStream()
            .write("UNAUTHORIZED".getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler(EntityExistsException.class)
    public void handleExists(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
        resp.setStatus(HttpStatus.CONFLICT.value());
        resp.getOutputStream()
            .write("ENTITY_ALREADY_EXISTS".getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handleExists2(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
        resp.setStatus(HttpStatus.CONFLICT.value());
        resp.getOutputStream()
            .write("ENTITY_ALREADY_EXISTS".getBytes(StandardCharsets.UTF_8));
    }

}
