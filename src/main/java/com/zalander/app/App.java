package com.zalander.app;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.Frame;

public class App {
  public static void main(String[] args) {
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
  // try {
    player.main(args);
  // } catch(Exception e) {
  //   e.printStackTrace();
  // }
  // for (int i = 0; i < 6; i++) {
  //   System.out.println(((double)(i) / 5) * 255 + 50);
  // }
  }
}