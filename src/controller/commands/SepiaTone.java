package controller.commands;

import java.io.IOException;

import model.AdvImageModel;

/**
 * This class represents the Sepia command. It will redirect control to the model for sepia-tone.
 */
public class SepiaTone implements ImageOperationCommand {
  private final String imageName;
  private final String destImageName;

  /**
   * Public constructor of Sepia class.
   *
   * @param imageName     imageName which is going to be blurred.
   * @param destImageName destination name, by which the image will be saved.
   */
  public SepiaTone(String imageName, String destImageName) {
    this.imageName = imageName;
    this.destImageName = destImageName;
  }

  @Override
  public void executeCommand(AdvImageModel model) throws IOException {
    model.sepiaTone(imageName, destImageName);
  }
}
