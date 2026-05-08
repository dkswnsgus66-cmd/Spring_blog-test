package com.tenco.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;



}
