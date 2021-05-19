package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void Table10On16() {
        var im= new ImageWriter("second",800,500);
        for (int i=0;i<800;i++)
            for (int j=0;j<500;j++)
            {
                if (i%50==0||j%50==0)
                    im.writePixel(i,j, Color.BLACK);
                else im.writePixel(i,j, new Color(java.awt.Color.white));
            }
            im.writeToImage();
    }
}