package com.flowchart.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "flowchart")
@Data
@Builder
@AllArgsConstructor
public class Flowchart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@OneToMany(mappedBy = "flowchart", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonManagedReference 
    private List<Node> nodes = new ArrayList<>();

    @OneToMany(mappedBy = "flowchart", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference 
    private List<Edge> edges = new ArrayList<>();

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	public Flowchart() {
		this.createdAt = LocalDateTime.now();
	}

}
