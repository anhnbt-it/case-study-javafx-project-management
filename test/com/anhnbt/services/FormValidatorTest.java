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
class FormValidatorTest {
    private FormValidator formValidator = new FormValidator();

    @Test
    @DisplayName("anhnbt@outlook.com.vn")
    void testCaseEmailOne() {
        String regex = "anhnbt@outlook.com.vn";
        boolean actual = formValidator.email(regex);
        assertTrue(actual);
    }

    @Test
    @DisplayName("son.mai@codegym.vn")
    void testCaseEmailTwo() {
        String regex = "anhnbt.it@gmail.com";
        boolean actual = formValidator.email(regex);
        assertTrue(actual);
    }

    @Test
    @DisplayName("anh.vu@codegym.vn")
    void testCaseEmailThree() {
        String regex = "anh.vu@codegym.vn";
        boolean actual = formValidator.email(regex);
        assertTrue(actual);
    }

    @Test
    @DisplayName("Lorem Ipsum")
    void testCaseNameTwo() {
        String regex = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";
        boolean actual = formValidator.name(regex);
        assertFalse(actual);
    }

    @Test
    @DisplayName("0346868928")
    void testPhoneOne() {
        String regex = "0346868928";
        boolean actual = formValidator.phone(regex);
        assertTrue(actual);
    }

    @Test
    @DisplayName("03468689289")
    void testPhoneTwo() {
        String regex = "03468689289";
        boolean actual = formValidator.phone(regex);
        assertFalse(actual);
    }

    @Test
    @DisplayName("abc")
    void testPhoneThree() {
        String regex = "abc";
        boolean actual = formValidator.phone(regex);
        assertFalse(actual);
    }
}