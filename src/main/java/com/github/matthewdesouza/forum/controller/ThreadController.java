package com.github.matthewdesouza.forum.controller;

import com.github.matthewdesouza.forum.entity.Post;
import com.github.matthewdesouza.forum.entity.Thread;
import com.github.matthewdesouza.forum.service.PostService;
import com.github.matthewdesouza.forum.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ThreadController {
    private final ThreadService threadService;
    private final PostService postService;

    @Autowired
    public ThreadController(ThreadService threadService, PostService postService) {
        this.threadService = threadService;
        this.postService = postService;
    }

    @GetMapping("/threads")
    String getAllThreads(Model model) {
        model.addAttribute("threads", threadService.getAllThreads());
        return "thread/all";
    }

    @GetMapping("/threads/{name}")
    String getSingleThread(Model model, @PathVariable String name) {
        Thread thread = threadService.getThreadByName(name);
        model.addAttribute("thread", thread);
        return "thread/single";
    }

    @GetMapping("/threads/create")
    String createThread(Model model) {
        model.addAttribute("thread", new Thread());
        return "thread/create";
    }

    @PostMapping("/threads")
    String saveThread(@ModelAttribute("thread") Thread thread) {
        threadService.saveThread(thread);
        return "redirect:/threads";
    }

    @GetMapping("/threads/{threadName}/post/create")
    String createPostInThread(Model model, @PathVariable String threadName) {
        Thread thread = threadService.getThreadByName(threadName);
//        Post post = new Post();
//        post.setThread(thread);
//        thread.getPosts().add(post);
        model.addAttribute("post", new Post());
        model.addAttribute("thread", thread);
        return "post/create";
    }

    @PostMapping("/threads/{name}")
    String savePostInThread(@ModelAttribute("thread") Thread thread, @ModelAttribute("post") Post post) {
        thread.addPost(post);
        postService.savePost(post);
        threadService.updateThread(thread);
        return "redirect:/threads/{name}";
    }
}
