package com.flowchart.repository;

import com.flowchart.model.Flowchart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowchartRepository extends JpaRepository<Flowchart, Long> {
}
