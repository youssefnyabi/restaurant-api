package com.restaurant.exception;
    public class PlatNotFoundException extends RuntimeException {
        public PlatNotFoundException(Long id) {
            super("Plat introuvable avec l id : " + id);
        }
    }

