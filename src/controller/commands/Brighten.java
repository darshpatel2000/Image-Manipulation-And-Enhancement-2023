package controller.commands;

import java.io.IOException;

import model.AdvImageModel;

/**
 * This is a Java class named “brighten” that implements the ImageOperationCommand interface.
 * It represents a command to brighten or darken an image by a given
 * increment and create a new image.
 */
public class Brighten implements ImageOperationCommand {
  private final String increment;
  private final String imageName;
  private final String destImageName;

  /**
   * Public constructor for brighten class which takes input of increment, imageName and
   * destImageName.
   *
   * @param increment     brighten the image by the given increment to create a new image.
   * @param imageName     the image which is to be brightened.
   * @param destImageName referred to henceforth by the given destination name.
   */
  public Brighten(String increment, String imageName, String destImageName) {
    this.increment = increment;
    this.imageName = imageName;
    this.destImageName = destImageName;
  }

  @Override
  public void executeCommand(AdvImageModel model) throws IOException {
    model.brighten(Integer.valueOf(increment), imageName, destImageName);
  }
}
