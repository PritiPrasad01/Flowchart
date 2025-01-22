package com.flowchart.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flowchart.model.Edge;
import com.flowchart.model.Flowchart;
import com.flowchart.model.Node;
import com.flowchart.repository.FlowchartRepository;
import com.flowchart.service.EdgeService;
import com.flowchart.service.FlowchartService;
import com.flowchart.service.NodeService;

@RestController
@RequestMapping("/api/flowcharts")
@CrossOrigin(origins = "http://localhost:3000")
public class FlowchartController {

	@Autowired
	private FlowchartService flowchartService;

	@Autowired
	private FlowchartRepository flowchartRepository;

	@Autowired
	private NodeService nodeService;

	@Autowired
	private EdgeService edgeService;

	// Fetch all flowcharts
	@GetMapping
	public List<Flowchart> getAllFlowcharts() {
		return flowchartService.getAllFlowcharts();
	}

	// Fetch a flowchart by ID
	@GetMapping("/{id}")
	public ResponseEntity<Flowchart> getFlowchartById(@PathVariable Long id) {
		return flowchartService.getFlowchartById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Create a new flowchart
	@PostMapping
	public ResponseEntity<Flowchart> createFlowchart(@RequestBody Flowchart flowchart) {
		flowchart.setNodes(new ArrayList<>());
		flowchart.setEdges(new ArrayList<>());

		Flowchart savedFlowchart = flowchartService.createFlowchart(flowchart);
		return ResponseEntity.ok(savedFlowchart);
	}

	// Delete a flowchart
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFlowchart(@PathVariable Long id) {
		if (flowchartService.getFlowchartById(id).isPresent()) {
			flowchartService.deleteFlowchart(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Add Node to Flowchart
	@PostMapping("/{flowchartId}/nodes")
	public ResponseEntity<Node> addNodeToFlowchart(@PathVariable Long flowchartId, @RequestBody Node node) {

		Flowchart flowchart = flowchartService.getFlowchartById(flowchartId)
				.orElseThrow(() -> new RuntimeException("Flowchart not found"));
		node.setFlowchart(flowchart);
		Node savedNode = nodeService.saveNode(node);
		flowchart.getNodes().add(savedNode);
		flowchartRepository.save(flowchart);

		return ResponseEntity.ok(savedNode);
	}

	// Add Edge to Flowchart
	@PostMapping("/{flowchartId}/edges")
	public ResponseEntity<Edge> addEdgeToFlowchart(@PathVariable Long flowchartId, @RequestBody Edge edge) {

		Flowchart flowchart = flowchartService.getFlowchartById(flowchartId)
				.orElseThrow(() -> new RuntimeException("Flowchart not found"));
		Node sourceNode = nodeService.getNodeById(edge.getSource().getId())
				.orElseThrow(() -> new RuntimeException("Source Node not found"));
		Node targetNode = nodeService.getNodeById(edge.getTarget().getId())
				.orElseThrow(() -> new RuntimeException("Target Node not found"));
		edge.setFlowchart(flowchart);
		edge.setSource(sourceNode);
		edge.setTarget(targetNode);
		Edge savedEdge = edgeService.saveEdge(edge);
		flowchart.getEdges().add(savedEdge);
		flowchartService.createFlowchart(flowchart);

		return ResponseEntity.ok(savedEdge);
	}

//   Fetch all outgoing edges for a specific node
	@GetMapping("/{id}/node/{nodeId}/outgoing-edges")
	public ResponseEntity<List<Edge>> getOutgoingEdges(@PathVariable Long id, @PathVariable Long nodeId) {
		List<Edge> outgoingEdges = flowchartService.getOutgoingEdges(id, nodeId);
		return ResponseEntity.ok(outgoingEdges);
	}

	// Fetch all nodes connected to a specific node (directly or indirectly)
	@GetMapping("/{id}/node/{nodeId}/connected-nodes")
	public ResponseEntity<List<Node>> getConnectedNodes(@PathVariable Long id, @PathVariable Long nodeId) {
		List<Node> connectedNodes = flowchartService.getConnectedNodes(id, nodeId);
		return ResponseEntity.ok(connectedNodes);
	}
	// To validate graph
	@GetMapping("/{id}/validate-graph")
	public ResponseEntity<Map<String, Object>> validateGraph(@PathVariable Long id) {
	    boolean isValid = flowchartService.validateGraph(id);
	    Map<String, Object> response = new HashMap<>();
	    response.put("isValid", isValid);
	    response.put("message", isValid ? "Valid flowchart" : "Invalid flowchart");
	    return ResponseEntity.ok(response);
	}
	
	// Delete Node from Flowchart
	@DeleteMapping("/{flowchartId}/nodes/{nodeId}")
	public ResponseEntity<Void> deleteNodeFromFlowchart(@PathVariable Long flowchartId, @PathVariable Long nodeId) {
	    Flowchart flowchart = flowchartService.getFlowchartById(flowchartId)
	            .orElseThrow(() -> new RuntimeException("Flowchart not found"));
	    Node node = nodeService.getNodeById(nodeId)
	            .orElseThrow(() -> new RuntimeException("Node not found"));
	    flowchart.getNodes().remove(node);
	    flowchartRepository.save(flowchart);
	    nodeService.deleteNode(nodeId);
	    return ResponseEntity.noContent().build();
	}

	// Delete Edge from Flowchart
	@DeleteMapping("/{flowchartId}/edges/{edgeId}")
	public ResponseEntity<Void> deleteEdgeFromFlowchart(@PathVariable Long flowchartId, @PathVariable Long edgeId) {
	    Flowchart flowchart = flowchartService.getFlowchartById(flowchartId)
	            .orElseThrow(() -> new RuntimeException("Flowchart not found"));
	    Edge edge = edgeService.getEdgeById(edgeId)
	            .orElseThrow(() -> new RuntimeException("Edge not found"));
	    flowchart.getEdges().remove(edge);
	    flowchartRepository.save(flowchart);
	    edgeService.deleteEdge(edgeId);
	    return ResponseEntity.noContent().build();
	}

}
