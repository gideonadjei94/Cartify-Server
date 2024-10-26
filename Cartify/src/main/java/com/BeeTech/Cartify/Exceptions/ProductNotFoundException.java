package com.BeeTech.Cartify.Exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException (String message) {
        super(message);
    }
}
