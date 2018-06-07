
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Character extends Thread {

    private int x;
    private int y;
    int imgNum;
    private BufferedImage image;
    private ArrayList<BufferedImage> sprite;

    public Character(int x, int y, int imgNum) {
        this.x = x;
        this.y = y;
        this.imgNum = imgNum;
        this.sprite = new ArrayList<BufferedImage>();
    }

    public Character() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getImgNum() {
        return imgNum;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setImgNum(int imgNum) {
        this.imgNum = imgNum;
    }

    public ArrayList<BufferedImage> getSprite() {
        return sprite;
    }

    public void setSprite(ArrayList<BufferedImage> sprite) {
        this.sprite = sprite;
    }
}
