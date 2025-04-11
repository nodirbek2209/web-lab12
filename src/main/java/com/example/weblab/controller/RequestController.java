package com.example.weblab.controller;

import com.example.weblab.dto.RequestDTO;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/request")
public class RequestController {

    @PostMapping
    public String handleRequest(@Valid @RequestBody RequestDTO dto) {
        System.out.println("Received: " + dto.getName() + ", " + dto.getAge());
        return "Received successfully";
    }
}
