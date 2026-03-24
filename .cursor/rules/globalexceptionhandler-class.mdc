---
description: Structure of GlobalExceptionHandler class.
globs: **/src/main/java/com/example/demo/exception/GlobalExceptionHandler.java
---
- Must annotate the class with `@RestControllerAdvice`.
- Must expose a reusable helper to build standardized error `ResponseEntity<ApiResponse<?>>`.
- Must include `@ExceptionHandler(IllegalArgumentException.class)` returning HTTP 400.
- Exception responses must use the ApiResponse error shape (message + null data).
