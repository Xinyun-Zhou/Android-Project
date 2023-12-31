package com.example.mymarketplaceapp.utils;

/**
 * Tokenizer modified from my lab code
 *
 * Example:
 * "uPhone #ELECTRONICS @JB Wi-Fi >1024 <2048 ^bullet-proof"
 * ->
 * Token.Type.ITEM_NAME uPhone
 * Token.Type.CATEGORY ELECTRONICS
 * Token.Type.USERNAME JB Wi-Fi
 * Token.Type.MIN_PRICE 1024
 * Token.Type.MAX_PRICE 2048
 * Token.Type.Description bullet-proof
 *
 * @author u7366711 Yuxuan Zhao
 */
public class Tokenizer {

    private String buffer;
    private Token currentToken;

    public static class IllegalTokenException extends IllegalArgumentException {
        public IllegalTokenException(String errorMessage) {
            super(errorMessage);
        }
    }

    public Tokenizer(String text) {
        buffer = text;
        next();
    }

    public void next() {
        buffer = buffer.trim();

        if (buffer.isEmpty()) {
            currentToken = null;
            return;
        }

        char firstChar = buffer.charAt(0);

        Token.Type tokenType;
        String tokenString = "";

        if (Character.isLetterOrDigit(firstChar)) {
            tokenType = Token.Type.ITEM_NAME;
            tokenString += firstChar;
        } else {
            switch (firstChar) {
                case '#':
                    tokenType = Token.Type.CATEGORY;
                    break;
                case '@':
                    tokenType = Token.Type.USERNAME;
                    break;
                case '>':
                    tokenType = Token.Type.MIN_PRICE;
                    break;
                case '<':
                    tokenType = Token.Type.MAX_PRICE;
                    break;
                case '^':
                    tokenType = Token.Type.DESCRIPTION;
                    break;
                default:
                    throw new Token.IllegalTokenException("");
            }
        }


        for (int i = 1; i < buffer.length(); i++) {
            if (buffer.charAt(i) == '#' || buffer.charAt(i) == '@' || buffer.charAt(i) == '>' || buffer.charAt(i) == '<' || buffer.charAt(i) == '^')
                break;
            else
                tokenString += buffer.charAt(i);
        }
        currentToken = new Token(tokenString.trim(), tokenType);

        int tokenLen = currentToken.getType().equals(Token.Type.ITEM_NAME) ? currentToken.getToken().length() : currentToken.getToken().length() + 1;
        buffer = buffer.substring(tokenLen);
    }

    public Token current() {
        return currentToken;
    }

    public boolean hasNext() {
        return !buffer.isEmpty();
    }
}
