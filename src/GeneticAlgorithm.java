package com.mycompany.finalproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import sun.security.x509.X500Name;

/**
 * @author zhang
 */
public class GeneticAlgorithm {

    private int chromosomeSize;
    private int geneLength;
    private double crossoverProbability;
    private double mutationProbability;
    private int[][] map;
    private List<Gene> geneGroup;
    private Gene maxGene;
    private Random random;
    private int endingX;
    private int endingY;
    private TreeMap<Double,Gene>maxGeneGroup;
    public GeneticAlgorithm(int chromosomeSize, int geneLength, double crossoverProbability, double mutationProbability, int[][] map, int endingX, int endingY) {
        this.chromosomeSize = chromosomeSize;
        this.geneLength = geneLength;
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
        this.map = map;
        this.random = new Random();
        this.maxGene = new Gene(chromosomeSize);
        this.endingX = endingX;
        this.endingY = endingY;
        this.geneGroup = new ArrayList<>();
        this.maxGeneGroup = new TreeMap<>();
        for (int i = 0; i < chromosomeSize; i++) {
            Gene gene = new Gene(geneLength);
            double score = getFitnessScore(gene.getGeneSequence());
            if (score > maxGene.getScore()) {
                maxGene = gene;
            }
            gene.setScore(score);
            geneGroup.add(gene);
        }
    }

    public Gene getMaxGene() {
        return maxGene;
    }

    public void crossoverAndMutation(int number1, int number2) {
        Gene p1 = geneGroup.get(number1);
        Gene p2 = geneGroup.get(number2);
        Gene c1 = new Gene(geneLength);
        Gene c2 = new Gene(geneLength);
        int[] gene1 = new int[geneLength];
        int[] gene2 = new int[geneLength];
        for (int i = 0; i < geneLength; i++) {
            gene1[i] = p1.getGeneSequence()[i];
            gene2[i] = p2.getGeneSequence()[i];
        }
        double r = new Random().nextDouble();
        if (r >= crossoverProbability) {
            int n = random.nextInt(geneLength);
            mutation(gene1, gene2, n);

        }
        r = random.nextDouble();
        if (r >= mutationProbability) {
            int n = random.nextInt(geneLength);
            crossover(gene1, gene2, n);

        }
        c1.setGeneSequence(gene1);
        c2.setGeneSequence(gene2);

        double score1 = getFitnessScore(c1.getGeneSequence());
        //System.out.println("Score1");
        double score2 = getFitnessScore(c2.getGeneSequence());
        if (score1 > maxGene.getScore()) {
            maxGene = c1;
            c1.setScore(score1);
            maxGeneGroup.put(c1.getScore(), c1);
        }
        if (score2 > maxGene.getScore()) {
            maxGene = c2;
            c2.setScore(score2);
            maxGeneGroup.put(c2.getScore(), c2);
        }
        c1.setScore(score1);
        c2.setScore(score2);

        geneGroup.add(c1);
        geneGroup.add(c2);
    }

    private void mutation(int[] gene1, int[] gene2, int n) {
        System.out.println("entering mutation");
        for (int i = n; i < geneLength; i++) {
            int tmp = gene1[i];
            gene1[i] = gene2[i];
            gene2[i] = tmp;
        }
    }

    private void crossover(int[] gene1, int[] gene2, int n) {
        System.out.println("Entering crossover");
        if (gene1[n] == 0) {
            gene1[n] = 1;
        } else {
            gene1[n] = 0;
        }

        if (gene2[n] == 0) {
            gene2[n] = 1;
        } else {
            gene2[n] = 0;
        }
    }

    public double getFitnessScore(int[] genome) {
        double score = 0.000;
        int X = 2, Y = 0;
        int count = 0;
        int refind=0;
        int[][] curMap = ArrayCopy(map);
        for (int i = 0; i < genome.length; i += 2) {
            //up
            if (genome[i] == 0 && genome[i + 1] == 0) {
                if (X >= 1 && (curMap[X - 1][Y] == 0||curMap[X - 1][Y] == -1)) {
                    if(curMap[X - 1][Y] == -1)
                        refind++;
                    else
                    curMap[X - 1][Y] = -1;
                    X--;
                    count++;
                    
                    double a = getDistance(X, Y, count,refind);
                    if (a != -1) {
                        return a;
                    }
                }
            } //down
            else if (genome[i] == 0 && genome[i + 1] == 1) {
                if (X <= curMap.length - 1 && (curMap[X + 1][Y] == 0||curMap[X + 1][Y] == -1)) {
                    if(curMap[X + 1][Y] == -1)
                        refind++;
                    else
                    curMap[X + 1][Y] =-1;
                    X++;
                    count++;
                    
                    double a = getDistance(X, Y, count,refind);
                    if (a != -1) {
                        return a;
                    }
                }
            } //left
            else if (genome[i] == 1 && genome[i + 1] == 0) {
                if (Y >= 1 && (curMap[X][Y - 1] == 0||curMap[X][Y - 1] == -1)) {
                    if(curMap[X][Y - 1] == -1)
                        refind++;
                    else
                    curMap[X][Y - 1] =-1;
                    Y--;
                    count++;
                    
                    double a = getDistance(X, Y, count,refind);
                    if (a != -1) {
                        return a;
                    }
                }
            } //right
            else {
                if (Y <= curMap[0].length - 1 && (curMap[X][Y + 1] == 0||curMap[X][Y + 1] == -1)) {
                    if(curMap[X][Y + 1] == -1)
                        refind++;
                    else
                    curMap[X][Y + 1] =-1;
                    Y++;
                    count++;
                    
                    double a = getDistance(X, Y, count,refind);
                    if (a != -1) {
                        return a;
                    }
                }
            }
        }
        double x = Math.abs(X - endingX);
        double y = Math.abs(Y - endingY);
        //System.out.println("X " + x + " Y:" + y);

        score = 1 / (double)(x + y + 1+refind);
        return score;
    }
    public int [][] ArrayCopy(int [][]a){
        int b[][]= new int [a.length][];
        for(int i=0;i<a.length;i++)
            b[i]=a[i].clone();
        return b;
    }
    public double getDistance(int X, int Y, int count,int refind) {
        double x = Math.abs(X - endingX);
        double y = Math.abs(Y - endingY);
        if ((x == 1 && y == 0) || (x == 0 && y == 1)) {
            return  (0.6 + 1/(double)(count+refind));
        }
        return -1;
    }

    public double getTotalFitnessScore(List<Gene> genes) {
        double score = 0.0;
        for (int i = 0; i < genes.size(); i++) {
            score += genes.get(i).getScore();
        }
        return score;
    }

    public int getParent() {
        Random random = new Random();
        int position = 0;
        double score, sum = 0, num = random.nextDouble(), total = getTotalFitnessScore(geneGroup);
        for (int i = 0; i < geneGroup.size(); i++) {
            Gene gene = geneGroup.get(i);
            score = gene.getScore();
            sum += score / total;
            if (sum > num) {
                return i;
            }
        }
        return position;
    }

    public TreeMap<Double, Gene> getMaxGeneGroup() {
        return maxGeneGroup;
    }
    
}
