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
public class ImageModelIntegratedTest {

  // These are the true images generated from gimp to test the model.
  // Basic.ppm is 3X3 with max value of 127
  // Boys.ppm is 324X432


  @Test
  public void errorInLoad() {
    List<String> errorFiles = new ArrayList<>(Arrays.asList("error-not-p3.ppm", // Image 0
            "error-negative-height.ppm", // Image 1
            "error-negative-width.ppm", //Image 2
            "error-negative-max.ppm", // Image 3
            "error-negative-green.ppm", // Image 4
            "error-max-exceeds.ppm", // Image 5
            "error-less-values.ppm", // Image 6
            "error-more-values.ppm" // Image 7
    ));
    StringBuilder errorCommand = new StringBuilder();
    for (String s : errorFiles) {
      errorCommand.append("load test/images/errorImages/").append(s).append(" ").append(s).append(
              "\n");
    }

    String expectedOutput = "Invalid PPM file: plain RAW file should begin with P3\n"
            + "Height should be positive.\n"
            + "Width should be positive.\n"
            + "Maximum value should be positive.\n"
            + "Green pixel has invalid value.\n"
            + "Blue pixel has invalid value.\n"
            + "Less values of pixels than expected.\n"
            + "More values of pixels than expected.\n";

    Reader in = new StringReader(errorCommand.toString());
    StringBuffer out = new StringBuffer();
    ImageController controller = new ExtensibleImageController(in, out);
    AdvImageModelImpl model = new AdvImageModelImpl();
    controller.imageControllerHandler(model);

    // Check all the exception generated from running command.
    assertEquals(expectedOutput, out.toString());

    // Check if any image loaded to model having error value within it or not.
    assertEquals(0, model.getImages().size());

  }

  @Test
  public void fileNotFoundForEachCommand() {
    String expected = "File at res/noBoys.ppm not found.\n";


    ArrayList<String> commands = new ArrayList<String>();
    commands.add("brighten -50 noBoys boys-darker\n");
    commands.add("vertical-flip noBoys boys-vertical\n");
    commands.add("horizontal-flip noBoys boys-horizontal\n");
    commands.add("greyscale value-component noBoys boys-greyscale-value\n");
    commands.add("greyscale luma-component noBoys boys-greyscale-luma\n");
    commands.add("greyscale intensity-component noBoys boys-greyscale-intensity\n");
    commands.add("rgb-split noBoys boys-red boys-green boys-blue\n");


    // No file to load
    Reader in = new StringReader("load res/noBoys.ppm noBoys\n");
    StringBuffer out = new StringBuffer();
    ImageController controller = new ExtensibleImageController(in, out);
    AdvImageModelImpl model = new AdvImageModelImpl();
    controller.imageControllerHandler(model);
    assertEquals(expected, out.toString());
    assertEquals(0, model.getImages().size());


    // Loading one 'boys' image and then checking with false name.
    for (String command : commands) {
      String withLoadCommand = "load res/boys.ppm boys\n" + command;
      Reader checkAllIN = new StringReader(withLoadCommand);
      StringBuffer checkAllOut = new StringBuffer();
      ExtensibleImageController controller2 = new ExtensibleImageController(checkAllIN,
              checkAllOut);
      AdvImageModelImpl model2 = new AdvImageModelImpl();
      controller2.imageControllerHandler(model2);

      assertEquals("File noBoys not found.\n", checkAllOut.toString());
      assertEquals(1, model2.getImages().size());
    }
  }

  @Test
  public void greyScaleWithNotValidComponent() {

    String command = "load res/boys.ppm boys\n" + "greyscale grey boys image-grey\n";
    Reader in = new StringReader(command);
    StringBuffer out = new StringBuffer();
    ImageController controller = new ExtensibleImageController(in, out);
    AdvImageModelImpl model = new AdvImageModelImpl();
    controller.imageControllerHandler(model);

    assertEquals(1, model.getImages().size());
    assertEquals("Enter valid component.\n", out.toString());
  }

  @Test
  public void failingTestForCombine() {
    String expected = "Given file no-red for red component not found.\n"
            + "Given file no-green for green component not found.\n"
            + "Given file no-blue for blue component not found.\n";


    ArrayList<String> commands = new ArrayList<>();
    commands.add("load res/ppm/boys.ppm boys");
    commands.add("rgb-combine combined.ppm no-red boys boys");
    commands.add("rgb-combine combined.ppm boys no-green boys");
    commands.add("rgb-combine combined.ppm boys boys no-blue");

    String errorCommands = String.join("\n", commands);

    Reader in = new StringReader(errorCommands);
    StringBuffer out = new StringBuffer();
    ImageController controller = new ExtensibleImageController(in, out);
    AdvImageModelImpl model = new AdvImageModelImpl();
    controller.imageControllerHandler(model);
    assertEquals(expected, out.toString());
  }

  @Test
  public void combineNotCompatible() {
    String expected = "Three images provided are not compatible to get merged.\n"
            + "Three images provided are not compatible to get merged.\n";

    // basic.ppm width:3 height:3 max-value:127
    // basic-diff-height.ppm width:3 height:4
    // basic-diff-max.ppm width:3 height:3 max-value:255

    ArrayList<String> commands = new ArrayList<>();
    commands.add("load res/basic.ppm basic");
    commands.add("load test/images/errorImages/basic-diff-height.ppm basic-diff-height");
    commands.add("load test/images/errorImages/basic-diff-max.ppm basic-diff-max");
    commands.add("rgb-combine combined.ppm basic basic basic-diff-height");
    commands.add("rgb-combine combined.ppm basic-diff-max basic basic");

    String errorCommands = String.join("\n", commands);

    Reader in = new StringReader(errorCommands);
    StringBuffer out = new StringBuffer();
    ImageController controller = new ExtensibleImageController(in, out);
    AdvImageModelImpl model = new AdvImageModelImpl();
    controller.imageControllerHandler(model);
    assertEquals(expected, out.toString());
  }

  @Test
  public void testingPPMForAllOperations() {

    List<String> expectedFileName = new ArrayList<>(Arrays.asList("boys-red.ppm",
            "boys-green.ppm",
            "boys-blue.ppm",
            "boys-combined.ppm",
            "boys-brighten.ppm",
            "boys-darker.ppm",
            "boys-vertical.ppm",
            "boys-horizontal.ppm",
            "boys-value.ppm",
            "boys-intensity.ppm",
            "boys-luma.ppm"
    ));

    Reader in = new StringReader("run res/ppm/boys.txt\n");

    StringBuffer out = new StringBuffer();

    ImageController imageController = new ExtensibleImageController(in, out);
    AdvImageModelImpl imageProcess = new AdvImageModelImpl();
    imageController.imageControllerHandler(imageProcess);

    assertEquals(12, imageProcess.getImages().size());

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
  public void runWithNormalCommandToCheckInputIsTakenOrNot() {

    Reader in = new StringReader("run res/ppm/boys.txt\n" +
            "load res/ppm/boys-blue.ppm boys-new-blue\n");

    StringBuffer out = new StringBuffer();

    ImageController imageController = new ExtensibleImageController(in, out);
    AdvImageModelImpl imageProcess = new AdvImageModelImpl();
    imageController.imageControllerHandler(imageProcess);

    // 12 from script as we have seen above and one more for loading the blue image again.
    assertEquals(13, imageProcess.getImages().size());

  }
}