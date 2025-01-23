package com.flowchart.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flowchart.model.Edge;
import com.flowchart.model.Flowchart;
import com.flowchart.repository.EdgeRepository;
import com.flowchart.service.EdgeService;

@Service
public class EdgeServiceImpl implements EdgeService {

	@Autowired
	private EdgeRepository edgeRepository;
	
	@Override
	public Edge saveEdge(Edge edge) {
		return edgeRepository.save(edge);
	}

	@Override
	public void deleteEdge(String edgeId) {
		edgeRepository.deleteById(edgeId);
	}

	@Override
	public Optional<Edge> getEdgeById(String edgeId) {
		// TODO Auto-generated method stub
		return edgeRepository.findById(edgeId);
	}
	
}
