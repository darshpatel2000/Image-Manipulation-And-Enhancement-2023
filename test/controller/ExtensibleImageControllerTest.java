package controller;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import utils.ImageDetails;
import utils.ImageHandler;
import utils.ImageRead;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the controller solely with the help of our AdvImageControllerMockModel.
 */
public class ExtensibleImageControllerTest {

  @Test
  public void testCorrectLoad() throws IOException {
    Reader in = new StringReader("load res/ppm/boys.ppm boys\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageHandler imageHandler = new ImageRead("res/ppm/boys.ppm");
    imageController.imageControllerHandler(
            new AdvImageControllerMockModel(log, imageHandler.getImageDetails()));
    assertEquals("Input: " + imageHandler.getImageDetails() + "boys\n", log.toString());
  }

  @Test
  public void testWrongLoad() {
    Reader in = new StringReader("load boys.ppm boys");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));
    assertEquals("", log.toString());
  }

  @Test
  public void testPositiveBrighten() {
    Reader in = new StringReader("brighten 50 boys boys-brighter\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));
    assertEquals("Input: 50 boys boys-brighter\n", log.toString());

  }

  @Test
  public void testNegativeBrighten() {
    Reader in = new StringReader("brighten -50 boys boys-darker\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));
    assertEquals("Input: -50 boys boys-darker\n", log.toString());
  }

  @Test
  public void testMaxBrightness() {
    Reader in = new StringReader("brighten 255 boys boys-brighter\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));

    assertEquals("Input: 255 boys boys-brighter\n", log.toString());
  }

  @Test
  public void testMixBrightness() throws IOException {
    String inputCommand = "load res/ppm/boys.ppm boys\n"
            + "brighten -50 boys boys-darker\n"
            + "brighten 20 boys-darker boys-brighter\n";
    String expected = "Input: -50 boys boys-darker\n"
            + "Input: 20 boys-darker boys-brighter\n";

    ImageHandler imageHandler = new ImageRead("res/ppm/boys.ppm");
    String expectedOutput = "Input: " + imageHandler.getImageDetails() + "boys\n" + expected;

    Reader in = new StringReader(inputCommand);
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log,
            imageHandler.getImageDetails()));

    assertEquals(expectedOutput, log.toString());

  }


  @Test
  public void testFuzzyBrightness() {
    for (int i = -255; i <= 255; i++) {
      Reader in = new StringReader("brighten " + i
              + " boys boys-brighter\n");
      Appendable out = new StringBuilder();
      ImageController imageController = new ExtensibleImageController(in, out);
      StringBuilder log = new StringBuilder();
      ImageDetails testImage = new ImageDetails();
      imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));
      assertEquals("Input: "
              + i
              + " boys boys-brighter\n", log.toString());
    }
  }

  @Test
  public void testHorizontalFlip() {
    Reader in = new StringReader("horizontal-flip boys " +
            "boys-horizontal\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));

    assertEquals("Input: boys boys-horizontal\n", log.toString());
  }

  @Test
  public void testVerticalFlip() {
    Reader in = new StringReader("horizontal-flip boys boys-vertical\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));

    assertEquals("Input: boys boys-vertical\n", log.toString());
  }

  @Test
  public void testMixFlips() throws IOException {
    String inputCommand = "load res/ppm/boys.ppm boys\n"
            + "horizontal-flip boys boys-horizontal\n"
            + "vertical-flip boys boys-horizontal\n";
    String expected = "Input: boys boys-horizontal\n"
            + "Input: boys boys-horizontal\n";

    ImageHandler imageHandler = new ImageRead("res/ppm/boys.ppm");
    String expectedOutput = "Input: " + imageHandler.getImageDetails() + "boys\n" + expected;

    Reader in = new StringReader(inputCommand);
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log,
            imageHandler.getImageDetails()));

    assertEquals(expectedOutput, log.toString());

  }

  @Test
  public void testGreyScaleValue() {
    Reader in = new StringReader("greyscale value-component boys "
            + "boys-value-component\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));
    assertEquals("Input: value-component boys " +
            "boys-value-component\n", log.toString());
  }

  @Test
  public void testGreyScaleLuma() {
    Reader in = new StringReader("greyscale luma-component boys"
            + " boys-luma-component\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));
    assertEquals("Input: luma-component boys boys-luma-component\n",
            log.toString());
  }

  @Test
  public void testGreyScaleIntensity() {
    Reader in = new StringReader("greyscale intensity-component boys "
            + "boys-intensity-component\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));
    assertEquals("Input: intensity-component boys "
                    + "boys-intensity-component\n",
            log.toString());
  }

  @Test
  public void testGreyScaleRed() {
    Reader in = new StringReader("greyscale red-component boys "
            + "boys-red-component\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));
    assertEquals("Input: red-component boys "
            + "boys-red-component\n", log.toString());
  }

  @Test
  public void testGreyScaleGreen() {
    Reader in = new StringReader("greyscale green-component boys "
            + "boys-green-component\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));
    assertEquals("Input: green-component boys "
            + "boys-green-component\n", log.toString());
  }

  @Test
  public void testGreyScaleBlue() {
    Reader in = new StringReader("greyscale blue-component boys "
            + "boys-blue-component\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));
    assertEquals("Input: blue-component boys "
            + "boys-blue-component\n", log.toString());
  }

  @Test
  public void testMixGreyScale() throws IOException {
    String inputCommand = "load res/ppm/boys.ppm boys\n"
            + "greyscale blue-component boys boys-blue-component\n"
            + "greyscale intensity-component boys-blue-component "
            + "boys-intensity-component\n";
    String expected = "Input: blue-component boys boys-blue-component\n"
            + "Input: intensity-component boys-blue-component "
            + "boys-intensity-component\n";

    ImageHandler imageHandler = new ImageRead("res/ppm/boys.ppm");

    String expectedOutput = "Input: " + imageHandler.getImageDetails() + "boys\n" + expected;

    Reader in = new StringReader(inputCommand);
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log,
            imageHandler.getImageDetails()));
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void greyScaleWithWrongComponent() {
    Reader in = new StringReader("greyscale power-component " +
            "boys boys-value-component\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));
    assertEquals("Input: power-component boys " +
            "boys-value-component\n", log.toString());
  }

  @Test
  public void testSplitRGB() {
    Reader in = new StringReader("rgb-split boys boys-red-image " +
            "boys-green-image " + "boys-blue-image\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));
    assertEquals("Input: boys boys-red-image " +
            "boys-green-image boys-blue-image\n", log.toString());
  }

  @Test
  public void testCombineRGB() {
    Reader in = new StringReader("rgb-combine boys-combine "
            + "boys-red-image "
            + "boys-green-image " + "boys-blue-image\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));
    assertEquals("Input: boys-combine boys-red-image "
            + "boys-green-image boys-blue-image\n", log.toString());
  }

  @Test
  public void testBlur() {
    Reader in = new StringReader("blur basic basic-blur\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));

    assertEquals("Input: basic basic-blur\n", log.toString());
  }

  @Test
  public void testSharpen() {
    Reader in = new StringReader("sharpen basic basic-sharpen\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));

    assertEquals("Input: basic basic-sharpen\n", log.toString());
  }

  @Test
  public void testDithering() {
    Reader in = new StringReader("dither basic basic-dither\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));

    assertEquals("Input: basic basic-dither\n", log.toString());
  }

  @Test
  public void testSepia() {
    Reader in = new StringReader("sepia basic basic-sepia\n");
    Appendable out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    ImageDetails testImage = new ImageDetails();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log, testImage));

    assertEquals("Input: basic basic-sepia\n", log.toString());
  }


  @Test
  public void testSaveController() {
    ImageHandler imageHandler;
    try {
      imageHandler = new ImageRead("res/ppm/boys.ppm");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    Reader in = new StringReader("save res/ppm/boys.ppm boys\n");
    StringBuilder out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log,
            imageHandler.getImageDetails()));
    assertEquals("Input: boys\n", log.toString());
    assertEquals(imageHandler.getImageDetails().toString(), out.toString());
  }


  @Test
  public void testRunScript() throws IOException {
    ImageHandler imageHandler = new ImageRead("res/ppm/boys.ppm");
    String expectedOutput = "Input: " + imageHandler.getImageDetails().toString() + "boys\n"
            + "Input: 40 boys boys-brighten\n"
            + "Input: boys-brighten\n"
            + "Input: boys boys-vertical\n"
            + "Input: boys boys-horizontal\n"
            + "Input: value-component boys boys-greyscale-value\n"
            + "Input: boys-greyscale-value\n"
            + "Input: boys "
            + "boys-red boys-green boys-blue\n"
            + "Input: 50 boys-red boys-red\n"
            + "Input: boys-combined boys-red "
            + "boys-green boys-blue\n"
            + "Input: boys-combined\n"
            + "Input: boys boys-blur\n"
            + "Input: boys boys-sharpen\n"
            + "Input: boys-blur\n"
            + "Input: boys-sharpen\n";

    Reader in = new StringReader("run test/images/controllerTestImages/small-test.txt");
    StringBuilder out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log,
            imageHandler.getImageDetails()));
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testMixOps() throws IOException {
    ImageHandler imageHandler = new ImageRead("res/ppm/boys.ppm");
    String expectedOutput =
            "Input: " + imageHandler.getImageDetails().toString() + "new-boys\n"
                    + "Input: new-boys boys-horizontal\n"
                    + "Input: boys-brighten\n"
                    + "Input: boys boys-vertical\n"
                    + "Input: boys boys-horizontal\n"
                    + "Input: value-component boys boys-greyscale-value\n"
                    + "Input: boys-greyscale-value\n"
                    + "Input: boys boys-red boys-green boys-blue\n"
                    + "Input: 50 boys-red boys-red\n"
                    + "Input: boys-red boys-red-dither\n"
                    + "Input: boys-red-dither\n"
                    + "Input: " + imageHandler.getImageDetails().toString() + "boys\n"
                    + "Input: red-component boys boys-red-greyscale\n";


    String inputCommand =
            "load res/ppm/boys.ppm new-boys\n"
                    + "horizontal-flip new-boys boys-horizontal\n"
                    + "run test/images/controllerTestImages/smaller-test.txt\n"
                    + "load res/ppm/boys.ppm boys\n"
                    + "greyscale red-component boys boys-red-greyscale\n";

    Reader in = new StringReader(inputCommand);
    StringBuilder out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log,
            imageHandler.getImageDetails()));
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testMultipleSave() {
    ImageHandler imageHandler;
    try {
      imageHandler = new ImageRead("res/ppm/boys.ppm");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    Reader in = new StringReader("save res/ppm/boys.ppm buoys\n" +
            "save res/ppm/boys.ppm buoys\n");
    StringBuilder out = new StringBuilder();
    ImageController imageController = new ExtensibleImageController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerHandler(new AdvImageControllerMockModel(log,
            imageHandler.getImageDetails()));
    assertEquals("Input: buoys\n" +
            "Input: buoys\n", log.toString());
    assertEquals(imageHandler.getImageDetails().toString()
            + imageHandler.getImageDetails().toString(), out.toString());
  }
}
