package com.BeeTech.Cartify.Exceptions;

import java.sql.SQLException;

public class FileprocessingException extends RuntimeException {
    public FileprocessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
