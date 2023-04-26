package com.jlisok.githubproxy;

import com.jlisok.githubproxy.exceptions.CustomExceptionResponse;
import com.jlisok.githubproxy.exceptions.ExceptionCode;
import com.jlisok.githubproxy.exceptions.GithubProxyException;
import feign.FeignException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.function.Supplier;

import static com.jlisok.githubproxy.exceptions.ExceptionMessageConstants.*;

@EnableWebMvc
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GithubExceptionHandler {

    @ExceptionHandler({GithubProxyException.class})
    public ResponseEntity<Object> handleGithubProxyException(GithubProxyException exception) {
        var response = new CustomExceptionResponse(exception.getCode().toString(), exception.getMessage());
        return ResponseEntity
                .status(exception.getStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }

    @ExceptionHandler({FeignException.class})
    public ResponseEntity<Object> handleFeignException(FeignException exception) {

        Supplier<CustomExceptionResponse> response = () -> switch (exception.status()) {
            case 400 -> new CustomExceptionResponse(ExceptionCode.BAD_REQUEST.toString(), BAD_REQUEST);
            case 404 -> new CustomExceptionResponse(ExceptionCode.OBJECT_NOT_FOUND.toString(), REPOSITORIES_NOT_FOUND);
            case 503 -> new CustomExceptionResponse(ExceptionCode.GITHUB_API_EXCEPTION.toString(), GITHUB_UNAVAILABLE);
            default -> new CustomExceptionResponse(ExceptionCode.INTERNAL_SERVER_ERROR.toString(), GENERIC_MICROSERVICE_EXCEPTION);
        };

        return ResponseEntity
                .status(exception.status())
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.get());
    }

    @ExceptionHandler(value = {HttpMediaTypeNotAcceptableException.class})
    public ResponseEntity<Object> handleUnsupportedMediaType(HttpMediaTypeNotAcceptableException exception) {
        var response = new CustomExceptionResponse(ExceptionCode.BAD_MEDIA_TYPE.toString(), MEDIA_TYPE_NOT_SUPPORTED);
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<Object> handleGenericException(Throwable exception) {
        var response = new CustomExceptionResponse(ExceptionCode.INTERNAL_SERVER_ERROR.toString(), GENERIC_MICROSERVICE_EXCEPTION);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
