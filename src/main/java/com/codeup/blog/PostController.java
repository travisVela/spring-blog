package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String showAll() {
        return "Posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String showPostById(@PathVariable long id) {
        return "View individual post: " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String showCreateForm() {
        return "View form for creating post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "new post";
    }
}
