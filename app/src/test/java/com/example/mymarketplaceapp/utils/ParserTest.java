package com.example.mymarketplaceapp.utils;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.Assert.*;


/**
 * Unit tests for the Parser class
 *
 * @author u7366711 Yuxuan Zhao
 */
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
        assertEquals("ELECTRONICS",query.getCategory());
    }

    @Test
    public void testDoubleCriteria() {
        tokenizer = new Tokenizer("uPhone #ELECTRONICS @JB Wi-Fi");
        Query query = new Parser(tokenizer).parseQuery();
        assertEquals("uPhone",query.getItemName());
        assertEquals("ELECTRONICS",query.getCategory());
        assertEquals("JB Wi-Fi",query.getUsername());
    }

    @Test
    public void testMultipleCriteria() {
        tokenizer = new Tokenizer("uPhone #ELECTRONICS @JB Wi-Fi >1024 <2048 ^bullet-proof");
        Query query = new Parser(tokenizer).parseQuery();
        assertEquals("uPhone",query.getItemName());
        assertEquals("ELECTRONICS",query.getCategory());
        assertEquals("JB Wi-Fi",query.getItemName());
        assertEquals("1024",query.getMinPrice());
        assertEquals("2048",query.getMaxPrice());
        assertEquals("bullet-proof",query.getDescription());
    }
}