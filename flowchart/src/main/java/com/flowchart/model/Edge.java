package com.flowchart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "edges")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Edge {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private Node source;

    @ManyToOne
    @JoinColumn(name = "target_id")
    private Node target;

    @ManyToOne
    @JoinColumn(name = "flowchart_id")
    @JsonIgnore
    private Flowchart flowchart;
    
}
