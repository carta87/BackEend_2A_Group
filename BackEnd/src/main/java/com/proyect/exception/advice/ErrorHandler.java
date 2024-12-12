package com.proyect.exception.advice;

import com.proyect.dto.ErrorDTO;
import com.proyect.exception.exception.NotUsernameFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDTO> runtimeEceptionHandler(RuntimeException ex){
        ErrorDTO errorDTO = ErrorDTO.builder()
                .code("P-500")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotUsernameFoundException.class)
    public ResponseEntity<ErrorDTO> usernameNotFoundExceptionHandler(NotUsernameFoundException ex){
        ErrorDTO errorDTO = ErrorDTO.builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, ex.getHttpStatus());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> illegalArgumentExceptionHandler(IllegalArgumentException ex){
        ErrorDTO errorDTO = ErrorDTO.builder()
                .code("P-400")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDTO> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException  ex){
        ErrorDTO errorDTO = ErrorDTO.builder().code("P-400").message(ex.getMessage()).build();
        if (ex.getMessage().contains("Duplicate entry"))
            errorDTO.setMessage("usuario repetido en base datos... cambie username");
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ErrorDTO> nullPointerExceptionHandler(NullPointerException  ex){
        ErrorDTO errorDTO = ErrorDTO.builder().code("P-400").message(ex.getMessage()).build();
        if (ex.getMessage().contains("because \"password\" is null"))
            errorDTO.setMessage("datos incompletos falta password");
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException  ex){
        ErrorDTO errorDTO = ErrorDTO.builder().code("P-400").message(ex.getMessage()).build();
        if (ex.getMessage().contains("default message [username]]")){
            errorDTO.setMessage("datos incompletos falta username");
        }else if (ex.getMessage().contains("default message [password]]")){
            errorDTO.setMessage("datos incompletos falta contraseña");
        }else if (ex.getMessage().contains("default message [email]]") || ex.getMessage().contains(
                "default message [debe ser una dirección de correo electrónico con formato correcto]]")){
            errorDTO.setMessage("datos incompletos falta email");
        }else if (ex.getMessage().contains("default message [firsName]]")){
            errorDTO.setMessage("datos incompletos falta firsName");
        }else if (ex.getMessage().contains("default message [lastName]]")){
            errorDTO.setMessage("datos incompletos falta lastName");
        }else if (ex.getMessage().contains("default message [country]]")){
            errorDTO.setMessage("datos incompletos falta country");
        }
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
