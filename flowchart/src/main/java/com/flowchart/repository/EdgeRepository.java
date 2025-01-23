package com.flowchart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flowchart.model.Edge;

@Repository
public interface EdgeRepository extends JpaRepository<Edge, String> {
}
