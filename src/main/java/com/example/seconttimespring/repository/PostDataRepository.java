package com.example.seconttimespring.repository;

import com.example.seconttimespring.entity.PostData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDataRepository extends JpaRepository<PostData,Long> {
}
