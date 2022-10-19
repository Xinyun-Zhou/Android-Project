package com.example.mymarketplaceapp.utils;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.Assert;

/**
 * Unit tests for the Tokenizer class
 *
 * @author u7366711 Yuxuan Zhao
 */
public class TokenizerTest extends TestCase {
    private static Tokenizer tokenizer;

    @Test
    public void testItemNameToken() {
        tokenizer = new Tokenizer("uPhone #ELECTRONICS @JB Wi-Fi >1024 <2048 ^bullet-proof");

        Assert.assertEquals("wrong token type", Token.Type.ITEM_NAME, tokenizer.current().getType());
        Assert.assertEquals("wrong token value", "uPhone", tokenizer.current().getToken());
    }

    @Test
    public void testCategoryToken() {
        tokenizer = new Tokenizer("#ELECTRONICS @JB Wi-Fi >1024 <2048 ^bullet-proof");

        Assert.assertEquals("wrong token type", Token.Type.CATEGORY, tokenizer.current().getType());
        Assert.assertEquals("wrong token value", "ELECTRONICS", tokenizer.current().getToken());
    }

    @Test
    public void testUserNameToken() {
        tokenizer = new Tokenizer("@JB Wi-Fi >1024 <2048 ^bullet-proof");

        Assert.assertEquals("wrong token type", Token.Type.USER_NAME, tokenizer.current().getType());
        Assert.assertEquals("wrong token value", "JB Wi-Fi", tokenizer.current().getToken());
    }

    @Test
    public void testPriceGreaterToken() {
        tokenizer = new Tokenizer(">1024 <2048 ^bullet-proof");

        Assert.assertEquals("wrong token type", Token.Type.MIN_PRICE, tokenizer.current().getType());
        Assert.assertEquals("wrong token value", "1024", tokenizer.current().getToken());
    }

    @Test
    public void testPriceLessToken() {
        tokenizer = new Tokenizer("<2048 ^bullet-proof");

        Assert.assertEquals("wrong token type", Token.Type.MAX_PRICE, tokenizer.current().getType());
        Assert.assertEquals("wrong token value", "2048", tokenizer.current().getToken());
    }

    @Test
    public void testDescriptionToken() {
        tokenizer = new Tokenizer("^bullet-proof");

        Assert.assertEquals("wrong token type", Token.Type.DESCRIPTION, tokenizer.current().getType());
        Assert.assertEquals("wrong token value", "bullet-proof", tokenizer.current().getToken());
    }
}