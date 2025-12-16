package com.knapsack;

import com.knapsack.algorithm.DynamicProgramming;
import com.knapsack.algorithm.GreedyAlgorithm;
import com.knapsack.model.Item;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;

/**
 * 算法单元测试（固定用例验证正确性）
 */
public class AlgorithmTest {
    // 固定测试用例：物品(2,3),(3,4),(4,5),(5,6)，容量8 → 最优解10（选(3,4)+(5,6)）
    @Test
    public void testFixedCase() {
        List<Item> items = Arrays.asList(
                new Item(2, 3),
                new Item(3, 4),
                new Item(4, 5),
                new Item(5, 6)
        );
        int capacity = 8;

        // 动态规划验证
        assertEquals(10, DynamicProgramming.solve(items, capacity));
        // 贪心算法（按性价比：(2,3)=1.5, (3,4)=1.33, (4,5)=1.25, (5,6)=1.2 → 选(2,3)+(3,4)+(4,5)超重，实际选(2,3)+(3,4)=7，价值7）
        assertEquals(7, GreedyAlgorithm.solve(items, capacity));
    }
}