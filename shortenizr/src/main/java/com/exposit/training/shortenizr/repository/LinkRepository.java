package com.exposit.training.shortenizr.repository;

import com.exposit.training.shortenizr.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, String> {
}
