package model;

import java.util.Map;

import utils.Image;

/**
 * This interface represents the read only model. It is helpful to view to get data.
 */
public interface ViewModel {
  /**
   * This read only method returns the current map of images data.
   * @return image map of the current data set.
   */
  Map<String, Image> getImages();
}
