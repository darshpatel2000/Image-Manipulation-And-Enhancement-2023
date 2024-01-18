package controller.commands;

import java.io.IOException;

import model.AdvImageModel;

/**
 * This command will create a rgb-image from the three greyscale images. This greyscale images
 * holds the values of red, green, and blue pixels.
 */

public class RgbCombine implements ImageOperationCommand {
  private final String imageName;
  private final String redImageName;
  private final String greenImageName;
  private final String blueImageName;

  /**
   * Public constructor for rgb combine class which takes input of imageName and
   * redImageName, greenImageName, blueImageName.
   *
   * @param imageName      the name with which the combined image is to be stored.
   * @param redImageName   name of the red image.
   * @param greenImageName name of the green image.
   * @param blueImageName  name of the blue image.
   */
  public RgbCombine(String imageName, String redImageName, String greenImageName,
                    String blueImageName) {
    this.imageName = imageName;
    this.redImageName = redImageName;
    this.greenImageName = greenImageName;
    this.blueImageName = blueImageName;
  }

  @Override
  public void executeCommand(AdvImageModel model) throws IOException {
    model.combineRGB(imageName, redImageName, greenImageName, blueImageName);
  }
}
