
## Project Title

Library Management System

## Description

A Java project that manages library operations such as borrowing and returning books. 
This project demonstrates the separation of the responsibilities across service, model, and repository layers.

The main goal is to practice clean code structure and design principles without using external frameworks.
## Features

- Add and store member details
- Add and store book details
- Borrow books with membership-based rules
- Return borrowed books
- List books, members, and borrowed records
- Delete book when eligible


## Tech Stack

- Java(core)

- No external framework(CLI-based)

## Project Structure
```
src/
├─ Main.java
└─ model/
    ├─BasicMembership.java
    ├─Book.java
    ├─BorrowRecord.java
    ├─Member.java
    ├─Membership.java
    └─PremiumMembership.java 
└─repository/
    ├─BookRepository.java
    ├─BorrowRecordRepository.java
    └─MemberRepository.java
└─ service/
    ├─BorrowResult.java
    ├─Library.java
    └─ReturnResult.java
    
```

## Current Progress

- Core library operations are fully implemented using a layered architecture
- Repository layer manages data storage for books, members, and borrow records
- Service layer enforces business rules such as borrow limits and availability checks
- Borrow and return operations use enum-based result handling instead of boolean flags
- Console-based UI is functional and separated from core logic

## Future Work

- Add persistent storage using files or a database
- Expose the system as a REST API using a web framework