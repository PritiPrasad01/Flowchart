package com.flowchart.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flowchart.model.Edge;
import com.flowchart.model.Flowchart;
import com.flowchart.model.Node;
import com.flowchart.repository.FlowchartRepository;
import com.flowchart.service.FlowchartService;

@Service
public class FlowchartServiceImpl implements FlowchartService {

	@Autowired
	private FlowchartRepository flowchartRepository;

	public List<Flowchart> getAllFlowcharts() {
		return flowchartRepository.findAll();
	}

	public Optional<Flowchart> getFlowchartById(Long id) {
		return flowchartRepository.findById(id);
	}

	public Flowchart createFlowchart(Flowchart flowchart) {
		return flowchartRepository.save(flowchart);
	}

	public void deleteFlowchart(Long id) {
		flowchartRepository.deleteById(id);
	}

	@Override
	public List<Edge> getOutgoingEdges(Long flowchartId, Long nodeId) {
		Optional<Flowchart> flowchart = getFlowchartById(flowchartId);
		return flowchart.map(fc -> fc.getEdges().stream().filter(edge -> edge.getSource().getId().equals(nodeId))
				.collect(Collectors.toList())).orElse(Collections.emptyList());
	}

	@Override
	public List<Node> getConnectedNodes(Long flowchartId, Long nodeId) {
		Optional<Flowchart> flowchart = getFlowchartById(flowchartId);
		return flowchart.map(fc -> {
			// Using BFS/DFS to find all connected nodes
			Set<Long> visited = new HashSet<>();
			Queue<Long> queue = new LinkedList<>();
			queue.add(nodeId);
			while (!queue.isEmpty()) {
				Long currentNodeId = queue.poll();
				if (!visited.contains(currentNodeId)) {
					visited.add(currentNodeId);
					List<Long> neighbors = fc.getEdges().stream()
							.filter(edge -> edge.getSource().getId().equals(currentNodeId))
							.map(edge -> edge.getTarget().getId()).collect(Collectors.toList());
					queue.addAll(neighbors);
				}
			}
			return fc.getNodes().stream().filter(node -> visited.contains(node.getId())).collect(Collectors.toList());
		}).orElse(Collections.emptyList());
	}
	
	
	public boolean validateGraph(Long flowchartId) {
	    Flowchart flowchart = getFlowchartById(flowchartId)
	            .orElseThrow(() -> new RuntimeException("Flowchart not found"));

	    // Check for cycles
	    if (hasCycle(flowchart)) {
	        return false;
	    }

	    // Check for disjoint nodes
	    if (hasDisjointNodes(flowchart)) {
	        return false;
	    }

	    // Check edge consistency
	    if (!hasConsistentEdges(flowchart)) {
	        return false;
	    }

	    return true;
	}

	 boolean hasCycle(Flowchart flowchart) {
	    Set<Node> visited = new HashSet<>();
	    for (Node node : flowchart.getNodes()) {
	        if (hasCycle(node, visited, new HashSet<>(),flowchart.getId())) {
	            return true;
	        }
	    }
	    return false;
	}

	 boolean hasCycle(Node node, Set<Node> visited, Set<Node> currentPath, Long flowchartId) {
	    if (currentPath.contains(node)) {
	        return true;
	    }
	    if (visited.contains(node)) {
	        return false;
	    }
	    visited.add(node);
	    currentPath.add(node);
	    List<Edge> outgoingEdges = getOutgoingEdges(flowchartId, node.getId());
	    for (Edge edge : outgoingEdges) {
	        if (hasCycle(edge.getTarget(), visited, currentPath, flowchartId)) {
	            return true;
	        }
	    }
	    currentPath.remove(node);
	    return false;
	}

	boolean hasDisjointNodes(Flowchart flowchart) {
	    Set<Node> visited = new HashSet<>();
	    for (Node node : flowchart.getNodes()) {
	        if (!visited.contains(node)) {
	            dfs(node, visited,flowchart.getId());
	        }
	    }
	    return visited.size() != flowchart.getNodes().size();
	}

	private void dfs(Node node, Set<Node> visited, Long flowchartId) {
	    visited.add(node);
	    List<Edge> outgoingEdges = getOutgoingEdges(flowchartId, node.getId());
	    for (Edge edge : outgoingEdges) {
	        if (!visited.contains(edge.getTarget())) {
	            dfs(edge.getTarget(), visited, flowchartId);
	        }
	    }
	}

	boolean hasConsistentEdges(Flowchart flowchart) {
	    for (Edge edge : flowchart.getEdges()) {
	        if (!flowchart.getNodes().contains(edge.getSource()) ||
	                !flowchart.getNodes().contains(edge.getTarget())) {
	            return false;
	        }
	    }
	    return true;
	}

}
