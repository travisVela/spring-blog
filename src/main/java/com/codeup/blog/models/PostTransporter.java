package com.codeup.blog.models;

public class PostTransporter {
    private Post post;

    public PostTransporter(Post post) {
        this.post = post;
    }

    public PostTransporter() {

    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
