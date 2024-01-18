package model;


import java.io.IOException;

/**
 * This interface represents the advance command operations done on the given Image. This
 * interface is extension of basic ImageModel.
 */
public interface AdvImageModel extends ImageModel {

  /**
   * This function blur the given image and save it to the map.
   *
   * @param imageName     name of the image, on which blur will be performed.
   * @param destImageName image name, by which the blurred image should be saved.
   * @throws IOException throws exception if given image name does not exist in model.
   */
  void blur(String imageName, String destImageName) throws IOException;

  /**
   * This function sharpen the given image and save it to the map.
   *
   * @param imageName     name of the image, on which sharpening will be performed.
   * @param destImageName image name, by which the sharpened image should be saved.
   * @throws IOException throws exception if given image name does not exist in model.
   */
  void sharpen(String imageName, String destImageName) throws IOException;

  /**
   * This function performs dither on the given image and save it to the map.
   *
   * @param imageName     name of the image, on which dithering will be performed.
   * @param destImageName image name, by which the dithered image should be saved.
   * @throws IOException throws exception if given image name does not exist in model.
   */

  void dithering(String imageName, String destImageName) throws IOException;

  /**
   * This function sepia transformation the given image and save it to the map.
   *
   * @param imageName     name of the image, on which sepia transformation will be performed.
   * @param destImageName image name, by which the sepia toned image should be saved.
   * @throws IOException throws exception if given image name does not exist in model.
   */
  void sepiaTone(String imageName, String destImageName) throws IOException;
}
