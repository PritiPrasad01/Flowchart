package com.flowchart.service;

import java.util.Optional;

import com.flowchart.model.Node;

public interface NodeService {
	
	Node saveNode(Node node);
	Optional<Node> getNodeById(Long id); 

	void deleteNode(Long nodeId);
}
