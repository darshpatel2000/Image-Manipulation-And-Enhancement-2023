package integratedtests;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.ExtensibleImageController;
import controller.ImageController;
import model.AdvImageModelImpl;
import utils.ImageRead;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This is an integrated test class which tests the controller and model together.
 */
public class AdvImageModelIntegratedTest {

  @Test
  public void newOperationsOnPPMImage() {

    List<String> expectedFileName = new ArrayList<>(Arrays.asList("boys-blur.ppm",
            "boys-sharpen.ppm",
            "boys-sepia.ppm",
            "boys-dither.ppm"
    ));

    Reader in = new StringReader("run res/ppm/boys-new.txt\n");

    StringBuffer out = new StringBuffer();

    ImageController imageController = new ExtensibleImageController(in, out);
    AdvImageModelImpl imageProcess = new AdvImageModelImpl();
    imageController.imageControllerHandler(imageProcess);

    assertEquals(5, imageProcess.getImages().size());

    try {
      for (int i = 0; i < expectedFileName.size(); i++) {
        String outputImagePath = "res/ppm/" + expectedFileName.get(i);
        String expectedImagePath = "test/images/ppm/" + expectedFileName.get(i);
        assertEquals(new ImageRead(outputImagePath).getImageDetails(),
                new ImageRead(expectedImagePath).getImageDetails()
        );
      }
    } catch (Exception e) {
      fail("Specified path has no file.");
    }
  }

  @Test
  public void testingJPGForAllOperations() {

    List<String> expectedFileName = new ArrayList<>(Arrays.asList("boys-red.jpg",
            "boys-green.jpg",
            "boys-blue.jpg",
            "boys-combined.jpg",
            "boys-brighten.jpg",
            "boys-darker.jpg",
            "boys-vertical.jpg",
            "boys-horizontal.jpg",
            "boys-value.jpg",
            "boys-intensity.jpg",
            "boys-luma.jpg",
            "boys-blur.jpg",
            "boys-sharpen.jpg",
            "boys-sepia.jpg",
            "boys-dither.jpg"
    ));

    Reader in = new StringReader("run res/jpg/boys.txt\n");

    StringBuffer out = new StringBuffer();

    ImageController imageController = new ExtensibleImageController(in, out);
    AdvImageModelImpl imageProcess = new AdvImageModelImpl();
    imageController.imageControllerHandler(imageProcess);

    assertEquals(16, imageProcess.getImages().size());

    try {
      for (int i = 0; i < expectedFileName.size(); i++) {
        String outputImagePath = "res/jpg/" + expectedFileName.get(i);
        String expectedImagePath = "test/images/jpg/" + expectedFileName.get(i);
        assertEquals(new ImageRead(outputImagePath).getImageDetails(),
                new ImageRead(expectedImagePath).getImageDetails()
        );
      }
    } catch (Exception e) {
      fail("Specified path has no file.");
    }
  }

  @Test
  public void testingBMPForAllOperations() {

    List<String> expectedFileName = new ArrayList<>(Arrays.asList("boys-red.bmp",
            "boys-green.bmp",
            "boys-blue.bmp",
            "boys-combined.bmp",
            "boys-brighten.bmp",
            "boys-darker.bmp",
            "boys-vertical.bmp",
            "boys-horizontal.bmp",
            "boys-value.bmp",
            "boys-intensity.bmp",
            "boys-luma.bmp",
            "boys-blur.bmp",
            "boys-sharpen.bmp",
            "boys-sepia.bmp",
            "boys-dither.bmp"
    ));

    Reader in = new StringReader("run res/bmp/boys.txt\n");

    StringBuffer out = new StringBuffer();

    ImageController imageController = new ExtensibleImageController(in, out);
    AdvImageModelImpl imageProcess = new AdvImageModelImpl();
    imageController.imageControllerHandler(imageProcess);

    assertEquals(16, imageProcess.getImages().size());

    try {
      for (int i = 0; i < expectedFileName.size(); i++) {
        String outputImagePath = "res/bmp/" + expectedFileName.get(i);
        String expectedImagePath = "test/images/bmp/" + expectedFileName.get(i);
        assertEquals(new ImageRead(outputImagePath).getImageDetails(),
                new ImageRead(expectedImagePath).getImageDetails()
        );
      }
    } catch (Exception e) {
      fail("Specified path has no file.");
    }
  }

  @Test
  public void testingPNGForAllOperations() {

    List<String> expectedFileName = new ArrayList<>(Arrays.asList("boys-red.png",
            "boys-green.png",
            "boys-blue.png",
            "boys-combined.png",
            "boys-brighten.png",
            "boys-darker.png",
            "boys-vertical.png",
            "boys-horizontal.png",
            "boys-value.png",
            "boys-intensity.png",
            "boys-luma.png",
            "boys-blur.png",
            "boys-sharpen.png",
            "boys-sepia.png",
            "boys-dither.png"
    ));

    Reader in = new StringReader("run res/png/boys.txt\n");

    StringBuffer out = new StringBuffer();

    ImageController imageController = new ExtensibleImageController(in, out);
    AdvImageModelImpl imageProcess = new AdvImageModelImpl();
    imageController.imageControllerHandler(imageProcess);

    assertEquals(16, imageProcess.getImages().size());

    try {
      for (int i = 0; i < expectedFileName.size(); i++) {
        String outputImagePath = "res/png/" + expectedFileName.get(i);
        String expectedImagePath = "test/images/png/" + expectedFileName.get(i);
        assertEquals(new ImageRead(outputImagePath).getImageDetails(),
                new ImageRead(expectedImagePath).getImageDetails()
        );
      }
    } catch (Exception e) {
      fail("Specified path has no file.");
    }
  }

  @Test
  public void combinedTests() {

    List<String> expectedFileName = new ArrayList<>(List.of("small-blur-png.png",
            "small-blur-jpg.jpg",
            "small-double-blur-bmp.bmp",
            "darker-sepia.ppm"
    ));

    Reader in = new StringReader("load res/random/small.ppm small\n" +
            "blur small small-blur-png\n" +
            "blur small small-blur-jpg\n" +
            "save res/random/small-blur-png.png small-blur-png\n" +
            "save res/random/small-blur-jpg.jpg small-blur-jpg\n" +
            "blur small-blur-png small-double-blur-bmp\n" +
            "save res/random/small-double-blur-bmp.bmp small-double-blur-bmp\n" +
            "load res/random/small-double-blur-bmp.bmp small-new\n" +
            "brighten -42 small-new small-darker\n" +
            "sepia small-darker darker-sepia\n" +
            "save res/random/darker-sepia.ppm darker-sepia");

    StringBuffer out = new StringBuffer();

    ImageController imageController = new ExtensibleImageController(in, out);
    AdvImageModelImpl imageProcess = new AdvImageModelImpl();
    imageController.imageControllerHandler(imageProcess);

    assertEquals(7, imageProcess.getImages().size());

    try {
      for (int i = 0; i < expectedFileName.size(); i++) {
        String outputImagePath = "res/random/" + expectedFileName.get(i);
        String expectedImagePath = "test/images/random/" + expectedFileName.get(i);
        assertEquals(new ImageRead(outputImagePath).getImageDetails(),
                new ImageRead(expectedImagePath).getImageDetails()
        );
      }
    } catch (Exception e) {
      fail("Specified path has no file.");
    }
  }

  @Test
  public void testingDitherForMultipleTimes() {


    Reader in = new StringReader("load res/random/small.ppm small\n" +
            "dither small dither1\n" +
            "dither dither1 dither2\n" +
            "dither dither2 dither3\n" +
            "dither dither3 dither4\n" +
            "dither dither4 dither5\n" +
            "save res/random/dither1.ppm dither1\n" +
            "save res/random/dither5.ppm dither5\n"
    );

    StringBuffer out = new StringBuffer();

    ImageController imageController = new ExtensibleImageController(in, out);
    AdvImageModelImpl imageProcess = new AdvImageModelImpl();
    imageController.imageControllerHandler(imageProcess);

    assertEquals(6, imageProcess.getImages().size());

    try {

      assertEquals(new ImageRead("res/random/dither1.ppm").getImageDetails(),
              new ImageRead("res/random/dither5.ppm").getImageDetails()
      );

    } catch (Exception e) {
      fail("Specified path has no file.");
    }
  }

  @Test
  public void testWrittenToCheckAllTypeOfImagesWithAllTypeOfCommands() {

    List<String> expectedFileName = new ArrayList<>(List.of(
            "bmp/boys-brighter-bmp.bmp",
            "bmp/boys-darken-bmp.bmp",
            "png/boys-green-split-png.png",
            "ppm/boys-red-split-ppm.ppm",
            "jpg/boys-blue-split-jpg.jpg",
            "jpg/boys-combined-jpg.jpg",
            "ppm/boys-value-greyscale-ppm.ppm",
            "jpg/boys-luma-greyscale-jpg.jpg",
            "bmp/boys-intensity-greyscale-bmp.bmp",
            "ppm/boys-red-greyscale-ppm.ppm",
            "png/boys-green-greyscale-png.png",
            "bmp/boys-blue-greyscale-bmp.bmp",
            "ppm/boys-horizontal-ppm.ppm",
            "png/boys-horizontal-vertical-png.png",
            "ppm/boys-blur-ppm.ppm",
            "png/boys-sharpen-png.png",
            "jpg/boys-luma-greyscale-jpg.jpg",
            "jpg/boys-sepia-jpg.jpg",
            "bmp/boys-dither-bpm.bmp"
    ));


    Reader in = new StringReader("load res/ppm/boys.ppm boys-ppm\n" +
            "load res/png/boys.png boys-png\n" +
            "load res/jpg/boys.jpg boys-jpg\n" +
            "load res/bmp/boys.bmp boys-bmp\n" +
            "brighten 50 boys-ppm boys-brighter\n" +
            "save res/bmp/boys-brighter-bmp.bmp boys-brighter\n" +
            "brighten -50 boys-ppm boys-darken\n" +
            "save res/bmp/boys-darken-bmp.bmp boys-darken\n" +
            "rgb-split boys-ppm boys-red boys-green boys-blue\n" +
            "save res/png/boys-green-split-png.png boys-green\n" +
            "save res/ppm/boys-red-split-ppm.ppm boys-red\n" +
            "save res/jpg/boys-blue-split-jpg.jpg boys-blue\n" +
            "load res/png/boys-green-split-png.png boys-green\n" +
            "load res/ppm/boys-red-split-ppm.ppm boys-red\n" +
            "load res/jpg/boys-blue-split-jpg.jpg boys-blue\n" +
            "rgb-combine boys-combined boys-red boys-green boys-blue\n" +
            "save res/jpg/boys-combined-jpg.jpg boys-combined\n" +
            "greyscale value-component boys-png boys-value-greyscale\n" +
            "save res/ppm/boys-value-greyscale-ppm.ppm boys-value-greyscale\n" +
            "greyscale luma-component boys-png boys-luma-greyscale\n" +
            "save res/jpg/boys-luma-greyscale-jpg.jpg boys-luma-greyscale\n" +
            "greyscale intensity-component boys-png boys-intensity-greyscale\n" +
            "save res/bmp/boys-intensity-greyscale-bmp.bmp boys-intensity-greyscale\n" +
            "greyscale red-component boys-jpg boys-red-greyscale\n" +
            "save res/ppm/boys-red-greyscale-ppm.ppm boys-red-greyscale\n" +
            "greyscale green-component boys-jpg boys-green-greyscale\n" +
            "save res/png/boys-green-greyscale-png.png boys-green-greyscale\n" +
            "greyscale blue-component boys-jpg boys-blue-greyscale\n" +
            "save res/bmp/boys-blue-greyscale-bmp.bmp boys-blue-greyscale\n" +
            "horizontal-flip boys-bmp boys-horizontal\n" +
            "save res/ppm/boys-horizontal-ppm.ppm boys-horizontal\n" +
            "vertical-flip boys-horizontal boys-horizontal-vertical\n" +
            "save res/png/boys-horizontal-vertical-png.png boys-horizontal-vertical\n" +
            "blur boys-ppm boys-blur\n" +
            "save res/ppm/boys-blur-ppm.ppm boys-blur\n" +
            "sharpen boys-png boys-sharpen\n" +
            "save res/png/boys-sharpen-png.png boys-sharpen\n" +
            "greyscale luma-component boys-png boys-luma-greyscale\n" +
            "save res/jpg/boys-luma-greyscale-jpg.jpg boys-luma-greyscale\n" +
            "sepia boys-jpg boys-sepia\n" +
            "save res/jpg/boys-sepia-jpg.jpg boys-sepia\n" +
            "dither boys-bmp boys-dither\n" +
            "save res/bmp/boys-dither-bpm.bmp boys-dither\n"
    );

    StringBuffer out = new StringBuffer();

    ImageController imageController = new ExtensibleImageController(in, out);
    AdvImageModelImpl imageProcess = new AdvImageModelImpl();
    imageController.imageControllerHandler(imageProcess);

    try {
      for (int i = 0; i < expectedFileName.size(); i++) {
        String outputImagePath = "res/" + expectedFileName.get(i);
        String expectedImagePath = "test/" + expectedFileName.get(i);
        assertEquals(new ImageRead(outputImagePath).getImageDetails(),
                new ImageRead(expectedImagePath).getImageDetails()
        );
      }
    } catch (Exception e) {
      fail("Specified path has no file.");
    }
  }
}
