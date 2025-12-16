package com.knapsack.algorithm;

import com.knapsack.model.Item;
import java.util.List;

/**
 * 动态规划求解0/1背包（精确最优解）
 */
public class DynamicProgramming {
    /**
     * 计算最大价值
     * @param items 物品列表
     * @param capacity 背包容量
     * @return 最大价值
     */
    public static int solve(List<Item> items, int capacity) {
        int n = items.size();
        // dp[i][j]：前i个物品，容量j时的最大价值
        int[][] dp = new int[n + 1][capacity + 1];

        // 填充dp表
        for (int i = 1; i <= n; i++) {
            Item item = items.get(i - 1);
            int weight = item.getWeight();
            int value = item.getValue();
            for (int j = 1; j <= capacity; j++) {
                if (weight > j) {
                    // 物品重量超过容量，不选
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 选/不选取最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight] + value);
                }
            }
        }
        return dp[n][capacity];
    }
}