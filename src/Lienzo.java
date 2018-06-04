
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Lienzo extends javax.swing.JPanel implements Runnable{
    int matrizLaberinto[][] = new int[7][10];
    int f = 0;
    Thread thread;
    static int e=3, s=4, filas=10, columnas=10;
    int x=0, y=0, termino=0,Character1=5;
    BufferedImage personaje,wall,ryu,bill;
    URL ryuIMG = getClass().getResource("Images/Ryu.gif");
    URL wallImg = getClass().getResource("Images/Wall.jpg");
    URL billIMG = getClass().getResource("Images/BillRizer.gif");
    public int numCharacter=1;

    public void setNumCharacter(int numCharacter) {
        this.numCharacter = numCharacter;
    }
    
    public Lienzo() {
        initComponents();
        RandomStart();
    }
    
    public void RandomStart(){
        for (int i = 0; i < matrizLaberinto.length; i++) {
            for (int j = 0; j < matrizLaberinto[0].length; j++) {
                matrizLaberinto[i][j] = (int) (Math.random()*1.99);
            }
        }
    }
    
    @Override
    public void paint(Graphics g){
        try {
            wall = ImageIO.read(wallImg);
            ryu = ImageIO.read(ryuIMG);
            bill = ImageIO.read(billIMG);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
//        int posX = Utilidades.getAleatoriosRango(0, 6);
//        int posY = Utilidades.getAleatoriosRango(0, 9);
        matrizLaberinto[0][0] = 5;
        for (int i = 0; i < matrizLaberinto.length; i++) {
            for (int j = 0; j < matrizLaberinto[0].length; j++) {
                switch (matrizLaberinto[i][j]) {
                    case 0:
                        g.setColor(Color.RED);
                        g.drawRect(j * 93, i * 93, 93, 93);
                        break;
                    case 1:
                        g.drawImage(wall, j * 93, i * 93, 93, 93, this);
                        break;
                    case 5:
                        g.drawImage(ryu, j * 93, i * 93, 93, 93, this);
                        break;
                    default:
                        break;
                }
            }
        }      
    }
    public boolean existeCamino(int fila, int columna){
        if(fila<0||fila>=filas||columna<0||columna>=filas){
            return false;
        }
        if(matrizLaberinto[fila][columna]==7||matrizLaberinto[fila][columna]==1){
            return false;
        }
        return true;     
    }
    public boolean resolver() throws InterruptedException{
        boolean salida=false;
        try{
            Thread.sleep(2000);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());     
        }
        matrizLaberinto[fila][columna]
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 976, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
//        JOptionPane.showMessageDialog(null, evt.getX()/93+","+evt.getY()/93);
        int xI = evt.getX()/93;
        int yJ = evt.getY()/93;
        switch (matrizLaberinto[yJ][xI]) {
            case 1:
                matrizLaberinto[yJ][xI] = 0;
                break;
            case 0:
                matrizLaberinto[yJ][xI] = 1;
                break;
            case 5: matrizLaberinto[yJ][xI] = 0;
                break;
        }
        
            this.repaint();
        
    }//GEN-LAST:event_formMouseClicked

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
