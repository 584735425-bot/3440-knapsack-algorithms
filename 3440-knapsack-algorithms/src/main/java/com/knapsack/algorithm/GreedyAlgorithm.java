package com.knapsack.algorithm;

import com.knapsack.model.Item;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 贪心算法求解0/1背包（按性价比排序，快速近似解）
 */
public class GreedyAlgorithm {
    /**
     * 计算最大价值
     * @param items 物品列表
     * @param capacity 背包容量
     * @return 最大价值
     */
    public static int solve(List<Item> items, int capacity) {
        // 按性价比从高到低排序
        List<Item> sortedItems = items.stream()
                .sorted(Comparator.comparing(Item::getRatio).reversed())
                .collect(Collectors.toList());

        int totalValue = 0;
        int remainingCapacity = capacity;

        // 依次装入物品，直到容量不足
        for (Item item : sortedItems) {
            if (item.getWeight() <= remainingCapacity) {
                totalValue += item.getValue();
                remainingCapacity -= item.getWeight();
            }
            if (remainingCapacity == 0) {
                break;
            }
        }
        return totalValue;
    }
}