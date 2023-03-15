package com.example.seconttimespring.webrest;
import com.example.seconttimespring.entity.PostData;
import com.example.seconttimespring.model.Post;
import com.example.seconttimespring.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostResource {
    private final PostService postService;
    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity getAll(){
        Object result =  postService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("posts/prams")
    public ResponseEntity getAllById(@RequestParam Long postId){
        List<Post> result = postService.findAllByQueryParam(postId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("posts/paging")
    public ResponseEntity findAllbyPageble(Pageable pageable){
        Page<PostData> result = postService.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/posts")
    public ResponseEntity create(@RequestBody Post post){
        Post post1 = postService.save(post);
        return ResponseEntity.ok(post1);
    }
}





