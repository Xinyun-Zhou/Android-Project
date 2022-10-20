package com.example.mymarketplaceapp.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Grammar:
 * <query>      ::= <item name> | <item name> <filter>
 * <filter>     ::= <criteria> | <criteria> <filter>
 * <criteria>   ::= <category> | <user name> | <price greater> | <price less> | <description>
 *
 * @author u7366711 Yuxuan Zhao
 */
public class Parser {

    public static class IllegalProductionException extends IllegalArgumentException {
        public IllegalProductionException(String errorMessage) {
            super(errorMessage);
        }
    }

    Tokenizer tokenizer;

    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public Query parseQuery() {
        if (tokenizer.current() == null)
            throw new IllegalProductionException("");

        String itemName = parseItemName();

        if (!tokenizer.hasNext())
            return new Query(itemName);

        List<Token> filter = new ArrayList<>();
        parseFilter(filter);

        return new Query(itemName, filter);
    }

    public String parseItemName() {
        if (tokenizer.current() == null)
            throw new IllegalProductionException("");
        else
            return tokenizer.current().getToken();
    }

    public void parseFilter(List<Token> filter) {
        Token criteria = parseCriteria();
        filter.add(criteria);

        if (tokenizer.hasNext())
            parseFilter(filter);

        return;
    }

    public Token parseCriteria() {
        try {
            tokenizer.next();
        }catch (Tokenizer.IllegalTokenException e){
            throw new IllegalProductionException("");
        }

        return tokenizer.current();
    }

}
