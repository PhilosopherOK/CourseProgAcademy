package ua.kiev.prog.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.kiev.prog.dto.PageCountDTO;
import ua.kiev.prog.model.User;
import ua.kiev.prog.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class MainPageController {
    private static final int PAGE_SIZE = 10;
    private final UserService userService;

    public MainPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("count") // /admin/count
    public PageCountDTO count() {
        return PageCountDTO.of(userService.countUsers(), PAGE_SIZE);
    }

    @GetMapping("geo") // /admin/geo
    public List<User> locations(@RequestParam(required = false, defaultValue = "0") int page) {
        return userService.findAllUsers(
                PageRequest.of(page, PAGE_SIZE, Sort.Direction.DESC, "id"));
    }



}
