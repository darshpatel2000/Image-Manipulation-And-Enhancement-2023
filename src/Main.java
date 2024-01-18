import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import controller.ExtensibleImageController;
import controller.ImageController;
import controller.ViewController;
import controller.ViewControllerImpl;
import model.AdvImageModel;
import model.AdvImageModelImpl;
import model.ViewModel;
import model.ViewModelImpl;
import view.ImageView;
import view.ImageViewImpl;

import static java.lang.System.exit;

/**
 * Main class where the program will begin to execute.
 */
public class Main {

  /**
   * Main method of the program from where all the execution will begin.
   *
   * @param args Arguments passed when compiling the program.
   */
  public static void main(String[] args) {
    AdvImageModel model = new AdvImageModelImpl();
    ImageController controller = null;

    if (args.length != 0) {
      if (args[0].equals("-file")) {
        InputStream in = null;
        try {
          in = new FileInputStream(args[1]);
        } catch (Exception e) {
          System.out.println(e.getMessage());
          exit(0);
        }
        controller = new ExtensibleImageController(new InputStreamReader(in),
                new StringBuffer());
      }
      else if (args[0].equals("-text")) {
        controller = new ExtensibleImageController(new InputStreamReader(System.in),
                new StringBuffer());
      }
      else {
        exit(1234);
      }
      controller.imageControllerHandler(model);
    } else {
      ViewModel viewModel = new ViewModelImpl(model);
      ViewController ctrl = new ViewControllerImpl(model);
      ImageView view = new ImageViewImpl(viewModel);
      ctrl.setView(view);
    }
  }
}
