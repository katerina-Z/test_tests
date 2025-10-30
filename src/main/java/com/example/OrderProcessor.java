package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class OrderProcessor {

    /**
     * Возвращает список ID заказов, соответствующих заданному статусу,
     * отсортированных по ID.
     *
     * @param orders список заказов.
     * @param status целевой статус.
     * @return отсортированный список ID заказов с заданным статусом.
     */
    public List<Integer> getOrderIdsByStatus(List<Order> orders, String status) {
        if (orders == null || status == null) return Collections.emptyList();
        return orders.stream()
                .filter(Objects::nonNull)
                .filter(o -> status.equals(o.getStatus()))
                .map(Order::getId)
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Сгруппировать заказы по их статусу.
     *
     * @param orders список заказов.
     * @return карта, где ключ — статус, а значение — список заказов с этим статусом.
     */
    public Map<String, List<Order>> groupOrdersByStatus(List<Order> orders) {
        if (orders == null) return Collections.emptyMap();
        return orders.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Order::getStatus, LinkedHashMap::new, Collectors.toList()));
    }

    /**
     * Подсчитывает количество заказов для каждого статуса.
     *
     * @param orders список заказов.
     * @return карта, где ключ — статус, а значение — количество заказов с этим статусом.
     */
    public Map<String, Long> countOrdersByStatus(List<Order> orders) {
        if (orders == null) return Collections.emptyMap();
        return orders.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));
    }

    /**
     * Формирует сообщение для клиента о статусе его заказа.
     *
     * @param order заказ.
     * @return сообщение в формате: "Заказ с ID {id} находится в статусе {status}".
     */
    public String generateOrderMessage(Order order) {
        if (order == null) return "Order is null";
        return String.format("Заказ с ID %d находится в статусе %s", order.getId(), order.getStatus());
    }
}