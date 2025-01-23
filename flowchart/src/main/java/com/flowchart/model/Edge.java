package com.flowchart.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
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
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "source_id")
    private Node source;
    
    @ManyToOne
    @JoinColumn(name = "target_id")
    private Node target;
    
    private boolean animated;
    
    private String style;
    
    @ManyToOne
    @JoinColumn(name = "flowchart_id")
    @JsonIgnore
    private Flowchart flowchart;
    
    @JsonGetter("source")
    public String getSourceLabel() {
        return source.getData().getLabel();
    }
    
    @JsonGetter("target")
    public String getTargetLabel() {
        return target.getData().getLabel();
    }

    
}
