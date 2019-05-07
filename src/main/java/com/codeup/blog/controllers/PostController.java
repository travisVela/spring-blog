package com.codeup.blog.controllers;

import com.codeup.blog.models.User;
import com.codeup.blog.repositories.PostRepository;
import com.codeup.blog.models.Post;
import com.codeup.blog.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
//    private EmailService emailService;


    private final PostRepository postRepo;
    private final UserRepository userRepo;


    public PostController(PostRepository postDao, UserRepository userRepo) {
        this.postRepo = postDao;
        this.userRepo = userRepo;
    }


//
//    public PostController(EmailService emailService) {
//        this.emailService = emailService;
//    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "posts/index";
    }


    @GetMapping("/show/{id}")
    public String singlePost(@PathVariable long id,  Model model) {

        model.addAttribute("post", postRepo.findOne(id));

        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreateForm() {
        return "posts/create";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(@RequestParam String title, @RequestParam String body, User user) {
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setBody(body);
        user = new User();
        user.setId(1);
        newPost.setUser(user);
        postRepo.save(newPost);

        return "new post created";
    }


    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        Post post = postRepo.findOne(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    @ResponseBody
    public String editPost(@RequestParam String title, @RequestParam String body, @PathVariable long id) {
        Post post = postRepo.findOne(id);
        post.setTitle(title);
        post.setBody(body);
        postRepo.save(post);
        return "Successfully edited post";
    }

    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        Post post = postRepo.findOne(id);

        postRepo.delete(post);
        return "posts/index";
    }
}
