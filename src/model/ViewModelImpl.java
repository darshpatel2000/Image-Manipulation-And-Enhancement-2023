package model;

import java.util.Map;

import utils.Image;

/**
 * This class implements the read only interface of the model.
 */
public class ViewModelImpl implements ViewModel {
  private final Map<String, Image> imageData;

  /**
   * Public constructor of ViewModelImpl class.
   * @param imageModel Object of the basic image model interface.
   */
  public ViewModelImpl(ImageModel imageModel) {
    this.imageData = imageModel.getImages();
  }

  @Override
  public Map<String, Image> getImages() {
   return imageData;
  }

}
