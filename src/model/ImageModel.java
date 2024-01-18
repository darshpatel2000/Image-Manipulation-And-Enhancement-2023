package model;

import java.io.IOException;
import java.util.Map;

import utils.Image;

/**
 * Interface titled "ImageModel" to define all the basic operations 
 * that can be performed on an image.
 */
public interface ImageModel extends ViewModel {
  /**
   * Load an image from the specified path and refer it to
   * henceforth in the program by the given image name.
   *
   * @param image     the image object which should be loaded in model.
   * @param imageName name of the image to be loaded.
   */
  void load(Image image, String imageName) throws IOException;

  /**
   * The getter method for loaded or processed images data.
   *
   * @return The images array with its key values.
   */
  Map<String, Image> getImages();

  /**
   * brighten the image by the given increment to create a new image,
   * referred to henceforth by the given destination name.
   *
   * @param increment     the value which is to be added to brighten or darken the image.
   * @param imageName     the name of image which is to be brightened.
   * @param destImageName the name with which the edited image is to be saved.
   */
  void brighten(Integer increment, String imageName, String destImageName) throws IOException;

  /**
   * Flip an image vertically to create a new image,
   * referred to henceforth by the given destination name.
   *
   * @param imageName     the name of image which is to be flipped.
   * @param destImageName the name with which the edited image is to be saved.
   */
  void verticalFlip(String imageName, String destImageName) throws IOException;

  /**
   * Flip an image horizontally to create a new image,
   * referred to henceforth by the given destination name.
   *
   * @param imageName     the name of image which is to be flipped.
   * @param destImageName the name with which the edited image is to be saved.
   */
  void horizontalFlip(String imageName, String destImageName) throws IOException;

  /**
   * Create a greyscale image with the red-component of the image with the given name,
   * and refer to it henceforth in the program by the given destination name.
   *
   * @param component     can be either of these red, green, blue, value, luma, intensity.
   * @param imageName     the name of image which is to be greyscale.
   * @param destImageName the name with which the edited image is to be saved.
   */
  void greyScale(String component, String imageName, String destImageName) throws IOException;

  /**
   * Save the image with the given name to the specified path which should include the name of
   * the file.
   *
   * @param imageName name of the image which is used to save.
   * @return Image object will be returned which is going to be saved.
   */

  Image save(String imageName) throws IOException;

  /**
   * split the given image into three greyscale images containing its
   * red, green and blue components respectively.
   *
   * @param imageName      name of the image which is to be split.
   * @param redImageName   name of the red image.
   * @param greenImageName name of the green image.
   * @param blueImageName  name of the blue image.
   */
  void splitRGB(String imageName, String redImageName, String greenImageName, String blueImageName)
          throws IOException;

  /**
   * Combine the three greyscale images into a single image that gets its red, green and blue
   * components from the three images respectively.
   *
   * @param destImageName  the name with which the combined image is to be stored.
   * @param redImageName   name of the red image.
   * @param greenImageName name of the green image.
   * @param blueImageName  name of the blue image.
   */
  void combineRGB(String destImageName, String redImageName, String greenImageName,
                  String blueImageName) throws IOException;
}
