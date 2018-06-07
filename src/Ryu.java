import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Diego
 */
public class Ryu extends Character{

    public Ryu(int x, int y, int imgNum) throws IOException {
        super(x, y, imgNum);
        try {
            setSprite();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ryu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setSprite() throws FileNotFoundException, IOException {
        ArrayList<BufferedImage> sprite = super.getSprite();
        BufferedImage bf;
        for (int i = 0; i < 3; i++) {
            bf = ImageIO.read(new FileInputStream("src/Images/Ryu"+i+".gif"));
            sprite.add(bf);
        }
        super.setSprite(sprite);
    }
    
    @Override
    public void run(){
        ArrayList<BufferedImage> sprite = super.getSprite();
        while (true) {            
            try {
                super.setImage(sprite.get(0));
                Thread.sleep(500);
//                System.out.println("Pinta 0");
                super.setImage(sprite.get(1));
                Thread.sleep(500);
//                System.out.println("Pinta 1");
                super.setImage(sprite.get(2));
                Thread.sleep(500);
//                System.out.println("Pinta 2");
            } catch (InterruptedException ex) {
                Logger.getLogger(Ryu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
