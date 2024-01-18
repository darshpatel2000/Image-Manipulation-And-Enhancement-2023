package controller.commands;

import java.io.IOException;

import model.AdvImageModel;

/**
 * This interface is a useful for organizing image processing code and making it more
 * modular and maintainable.
 */
public interface ImageOperationCommand {
  /**
   * The purpose of this method is to perform a specific image operation on the given image model.
   *
   * @param model takes an ImageModel as input and returns void.
   */
  void executeCommand(AdvImageModel model) throws IOException;
}
