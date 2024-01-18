package controller;

import model.AdvImageModel;

/**
 * This interface represents the Controller of our design.
 */
public interface ImageController {

  /**
   * Reads input commands from a Scanner object, processes them, and executes the corresponding
   * ImageOperationCommand objects on an AdvImageModel object.
   *
   * @param model an object of AdvImageModel interface.
   */
  void imageControllerHandler(AdvImageModel model);
}
