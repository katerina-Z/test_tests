package com.example;

import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;

public class OrderProcessorTests {
    private final OrderProcessor processor = new OrderProcessor();

    @Test
    public void testGetOrderIdsByStatus() {
        List<Order> orders = List.of(
                new Order(1, "PENDING"),
                new Order(2, "COMPLETED"),
                new Order(3, "COMPLETED")
        );
        assertEquals(processor.getOrderIdsByStatus(orders, "COMPLETED"), List.of(2, 3));
    }
}