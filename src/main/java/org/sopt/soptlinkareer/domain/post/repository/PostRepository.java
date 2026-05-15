package org.sopt.soptlinkareer.domain.post.repository;

import org.sopt.soptlinkareer.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {}
