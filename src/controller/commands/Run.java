package controller.commands;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import controller.ExtensibleImageController;
import controller.ImageController;
import model.AdvImageModel;

/**
 * This command will run the given script of commands.
 */
public class Run implements ImageOperationCommand {

  private final String fileName;
  private final Appendable out;

  /**
   * Public constructor for run class which takes input of fileName.
   *
   * @param fileName the filename which has commands to run.
   * @param out      Object of appendable class which is going to remain same while running script.
   */
  public Run(String fileName, Appendable out) {

    this.fileName = fileName;
    this.out = out;
  }

  @Override
  public void executeCommand(AdvImageModel model) throws IOException {
    InputStream in = null;
    try {
      in = new FileInputStream(this.fileName);
    } catch (Exception e) {
      throw new IOException("Script not found to run.");
    }
    ImageController ioc = new ExtensibleImageController(new InputStreamReader(in),
            this.out);
    ioc.imageControllerHandler(model);
  }
}
