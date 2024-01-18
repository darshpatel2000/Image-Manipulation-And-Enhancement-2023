package model;

import org.junit.Test;

import java.io.IOException;
import utils.ImageHandler;
import utils.ImageRead;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test all the methods of Model, ImageModelImpl.
 */

public class AdvImageModelImplTest {

  @Test
  public void testModelForAllOps() throws IOException {
    AdvImageModel imageOps = new AdvImageModelImpl();
    ImageHandler imageHandler = new ImageRead("res/ppm/boys.ppm");
    ImageHandler imageHandler2 = new ImageRead("res/ppm/genZboys.ppm");

    imageOps.load(imageHandler.getImageDetails(), "boys");

    imageOps.brighten(10, "boys", "boys-brighten");

    imageOps.verticalFlip("boys", "boys-vertical");

    imageOps.horizontalFlip("boys-vertical",
            "boys-horizontal-vertical");

    imageOps.greyScale("value-component", "boys",
            "boys-greyscale-value");

    imageOps.save("boys-brighten");

    imageOps.save("boys-greyscale-value");

    imageOps.load(imageHandler2.getImageDetails(), "boys");

    imageOps.splitRGB("boys", "boys-red", "boys-green",
            "boys-blue");

    imageOps.brighten(10, "boys-red", "boys-red");

    imageOps.combineRGB("boys-combined", "boys-red",
            "boys-green", "boys-blue");

    imageOps.save("boys-combined");

    imageOps.blur("boys", "boys-blur");

    imageOps.sharpen("boys", "boys-sharpen");

    imageOps.dithering("boys", "boys-dither");

    imageOps.sepiaTone("boys", "boys-sepia");

    imageOps.save("boys-blur");

    imageOps.save("boys-sharpen");

    imageOps.save("boys-dither");

    imageOps.save("boys-sepia");

    assertEquals(13, imageOps.getImages().size());
  }
}
