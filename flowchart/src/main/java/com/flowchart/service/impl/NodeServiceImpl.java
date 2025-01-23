package com.flowchart.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flowchart.model.Node;
import com.flowchart.repository.NodeRepository;
import com.flowchart.service.NodeService;
@Service
public class NodeServiceImpl implements NodeService {

	
	@Autowired
	private NodeRepository nodeRepository;
	
	@Override
	public Node saveNode(Node node) {
		return nodeRepository.save(node);
	}

	@Override
	public Optional<Node> getNodeById(String id) {
		// TODO Auto-generated method stub
		return nodeRepository.findById(id);
	}

	@Override
	public void deleteNode(String nodeId) {
		nodeRepository.deleteById(nodeId);
		
	}
	


}
