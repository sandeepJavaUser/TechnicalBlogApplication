package technicalblog.repository;

import org.springframework.stereotype.Repository;
import technicalblog.model.Post;

import javax.persistence.*;
import java.util.List;

@Repository
public class PostRepository {
    @PersistenceUnit(unitName = "techblog")
    private EntityManagerFactory emf;

    public List<Post> getAllPosts() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Post> typedQuery = em.createQuery("select p from Post p", Post.class); //JPQL is used here
        List<Post> resultList = typedQuery.getResultList();
        return resultList;
    }

    public Post getLatestPost() {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.find(Post.class, 3);
    }

    public Post createPost(Post updatedPost) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(updatedPost);
                    transaction.commit();
        } catch (Exception e) {
           transaction.rollback();
        }
       return updatedPost;
    }
    public Post getPost(Integer postId){
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.find(Post.class, postId);
    }

    public void updatePost(Post post) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(post);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void deletePost(Integer postId) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Post post=entityManager.find(Post.class, postId);
            entityManager.remove(post);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}