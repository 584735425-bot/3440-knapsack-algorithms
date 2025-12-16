package com.knapsack.model;

/**
 * 背包物品类：包含重量、价值属性
 */
public class Item {
    private int weight;  // 重量
    private int value;   // 价值

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    // Getter & Setter
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // 计算性价比（价值/重量）
    public double getRatio() {
        return (double) value / weight;
    }
}