package com.flowchart.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.flowchart.model.Flowchart;
import com.flowchart.service.FlowchartService;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FlowchartControllerTest {

    @Mock
    private FlowchartService flowchartService;

    @InjectMocks
    private FlowchartController flowchartController;

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
        when(flowchartService.getAllFlowcharts()).thenReturn(flowcharts);
        ResponseEntity<List<Flowchart>> response = (ResponseEntity<List<Flowchart>>) flowchartController.getAllFlowcharts();
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testGetFlowchartById() {
        when(flowchartService.getFlowchartById(1L)).thenReturn(java.util.Optional.of(flowchart));
        ResponseEntity<Flowchart> response = flowchartController.getFlowchartById(1L);
        assertEquals(flowchart.getId(), response.getBody().getId());
    }

    @Test
    public void testCreateFlowchart() {
        when(flowchartService.createFlowchart(any(Flowchart.class))).thenReturn(flowchart);
        ResponseEntity<Flowchart> response = flowchartController.createFlowchart(flowchart);
        assertEquals(flowchart.getId(), response.getBody().getId());
    }

    @Test
    public void testDeleteFlowchart() {
        when(flowchartService.getFlowchartById(1L)).thenReturn(java.util.Optional.of(flowchart));
        ResponseEntity<Void> response = flowchartController.deleteFlowchart(1L);
        assertEquals(204, response.getStatusCodeValue());
    }
}