
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * Created by xingyaowu on 13/04/2018.
 * This class visualizes the maze solution by using a JFrame
 */
public class MazePrinter extends JFrame {
  // Initializing a list of Genes
  // Constant Starting Position
  public static final int START_X = 2;
  public static final int START_Y = 0;
  // Constant Ending Position
  public static final int END_X = 7;
  public static final int END_Y = 14;
  // Map for the maze, and this should be reconfigurable in case of different scenarios
  // 5 represents entrance and 8 represents exit.
  public static int[][] map = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
          {1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1},
          {5, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1},
          {1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1},
          {1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
          {1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1},
          {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1},
          {1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 8},
          {1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
          {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
  public static int MAP_HEIGHT = 10;
  public static int MAP_WIDTH = 15;
  // Best gene
  public Gene maxGene = null;
  // Creating a JLabel to indicate on the GUI
  public List<JLabel> labels = new ArrayList<>();

  // Initializing the Maze Map
  public MazePrinter() {
    setSize(700, 500);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setResizable(false);
    getContentPane().setLayout(null);

    JPanel mapPanel = new JPanel();
    mapPanel.setLayout(new GridLayout(MAP_HEIGHT, MAP_WIDTH));
    mapPanel.setBounds(10, 10, MAP_WIDTH * 40, MAP_HEIGHT * 40);
    getContentPane().add(mapPanel);
    for (int i = 0; i < MAP_HEIGHT; i++) {
      for (int j = 0; j < MAP_WIDTH; j++) {
        JLabel label = new JLabel();
        Color color = null;
        if (map[i][j] == 1) {
          color = Color.black;
        }
        if (map[i][j] == 0) {
          color = Color.GRAY;
        }
        if (map[i][j] == 5 || map[i][j] == 8) {
          color = Color.red;
          if (map[i][j] == 5) {
            label.setText("START");
            label.setFont(new Font("Serif", Font.PLAIN, 10));
          } else {
            label.setText("FINISH");
            label.setFont(new Font("Serif", Font.PLAIN, 10));
          }
        }
        label.setBackground(color);
        label.setOpaque(true);
        mapPanel.add(label);
        labels.add(label);
        this.setVisible(true);
      }
    }
  }

  public static int[][] getMap() {
    return map;
  }

  public static void main(String[] main) {
    GeneticAlgorithm ga = new GeneticAlgorithm(140, 70, 0.7, 0.001, map, MazePrinter.END_X, MazePrinter.END_Y);
    int i = 0;
    Logger logger = Logger.getLogger("main");
    while (ga.getMaxGene().getFitnessScore() < 1) {
      int p1 = ga.getParent();
      int p2 = ga.getParent();
      ga.crossoverAndMutation(p1, p2);
      logger.info("No: " + i + " fitness score: " + ga.getMaxGene().getFitnessScore());
      i++;
      if (i > 20000)
        break;
    }

    MazePrinter printer = new MazePrinter();
    printer.setMaxGene(ga.getMaxGene());
    printer.repaint();

    logger.info("Final gene score: " + ga.getMaxGene().getFitnessScore());
  }

  // Setting the best gene by calculation
  public void setMaxGene(Gene maxGene) {
    this.maxGene = maxGene;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    // Checking if the best solution is presented.
    if (maxGene == null) {
      return;
    }
    // Getting the path from the best gene
    int[] geneSequence = maxGene.getGeneSequence();
    // Initializing the cursor to the starting point
    int cursorX = START_X;
    int cursorY = START_Y;

    for (int i = 0; i < geneSequence.length; i += 2) {
      // Moving Up
      if (geneSequence[i] == 0 && geneSequence[i + 1] == 0) {
        if (cursorX >= 1 && map[cursorX - 1][cursorY] == 0) {
          cursorX--;
        }
      }
      // Moving Down
      else if (geneSequence[i] == 0 && geneSequence[i + 1] == 1) {
        if (cursorX <= MAP_HEIGHT - 1 && map[cursorX + 1][cursorY] == 0) {
          cursorX++;
        }
      }
      // Moving Left
      else if (geneSequence[i] == 1 && geneSequence[i + 1] == 0) {
        if (cursorY >= 1 && map[cursorX][cursorY - 1] == 0) {
          cursorY--;
        }
      }
      // Moving Right
      else {
        if (cursorY <= MAP_WIDTH - 1 && map[cursorX][cursorY + 1] == 0) {
          cursorY++;
        }
      }
      labels.get(cursorX * MAP_WIDTH + cursorY).setBackground(Color.BLUE);
    }
  }

}
