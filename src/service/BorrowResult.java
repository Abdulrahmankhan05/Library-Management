package service;

public enum BorrowResult{
    SUCCESS,
    MEMBER_NOT_FOUND,
    BOOK_NOT_FOUND,
    BOOK_ALREADY_BORROWED,
    BORROW_LIMIT_REACHED

}