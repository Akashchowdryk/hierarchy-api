package com.example.hierarchy.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hierarchy.service.HierarchyValidator;

@RestController
@RequestMapping("/api")
public class HierarchyController {

    private final HierarchyValidator service;

    public HierarchyController(HierarchyValidator service) {
        this.service = service;
    }

    @PostMapping("/admin")
    public String addAdmin(@RequestBody Map<String, String> request) {
        service.addAdmin(request.get("userId"));
        return "Admin added successfully";
    }

 
    @PostMapping("/user")
    public String addUser(@RequestBody Map<String, String> request) {
        service.addUser(
                request.get("parentId"),
                request.get("userId"),
                request.get("role")
        );
        return "User added successfully";
    }

    @GetMapping("/hierarchy")
    public Object getHierarchy() {
        return service.getHierarchy();
    }
}
