package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import technicalblog.model.Post;
import technicalblog.repository.PostRepository;
import technicalblog.service.PostService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    PostRepository postRepository;

    @RequestMapping("posts")
    public String getUserPosts(Model model){
        List<Post> posts =postRepository.getAllPosts();
        model.addAttribute("posts", posts);
         return "posts";
    }

    @RequestMapping("posts/newpost")
    public String newPost(){
        return "posts/create";
    }


    @RequestMapping(value ="posts/create" , method = RequestMethod.POST)
    public String createPost(Post updatedPost){
        postService.createPost(updatedPost);
        return "redirect:/posts";
    }
    ///posts/create
    @RequestMapping(value ="/editPost",method = RequestMethod.GET)
    public String editPost(@RequestParam(name="postId") Integer postId , Model model){
       Post getpost= postService.getPost(postId);
       model.addAttribute("post",getpost);
        return "posts/edit";
    }
    @RequestMapping(value ="/editPost",method = RequestMethod.PUT)
    public String editPostSubmit(@RequestParam(name="postId") Integer postId , Post post){
        post.setId(postId);
        postService.updatePost(post);
        return "redirect:/posts";
    }
    @RequestMapping(value ="/deletePost",method = RequestMethod.DELETE)
    public String deletePostSubmit(@RequestParam(name="postId") Integer postId){
        postService.deletePost(postId);
        return "redirect:/posts";
    }
}
