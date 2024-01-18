package model;

import java.io.IOException;

import utils.Image;
import utils.ImageDetails;

/**
 * This class implements the AdvImageModel interface. Thus, it will override all the interface
 * methods and this class extends the ImageModelImpl class which contains basic operations
 * performed on the image.
 */
public class AdvImageModelImpl extends ImageModelImpl implements AdvImageModel {

  @Override
  public void blur(String imageName, String destImageName) throws IOException {
    Image image = null;
    int[][][] blurredImage = null;
    if (images.containsKey(imageName)) {
      image = images.get(imageName);

      double[][] blurFilter = new double[][]{
              {0.0625, 0.125, 0.0625},
              {0.125, 0.25, 0.125},
              {0.0625, 0.125, 0.0625}};

      blurredImage = convolute(image, blurFilter);

      // Update the image details with the blurred image
      images.put(destImageName, new ImageDetails(image.getFormat(), image.getWidth(),
              image.getHeight(), image.getMaxValue(), blurredImage));
    } else {
      throw new IOException("Given file " + imageName + " not found.");
    }
  }

  private int[][][] convolute(Image image, double[][] kernel) {
    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] pixels = image.getPixels();
    int[][][] result = new int[height][width][3];
    int kl = kernel.length / 2;
    // Iterate over each pixel in the image
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        // Iterate over each color channel (R, G, B)
        for (int c = 0; c < 3; c++) {
          double sum = 0.0;
          // Iterate over each neighbor pixel
          for (int k = -kl; k <= kl; k++) {
            for (int l = -kl; l <= kl; l++) {
              int row = i + k;
              int col = j + l;
              // Check if the neighbor pixel is within the bounds of the image
              if (row >= 0 && row < height && col >= 0 && col < width) {
                sum += pixels[row][col][c] * kernel[k + kl][l + kl];
              }
            }
          }
          // Round the sum to an integer value and store it in the result image
          result[i][j][c] = Math.max(0, Math.min(255, (int) Math.round(sum)));

        }
      }
    }
    return result;
  }


  @Override
  public void sharpen(String imageName, String destImageName) throws IOException {
    Image image = null;
    int[][][] sharpenImage = null;
    if (images.containsKey(imageName)) {
      image = images.get(imageName);

      double[][] sharpenFilter;
      sharpenFilter = new double[][]{
              {-0.125, -0.125, -0.125, -0.125, -0.125,},
              {-0.125, 0.25, 0.25, 0.25, -0.125,},
              {-0.125, 0.25, 1.00, 0.25, -0.125,},
              {-0.125, 0.25, 0.25, 0.25, -0.125,},
              {-0.125, -0.125, -0.125, -0.125, -0.125,}};
      sharpenImage = convolute(image, sharpenFilter);

      // Update the image details with the blurred image
      images.put(destImageName, new ImageDetails(image.getFormat(), image.getWidth(),
              image.getHeight(), image.getMaxValue(), sharpenImage));
    } else {
      throw new IOException("Given file " + imageName + " not found.");
    }
  }

  @Override
  public void dithering(String imageName, String destImageName) throws IOException {
    if (images.containsKey(imageName)) {
      Image image = images.get(imageName);
      greyScale("luma-component", imageName, destImageName);
      int[][][] bwImage = images.get(destImageName).getPixels();
      int height = image.getHeight();
      int width = image.getWidth();
      int[][][] ditheredImage = new int[height][width][3];

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int oldPixel = bwImage[i][j][0];
          // Binary thresholding
          int newPixel = oldPixel <= (image.getMaxValue() / 2) ? 0 : image.getMaxValue();

          ditheredImage[i][j][0] = newPixel;
          ditheredImage[i][j][1] = newPixel;
          ditheredImage[i][j][2] = newPixel;

          int quantError = oldPixel - newPixel;
          if (j < width - 1) {
            bwImage[i][j + 1][0] = (int) Math.round(bwImage[i][j + 1][0] + quantError * 0.4375);
          }
          if (j > 0 && i < height - 1) {
            bwImage[i + 1][j - 1][0] =
                    (int) Math.round(bwImage[i + 1][j - 1][0] + quantError * 0.1875);
          }
          if (i < height - 1) {
            bwImage[i + 1][j][0] = (int) Math.round(bwImage[i + 1][j][0] + quantError * 0.3125);
          }
          if (j < width - 1 && i < height - 1) {
            bwImage[i + 1][j + 1][0] =
                    (int) Math.round(bwImage[i + 1][j + 1][0] + quantError * 0.0625);
          }
        }
      }

      // Update the image details with the black and white image
      images.put(destImageName, new ImageDetails(image.getFormat(), image.getWidth(),
              image.getHeight(), image.getMaxValue(), ditheredImage));
    } else {
      throw new IOException("Given file " + imageName + " not found.");
    }
  }

  @Override
  public void sepiaTone(String imageName, String destImageName) throws IOException {
    Image image = null;
    if (images.containsKey(imageName)) {
      image = images.get(imageName);
      int[][][] originalPixels = image.getPixels();
      double[][] array = new double[][]{
              {0.393, 0.769, 0.189},
              {0.349, 0.686, 0.168},
              {0.272, 0.534, 0.131}
      };

      int[][][] sepiaImage = colorTransformation(originalPixels, array);

      // Update the image details map with the sepia image
      images.put(destImageName, new ImageDetails(image.getFormat(), image.getWidth(),
              image.getHeight(), image.getMaxValue(), sepiaImage));
    } else {
      throw new IOException("Given file " + imageName + " not found.");
    }
  }
}
