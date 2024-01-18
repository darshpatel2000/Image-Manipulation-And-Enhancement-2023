package controller.commands;

import java.io.IOException;

import model.AdvImageModel;

/**
 * This command will split the given image to three grey scale images of red, green, and blue.
 */
public class RgbSplit implements ImageOperationCommand {

  private final String imageName;
  private final String redImageName;
  private final String greenImageName;
  private final String blueImageName;

  /**
   * Public constructor for rgb split class which takes input of imageName and
   * redImageName, greenImageName, blueImageName.
   *
   * @param imageName      the image which is to be split.
   * @param redImageName   name of the red image.
   * @param greenImageName name of the green image.
   * @param blueImageName  name of the blue image.
   */
  public RgbSplit(String imageName, String redImageName, String greenImageName,
                  String blueImageName) {
    this.imageName = imageName;
    this.redImageName = redImageName;
    this.greenImageName = greenImageName;
    this.blueImageName = blueImageName;
  }

  @Override
  public void executeCommand(AdvImageModel model) throws IOException {
    model.splitRGB(imageName, redImageName, greenImageName, blueImageName);
  }
}
