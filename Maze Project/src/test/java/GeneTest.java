import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;

/**
 * Created by xingyaowu on 14/04/2018.
 */
public class GeneTest {
  Gene gene1;
  Gene gene2;
  Gene gene3;
  Gene gene4;

  @Before
  public void configure() {
    int[] sequence  = new int[70];
    for (int i = 0; i < 70; i++) {
      sequence[i] = 1;
    }
    this.gene1 = new Gene(sequence);

    int[] sequence2  = new int[70];
    for (int i = 0; i < 70; i++) {
      sequence2[i] = 0;
    }
    this.gene2 = new Gene(sequence2);

    int[] sequence3  = new int[70];
    for (int i = 0; i < 70; i++) {
      if (i % 2 == 0)
        sequence3[i] = 0;
      else
        sequence3[i] = 1;
    }
    this.gene3 = new Gene(sequence3);

    int[] sequence4  = new int[70];
    for (int i = 0; i < 70; i++) {
      if (i % 2 == 0)
        sequence4[i] = 1;
      else
        sequence4[i] = 0;
    }
    this.gene4 = new Gene(sequence4);
  }

  @Test
  public void testGene1() {
    Assert.assertEquals("RightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRightRight", gene1.getSteps());
  }

  @Test
  public void testGene2() {
    Assert.assertEquals("UpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUpUp", gene2.getSteps());
  }

  @Test
  public void testGene3() {
    Assert.assertEquals("DownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDownDown", gene3.getSteps());
  }

  @Test
  public void testGene4() {
    Assert.assertEquals("LeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeftLeft", gene4.getSteps());
  }

}