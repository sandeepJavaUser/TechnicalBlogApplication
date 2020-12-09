package technicalblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalblog.model.Post;
import technicalblog.repository.PostRepository;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public List<Post> getAllPost() {

        return postRepository.getAllPosts();

    }

    public Post getOnePost() {

        return postRepository.getLatestPost();
    }

    public void createPost(Post updatedPost) {
        updatedPost.setDate(new Date());
        postRepository.createPost(updatedPost);
        System.out.println("New Post: " + updatedPost);
    }
    public Post getPost(Integer postId){
       return postRepository.getPost(postId);
    }

    public void updatePost(Post post) {
        post.setDate(new Date());
        postRepository.updatePost(post);
    }

    public void deletePost(Integer postId) {
           postRepository.deletePost(postId);
    }
}
