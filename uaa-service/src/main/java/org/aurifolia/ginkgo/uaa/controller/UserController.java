package org.aurifolia.ginkgo.uaa.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author VNElinpe
 * @since 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/test")
    @PreAuthorize("hasAuthority('read1')")
    public String test() {
        return LocalDateTime.now().toString();
    }

    @GetMapping("/test1")
    @PreAuthorize("hasAuthority('read')")
    public String test1() {
        return LocalDateTime.now().toString();
    }
}
