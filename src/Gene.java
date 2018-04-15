import java.util.Random;

/**
 * @author zhang
 */
public class Gene {
  private int length; //chromosome length
  private int[] geneSequence; // base pair of gene
  private double fitnessScore; // Adaptive fitnessScore

  public Gene(int length) {
    this.length = length;
    geneSequence = new int[length];
    Random random = new Random();// Random a gene sequences
    for (int i = 0; i < length; i++) {
      geneSequence[i] = random.nextInt(2);
    }
    this.fitnessScore = 0.0;
  }

  public Gene(int[] sequence) {
    setGeneSequence(sequence);
    this.length = sequence.length;
    this.fitnessScore = 0.0;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public int[] getGeneSequence() {
    return geneSequence;
  }

  public void setGeneSequence(int[] geneSequence) {
    this.geneSequence = geneSequence;
  }

  public double getFitnessScore() {
    return fitnessScore;
  }

  public void setFitnessScore(double fitnessScore) {
    this.fitnessScore = fitnessScore;
  }

  public String getSteps() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < geneSequence.length; i += 2) {
      if (geneSequence[i] == 0 && geneSequence[i + 1] == 0) {
        sb.append("Up");
      } else if (geneSequence[i] == 0 && geneSequence[i + 1] == 1) {
        sb.append("Down");
      } else if (geneSequence[i] == 1 && geneSequence[i + 1] == 0) {
        sb.append("Left");
      } else {
        sb.append("Right");
      }
    }
    return sb.toString();
  }


}
