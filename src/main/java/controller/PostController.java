package controller;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IPostService;
import service.impl.PostService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("/post/list");
        modelAndView.addObject("posts", postService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm(Model model) {
        ModelAndView modelAndView = new ModelAndView("/post/create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(Post post) {
        post.setCreateAt(LocalDateTime.now());
        postService.save(post);
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/post/edit");
        Post post = postService.findById(id).get();
        modelAndView.addObject("pos", post);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView update(@PathVariable Long id, Post post) {
        post.setCreateAt(LocalDateTime.now());
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        postService.save(post);
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView find(@RequestParam String title){
        ModelAndView modelAndView = new ModelAndView("/post/search");
        modelAndView.addObject("posts",postService.findByTitle(title));
        return modelAndView;
    }
    @GetMapping("/findLike")
    public ModelAndView findLike(){
        ModelAndView modelAndView = new ModelAndView("/post/finds");
        modelAndView.addObject("posts", postService.findByLikes());
        return modelAndView;
    }
}