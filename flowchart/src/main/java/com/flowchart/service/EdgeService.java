package com.flowchart.service;

import java.util.Optional;

import com.flowchart.model.Edge;
import com.flowchart.model.Flowchart;

public interface EdgeService {

	Edge saveEdge(Edge edge);
	
	void deleteEdge(Long edgeId);

	Optional<Edge> getEdgeById(Long edgeId);
}
