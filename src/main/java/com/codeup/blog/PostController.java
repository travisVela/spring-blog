package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private Post post = new Post();
    private Post post2 = new Post();



    @GetMapping("/index")
    public String showAll(Model model) {
        List<Post> posts = new ArrayList<>();
        String title = "Post # 2";
        String body = "These things happen automatically. All you have to do is just let them happen. Now we don't want him to get lonely, so we'll give him a little friend. There isn't a rule. You just practice and find out which way works best for you. With practice comes confidence. A fan brush can be your best friend. If you don't think every day is a good day - try missing a few. You'll see.";

        post2.setTitle(title);
        post2.setBody(body);

        posts.add(post);
        posts.add(post2);

        model.addAttribute("posts", posts);
        model.addAttribute("title", title);
        model.addAttribute("body", body);
        return "posts/index";
    }

    @GetMapping("/show")
    public String showPostById(Model model) {
        String body = "This is the first post. This is filler test to take up as much space as I can for the first post. Posts are usually about something more interesting, but I don't want to write anything. I would rather post about posting.";

        String title = "Hello World";
        post.setTitle(title);
        post.setBody(body);

        model.addAttribute("title", post.getTitle());
        model.addAttribute("body", post.getBody());
        return "posts/show";
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
