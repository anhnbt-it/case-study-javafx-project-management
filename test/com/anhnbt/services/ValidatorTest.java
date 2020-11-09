package com.anhnbt.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: AnhNBT (anhnbt.it@gmail.com)
 * Date: 09/11/2020
 * Time: 1:53 CH
 */
class ValidatorTest {
    private Validator validator = new Validator();

    @Test
    @DisplayName("anhnbt@outlook.com.vn")
    void testCaseEmailOne() {
        String regex = "anhnbt@outlook.com.vn";
        boolean actual = validator.email(regex);
        assertTrue(actual);
    }

    @Test
    @DisplayName("son.mai@codegym.vn")
    void testCaseEmailTwo() {
        String regex = "anhnbt.it@gmail.com";
        boolean actual = validator.email(regex);
        assertTrue(actual);
    }

    @Test
    @DisplayName("anh.vu@codegym.vn")
    void testCaseEmailThree() {
        String regex = "anh.vu@codegym.vn";
        boolean actual = validator.email(regex);
        assertTrue(actual);
    }

    @Test
    @DisplayName("Lorem Ipsum")
    void testCaseNameTwo() {
        String regex = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";
        boolean actual = validator.name(regex);
        assertFalse(actual);
    }
}