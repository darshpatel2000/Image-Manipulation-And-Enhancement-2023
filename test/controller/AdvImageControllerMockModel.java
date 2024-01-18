package controller;

import java.io.IOException;
import java.util.Map;

import model.AdvImageModel;
import utils.Image;

/**
 * This is a Mock Model class which is helpful to test that the
 * controller passes correct parameters to model.
 */
public class AdvImageControllerMockModel implements AdvImageModel {
  private final Image uniqueCode;
  private final StringBuilder log;

  public AdvImageControllerMockModel(StringBuilder log, Image uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void load(Image img, String imageName) {
    log.append("Input: " + img.toString() + imageName + "\n");
  }

  @Override
  public Map<String, Image> getImages() {
    return null;
  }

  @Override
  public void brighten(Integer increment, String imageName, String destImageName) {
    log.append("Input:" + " " + increment + " " + imageName + " " + destImageName + "\n");
  }

  @Override
  public void verticalFlip(String imageName, String destImageName) {
    log.append("Input:" + " " + imageName + " " + destImageName + "\n");
  }

  @Override
  public void horizontalFlip(String imageName, String destImageName) {
    log.append("Input:" + " " + imageName + " " + destImageName + "\n");
  }

  @Override
  public void greyScale(String component, String imageName, String destImageName) {
    log.append("Input:" + " " + component + " " + imageName + " " + destImageName + "\n");
  }

  @Override
  public Image save(String imageName) throws IOException {
    log.append("Input:" + " " + imageName + "\n");
    return uniqueCode;
  }


  @Override
  public void splitRGB(String imageName, String redImageName,
                       String greenImageName, String blueImageName) {
    log.append("Input:" + " "
            + imageName + " "
            + redImageName + " "
            + greenImageName + " "
            + blueImageName + "\n");
  }

  @Override
  public void combineRGB(String destImageName, String redImageName,
                         String greenImageName, String blueImageName) {
    log.append("Input:" + " "
            + destImageName + " "
            + redImageName + " "
            + greenImageName + " "
            + blueImageName + "\n");
  }

  @Override
  public void blur(String imageName, String destImageName) throws IOException {
    log.append("Input:" + " " + imageName + " " + destImageName + "\n");
  }

  @Override
  public void sharpen(String imageName, String destImageName) throws IOException {
    log.append("Input:" + " " + imageName + " " + destImageName + "\n");
  }

  @Override
  public void dithering(String imageName, String destImageName) throws IOException {
    log.append("Input:" + " " + imageName + " " + destImageName + "\n");
  }

  @Override
  public void sepiaTone(String imageName, String destImageName) throws IOException {
    log.append("Input:" + " " + imageName + " " + destImageName + "\n");
  }
}
