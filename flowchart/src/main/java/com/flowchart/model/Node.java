package com.flowchart.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
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
@Table(name = "nodes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Node {

	@Id
    private String id;

	 @JsonProperty("data")
	    private NodeData data;

    @ManyToOne
    @JoinColumn(name = "flowchart_id")
    @JsonIgnore
    private Flowchart flowchart;

    @Embedded
    private Position position = new Position();


    public Node(String id) {
        this.id = id;
    }


	@Embeddable
    public static class Position {
        private int x;
        private int y;
        
        // Getters and setters
        public int getX() {
            return x;
        }
        
        public void setX(int x) {
            this.x = x;
        }
        
        public int getY() {
            return y;
        }
        
        public void setY(int y) {
            this.y = y;
        }
    }
    

	@Embeddable
    public static class NodeData {
        @JsonProperty("label")
        private String label;

        // Getters and setters
        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
	
	public Node(String id, Flowchart flowchart2) {
		this.id=id;
		this.flowchart=flowchart2;
		this.data = new NodeData();
	}

	


}
