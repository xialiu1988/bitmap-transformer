package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bitmap {

    public int width;
    public int height;
    public byte m_components[];
    public BufferedImage image;

    //constructor
    public Bitmap(BufferedImage image){
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.m_components = new byte[width*height*4];
    }

   //constructor passing the fileName
    public Bitmap(String filePath) throws IOException {

        int width = 0;
        int height= 0;
        byte[] components = null;

        BufferedImage image = ImageIO.read(new File(filePath));
        width = image.getWidth();
        height = image.getHeight();

        int imgPixel[] = new int[width*height];
        image.getRGB(0,0,width,height,imgPixel,0,width);
        components = new byte[width*height*4];

        //loop throught the byte array
        for(int i =0 ;i<width*height;i++){
            int pix =  imgPixel[i];
            components[i * 4]  = (byte)((pix>> 24) & 0xFF);
            components[i * 4+1]  = (byte)((pix ) & 0xFF);
            components[i * 4+2]  = (byte)((pix>> 8) & 0xFF);
            components[i * 4+3]  = (byte)((pix>> 16) & 0xFF);

        }

        this.width = width;
        this.height = height;
        this.m_components = components;

    }


    //transform: convert to grayscale
    public BufferedImage grayscale(BufferedImage image){

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int p = image.getRGB(x,y);

                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;

                //calculate average
                int avg = (r+g+b)/3;

                //replace RGB value with avg
                p = (a<<24) | (avg<<16) | (avg<<8) | avg;

                image.setRGB(x, y, p);
            }
        }

return image;
    }



}