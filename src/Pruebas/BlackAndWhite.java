package Pruebas;

import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;

public class BlackAndWhite
{
          public static void main(String asr[])
          {
                   try
                   {
                             //colored image path
                             BufferedImage image = ImageIO.read(new  
                                                File("C:\\output.jpg"));

                             //getting width and height of image
                             double image_width = image.getWidth();
                             double image_height = image.getHeight();

                             BufferedImage bimg = null;
                             BufferedImage img = image;

                             //drawing a new image      
                             bimg = new BufferedImage((int)image_width, (int)image_height,
                                                                    BufferedImage.TYPE_BYTE_GRAY);
                             
                             
                             
                             Graphics2D gg = bimg.createGraphics();
                             gg.drawImage(img, 0, 0, img.getWidth(null), img.getHeight(null), null);

                             //saving black and white image onto drive
                             String temp = "blackAndwhite.jpeg";
                             File fi = new File("D:\\My Program\\" + temp);
                             ImageIO.write(bimg, "jpg", fi);
                   }
                   catch (Exception e)
                   {
                             System.out.println(e);
                   }
          }
}