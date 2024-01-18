## Script of commands

<hr>

- Run the script file in command-line
    - open cmd inside res folder where jar file is located.
    - Run the `java -jar assignment6.jar -file runForAll.txt`
    - When invoked in this manner, it will generate 60 images, 15 operations for each 4 types of images.

- Run the commands as text interactive mode
    - open cmd inside res folder where jar file is located.
    - Run the `java -jar assignment6.jar -text`
    - When invoked in this manner, it will open in an interactive text mode, allowing the user to type the script and execute it one line at a time.


- Run just the jar file.
    - Open cmd inside res folder where jar file is located.
    - Run the `java -jar assignment6.jar`
    - When invoked in this manner, it will open and interactive GUI to work with images.

- Run the script file in Project
    - Go-To `src`
    - Open Class `Main.java`
    - Run the `public static void main` method
    - It will open the GUI and let you work with images and its operations.

<br>

- <b> Interacting GUI </b><br><br>

    - To load an image, click on the `Open a file` button and select the file from folder.
    - To save an image, click on the `Save a file` button and give name to file as you want to save.
    - To perform brighten or darker on image, enter integer value in the textbox and then click on the `Brighten by` button.
    - To perform horizontal-flip, click on the `Horizontal-Flip` button.
    - To perform vertical-flip, click on the `Vertical-Flip` button.
    - To perform grayscale, click on the `Click to check components` button, and then click on the Grayscale component you want.
    - To perform split of an image, click on the `Split RGB` button and click on any component of split you want to see.
    - To perform combine, click on the `Combine` button, and it will open a dialogue. Load three images and click on the combine.
    - To perform blur on the image, click on the `Blur` button.
    - To perform sepia tone on the image, click on the `Sepia` button.
    - To perform sharpening on the image, click on the `Sharpen` button.
    - To perform dither on the image, click on the `Dither` button.

<br>
- <b>Run the following commands in the following sequence. </b>
<br><br>

- File 'combinedOperations.txt' inside the res folder contains the same commands.
    - Command to `load` the file `boys.ppm`, `boys.png`, `boys.jpg`, `boys.bmp`.
      ```
      load ppm/boys.ppm boys-ppm
      load png/boys.png boys-png
      load jpg/boys.jpg boys-jpg
      load bmp/boys.bmp boys-bmp
      ```

    - command to `brightness` the file `boys.ppm`. And save the file as `bmp` at provided
      location.
      ```
      brighten 50 boys-ppm boys-brighter
      save bmp/boys-brighter-bmp.bmp boys-brighter
      
      brighten -50 boys-ppm boys-darken
      save bmp/boys-darken-bmp.bmp boys-darken
      ```

    - command to `rgb-split` the file `boys.ppm`. And save the file as different format at provided
      location.
      ```
      rgb-split boys-ppm boys-red boys-green boys-blue
      save png/boys-green-split-png.png boys-green
      save ppm/boys-red-split-ppm.ppm boys-red
      save jpg/boys-blue-split-jpg.jpg boys-blue  

      ```

    - command to `rgb-combine` the three red, green, and blue image from different types of images
      as
      name `boys-red`, `boys-green`, and `boys-blue`. And save the file as `jpg` at provided
      location.
      ```
      load png/boys-green-split-png.png boys-green
      load ppm/boys-red-split-ppm.ppm boys-red
      load jpg/boys-blue-split-jpg.jpg boys-blue
      rgb-combine boys-combined boys-red boys-green boys-blue
      save jpg/boys-combined-jpg.jpg boys-combined
      ```

    - command to `greyscale` the file `boys.png` and `boys.jpg` into all component. And save
      the file as `ppm`, `png`, `jpg`, and `bmp` at provided location.
      ```
      greyscale value-component boys-png boys-value-greyscale
      save ppm/boys-value-greyscale-ppm.ppm boys-value-greyscale
      
      greyscale luma-component boys-png boys-luma-greyscale
      save jpg/boys-luma-greyscale-jpg.jpg boys-luma-greyscale
      
      greyscale intensity-component boys-png boys-intensity-greyscale
      save bmp/boys-intensity-greyscale-bmp.bmp boys-intensity-greyscale
      
      greyscale red-component boys-jpg boys-red-greyscale
      save ppm/boys-red-greyscale-ppm.ppm boys-red-greyscale
      
      greyscale green-component boys-jpg boys-green-greyscale
      save png/boys-green-greyscale-png.png boys-green-greyscale
      
      greyscale blue-component boys-jpg boys-blue-greyscale
      save bmp/boys-blue-greyscale-bmp.bmp boys-blue-greyscale
      ```

    - command to `flip` the file `boys.bmp`. And save the file as `ppm`, and `png` at
      provided location.
      ```
      horizontal-flip boys-bmp boys-horizontal
      save ppm/boys-horizontal-ppm.ppm boys-horizontal
      
      vertical-flip boys-horizontal boys-horizontal-vertical
      save png/boys-horizontal-vertical-png.png boys-horizontal-vertical
      
      ```

    - command to `blur` the file `boys.ppm`. And save the file as `ppm` provided location.
      ```
      blur boys-ppm boys-blur
      save ppm/boys-blur-ppm.ppm boys-blur
      ```

    - command to `sharpen` the file `boys.png`. And save the file as `png` provided location.
      ```
      sharpen boys-png boys-sharpen
      save png/boys-sharpen-png.png boys-sharpen
      ```
    - command to `dither` the file `boys.bmp`. And save the file as `bmp` provided location.
      ```
      dither boys-bmp boys-dither
      save bmp/boys-dither-bpm.bmp boys-dither
      ```

    - command to color transformations into `greyscale` the file `boys.jpg`. And save the file
      as `jpg` provided location.
      ```
      greyscale luma-component boys-png boys-luma-greyscale
      save jpg/boys-luma-greyscale-jpg.jpg boys-luma-greyscale
      ```

    - command to color transformations into `sepia-tone` the file `boys.jpg`. And save the file
      as `jpg` provided location.
      ```
      sepia boys-jpg boys-sepia
      save jpg/boys-sepia-jpg.jpg boys-sepia
      ```
