package com.knapsack;

import com.knapsack.algorithm.DynamicProgramming;
import com.knapsack.algorithm.GreedyAlgorithm;
import com.knapsack.algorithm.GeneticAlgorithm;
import com.knapsack.model.Item;
import com.knapsack.util.RandomItemGenerator;
import java.util.List;


  主程序：执行01背包三种算法的对比实验（适配Proposal的102030物品规模）
 
public class App {
     GA参数（适配Proposal）
    private static final int[] POPULATION_SIZES = {20, 50, 100};
    private static final double[] MUTATION_RATES = {0.01, 0.05, 0.1};

    public static void main(String[] args) {
         测试不同规模：10（小）、20（中）、30（大）
        testKnapsack(10);
        testKnapsack(20);
        testKnapsack(30);
    }

    
      测试指定物品数量的背包问题
      @param itemCount 物品数量
     
    private static void testKnapsack(int itemCount) {
        System.out.println(========== 测试规模： + itemCount + 个物品 ==========);
         1. 生成随机物品和背包容量
        ListItem items = RandomItemGenerator.generateItems(itemCount);
        int capacity = RandomItemGenerator.calculateKnapsackCapacity(items);
        System.out.println(背包容量： + capacity);

         2. 动态规划（最优解）
        int dpResult = DynamicProgramming.solve(items, capacity);
        System.out.println(动态规划（最优解）： + dpResult);

         3. 贪心算法
        int greedyResult = GreedyAlgorithm.solve(items, capacity);
        System.out.println(贪心算法结果： + greedyResult + （误差： + String.format(%.2f, (double)(dpResult - greedyResult)dpResult100) + %）);

         4. 遗传算法（遍历所有参数组合）
        System.out.println(--- 遗传算法不同参数结果 ---);
        for (int popSize  POPULATION_SIZES) {
            for (double mutationRate  MUTATION_RATES) {
                int gaResult = GeneticAlgorithm.solve(items, capacity, popSize, mutationRate);
                double error = dpResult == 0  0  (double)(dpResult - gaResult)dpResult100;
                System.out.println(种群大小： + popSize + ，变异率： + mutationRate +  → 结果： + gaResult + （误差： + String.format(%.2f, error) + %）);
            }
        }
        System.out.println(=====================================n);
    }
}