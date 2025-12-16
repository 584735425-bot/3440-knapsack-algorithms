package com.knapsack.util;

import com.knapsack.model.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 随机生成背包物品（适配Proposal的实验参数：价值10-100，重量1-50）
 */
public class RandomItemGenerator {
    private static final Random RANDOM = new Random();

    /**
     * 生成指定数量的随机物品
     * @param count 物品数量
     * @return 物品列表
     */
    public static List<Item> generateItems(int count) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int weight = RANDOM.nextInt(50) + 1;  // 1-50
            int value = RANDOM.nextInt(91) + 10;   // 10-100
            items.add(new Item(weight, value));
        }
        return items;
    }

    /**
     * 计算背包容量（总重量的50%~100%之间，适配Proposal）
     * @param items 物品列表
     * @return 背包容量
     */
    public static int calculateKnapsackCapacity(List<Item> items) {
        int totalWeight = items.stream().mapToInt(Item::getWeight).sum();
        return RANDOM.nextInt(totalWeight / 2) + totalWeight / 2;  // 总重量50% ~ 100%
    }
}