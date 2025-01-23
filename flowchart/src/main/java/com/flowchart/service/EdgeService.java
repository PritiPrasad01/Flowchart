package com.flowchart.service;

import java.util.Optional;

import com.flowchart.model.Edge;
import com.flowchart.model.Flowchart;

public interface EdgeService {

	Edge saveEdge(Edge edge);
	
	void deleteEdge(String edgeId);

	Optional<Edge> getEdgeById(String edgeId);
}
