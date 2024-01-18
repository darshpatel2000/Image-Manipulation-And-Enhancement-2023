package controller.commands;

import java.io.IOException;

import model.AdvImageModel;

/**
 * This class represents 'Blur' command. It redirects the control to model to blur.
 */
public class Blur implements ImageOperationCommand {
  private final String imageName;
  private final String destImageName;

  /**
   * Public constructor of Blur class.
   *
   * @param imageName     imageName which is going to be blurred.
   * @param destImageName destination name, by which the image will be saved.
   */
  public Blur(String imageName, String destImageName) {
    this.imageName = imageName;
    this.destImageName = destImageName;
  }

  @Override
  public void executeCommand(AdvImageModel model) throws IOException {
    model.blur(imageName, destImageName);
  }
}
