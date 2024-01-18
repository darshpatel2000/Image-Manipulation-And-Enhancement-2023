package controller.commands;

import java.io.IOException;

import model.AdvImageModel;
import utils.Image;
import utils.ImageRead;

/**
 * This command load the image from the specified to system.
 */
public class Load implements ImageOperationCommand {
  private final String imageName;
  private final String imagePath;

  /**
   * Public constructor for load class which takes input of imageName and
   * destImageName.
   *
   * @param imagePath the path of image loaded.
   * @param imageName name of the image to be loaded.
   */
  public Load(String imagePath, String imageName) {
    this.imagePath = imagePath;
    this.imageName = imageName;
  }

  @Override
  public void executeCommand(AdvImageModel model) throws IOException {
    Image image = new ImageRead(imagePath).getImageDetails();
    model.load(image, imageName);
  }
}