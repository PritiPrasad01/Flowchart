package com.flowchart.service;

import com.flowchart.model.Flowchart;
import com.flowchart.model.Node;
import com.flowchart.model.Edge;

import java.util.List;
import java.util.Optional;

public interface FlowchartService {

	Flowchart createFlowchart(Flowchart flowchart);

	Optional<Flowchart> getFlowchartById(Long id);

	void deleteFlowchart(Long id);

	List<Edge> getOutgoingEdges(Long flowchartId, Long nodeId);

	List<Node> getConnectedNodes(Long flowchartId, Long nodeId);

	List<Flowchart> getAllFlowcharts();

	boolean validateGraph(Long id);
}
