package controller;

import java.io.InputStreamReader;
import java.io.StringReader;

import model.AdvImageModel;
import view.ImageView;

/**
 * This class implements the ViewController interface and it's methods. This class performs all
 * the actions performed by user via GUI.
 */
public class ViewControllerImpl extends ExtensibleImageController implements ViewController {
  private final AdvImageModel model;

  private ImageView view;
  private String currentImage = "boys";

  /**
   * Public constructor of the ViewControllerImpl class.
   *
   * @param model AdvImageModel object to access the model.
   */
  public ViewControllerImpl(AdvImageModel model) {
    super(new InputStreamReader(System.in), new StringBuffer());
    this.model = model;
  }

  @Override
  public void setView(ImageView v) {
    view = v;
    view.addFeatures(this);
  }

  @Override
  public void loadImage(String imagePath) {
    currentImage = "boys";
    in = new StringReader("load " + imagePath + " " + currentImage);
    performTask();
  }

  @Override
  public void performDither() {
    in = new StringReader("dither "+ currentImage + " " + currentImage);
    performTask();
  }

  @Override
  public void performSepia() {
    in = new StringReader("sepia "+ currentImage + " " + currentImage);
    performTask();
  }

  @Override
  public void performHorizontalFlip() {
    in = new StringReader("horizontal-flip "+ currentImage + " " + currentImage);
    performTask();
  }

  @Override
  public void performVerticalFlip() {
    in = new StringReader("vertical-flip "+ currentImage + " " + currentImage);
    performTask();
  }

  @Override
  public void performBlur() {
    in = new StringReader("blur "+ currentImage + " " + currentImage);
    performTask();
  }

  @Override
  public void performSharpening() {
    in = new StringReader("sharpen "+ currentImage + " " + currentImage);
    performTask();
  }

  @Override
  public void saveImage(String absolutePath) {
    in = new StringReader("save " + absolutePath + " " + currentImage);
  }

  @Override
  public void performBrighten(int value) {
    in = new StringReader("brighten " + value + " " + currentImage + " " + currentImage);
    performTask();
  }

  @Override
  public void performGreyScale(String option) {
    in = new StringReader("greyscale " + option + " " + currentImage + " " + currentImage);
    performTask();
  }

  @Override
  public void combineImage(String absolutePathOne, String absolutePathTwo,
                           String absolutePathThree) {
    in = new StringReader("load " + absolutePathOne + " boys-combine-red\n" +
            "load " + absolutePathTwo + " boys-combine-green\n" +
            "load " + absolutePathThree + " boys-combine-blue\n" +
            "rgb-combine boys boys-combine-red boys-combine-green boys-combine-blue");
    performTask();
  }

  @Override
  public void splitImage() {
    in = new StringReader("rgb-split " + currentImage + " boys-split-red boys-split-green " +
            "boys-split-blue");
    imageControllerHandler(model);
  }

  private void performTask() {
    imageControllerHandler(model);
    String errorFound = super.out.toString();
    if (errorFound.equals("")) {
      view.showImageData(currentImage);
    } else {
      view.showError(errorFound);
      super.out = new StringBuilder();
    }
  }

  @Override
  public void setCurrentImageName(String imageName) {
    currentImage = imageName;
  }
}
