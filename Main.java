import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Main {

    static String fileName = "Hello.png";
    static String fileFormat = "png";

    public static void main(String[] args) {
        BufferedImage image = null;
        File file = null;

        try {
            file = new File(fileName);
            image = ImageIO.read(file);
        }catch (IOException e) {}

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        for (int x = 0; x < imageWidth; x++) {
            for (int y = 0; y < imageHeight; y++) {
                
                // extracts the color
                int rgb = image.getRGB(x,y);

                int alpha = (rgb>>24)&0xff;
                int red = (rgb>>16)&0xff;
                int green = (rgb>>8)&0xff;
                int blue = rgb&0xff;

                int r = 255,g = 255, b = 255;

                if (red == r && green == g && blue == b) {
                    // if the color r,g,b is found, we will keep it in a new image
                    rgb = (alpha<<24) | (red<<16) | (green<<8) | blue;
                    image.setRGB(x, y, rgb);
                }
                else {
                    // if the color r,g,b isn't found, we will replace it with the color black
                    rgb = (alpha<<24) | (0<<16) | (0<<8) | 0;
                    image.setRGB(x, y, rgb);
                }
            }
        }

        try {
            // creates a new file
            file = new File("IsolatedImage." + fileFormat);
            file.createNewFile();

            ImageIO.write(image,fileFormat,file);
        }catch(IOException e) {}
    }
}
