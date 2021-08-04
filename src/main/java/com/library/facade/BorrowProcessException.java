package com.library.facade;

import java.util.function.Supplier;

public class BorrowProcessException extends Exception{

    public static String TITLE_NOT_FOUND = "There is no such title";
    public static String USER_NOT_FOUND = "There is no such user";
    public static String BORROW_ERR = "Cannot finish borrow process";

    public BorrowProcessException(String message) {
        super(message);
    }
}
