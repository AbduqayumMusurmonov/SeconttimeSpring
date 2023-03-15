package com.example.seconttimespring.repository;

import com.example.seconttimespring.entity.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage,Long> {

    FileStorage findByHashIda(String hashId);

}
