package com.example.mymarketplaceapp.utils;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.Assert.*;

public class ParserTest extends TestCase {
    private static Tokenizer tokenizer;

    @Test
    public void testItemName() {
        tokenizer = new Tokenizer("uPhone");
        Query query = new Parser(tokenizer).parseQuery();
        assertEquals("uPhone",query.getItemName());
    }

    @Test
    public void testSingleCriteria() {
        tokenizer = new Tokenizer("uPhone #ELECTRONICS");
        Query query = new Parser(tokenizer).parseQuery();
        assertEquals("uPhone",query.getItemName());
        assertEquals("ELECTRONICS",query.getFilter().get(0).getToken());
    }

    @Test
    public void testDoubleCriteria() {
        tokenizer = new Tokenizer("uPhone #ELECTRONICS @JB Wi-Fi");
        Query query = new Parser(tokenizer).parseQuery();
        assertEquals("uPhone",query.getItemName());
        assertEquals("ELECTRONICS",query.getFilter().get(0).getToken());
        assertEquals("JB Wi-Fi",query.getFilter().get(1).getToken());
    }

    @Test
    public void testMultipleCriteria() {
        tokenizer = new Tokenizer("uPhone #ELECTRONICS @JB Wi-Fi >1024 <2048 ^bullet-proof");
        Query query = new Parser(tokenizer).parseQuery();
        assertEquals("uPhone",query.getItemName());
        assertEquals("ELECTRONICS",query.getFilter().get(0).getToken());
        assertEquals("JB Wi-Fi",query.getFilter().get(1).getToken());
        assertEquals("1024",query.getFilter().get(2).getToken());
        assertEquals("2048",query.getFilter().get(3).getToken());
        assertEquals("bullet-proof",query.getFilter().get(4).getToken());
    }
}