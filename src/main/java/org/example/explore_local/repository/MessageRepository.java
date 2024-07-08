package org.example.explore_local.repository;

import org.example.explore_local.model.entity.Message;
import org.example.explore_local.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
}
