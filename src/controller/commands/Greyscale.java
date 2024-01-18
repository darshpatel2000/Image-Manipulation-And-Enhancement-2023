package controller.commands;

import java.io.IOException;

import model.AdvImageModel;

/**
 * The greyscale class is another implementation of the ImageOperationCommand interface.
 * It takes input parameters such as the colour component to use for the grayscale conversion,
 * the name of the source image, and the name of the destination image.
 */

public class Greyscale implements ImageOperationCommand {

  private final String component;
  private final String imageName;
  private final String destImageName;

  /**
   * Public constructor for greyscale class which takes input of component, imageName and
   * destImageName.
   *
   * @param component     can be either of these red, green, blue, value, luma, intensity.
   * @param imageName     the name of image which is to be greyscale.
   * @param destImageName the name with which the edited image is to be saved.
   */
  public Greyscale(String component, String imageName, String destImageName) {
    this.component = component;
    this.imageName = imageName;
    this.destImageName = destImageName;
  }

  @Override
  public void executeCommand(AdvImageModel model) throws IOException {
    model.greyScale(component, imageName, destImageName);
  }
}
