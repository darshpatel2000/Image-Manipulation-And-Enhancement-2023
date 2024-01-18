package utils;

/**
 * This interface handles the reading and writing of the Image.
 */
public interface ImageHandler {

  /**
   * This is a get method to fetch the ImageDetails object that is going to read from file or
   * wrote to the file.
   *
   * @return Image object containing all the details about Image.
   */
  Image getImageDetails();
}
