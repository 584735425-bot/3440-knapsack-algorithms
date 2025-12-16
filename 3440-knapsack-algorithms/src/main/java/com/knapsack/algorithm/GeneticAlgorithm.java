package com.knapsack.algorithm;

import com.knapsack.model.Item;
import java.util.*;

/**
 * 遗传算法求解0/1背包（适配Proposal的参数：种群大小、变异率）
 */
public class GeneticAlgorithm {
    private static final Random RANDOM = new Random();
    private static final int MAX_GENERATIONS = 100;  // 最大迭代次数（适配Proposal）

    /**
     * 求解0/1背包
     * @param items 物品列表
     * @param capacity 背包容量
     * @param populationSize 种群大小（20/50/100）
     * @param mutationRate 变异率（0.01/0.05/0.1）
     * @return 最大价值
     */
    public static int solve(List<Item> items, int capacity, int populationSize, double mutationRate) {
        int n = items.size();
        // 1. 初始化种群（二进制数组：1=选，0=不选）
        List<boolean[]> population = initPopulation(populationSize, n);

        // 2. 迭代进化
        for (int gen = 0; gen < MAX_GENERATIONS; gen++) {
            // 2.1 计算适应度（价值，超重则为0）
            Map<boolean[], Integer> fitnessMap = calculateFitness(population, items, capacity);
            // 2.2 选择（轮盘赌）
            List<boolean[]> newPopulation = select(population, fitnessMap);
            // 2.3 交叉（单点交叉）
            crossover(newPopulation);
            // 2.4 变异
            mutate(newPopulation, mutationRate);
            // 2.5 更新种群
            population = newPopulation;
        }

        // 3. 返回最优解价值
        return calculateFitness(population, items, capacity).values().stream()
                .max(Integer::compare)
                .orElse(0);
    }

    // 初始化种群
    private static List<boolean[]> initPopulation(int size, int n) {
        List<boolean[]> population = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            boolean[] chromosome = new boolean[n];
            for (int j = 0; j < n; j++) {
                chromosome[j] = RANDOM.nextBoolean();
            }
            population.add(chromosome);
        }
        return population;
    }

    // 计算适应度（价值，超重则为0）
    private static Map<boolean[], Integer> calculateFitness(List<boolean[]> population, List<Item> items, int capacity) {
        Map<boolean[], Integer> fitnessMap = new HashMap<>();
        for (boolean[] chromosome : population) {
            int totalWeight = 0;
            int totalValue = 0;
            for (int i = 0; i < chromosome.length; i++) {
                if (chromosome[i]) {
                    totalWeight += items.get(i).getWeight();
                    totalValue += items.get(i).getValue();
                }
            }
            // 超重则适应度为0
            fitnessMap.put(chromosome, totalWeight > capacity ? 0 : totalValue);
        }
        return fitnessMap;
    }

    // 轮盘赌选择
    private static List<boolean[]> select(List<boolean[]> population, Map<boolean[], Integer> fitnessMap) {
        List<boolean[]> selected = new ArrayList<>();
        int totalFitness = fitnessMap.values().stream().mapToInt(Integer::intValue).sum();
        // 总适应度为0时，随机选
        if (totalFitness == 0) {
            return initPopulation(population.size(), population.get(0).length);
        }

        for (int i = 0; i < population.size(); i++) {
            int random = RANDOM.nextInt(totalFitness);
            int sum = 0;
            for (boolean[] chromosome : population) {
                sum += fitnessMap.get(chromosome);
                if (sum >= random) {
                    selected.add(chromosome.clone());
                    break;
                }
            }
        }
        return selected;
    }

    // 单点交叉
    private static void crossover(List<boolean[]> population) {
        for (int i = 0; i < population.size() - 1; i += 2) {
            boolean[] parent1 = population.get(i);
            boolean[] parent2 = population.get(i + 1);
            int crossPoint = RANDOM.nextInt(parent1.length);
            // 交换交叉点后基因
            for (int j = crossPoint; j < parent1.length; j++) {
                boolean temp = parent1[j];
                parent1[j] = parent2[j];
                parent2[j] = temp;
            }
        }
    }

    // 变异
    private static void mutate(List<boolean[]> population, double mutationRate) {
        for (boolean[] chromosome : population) {
            for (int i = 0; i < chromosome.length; i++) {
                if (RANDOM.nextDouble() < mutationRate) {
                    chromosome[i] = !chromosome[i];
                }
            }
        }
    }
}