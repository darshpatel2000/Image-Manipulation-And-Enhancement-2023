package controller.commands;

import java.io.IOException;

import model.AdvImageModel;

/**
 * This class represents Sharpen command. It redirects the control to the model for sharpening.
 */
public class Sharpen implements ImageOperationCommand {
  private final String imageName;
  private final String destImageName;

  /**
   * Public constructor of Sharpen class.
   *
   * @param imageName     imageName which is going to be blurred.
   * @param destImageName destination name, by which the image will be saved.
   */
  public Sharpen(String imageName, String destImageName) {
    this.imageName = imageName;
    this.destImageName = destImageName;
  }

  @Override
  public void executeCommand(AdvImageModel model) throws IOException {
    model.sharpen(imageName, destImageName);
  }
}
