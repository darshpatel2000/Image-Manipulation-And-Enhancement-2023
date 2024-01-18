package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import utils.Image;
import utils.ImageDetails;

/**
 * This is ImageModelImpl class  which implements ImageModel and process the commands provided by
 * the controller.
 */
public class ImageModelImpl implements ImageModel {


  protected final Map<String, Image> images = new HashMap<>();

  @Override
  public Map<String, Image> getImages() {
    return images;
  }

  @Override
  public void load(Image image, String imageName) throws IOException {
    images.put(imageName, image);
  }

  @Override
  public void brighten(Integer increment, String imageName, String destImageName)
          throws IOException {
    if (images.containsKey(imageName)) {
      Image image = images.get(imageName);
      int[][][] originalPixels = image.getPixels();
      int height = originalPixels.length;
      int width = originalPixels[0].length;
      int[][][] imageBright = new int[height][width][3];

      for (int i = 0; i < originalPixels.length; i++) {
        for (int j = 0; j < originalPixels[i].length; j++) {
          int r = originalPixels[i][j][0] + increment;
          int g = originalPixels[i][j][1] + increment;
          int b = originalPixels[i][j][2] + increment;

          if (r > image.getMaxValue()) {
            r = image.getMaxValue();
          }
          if (g > image.getMaxValue()) {
            g = image.getMaxValue();
          }
          if (b > image.getMaxValue()) {
            b = image.getMaxValue();
          }

          if (r < 0) {
            r = 0;
          }
          if (g < 0) {
            g = 0;
          }
          if (b < 0) {
            b = 0;
          }

          imageBright[i][j][0] = r;
          imageBright[i][j][1] = g;
          imageBright[i][j][2] = b;
        }
      }
      images.put(destImageName, new ImageDetails(image.getFormat(), image.getWidth(),
              image.getHeight(), image.getMaxValue(), imageBright));
    } else {
      throw new IOException("File " + imageName + " not found.");
    }
  }

  @Override
  public void verticalFlip(String imageName, String destImageName) throws IOException {
    if (images.containsKey(imageName)) {
      Image image = images.get(imageName);
      int[][][] originalPixels = image.getPixels();
      int height = originalPixels.length;
      int width = originalPixels[0].length;
      int[][][] flippedPixels = new int[height][width][3];

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          flippedPixels[i][j][0] = originalPixels[height - i - 1][j][0];
          flippedPixels[i][j][1] = originalPixels[height - i - 1][j][1];
          flippedPixels[i][j][2] = originalPixels[height - i - 1][j][2];
        }
      }
      images.put(destImageName, new ImageDetails(image.getFormat(), image.getWidth(),
              image.getHeight(),
              image.getMaxValue(), flippedPixels));
    } else {
      throw new IOException("File " + imageName + " not found.");
    }
  }

  @Override
  public void horizontalFlip(String imageName, String destImageName) throws IOException {
    if (images.containsKey(imageName)) {
      Image image = images.get(imageName);
      int[][][] originalPixels = image.getPixels();
      int height = originalPixels.length;
      int width = originalPixels[0].length;
      int[][][] flippedPixels = new int[height][width][3];

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          flippedPixels[i][j] = originalPixels[i][width - j - 1];
        }
      }
      images.put(destImageName, new ImageDetails(image.getFormat(), image.getWidth(),
              image.getHeight(),
              image.getMaxValue(), flippedPixels));
    } else {
      throw new IOException("File " + imageName + " not found.");
    }
  }

  @Override
  public void greyScale(String component, String imageName, String destImageName)
          throws IOException {
    if (images.containsKey(imageName)) {
      Image image = images.get(imageName);
      int[][][] originalPixels = image.getPixels();
      int height = originalPixels.length;
      int width = originalPixels[0].length;
      int[][][] imageGrey = new int[height][width][3];

      for (int i = 0; i < imageGrey.length; i++) {
        for (int j = 0; j < imageGrey[0].length; j++) {
          int r = originalPixels[i][j][0];
          int g = originalPixels[i][j][1];
          int b = originalPixels[i][j][2];

          int gray;
          switch (component) {

            case "value-component":
              gray = Math.max(r, Math.max(g, b));
              break;
            case "intensity-component":
              gray = (r + g + b) / 3;
              break;
            case "luma-component":
              double[][] array = new double[][]{
                      {0.2126, 0.7152, 0.0722},
                      {0.2126, 0.7152, 0.0722},
                      {0.2126, 0.7152, 0.0722}
              };
              int[][][] luma = colorTransformation(originalPixels, array);
              images.put(destImageName, new ImageDetails(image.getFormat(), image.getWidth(),
                      image.getHeight(),
                      image.getMaxValue(), luma));
              return;
            case "red-component":
              gray = r;
              break;
            case "green-component":
              gray = g;
              break;
            case "blue-component":
              gray = b;
              break;
            default:
              throw new IOException("Enter valid component.");
          }

          imageGrey[i][j][0] = gray;
          imageGrey[i][j][1] = gray;
          imageGrey[i][j][2] = gray;
        }
      }
      images.put(destImageName, new ImageDetails(image.getFormat(), image.getWidth(),
              image.getHeight(),
              image.getMaxValue(), imageGrey));
    } else {
      throw new IOException("File " + imageName + " not found.");
    }
  }

  @Override
  public Image save(String imageName) throws IOException {
    if (images.containsKey(imageName)) {
      return images.get(imageName);
    } else {
      throw new IOException("File " + imageName + " not found.");
    }
  }

  @Override
  public void splitRGB(String imageName, String redImageName, String greenImageName,
                       String blueImageName) throws IOException {

    greyScale("red-component", imageName, redImageName);
    greyScale("green-component", imageName, greenImageName);
    greyScale("blue-component", imageName, blueImageName);
  }

  @Override
  public void combineRGB(String destImageName, String redImageName, String greenImageName,
                         String blueImageName) throws IOException {
    int[][][] redImagePixels = null;
    int[][][] greenImagePixels = null;
    int[][][] blueImagePixels = null;
    Image imageRed = null;
    Image imageGreen = null;
    Image imageBlue = null;

    if (images.containsKey(redImageName)) {
      imageRed = images.get(redImageName);
      redImagePixels = imageRed.getPixels();
    } else {
      throw new IOException("Given file " + redImageName + " for red component not found.");
    }

    if (images.containsKey(greenImageName)) {
      imageGreen = images.get(greenImageName);
      greenImagePixels = imageGreen.getPixels();
    } else {
      throw new IOException("Given file " + greenImageName + " for green component not found.");
    }

    if (images.containsKey(blueImageName)) {
      imageBlue = images.get(blueImageName);
      blueImagePixels = imageBlue.getPixels();
    } else {
      throw new IOException("Given file " + blueImageName + " for blue component not found.");
    }

    if (!compareImages(imageRed, imageGreen, imageBlue)) {
      throw new IOException("Three images provided are not compatible to get merged.");
    }


    if (redImagePixels != null && greenImagePixels != null && blueImagePixels != null) {
      int[][][] rgbNewImage = new int[redImagePixels.length][redImagePixels[0].length][3];

      for (int i = 0; i < redImagePixels.length; i++) {
        for (int j = 0; j < redImagePixels[0].length; j++) {
          rgbNewImage[i][j][0] = redImagePixels[i][j][0];
          rgbNewImage[i][j][1] = greenImagePixels[i][j][1];
          rgbNewImage[i][j][2] = blueImagePixels[i][j][2];
        }
      }
      images.put(destImageName, new ImageDetails(imageRed.getFormat(), imageRed.getWidth(),
              imageRed.getHeight(), imageRed.getMaxValue(), rgbNewImage));
    }
  }

  private boolean compareImages(Image redImageName, Image greenImageName,
                                Image blueImageName) {
    return redImageName.getWidth() == greenImageName.getWidth() && greenImageName.getWidth()
            == blueImageName.getWidth() && redImageName.getHeight() == greenImageName.getHeight()
            && greenImageName.getHeight() == blueImageName.getHeight()
            && redImageName.getMaxValue() == greenImageName.getMaxValue()
            && greenImageName.getMaxValue() == blueImageName.getMaxValue();
  }

  protected int[][][] colorTransformation(int[][][] originalPixels, double[][] array) {
    int[][][] transformed = new int[originalPixels.length][originalPixels[0].length][3];
    for (int i = 0; i < originalPixels.length; i++) {
      for (int j = 0; j < originalPixels[0].length; j++) {
        int r = originalPixels[i][j][0];
        int g = originalPixels[i][j][1];
        int b = originalPixels[i][j][2];
        for (int x = 0; x < 3; x++) {
          transformed[i][j][x] = (int) Math.min(255,
                  Math.round(array[x][0] * r + array[x][1] * g + array[x][2] * b));
        }
      }
    }
    return transformed;
  }
}