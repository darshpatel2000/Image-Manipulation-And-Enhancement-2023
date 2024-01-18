package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ViewController;
import model.ViewModel;
import utils.Histogram;
import utils.Image;

/**
 * This class implements the main view of the application and it provides all the functionality
 * of GUI to the user.
 */
public class ImageViewImpl extends JFrame implements ImageView {
  private final ViewModel viewModel;
  private final JButton fileSaveButton;
  private final JButton blurButton;
  private final JButton splitRGBButton;
  private final JButton sharpenButton;
  private final JButton sepiaButton;
  private final JButton horizontalFlipButton;
  private final JButton verticalFlipButton;
  private final JButton fileOpenButton;
  private final JButton ditherButton;
  private final JButton brightenButton;
  private final JButton optionButton;
  private final JLabel fileOpenDisplay;
  private final JLabel fileSaveDisplay;
  JPanel[] imageDataPanel;
  private final JTextField brightenValue;
  private final JButton combineRGBButton;

  private JLabel combineFileOpenDisplayOne;
  private JLabel combineFileOpenDisplayTwo;
  private JLabel combineFileOpenDisplayThree;

  private JButton combineButton;
  private final JButton redChannel;
  private final JButton greenChannel;
  private final JButton blueChannel;
  private String currentImage = "boys";

  /**
   * Public constructor of the ImageViewImpl class which will create the view.
   * @param viewModel model object to get the read-only data from the model.
   */
  public ImageViewImpl(ViewModel viewModel) {
    super();
    this.viewModel = viewModel;
    setTitle("Swing features");
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setSize(500, 500);


    JPanel mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    //imagePanel.setMaximumSize(null);
    mainPanel.add(imagePanel);

    imageDataPanel = new JPanel[2];
    JScrollPane[] imageScrollPane = new JScrollPane[2];

    for (int i = 0; i < imageDataPanel.length; i++) {
      imageDataPanel[i] = new JPanel();
      imageScrollPane[i] = new JScrollPane(imageDataPanel[i]);
      imageScrollPane[i].setPreferredSize(new Dimension(100, 500));
      imagePanel.add(imageScrollPane[i]);
    }

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Image Operations"));
    dialogBoxesPanel.setLayout(new GridLayout(2, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);

    //Panel for load and save
    JPanel loadAndSavePanel = new JPanel();
    loadAndSavePanel.setBorder(BorderFactory.createTitledBorder("Load and Save"));
    loadAndSavePanel.setLayout(new GridLayout(2, BoxLayout.PAGE_AXIS));
    dialogBoxesPanel.add(loadAndSavePanel);

    // Basic Operations
    JPanel basicOpsPanel = new JPanel();
    basicOpsPanel.setBorder(BorderFactory.createTitledBorder("Basic Operations"));
    basicOpsPanel.setLayout(new GridLayout(2, BoxLayout.PAGE_AXIS));
    dialogBoxesPanel.add(basicOpsPanel);

    // Advanced Operations
    JPanel advancedOpsPanel = new JPanel();
    advancedOpsPanel.setBorder(BorderFactory.createTitledBorder("Advanced Operations"));
    advancedOpsPanel.setLayout(new GridLayout(3, BoxLayout.PAGE_AXIS));
    dialogBoxesPanel.add(advancedOpsPanel);

    //SplitAndCombine Panel
    JPanel splitAndCombinePanel = new JPanel();
    splitAndCombinePanel.setBorder(BorderFactory.createTitledBorder("Split and Combine Image"));
    splitAndCombinePanel.setLayout(new GridLayout(2,BoxLayout.PAGE_AXIS));
    dialogBoxesPanel.add(splitAndCombinePanel);

    //file open
    JPanel fileOpenPanel = new JPanel();
    fileOpenPanel.setLayout(new FlowLayout());
    loadAndSavePanel.add(fileOpenPanel);
    fileOpenButton = new JButton("Open a file");
    fileOpenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("");
    fileOpenPanel.add(fileOpenDisplay);


    //file save
    JPanel fileSavePanel = new JPanel();
    fileSavePanel.setLayout(new FlowLayout());
    loadAndSavePanel.add(fileSavePanel);
    fileSaveButton = new JButton("Save a file");
    fileSavePanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel("");
    fileSavePanel.add(fileSaveDisplay);

    //blur save
    JPanel blurPanel = new JPanel();
    blurPanel.setLayout(new FlowLayout());
    advancedOpsPanel.add(blurPanel);
    blurButton = new JButton("Blur");
    blurPanel.add(blurButton);

    //sharpen save
    JPanel sharpenPanel = new JPanel();
    sharpenPanel.setLayout(new FlowLayout());
    advancedOpsPanel.add(sharpenPanel);
    sharpenButton = new JButton("Sharpen");
    sharpenPanel.add(sharpenButton);

    //dither save
    JPanel ditherPanel = new JPanel();
    ditherPanel.setLayout(new FlowLayout());
    advancedOpsPanel.add(ditherPanel);
    ditherButton = new JButton("Dither");
    ditherPanel.add(ditherButton);

    //sepia save
    JPanel sepiaPanel = new JPanel();
    sepiaPanel.setLayout(new FlowLayout());
    advancedOpsPanel.add(sepiaPanel);
    sepiaButton = new JButton("Sepia");
    sepiaPanel.add(sepiaButton);

    //horizontal-flip save
    JPanel horizontalFlipPanel = new JPanel();
    horizontalFlipPanel.setLayout(new FlowLayout());
    basicOpsPanel.add(horizontalFlipPanel);
    horizontalFlipButton = new JButton("Horizontal-Flip");
    horizontalFlipPanel.add(horizontalFlipButton);

    //vertical-flip save
    JPanel verticalFlipPanel = new JPanel();
    verticalFlipPanel.setLayout(new FlowLayout());
    basicOpsPanel.add(verticalFlipPanel);
    verticalFlipButton = new JButton("Vertical-Flip");
    verticalFlipPanel.add(verticalFlipButton);


    //brighten save
    JPanel brightenPanel = new JPanel();
    brightenPanel.setLayout(new FlowLayout());
    basicOpsPanel.add(brightenPanel);
    brightenButton = new JButton("Brighten by");
    brightenButton.setActionCommand("Brighten");
    brightenValue = new JTextField(5);
    brightenValue.setSize(10, 10);
    brightenPanel.add(brightenButton);
    brightenPanel.add(brightenValue);


    //Grayscale options dialog
    JPanel optionsDialogPanel = new JPanel();
    optionsDialogPanel.setLayout(new FlowLayout());
    basicOpsPanel.add(optionsDialogPanel);

    optionButton = new JButton("GrayScale");
    optionsDialogPanel.add(optionButton);



    //split-rgb save
    JPanel splitRGBInsidePanel = new JPanel();
    splitRGBInsidePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    splitRGBButton = new JButton("Split RGB");
    splitRGBInsidePanel.add(splitRGBButton);

    redChannel = new JButton("Red Channel");
    splitRGBInsidePanel.add(redChannel);
    redChannel.setVisible(false);


    greenChannel = new JButton("Green Channel");
    splitRGBInsidePanel.add(greenChannel);
    greenChannel.setVisible(false);


    blueChannel = new JButton("Blue Channel");
    splitRGBInsidePanel.add(blueChannel);
    blueChannel.setVisible(false);

    splitAndCombinePanel.add(splitRGBInsidePanel);


    //Combine dialog
    JPanel combineDialogPanel = new JPanel();
    combineDialogPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    combineRGBButton = new JButton("Combine");
    combineDialogPanel.add(combineRGBButton);
    splitAndCombinePanel.add(combineDialogPanel);

    setVisible(true);
  }

  @Override
  public void addFeatures(ViewController features) {
    fileOpenButton.addActionListener(e -> {
      final JFileChooser fChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "JPG & GIF Images", "jpg", "gif");
      fChooser.setFileFilter(filter);
      int retValue = fChooser.showOpenDialog(ImageViewImpl.this);
      if (retValue == JFileChooser.APPROVE_OPTION) {
        File f = fChooser.getSelectedFile();
        features.loadImage(f.getAbsolutePath());
        fileOpenDisplay.setText(f.getAbsolutePath());
      }
    });
    ditherButton.addActionListener(e -> {
      features.performDither();
    });
    sepiaButton.addActionListener(e -> {
      features.performSepia();
    });
    horizontalFlipButton.addActionListener(e -> {
      features.performHorizontalFlip();
    });
    verticalFlipButton.addActionListener(e -> {
      features.performVerticalFlip();
    });
    blurButton.addActionListener(e -> {
      features.performBlur();
    });
    sharpenButton.addActionListener(e -> {
      features.performSharpening();
    });
    fileSaveButton.addActionListener(e -> {
      final JFileChooser fChooser = new JFileChooser(".");
      int retValue = fChooser.showSaveDialog(ImageViewImpl.this);
      if (retValue == JFileChooser.APPROVE_OPTION) {
        File f = fChooser.getSelectedFile();
        fileSaveDisplay.setText(f.getAbsolutePath());
        features.saveImage(f.getAbsolutePath());
        fileSaveDisplay.setText("");
      }
    });
    brightenButton.addActionListener(e -> {
      try {
        int value = Integer.parseInt(brightenValue.getText());
        features.performBrighten(value);
      } catch (NumberFormatException ex) {
        showError("Input value is not an integer");
      }
    });

    optionButton.addActionListener(e -> {
      String[] options = {"red-component", "green-component", "blue-component", "value" +
              "-component", "luma-component", "intensity-component"};
      int retValue = JOptionPane.showOptionDialog(ImageViewImpl.this, "Please choose the " +
                      "component", "Components", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE,
              null, options, options[4]);
      features.performGreyScale(options[retValue]);
    });

    combineRGBButton.addActionListener(e -> {
      // Create the pop-up dialog
      JDialog dialog = new JDialog();
      dialog.setTitle("Load Images");
      dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      dialog.setSize(500, 150);
      dialog.setLocationRelativeTo(null);

      // Create the load buttons
      JButton loadButton1 = new JButton("Load Image 1");
      JButton loadButton2 = new JButton("Load Image 2");
      JButton loadButton3 = new JButton("Load Image 3");
      combineButton = new JButton("Combine");
      combineButton.setEnabled(false);

      combineFileOpenDisplayOne = new JLabel("Image 1 path will be displayed here");
      combineFileOpenDisplayTwo = new JLabel("Image 2 path will be displayed here");
      combineFileOpenDisplayThree = new JLabel("Image 3 path will be displayed here");

      AtomicInteger numberOfImagesLoaded = new AtomicInteger(0);

      loadButtonComputation(loadButton1, combineFileOpenDisplayOne, numberOfImagesLoaded);
      loadButtonComputation(loadButton2, combineFileOpenDisplayTwo, numberOfImagesLoaded);
      loadButtonComputation(loadButton3, combineFileOpenDisplayThree, numberOfImagesLoaded);

      combineButton.addActionListener(e14 -> {
        features.combineImage(combineFileOpenDisplayOne.getText(), combineFileOpenDisplayTwo.getText(),
                combineFileOpenDisplayThree.getText());
        dialog.dispose();
      });


      // Create the panel for the load buttons
      JPanel buttonPanel = new JPanel(new GridLayout(4, 2));
      buttonPanel.add(loadButton1);
      buttonPanel.add(combineFileOpenDisplayOne);

      buttonPanel.add(loadButton2);
      buttonPanel.add(combineFileOpenDisplayTwo);

      buttonPanel.add(loadButton3);
      buttonPanel.add(combineFileOpenDisplayThree);

      JPanel onlyCombineButtonPanel = new JPanel(new GridLayout(1, 1));
      onlyCombineButtonPanel.add(combineButton);

      // Create a container to hold the panels
      Container container = dialog.getContentPane();

      // Set the layout for the container
      container.setLayout(new BorderLayout());

      // Add the panels to the container
      container.add(buttonPanel, BorderLayout.CENTER);
      container.add(onlyCombineButtonPanel, BorderLayout.SOUTH);

      // Display the dialog
      dialog.setVisible(true);
    });

    splitRGBButton.addActionListener(e -> {
      features.splitImage();
      redChannel.setVisible(true);
      greenChannel.setVisible(true);
      blueChannel.setVisible(true);

      redChannel.addActionListener(eR -> {
        showImageData("boys-split-red");
        features.setCurrentImageName("boys-split-red");
      });
      greenChannel.addActionListener(eG -> {
        showImageData("boys-split-green");
        features.setCurrentImageName("boys-split-green");
      });
      blueChannel.addActionListener(eB -> {
        showImageData("boys-split-blue");
        features.setCurrentImageName("boys-split-blue");
      });

    });

  }

  @Override
  public void showImageData(String imagePath) {
    Image showImage = viewModel.getImages().get(imagePath);
    long[][] histogramData = createHistogram(showImage);
    int width = showImage.getWidth();
    int height = showImage.getHeight();
    int[][][] imagePixels = showImage.getPixels();

    // Create a BufferedImage from the image data
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = imagePixels[i][j][0];
        int green = imagePixels[i][j][1];
        int blue = imagePixels[i][j][2];
        Color rgb = new Color(red, green, blue);
        bufferedImage.setRGB(j, i, rgb.getRGB());
      }
    }
    imageDataPanel[0].removeAll();
    imageDataPanel[1].removeAll();
    imageDataPanel[0].add(new JLabel(new ImageIcon(bufferedImage)));
    imageDataPanel[1].add((new Histogram(histogramData)));
  }

  private long[][] createHistogram(Image showImage) {
    int[][][] imagePixels = showImage.getPixels();
    long[][] histogram = new long[4][showImage.getMaxValue()+1];
    for (int i = 0; i < 4; i++) {
      Arrays.fill(histogram[i], 0);
    }
    for (int i = 0; i < showImage.getHeight(); i++) {
      for (int j = 0; j < showImage.getWidth(); j++) {
        int red = imagePixels[i][j][0];
        int green = imagePixels[i][j][1];
        int blue = imagePixels[i][j][2];
        histogram[0][red] += 1;
        histogram[1][green] += 1;
        histogram[2][blue] += 1;
        int intensity = (red + blue + green) / 3;
        histogram[3][intensity] += 1;
      }
    }
    return histogram;
  }

  @Override
  public void showError(String errorFound) {
    JOptionPane.showMessageDialog(
            ImageViewImpl.this, errorFound, "Message",
            JOptionPane.PLAIN_MESSAGE);
  }
  private void loadButtonComputation(JButton button, JLabel label, AtomicInteger imageCount){
    button.addActionListener(e -> {
      final JFileChooser fChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "JPG & GIF Images", "jpg", "gif");
      fChooser.setFileFilter(filter);
      int retValue = fChooser.showOpenDialog(ImageViewImpl.this);
      if (retValue == JFileChooser.APPROVE_OPTION) {
        File f = fChooser.getSelectedFile();
        label.setText(f.getAbsolutePath());
        try {
          BufferedImage image = ImageIO.read(f);
          // Process the image as desired
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        // Increment the image counter
        int count = imageCount.incrementAndGet();
        // If all three images have been loaded, enable the combine button
        if (count == 3) {
          combineButton.setEnabled(true);
        }
      }
    });
  }
}