package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.Dither;
import controller.commands.Greyscale;
import controller.commands.HorizontalFlip;
import controller.commands.ImageOperationCommand;
import controller.commands.Load;
import controller.commands.RgbCombine;
import controller.commands.RgbSplit;
import controller.commands.Run;
import controller.commands.Save;
import controller.commands.SepiaTone;
import controller.commands.Sharpen;
import controller.commands.VerticalFlip;
import model.AdvImageModel;

/**
 * This class named "ExtensibleImageController" implements ImageController interface. It uses
 * various, other classes and interfaces to control the execution of image processing commands.
 */
public class ExtensibleImageController implements ImageController {

  protected Readable in;
  protected Appendable out;

  /**
   * Creates an ExtensibleImageController object with the given Scanner and Appendable objects.
   *
   * @param in  The Scanner object is used for input.
   * @param out the Appendable object is used for output.
   */
  public ExtensibleImageController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }
  
  @Override
  public void imageControllerHandler(AdvImageModel model) {
    Scanner sc = new Scanner(this.in);
    Stack<ImageOperationCommand> commands = new Stack<>();

    Map<String, Function<Scanner, ImageOperationCommand>> knownCommands = new HashMap<>();

    knownCommands.put("load", s -> new Load(s.next(), s.next()));
    knownCommands.put("save", s -> new Save(s.next(), s.next(), this.out));
    knownCommands.put("greyscale", s -> new Greyscale(s.next(), s.next(), s.next()));
    knownCommands.put("horizontal-flip", s -> new HorizontalFlip(s.next(), s.next()));
    knownCommands.put("vertical-flip", s -> new VerticalFlip(s.next(), s.next()));
    knownCommands.put("brighten", s -> new Brighten(s.next(), s.next(), s.next()));
    knownCommands.put("rgb-split", s -> new RgbSplit(s.next(), s.next(), s.next(), s.next()));
    knownCommands.put("rgb-combine", s -> new RgbCombine(s.next(), s.next(), s.next(), s.next()));
    knownCommands.put("run", s -> new Run(s.next(), this.out));
    knownCommands.put("blur", s -> new Blur(s.next(), s.next()));
    knownCommands.put("sharpen", s -> new Sharpen(s.next(), s.next()));
    knownCommands.put("dither", s -> new Dither(s.next(), s.next()));
    knownCommands.put("sepia", s -> new SepiaTone(s.next(), s.next()));


    while (sc.hasNext()) {

      try {
        ImageOperationCommand c;
        String commandName = sc.next();
        if (commandName.equalsIgnoreCase("q") || commandName.equalsIgnoreCase("quit")) {
          return;
        }

        Function<Scanner, ImageOperationCommand> cmd = knownCommands.getOrDefault(commandName,
                null);
        if (cmd == null) {
          throw new IllegalArgumentException();
        } else {
          c = cmd.apply(sc);
          commands.add(c);
          c.executeCommand(model);
        }
      } catch (IOException | IllegalArgumentException e) {
        try {
          this.out.append(e.getMessage()).append("\n");
        } catch (IOException ex) {
          throw new RuntimeException();
        }
      }
    }
  }
}