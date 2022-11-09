package com.zalander.app;

// import org.bytedeco.javacv.FFmpegFrameGrabber;
// import org.bytedeco.javacv.Java2DFrameConverter;
// import org.bytedeco.javacv.Frame;

public class Main {
  public static void main(String[] args) {
    String filename = "";
    if (args.length == 0) {
      System.out.println("Arguments: [file] [width(opt)] [height(opt)]");
      System.exit(1);
    } else {
      if (args.length >= 1) {
        filename = args[0];
      } 
      if (args.length >= 2) {
        try {
          Constants.videoWidth = Integer.parseInt(args[1]);
        } catch(NumberFormatException e) {
          System.out.println("Invalid argument [width]");
        }
      }
      if (args.length == 3) {
        try {
          Constants.videoHeight = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
          System.out.println("Invalid argument [height]");
        }
      }
    }
  //   FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("./test.mp4");
  //   ImageConverter imageConverter = new ImageConverter();
  //   try {
  //     grabber.start();
  //   } catch (Exception e) {
  //     e.printStackTrace();
  //     // TODO: handle exception
  //   }
    
  //   Java2DFrameConverter converter = new Java2DFrameConverter();
    
  //   for (int i = 0; i < 1; i++) {
  //     try {
  //       System.out.println("running");
  //       Frame frame = grabber.grab(); 
  //       BufferedImage image = converter.convert(frame);
  //       System.out.println("done");
  //       System.out.println(imageConverter.convert(image));
  //       } catch (Exception e) {
  //           e.printStackTrace();
  //           // TODO: handle exception
  //       } 
  //   }
  //   try {
  //       grabber.stop();
  //       grabber.close();
  //       converter.close();
  //   } catch (Exception e) {
  //       e.printStackTrace();
  //   }
  Player player = new Player();
  // player.launch("");
  try {
      player.start(filename);
  } catch(Exception e) {
    e.printStackTrace();
  }
  // for (int i = 0; i < 6; i++) {
  //   System.out.println(((double)(i) / 5) * 255 + 50);
  // }
  }
}