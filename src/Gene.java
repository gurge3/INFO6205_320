package com.mycompany.finalproject;

import java.util.Random;

/**
 *
 * @author zhang
 */
public class Gene {
    private int lenght; //chromosome length
    private int[] geneSequence; // base pair of gene
    private double score; // Adaptive score
    public Gene(int lenght){
        this.lenght=lenght;
        geneSequence = new int [lenght];
        Random random = new Random();// Random a gene sequences
        for(int i=0;i<lenght;i++){
            geneSequence[i]=random.nextInt(2);
        }
        this.score = 0.0;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public int[] getGeneSequence() {
        return geneSequence;
    }

    public void setGeneSequence(int[] geneSequence) {
        this.geneSequence = geneSequence;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    
    
}
