package controller;

import view.ImageView;

/**
 * This interface represents the controller for the view. View calls these methods, and it will
 * perform the given tasks.
 */
public interface ViewController {


  /**
   * This method performs the load of an image from by GUI.
   * @param imagePath path of the image selected from given folders.
   */
  void loadImage(String imagePath);

  /**
   * This method set the view for this controller.
   * @param view Object of the ImageView class.
   */
  void setView(ImageView view);

  /**
   * This method performs the dither operation from GUI.
   */
  void performDither();

  /**
   * This method performs the sepia color transformation operation from GUI.
   */
  void performSepia();

  /**
   * This method performs the horizontal flip operation from GUI.
   */
  void performHorizontalFlip();

  /**
   * This method performs the vertical flip operation from GUI.
   */
  void performVerticalFlip();

  /**
   * This method performs the blur operation from GUI.
   */
  void performBlur();

  /**
   * This method performs the sharpening of image operation from GUI.
   */
  void performSharpening();

  /**
   * This method helps to save the current image on the local disk via GUI.
   *
   * @param absolutePath The folder path selected by user on the local disk.
   * @param currentImage current Image that a user is working on.
   */
  void saveImage(String absolutePath);

  /**
   * This method performs brighten or darkening of an image.
   * @param value number you want to increase the brightness of an image.
   */
  void performBrighten(int value);

  /**
   * This method performs the greyScale of an image through GUI.
   * @param option grayScale component name.
   */
  void performGreyScale(String option);

  /**
   * This method performs the combining of three images.
   * @param absolutePathOne Path of the first image.
   * @param absolutePathTwo Path of the second image.
   * @param absolutePathThree Path of the third image.
   */
  void combineImage(String absolutePathOne, String absolutePathTwo, String absolutePathThree);

  /**
   *This method performs the splitting of an image.
   */
  void splitImage();

  /**
   * This method sets the current image that is being shown to user.
   * @param imageName name of the current image.
   */
  void setCurrentImageName(String imageName);
}
