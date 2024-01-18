package utils;

/**
 * This interface defines methods for an image. It has getters for each data Image can have.
 */
public interface Image {

  /**
   * Getter for the width of image.
   *
   * @return width of the image.
   */
  int getWidth();

  /**
   * Getter for the height of image.
   *
   * @return height of the image.
   */
  int getHeight();

  /**
   * Getter for the format of image.
   *
   * @return format of the image.
   */
  String getFormat();

  /**
   * Getter for the max value of image.
   *
   * @return max value of the image.
   */
  int getMaxValue();

  /**
   * Getter for the pixels of image.
   *
   * @return pixels of the image.
   */
  int[][][] getPixels();

}
