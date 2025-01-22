package com.flowchart.service.impl;

import com.flowchart.model.Edge;
import com.flowchart.model.Flowchart;
import com.flowchart.model.Node;
import com.flowchart.repository.FlowchartRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlowchartServiceImplTest {

    @Mock
    private FlowchartRepository flowchartRepository;

    @InjectMocks
    private FlowchartServiceImpl flowchartService;

    private Flowchart flowchart;

    @Before
    public void setup() {
        flowchart = new Flowchart();
        flowchart.setId(1L);
        flowchart.setNodes(new ArrayList<>());
        flowchart.setEdges(new ArrayList<>());
    }

    @Test
    public void testGetAllFlowcharts() {
        List<Flowchart> flowcharts = new ArrayList<>();
        flowcharts.add(flowchart);
        when(flowchartRepository.findAll()).thenReturn(flowcharts);
        List<Flowchart> result = flowchartService.getAllFlowcharts();
        assertEquals(1, result.size());
    }

    @Test
    public void testGetFlowchartById() {
        when(flowchartRepository.findById(1L)).thenReturn(Optional.of(flowchart));
        Optional<Flowchart> result = flowchartService.getFlowchartById(1L);
        assertTrue(result.isPresent());
        assertEquals(flowchart, result.get());
    }

    @Test
    public void testCreateFlowchart() {
        when(flowchartRepository.save(any(Flowchart.class))).thenReturn(flowchart);
        Flowchart result = flowchartService.createFlowchart(flowchart);
        assertEquals(flowchart, result);
    }

    @Test
    public void testDeleteFlowchart() {
        flowchartService.deleteFlowchart(1L);
        // No exception thrown
    }

    @Test
    public void testGetOutgoingEdges() {
        Node node = new Node();
        node.setId(1L);
        Edge edge = new Edge();
        edge.setSource(node);
        flowchart.getEdges().add(edge);
        when(flowchartRepository.findById(1L)).thenReturn(Optional.of(flowchart));
        List<Edge> result = flowchartService.getOutgoingEdges(1L, 1L);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetConnectedNodes() {
        Node node = new Node();
        node.setId(1L);
        flowchart.getNodes().add(node);
        when(flowchartRepository.findById(1L)).thenReturn(Optional.of(flowchart));
        List<Node> result = flowchartService.getConnectedNodes(1L, 1L);
        assertEquals(1, result.size());
    }

    @Test
    public void testValidateGraph() {
        when(flowchartRepository.findById(1L)).thenReturn(Optional.of(flowchart));
        boolean result = flowchartService.validateGraph(1L);
        assertTrue(result);
    }

    @Test
    public void testHasCycle() {
        Node node1 = new Node();
        node1.setId(1L);
        Node node2 = new Node();
        node2.setId(2L);
        Edge edge1 = new Edge();
        edge1.setSource(node1);
        edge1.setTarget(node2);
        Edge edge2 = new Edge();
        edge2.setSource(node2);
        edge2.setTarget(node1);
        flowchart.getEdges().add(edge1);
        flowchart.getEdges().add(edge2);
        flowchart.getNodes().add(node1);
        flowchart.getNodes().add(node2);
        when(flowchartRepository.findById(1L)).thenReturn(Optional.of(flowchart));
        boolean result = flowchartService.hasCycle(flowchart);
        assertTrue(result);
    }

    @Test
    public void testHasDisjointNodes() {
        Node node1 = new Node();
        node1.setId(1L);
        Node node2 = new Node();
        node2.setId(2L);
        flowchart.getNodes().add(node1);
        flowchart.getNodes().add(node2);
        when(flowchartRepository.findById(1L)).thenReturn(Optional.of(flowchart));
        boolean result = flowchartService.hasDisjointNodes(flowchart);
        assertTrue(result);
    }
    @Test
    public void testHasConsistentEdges() {
        Node node1 = new Node();
        node1.setId(1L);
        Node node2 = new Node();
        node2.setId(2L);
        Edge edge = new Edge();
        edge.setSource(node1);
        edge.setTarget(node2);
        flowchart.getEdges().add(edge);
        flowchart.getNodes().add(node1);
        flowchart.getNodes().add(node2);
        boolean result = flowchartService.hasConsistentEdges(flowchart);
        assertTrue(result);
    }

    @Test
    public void testHasConsistentEdges_False() {
        Node node1 = new Node();
        node1.setId(1L);
        Node node2 = new Node();
        node2.setId(2L);
        Edge edge = new Edge();
        edge.setSource(node1);
        edge.setTarget(node2);
        flowchart.getEdges().add(edge);
        flowchart.getNodes().add(node1);
        boolean result = flowchartService.hasConsistentEdges(flowchart);
        assertFalse(result);
    }
}