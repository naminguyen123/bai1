package com.taxi.booking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    // 1. GET /add?a=3&b=5
    @GetMapping("/add")
    public int add(@RequestParam int a,
                   @RequestParam int b) {
        return a + b;
    }

    // 2. GET /subtract/{a}/{b}
    @GetMapping("/subtract/{a}/{b}")
    public int subtract(@PathVariable int a,
                        @PathVariable int b) {
        return a - b;
    }

    // 3. POST /multiply
    @PostMapping("/multiply")
    public int multiply(@RequestBody CalculatorRequest request) {
        return request.a() * request.b();
    }

    // 4. POST /divide
    @PostMapping("/divide")
    public ResponseEntity<?> divide(@RequestBody CalculatorRequest request) {

        if (request.b() == 0) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Không thể chia cho 0");
        }

        double result = (double) request.a() / request.b();

        return ResponseEntity.ok(result);
    }
}