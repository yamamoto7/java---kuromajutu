import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.lang.Math;

public class MakeCribo{
  public static void main(String[] args){
    try{
      BufferedImage src = ImageIO.read(new File(args[0]));

      int color=0, r=0, g=0, b=0, a=0;
      final int start_x = Integer.parseInt(args[1]);
      final int start_y = Integer.parseInt(args[2]);
      final int padding_x = Integer.parseInt(args[3]);
      final int padding_y = Integer.parseInt(args[4]);
      final int content_width = Integer.parseInt(args[5]);
      final int content_height = Integer.parseInt(args[6]);
      final int repeat_x = Integer.parseInt(args[7]);
      final int repeat_y = Integer.parseInt(args[8]);
      final int repeat_width = Integer.parseInt(args[9]);
      final int repeat_height = Integer.parseInt(args[10]);

      // final int start_x = 0;
      // final int start_y = 0;
      // final int padding_x = 14;
      // final int padding_y = 4;
      // final int content_width = 96;
      // final int content_height = 96;
      // final int repeat_x = 8;
      // final int repeat_y = 2;
      // final int repeat_width = 121;
      // final int repeat_height = 107;


      BufferedImage editableImage  = new BufferedImage(content_width, content_height * (repeat_x * repeat_y), BufferedImage.TYPE_INT_ARGB);
      // System.out.println(height + "a" + width);

      for(int y = 0; y < repeat_y; y++){
        for(int x = 0; x < repeat_x; x++){
          System.out.println("x:" + (x*repeat_width + padding_x) + "y:" + (y*repeat_height + padding_y));
          for(int j = 0; j < content_height; j++){
            for(int i = 0; i < content_width; i++){
              color = src.getRGB((x*repeat_width + padding_x + i), (y*repeat_height + padding_y + j));
              b = color>>0&0xff;
              g = color>>8&0xff;
              r = color>>16&0xff;
              a = color>>24&0xff;
              System.out.println("r" + r + "g" + g + "b" + b);
              // if(a==0){
              if(r==147 && g==187 && b==236){
                editableImage.setRGB(i, (repeat_x*y + x)* content_height + j, ( 0 << 24 | 0 | 255 << 8 | 0 << 16));
                // editableImage.setRGB(j, i, (0xff000000 | y | y << 8 | y << 16));
              } else {
                editableImage.setRGB(i, (repeat_x*y + x)* content_height + j, (0xff000000 | b | g << 8 | r << 16));
              }
              // System.out.println((repeat_x*y + x)* content_height);
            }
          }
        }
      }
      ImageIO.write(editableImage, "png", new File("aaa.png"));
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
