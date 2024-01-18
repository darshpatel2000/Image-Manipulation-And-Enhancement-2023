package utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * This class helps to Read the image from given path.
 */
public class ImageRead implements ImageHandler {

  private final String imagePath;
  private final Image image;

  /**
   * This class is used to load an image of any format. It will segregate the images based on its
   * extension and call the respective helper method accordingly.
   *
   * @param imagePath the path of the image to be loaded.
   * @throws IOException the exception if the image is not supported by our application.
   */
  public ImageRead(String imagePath) throws IOException {
    this.imagePath = imagePath;
    int dotIndex = imagePath.lastIndexOf('.');
    String extension = imagePath.substring(dotIndex + 1);
    switch (extension.toLowerCase()) {
      case "ppm":
        this.image = processPPM();
        break;
      case "bmp":
      case "jpg":
      case "png":
        this.image = processImage(extension.toLowerCase());
        break;
      default:
        throw new IOException("Given file type is not supported.");
    }
  }

  private Image processImage(String extension) throws IOException {
    int height;
    int width;
    int[][][] image;
    int maxValue;
    File file = null;
    // implementation for loading Other images
    try {
      file = new File(imagePath);
    } catch (Exception e) {
      throw new IOException("File at " + imagePath + " not found.");
    }

    BufferedImage bufferedImage = ImageIO.read(file);
    height = bufferedImage.getHeight();
    width = bufferedImage.getWidth();

    if (height <= 0) {
      throw new IOException("Height should be positive.");
    }
    if (width <= 0) {
      throw new IOException("Width should be positive.");
    }

    image = new int[height][width][3];
    // loop through each pixel and extract the RGB values

    // Loop through the image pixels
    maxValue = Integer.MIN_VALUE;  // Initialize max value to minimum possible integer

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        // Get the integer value of the pixel
        Color rgba = new Color(bufferedImage.getRGB(j, i));
        int red = rgba.getRed();
        int green = rgba.getGreen();
        int blue = rgba.getBlue();

        // Store the pixel values in the 3D array
        image[i][j][0] = red;
        image[i][j][1] = green;
        image[i][j][2] = blue;


        // Update the maximum value if necessary
        int maxComponent = Math.max(Math.max(red, green), blue);
        maxValue = Math.max(maxValue, maxComponent);
      }
    }
    return new ImageDetails(extension, width, height, maxValue, image);
  }

  private Image processPPM() throws IOException {
    int height;
    int width;
    int[][][] image;
    int maxValue;
    Scanner sc = null;
    try {
      sc = new Scanner(new FileInputStream(imagePath));
    } catch (Exception e) {
      throw new IOException("File at " + imagePath + " not found.");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IOException("Invalid PPM file: plain RAW file should begin with P3");
    }
    width = sc.nextInt();
    height = sc.nextInt();
    if (height <= 0) {
      throw new IOException("Height should be positive.");
    }
    if (width <= 0) {
      throw new IOException("Width should be positive.");
    }
    maxValue = sc.nextInt();
    if (maxValue <= 0) {
      throw new IOException("Maximum value should be positive.");
    }

    image = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        try {
          image[i][j][0] = sc.nextInt();
          image[i][j][1] = sc.nextInt();
          image[i][j][2] = sc.nextInt();
        } catch (Exception e) {
          throw new IOException("Less values of pixels than expected.");
        }
        if (!(image[i][j][0] >= 0 && image[i][j][0] <= maxValue)) {
          throw new IOException("Red pixel has invalid value." + image[i][j][0]
                  + " " + i + " " + j + "\n");

        }
        if (!(image[i][j][1] >= 0 && image[i][j][1] <= maxValue)) {
          throw new IOException("Green pixel has invalid value.");
        }
        if (!(image[i][j][2] >= 0 && image[i][j][2] <= maxValue)) {
          throw new IOException("Blue pixel has invalid value.");
        }
      }
    }
    if (sc.hasNext()) {
      throw new IOException("More values of pixels than expected.");
    }
    return new ImageDetails("P3", width, height, maxValue, image);
  }

  @Override
  public Image getImageDetails() {
    return image;
  }
}