package com.example;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class OrderProcessorTests {
    private OrderProcessor processor;

    @BeforeMethod
    public void setUp() {
        processor = new OrderProcessor();
    }

    @DataProvider(name = "idsByStatusData")
    public Object[][] idsByStatusData() {
        return new Object[][]{
                // normal case
                { List.of(
                        new Order(1, "PENDING"),
                        new Order(2, "COMPLETED"),
                        new Order(3, "COMPLETED")
                ), "COMPLETED", List.of(2, 3) },

                // unsorted input -> output should be sorted
                { List.of(
                        new Order(5, "X"),
                        new Order(1, "X"),
                        new Order(4, "Y"),
                        new Order(2, "X")
                ), "X", List.of(1, 2, 5) },

                // null elements inside list are ignored
                { List.of(
                        null,
                        new Order(2, "A"),
                        new Order(1, "A")
                ), "A", List.of(1, 2) },

                // status not present -> empty result
                { List.of(
                        new Order(1, "S1"),
                        new Order(2, "S2")
                ), "MISSING", List.of() },

                // empty list -> empty result
                { List.of(), "ANY", List.of() }
        };
    }

    @Test(dataProvider = "idsByStatusData")
    public void testGetOrderIdsByStatus_DataDriven(List<Order> orders, String status, List<Integer> expected) {
        assertEquals(processor.getOrderIdsByStatus(orders, status), expected, "Mismatch for status: " + status);
    }

    @Test
    public void testGetOrderIdsByStatus_NullInputs() {
        assertTrue(processor.getOrderIdsByStatus(null, "ANY").isEmpty(), "null orders should return empty");
        assertTrue(processor.getOrderIdsByStatus(List.of(new Order(1, "A")), null).isEmpty(), "null status should return empty");
    }

    @Test
    public void testGroupOrdersByStatus_Basic() {
        List<Order> orders = List.of(
                new Order(1, "P"),
                new Order(2, "C"),
                new Order(3, "P")
        );
        Map<String, List<Order>> grouped = processor.groupOrdersByStatus(orders);

        assertEquals(grouped.get("P").size(), 2, "P group size");
        assertEquals(grouped.get("C").size(), 1, "C group size");
        assertFalse(grouped.containsKey(null), "null statuses should not be keys");
    }

    @Test
    public void testCountOrdersByStatus_Basic() {
        List<Order> orders = List.of(
                new Order(1, "A"),
                new Order(2, "B"),
                new Order(3, "A"),
                new Order(4, "B"),
                new Order(5, "A")
        );
        Map<String, Long> counts = processor.countOrdersByStatus(orders);

        assertEquals(counts.get("A").longValue(), 3L, "A count");
        assertEquals(counts.get("B").longValue(), 2L, "B count");
    }

    @Test
    public void testGenerateOrderMessage() {
        Order o = new Order(10, "READY");
        assertEquals(processor.generateOrderMessage(o), "Заказ с ID 10 находится в статусе READY");
        assertEquals(processor.generateOrderMessage(null), "Order is null");
    }
}