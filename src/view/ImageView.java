package view;

import controller.ViewController;

/**
 * This interface represents the view of the current application for displaying images.
 * It provides methods for adding controller features, showing image data, and displaying error messages.
 */
public interface ImageView {
  /**
   * This method adds the given ViewController object as a feature to the view.
   *
   * @param features The ViewController object to add as a feature to the view.
   */
  void addFeatures(ViewController features);

  /**
   * This method displays the image data for the given image name.
   *
   * @param imageName The name of the image whose data is to be displayed.
   */
  void showImageData(String imageName);

  /**
   * This method displays the given error message to the user.
   *
   * @param errorFound The error message to display to the user.
   */
  void showError(String errorFound);
}




