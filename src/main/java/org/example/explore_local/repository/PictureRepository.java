package org.example.explore_local.repository;

import org.example.explore_local.model.entity.Picture;
import org.example.explore_local.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture,Long> {
}
