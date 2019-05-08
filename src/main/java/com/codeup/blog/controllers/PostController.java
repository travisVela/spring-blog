package com.codeup.blog.controllers;

import com.codeup.blog.models.PostTransporter;
import com.codeup.blog.models.User;
import com.codeup.blog.repositories.PostRepository;
import com.codeup.blog.models.Post;
import com.codeup.blog.repositories.UserRepository;
import com.codeup.blog.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
//    private EmailService emailService;


    private final PostRepository postRepo;
    private final UserRepository userRepo;
    private EmailService emailService;
    PostTransporter pt = new PostTransporter();


    public PostController(PostRepository postDao, UserRepository userRepo, EmailService emailService) {
        this.postRepo = postDao;
        this.userRepo = userRepo;
        this.emailService = emailService;
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


    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id,  Model model) {

        model.addAttribute("post", postRepo.findOne(id));

        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post postToBeSaved) {
        postToBeSaved.setUser(userRepo.findOne(1L));
        Post savedPost = postRepo.save(postToBeSaved);
        emailService.prepareAndSend(savedPost, "Post has been created", "The post has been created successfully and you can find it with the ID of: " + savedPost.getId());

        return "redirect:/posts/" + savedPost.getId();
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        Post post = postRepo.findOne(id);
//        pt.setPost(post);
        model.addAttribute("post", post);
        return "posts/edit";
    }

//    @PostMapping("/posts/{id}/edit")
//    public String editPost(@RequestParam String title, @RequestParam String body) {
//        Post post = pt.getPost();
//        post.setTitle(title);
//        post.setBody(body);
//        Post saveEditPost = postRepo.save(post);
//        return "redirect:/posts/" + saveEditPost.getId();
//    }
    @PostMapping("/posts/{id}/edit")
    public String editPost(@ModelAttribute Post postToBeEdited) {
        postToBeEdited.setUser(userRepo.findOne(1L));
        postRepo.save(postToBeEdited);
        return "redirect:/posts/" + postToBeEdited.getId();
    }

    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        Post post = postRepo.findOne(id);
        postRepo.delete(post);
        return "redirect:/index";
    }
}
