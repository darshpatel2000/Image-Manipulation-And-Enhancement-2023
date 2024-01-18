package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

/**
 * This class write image to given path.
 */
public class ImageWrite implements ImageHandler {
  private final Image image;
  private final String imagePath;

  /**
   * This class is used to save an image of any format. It will segregate the images based on its
   * extension and call the respective helper method accordingly.
   *
   * @param image     the image which is to be written/saved.
   * @param imagePath the path where it is to be written.
   * @throws IOException the exception if the image is not supported by our application.
   */
  public ImageWrite(Image image, String imagePath) throws IOException {
    this.image = image;
    this.imagePath = imagePath;
    int dotIndex = imagePath.lastIndexOf('.');
    String extension = imagePath.substring(dotIndex + 1);
    switch (extension) {
      case "ppm":
        writePPM();
        break;
      case "bmp":
      case "jpg":
      case "png":
        writeImage(extension);
        break;
      default:
        throw new IOException("Given file type is not supported.");
    }
  }

  private void writeImage(String extension) throws IOException {
    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] imagePixels = image.getPixels();
    // Create a BufferedImage from the image data
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = imagePixels[i][j][0];
        int green = imagePixels[i][j][1];
        int blue = imagePixels[i][j][2];
        Color rgb = new Color(
                red,
                green,
                blue
        );
        bufferedImage.setRGB(j, i, rgb.getRGB());
      }
    }
    // Write the BufferedImage to a file
    File outputFile = new File(imagePath);
    ImageIO.write(bufferedImage, extension, outputFile);
  }

  private void writePPM() throws IOException {
    PrintWriter writer;
    try {
      writer = new PrintWriter(imagePath);

      String imageData = "P3" + System.lineSeparator() +
              "# Created by Aditya and Maurya." + System.lineSeparator() + image;
      writer.print(imageData);
      writer.close();
    } catch (FileNotFoundException e) {
      throw new IOException("Error: could not save image.");
    }
  }

  @Override
  public Image getImageDetails() {
    return this.image;
  }
}
