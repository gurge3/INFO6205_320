import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by xingyaowu on 14/04/2018.
 */
public class GeneticAlgorithmTest {

  GeneticAlgorithm ga;

  @Before
  public void configure() {
    ga = new GeneticAlgorithm(140, 70, 0.7, 0.0001, MazePrinter.map, MazePrinter.END_X, MazePrinter.END_Y);
  }

  @Test
  public void testMutation() throws Exception {
    int[] s1 = new int[70];
    int[] s2 = new int[70];
    for (int i = 0; i < 70; i++) {
      if (i % 2 == 0) {
        s1[i] = 0;
        s2[i] = 0;
      } else {
        s1[i] = 1;
        s2[i] = 0;
      }
    }
    Gene g1 = new Gene(s1);
    Gene g2 = new Gene(s2);

    int[] gene1 = new int[70];
    int[] gene2 = new int[70];
    for (int i = 0; i < 70; i++) {
      gene1[i] = g1.getGeneSequence()[i];
      gene2[i] = g2.getGeneSequence()[i];
    }
    ga.mutation(gene1, gene2, 68);
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    for (int i = 0; i < gene1.length; i++) {
      sb1.append(gene1[i]);
    }
    for (int i = 0; i < gene2.length; i++) {
      sb2.append(gene2[i]);
    }
    Assert.assertEquals("0101010101010101010101010101010101010101010101010101010101010101010100", sb1.toString());
    Assert.assertEquals("0000000000000000000000000000000000000000000000000000000000000000000001", sb2.toString());
  }

  @Test
  public void testCrossOver() throws Exception {
    int[] s1 = new int[70];
    int[] s2 = new int[70];
    for (int i = 0; i < 70; i++) {
      if (i % 2 == 0) {
        s1[i] = 0;
        s2[i] = 0;
      } else {
        s1[i] = 1;
        s2[i] = 0;
      }
    }
    Gene g1 = new Gene(s1);
    Gene g2 = new Gene(s2);

    int[] gene1 = new int[70];
    int[] gene2 = new int[70];
    for (int i = 0; i < 70; i++) {
      gene1[i] = g1.getGeneSequence()[i];
      gene2[i] = g2.getGeneSequence()[i];
    }
    ga.crossover(gene1, gene2, 68);
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    for (int i = 0; i < gene1.length; i++) {
      sb1.append(gene1[i]);
    }
    for (int i = 0; i < gene2.length; i++) {
      sb2.append(gene2[i]);
    }
    Assert.assertEquals("0101010101010101010101010101010101010101010101010101010101010101010111", sb1.toString());
    Assert.assertEquals("0000000000000000000000000000000000000000000000000000000000000000000010", sb2.toString());
  }

  @Test
  public void testGetFitnessScore() throws Exception {
    int[] s1 = new int[70];
    int[] s2 = new int[70];
    for (int i = 0; i < 70; i++) {
      if (i % 2 == 0) {
        s1[i] = 0;
        s2[i] = 0;
      } else {
        s1[i] = 1;
        s2[i] = 0;
      }
    }
    Gene g1 = new Gene(s1);
    Gene g2 = new Gene(s2);
    // Creating dummy path which shouldn't give any score
    Assert.assertEquals(0.0, g1.getFitnessScore(), 0.1);
    Assert.assertEquals(0.0, g2.getFitnessScore(), 0.1);
  }

  /**
   * For this test, we are using random evoluation to convince that after certain times of
   * evolution, the fitness will approach to a desired value.
   */
  @Test
  public void testEvoluation() {
    GeneticAlgorithm ga = new GeneticAlgorithm(140, 70, 0.7, 0.001, MazePrinter.map, MazePrinter.END_X, MazePrinter.END_Y);
    int i = 0;
    while (i < 30000) {
      int p1 = ga.getParent();
      int p2 = ga.getParent();
      ga.crossoverAndMutation(p1, p2);
      i++;
    }
    System.out.println(ga.getMaxGene().getFitnessScore());
    // We are testing that after certain amount of iterations, we can get a good fitness score
    Assert.assertTrue(ga.getMaxGene().getFitnessScore() > 0.5);
  }


}