package com.project.VehicleServiceManagement.Exception; 

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Custom Error Response Structure
    private ResponseEntity<Map<String, Object>> buildErrorResponse(
            String message, 
            HttpStatus status, 
            String path,
            String errorType) {
        
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", status.value());
        errorResponse.put("error", status.getReasonPhrase());
        errorResponse.put("message", message);
        errorResponse.put("path", path);
        errorResponse.put("errorType", errorType);
        
        return new ResponseEntity<>(errorResponse, status);
    }

    // Handle NoSuchElementException (when Optional.get() fails)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchElementException(
            NoSuchElementException ex, HttpServletRequest request) {
        return buildErrorResponse(
            "Resource not found. The requested item does not exist.",
            HttpStatus.NOT_FOUND,
            request.getRequestURI(),
            "RESOURCE_NOT_FOUND"
        );
    }

    // Handle EntityNotFoundException
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEntityNotFoundException(
            EntityNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(
            ex.getMessage() != null ? ex.getMessage() : "Entity not found",
            HttpStatus.NOT_FOUND,
            request.getRequestURI(),
            "ENTITY_NOT_FOUND"
        );
    }

    // Handle IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(
            IllegalArgumentException ex, HttpServletRequest request) {
        return buildErrorResponse(
            "Invalid argument provided: " + ex.getMessage(),
            HttpStatus.BAD_REQUEST,
            request.getRequestURI(),
            "INVALID_ARGUMENT"
        );
    }

    // Handle Authentication/Authorization exceptions
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleBadCredentialsException(
            BadCredentialsException ex, HttpServletRequest request) {
        return buildErrorResponse(
            "Invalid credentials provided",
            HttpStatus.UNAUTHORIZED,
            request.getRequestURI(),
            "AUTHENTICATION_FAILED"
        );
    }

    // Handle Custom Vehicle Service Exceptions
    @ExceptionHandler(VehicleServiceException.class)
    public ResponseEntity<Map<String, Object>> handleVehicleServiceException(
            VehicleServiceException ex, HttpServletRequest request) {
        
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorType = "BUSINESS_LOGIC_ERROR";
        
        // Determine specific status based on exception type
        if (ex instanceof ServiceAdvisorNotFoundException ||
            ex instanceof VehicleServiceNotFoundException ||
            ex instanceof ProvidedServiceNotFoundException ||
            ex instanceof ServiceRecordNotFoundException ||
            ex instanceof UserNotFoundException) {
            status = HttpStatus.NOT_FOUND;
            errorType = "RESOURCE_NOT_FOUND";
        } else if (ex instanceof InvalidCredentialsException) {
            status = HttpStatus.UNAUTHORIZED;
            errorType = "INVALID_CREDENTIALS";
        } else if (ex instanceof UserAlreadyExistsException) {
            status = HttpStatus.CONFLICT;
            errorType = "RESOURCE_CONFLICT";
        }
        
        return buildErrorResponse(
            ex.getMessage(),
            status,
            request.getRequestURI(),
            errorType
        );
    }

    // Handle RuntimeException (for custom business logic exceptions)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(
            RuntimeException ex, HttpServletRequest request) {
        
        // Check if it's a known business logic error
        String message = ex.getMessage();
        if (message != null) {
            if (message.contains("User not found")) {
                return buildErrorResponse(
                    "User with provided credentials not found",
                    HttpStatus.NOT_FOUND,
                    request.getRequestURI(),
                    "USER_NOT_FOUND"
                );
            }
            if (message.contains("Invalid credentials")) {
                return buildErrorResponse(
                    "Invalid login credentials",
                    HttpStatus.UNAUTHORIZED,
                    request.getRequestURI(),
                    "INVALID_CREDENTIALS"
                );
            }
        }
        
        return buildErrorResponse(
            message != null ? message : "An unexpected error occurred",
            HttpStatus.INTERNAL_SERVER_ERROR,
            request.getRequestURI(),
            "RUNTIME_ERROR"
        );
    }

    // Handle Data Integrity Violations (foreign key, unique constraints)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex, HttpServletRequest request) {
        
        String message = "Data integrity violation occurred";
        if (ex.getMessage() != null) {
            if (ex.getMessage().contains("foreign key")) {
                message = "Cannot delete record as it is referenced by other data";
            } else if (ex.getMessage().contains("unique")) {
                message = "Duplicate entry found. Record already exists";
            } else if (ex.getMessage().contains("not null")) {
                message = "Required field cannot be empty";
            }
        }
        
        return buildErrorResponse(
            message,
            HttpStatus.CONFLICT,
            request.getRequestURI(),
            "DATA_INTEGRITY_VIOLATION"
        );
    }

    // Handle Method Argument Type Mismatch (wrong parameter types)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        
        String message = String.format(
            "Parameter '%s' should be of type %s but received: %s",
            ex.getName(),
            ex.getRequiredType().getSimpleName(),
            ex.getValue()
        );
        
        return buildErrorResponse(
            message,
            HttpStatus.BAD_REQUEST,
            request.getRequestURI(),
            "PARAMETER_TYPE_MISMATCH"
        );
    }

    // Handle Missing Request Parameters
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, Object>> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex, HttpServletRequest request) {
        
        String message = String.format("Required parameter '%s' is missing", ex.getParameterName());
        
        return buildErrorResponse(
            message,
            HttpStatus.BAD_REQUEST,
            request.getRequestURI(),
            "MISSING_PARAMETER"
        );
    }

    // Handle JSON Parse Errors
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex, HttpServletRequest request) {
        
        String message = "Invalid JSON format or malformed request body";
        if (ex.getMessage() != null && ex.getMessage().contains("LocalDate")) {
            message = "Invalid date format. Please use YYYY-MM-DD format";
        }
        
        return buildErrorResponse(
            message,
            HttpStatus.BAD_REQUEST,
            request.getRequestURI(),
            "INVALID_REQUEST_BODY"
        );
    }

    // Handle Validation Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        
        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            validationErrors.put(error.getField(), error.getDefaultMessage())
        );
        
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Validation Failed");
        errorResponse.put("message", "Input validation failed");
        errorResponse.put("path", request.getRequestURI());
        errorResponse.put("errorType", "VALIDATION_ERROR");
        errorResponse.put("validationErrors", validationErrors);
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle 404 - No Handler Found
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpServletRequest request) {
        
        return buildErrorResponse(
            "The requested endpoint does not exist: " + ex.getRequestURL(),
            HttpStatus.NOT_FOUND,
            request.getRequestURI(),
            "ENDPOINT_NOT_FOUND"
        );
    }

    // Handle NullPointerException
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, Object>> handleNullPointerException(
            NullPointerException ex, HttpServletRequest request) {
        
        return buildErrorResponse(
            "A null value was encountered where it was not expected",
            HttpStatus.INTERNAL_SERVER_ERROR,
            request.getRequestURI(),
            "NULL_POINTER_ERROR"
        );
    }

    // Handle NumberFormatException
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Map<String, Object>> handleNumberFormatException(
            NumberFormatException ex, HttpServletRequest request) {
        
        return buildErrorResponse(
            "Invalid number format provided",
            HttpStatus.BAD_REQUEST,
            request.getRequestURI(),
            "NUMBER_FORMAT_ERROR"
        );
    }

    // Generic Exception Handler (catch-all)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(
            Exception ex, HttpServletRequest request) {
        
        // Log the exception for debugging (you can use your preferred logging framework)
        System.err.println("Unhandled exception: " + ex.getClass().getSimpleName() + " - " + ex.getMessage());
        ex.printStackTrace();
        
        return buildErrorResponse(
            "An unexpected error occurred. Please try again later.",
            HttpStatus.INTERNAL_SERVER_ERROR,
            request.getRequestURI(),
            "INTERNAL_SERVER_ERROR"
        );
    }
}