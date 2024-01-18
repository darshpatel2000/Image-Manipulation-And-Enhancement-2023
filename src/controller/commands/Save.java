package controller.commands;

import java.io.IOException;

import model.AdvImageModel;
import utils.Image;
import utils.ImageWrite;

/**
 * This is a Java class called Save that implements the ImageOperationCommand interface.
 * It allows an ImageModel object to be saved as an image file using a specific
 * imageName and imagePath.
 */
public class Save implements ImageOperationCommand {

  private final String imageName;
  private final String imagePath;

  private final Appendable out;

  /**
   * Public constructor for save class which takes input of imagePath and imageName.
   *
   * @param imagePath the path of image saved.
   * @param imageName name of the image to be saved.
   * @param out       Appendable variable to save the image data for testing.
   */
  public Save(String imagePath, String imageName, Appendable out) {
    this.imagePath = imagePath;
    this.imageName = imageName;
    this.out = out;
  }

  @Override
  public void executeCommand(AdvImageModel model) throws IOException {
    Image image = model.save(imageName);
    new ImageWrite(image, imagePath);
    this.out.append(image.toString());
  }

}