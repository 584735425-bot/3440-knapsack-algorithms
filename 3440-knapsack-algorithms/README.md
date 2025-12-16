Comparison of Three Algorithms for 0/1 Knapsack Problem (Java Implementation)
This project implements three solution algorithms for the 0/1 knapsack problem based on an academic Proposal: Dynamic Programming (exact solution), Greedy Algorithm (fast approximate solution), and Genetic Algorithm (parameter-tunable approximate solution). It is fully adapted to the experimental design specified in the Proposal (item scales of 10/20/30, population sizes of 20/50/100, and mutation rates of 0.01/0.05/0.1).
Project Structure
plaintext
src/main/java/com/knapsack/
├── algorithm/ # Core algorithm implementations
│   ├── DynamicProgramming.java # Dynamic Programming (exact optimal solution)
│   ├── GreedyAlgorithm.java    # Greedy Algorithm (sorted by cost-performance ratio)
│   └── GeneticAlgorithm.java   # Genetic Algorithm (tunable population size + mutation rate)
├── model/ # Item model definition
├── util/  # Utility for random generation of experimental items
└── App.java # Entry point for running experiments
Running Method
1. Environment Requirements
JDK 8 or higher

Maven 3.6 or higher
