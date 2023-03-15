package com.example.seconttimespring.services;

import com.example.seconttimespring.entity.PostData;
import com.example.seconttimespring.model.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.yaml.snakeyaml.util.UriEncoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {
    public final RestTemplate restTemplate;
    public final PostDataService postDataService;
    @Value("${api.jsonplaceholder}")
    private String api;

    public PostService(RestTemplate restTemplate, PostDataService postDataService) {
        this.restTemplate = restTemplate;
        this.postDataService = postDataService;
    }

    public Post save(Post post){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Post> entity = new HttpEntity<>(post,httpHeaders);
        Post result = restTemplate.postForObject(api + "/posts",entity, Post.class);
        return result;
    }

    public Post update(Long id,Post post){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Post> entity = new HttpEntity<>(post,httpHeaders);
        Post result = restTemplate.postForObject(api + "/posts" + id,entity, Post.class);
        return result;
    }

    public Object findAll(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Post[]> entity = new HttpEntity<>(httpHeaders);
        Post[] result = restTemplate.exchange(this.api + "/posts", HttpMethod.GET, entity,Post[].class).getBody();
        postDataService.saveAll(result);
        return result;
    }

    public List<Post> findAllByQueryParam(Long postId){  // request param restTemplateda!!!
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<Post>> entity = new HttpEntity<>(httpHeaders);
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(this.api + "/comments")
                .queryParam("postId","{postId}")
                .encode()
                .toUriString();

        Map<String,Object> params = new HashMap<>();
        params.put("postId",postId);

        List<Post> result = restTemplate.exchange(urlTemplate, HttpMethod.GET, entity,List.class,params).getBody();
        return result;


    }

    public Page<PostData> findAll(Pageable pageable){
        return postDataService.findAll(pageable);
    }


}
