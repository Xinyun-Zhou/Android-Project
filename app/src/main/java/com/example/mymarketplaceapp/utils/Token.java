package com.example.mymarketplaceapp.utils;

import java.util.Objects;

public class Token {

    public enum Type {ITEM_NAME, CATEGORY, USERNAME, MIN_PRICE, MAX_PRICE, DESCRIPTION}

    public static class IllegalTokenException extends IllegalArgumentException {
        public IllegalTokenException(String errorMessage) {
            super(errorMessage);
        }
    }

    private final String token;
    private final Type type;

    public Token(String token, Type type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Token)) return false;
        return this.type == ((Token) other).getType() && this.token.equals(((Token) other).getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, type);
    }
}
