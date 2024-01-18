package utils;

import java.util.Arrays;
import java.util.Objects;

/**
 * This is a class to represent details of an image such as width, height, format, maxValue and
 * pixels.
 */

public class ImageDetails implements Image {
  private final int width;
  private final int height;
  private final String format;
  private final int maxValue;
  private final int[][][] pixels;

  /**
   * This is public constructor of ImageDetails.
   *
   * @param format   Format of the  image.
   * @param width    Width of the image.
   * @param height   Height of image.
   * @param maxValue MaxValue an image can hold.
   * @param pixels   Array of pixels of image.
   */

  public ImageDetails(String format, int width, int height, int maxValue, int[][][] pixels) {
    this.width = width;
    this.height = height;
    this.format = format;
    this.maxValue = maxValue;
    this.pixels = pixels;
  }

  /**
   * The no argument constructor is used for testing our controller using mock model.
   */
  public ImageDetails() {
    this.width = 0;
    this.height = 0;
    this.format = "";
    this.maxValue = 0;
    this.pixels = new int[0][][];
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(getWidth() + System.lineSeparator() + getHeight() + System.lineSeparator());
    builder.append(getMaxValue() + System.lineSeparator());

    for (int i = 0; i < getPixels().length; i++) {
      for (int j = 0; j < getPixels()[0].length; j++) {
        builder.append(getPixels()[i][j][0] + System.lineSeparator());
        builder.append(getPixels()[i][j][1] + System.lineSeparator());
        builder.append(getPixels()[i][j][2] + System.lineSeparator());
      }
    }
    return builder.toString();
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(width, height, format, maxValue);
    result = 31 * result + Arrays.hashCode(pixels);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof ImageDetails)) {
      return false;
    }

    ImageDetails compare = (ImageDetails) obj;
    return this.toString().equals(compare.toString());
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public String getFormat() {
    return format;
  }

  @Override
  public int getMaxValue() {
    return maxValue;
  }

  @Override
  public int[][][] getPixels() {
    return pixels;
  }
}
