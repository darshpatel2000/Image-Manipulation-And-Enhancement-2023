package controller.commands;

import java.io.IOException;

import model.AdvImageModel;

/**
 * This is a Java class named horizontalFlip that implements the ImageOperationCommand interface.
 * It represents an operation to horizontally flip an image. The class has two instance variables:
 * imageName and destImageName, both of which are Strings.
 */
public class HorizontalFlip implements ImageOperationCommand {

  private final String imageName;
  private final String destImageName;

  /**
   * Public constructor for horizontal flip class which takes input of imageName and
   * destImageName.
   *
   * @param imageName     the name of image which is to be flipped.
   * @param destImageName the name with which the edited image is to be saved.
   */
  public HorizontalFlip(String imageName, String destImageName) {
    this.imageName = imageName;
    this.destImageName = destImageName;
  }

  @Override
  public void executeCommand(AdvImageModel model) throws IOException {
    model.horizontalFlip(imageName, destImageName);
  }
}
