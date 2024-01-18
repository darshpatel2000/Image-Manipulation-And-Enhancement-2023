package utils;


import java.awt.*;

import javax.swing.*;

/**
 * This class is utility class for creating a histogram.
 */
public class Histogram extends JPanel {
  private final long[] redData;
  private final long[] greenData;
  private final long[] blueData;
  private final long[] intensityData;
  private final long maxCount;

  /**
   * Public constructor of the Histogram class.
   * @param histogramData Array of data given by view to generate the histogram.
   */
  public Histogram(long[][] histogramData) {
    this.redData = histogramData[0];
    this.greenData = histogramData[1];
    this.blueData = histogramData[2];
    this.intensityData = histogramData[3];
    maxCount = getMaxCount(histogramData);
    setPreferredSize(new Dimension(570, 400));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    long width = getWidth();
    long height = getHeight();
    int padding = 60;

    Graphics2D g2d = (Graphics2D) g.create();

    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2d.drawLine(padding, (int) (height - padding), (int) (width - padding), (int) (height - padding));

    g2d.drawLine(padding, padding, padding, (int) (height - padding));

    for (int i = 15; i <= 255; i += 15) {
      long x = padding + i * (width - 2 * padding) / 255;
      g2d.drawString(Integer.toString(i), x, height - padding / 2);
    }
    g2d.drawString("255", width - padding, height - padding / 2);

    for (long i = 0; i <= maxCount; i += maxCount / 10) {
      long y = height - padding - i * (height - 2 * padding) / maxCount;
      g2d.drawString(Long.toString(i), padding / 2, y);
    }

    g2d.setColor(Color.RED);
    g2d.setStroke(new BasicStroke(2f));
    long xOld = padding;
    long yOld = height - padding;
    drawAxesLine(width, height, padding, g2d, xOld, yOld, redData);

    g2d.setColor(Color.GREEN);
    g2d.setStroke(new BasicStroke(2f));
    yOld = height - padding;
    drawAxesLine(width, height, padding, g2d, xOld, yOld, greenData);

    g2d.setColor(Color.BLUE);
    g2d.setStroke(new BasicStroke(2f));
    yOld = height - padding;
    drawAxesLine(width, height, padding, g2d, xOld, yOld, blueData);

    g2d.setColor(Color.BLACK);
    g2d.setStroke(new BasicStroke(2f));
    yOld = height - padding;
    drawAxesLine(width, height, padding, g2d, xOld, yOld, intensityData);

    g2d.dispose();
  }

  private long getMaxCount(long[][] data) {
    long max = 0;
    for (long[] outer : data) {
      for (long inner : outer) {
        if (inner > max) {
          max = inner;
        }
      }
    }
    return max;
  }

  private void drawAxesLine(long width, long height, long padding, Graphics2D g2d, long xOld, long yOld,
                        long[] rData) {
    for (int i = 0; i < rData.length; i++) {
      long x = padding + i * (width - 2 * padding) / 255;
      long y = height - padding - rData[i] * (height - 2 * padding) / maxCount;
      g2d.drawLine((int) xOld, (int) yOld, (int) x, (int) y);
      xOld = x;
      yOld = y;
    }
  }

}
