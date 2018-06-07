
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Canvas extends javax.swing.JPanel implements Runnable{
    //variables
    int maze[][] = new int[7][10];
    int f = 0;
    Thread thread;
    static int e=3, s=4, row=10, columns=10, finalRow=8,finalColumns=9, startRow=0,starColumns=0;
    int x=0, y=0, isOver=0,Character1=5;
    //images
    BufferedImage character,wall,ryu,bill;
    BufferedImage[] ryuArray;
    
    URL ryuIMG = getClass().getResource("Images/Ryu.gif");
    URL ryuIMG0 = getClass().getResource("Images/Ryu0.gif");
    URL ryuIMG1 = getClass().getResource("Images/Ryu1.gif");
    URL ryuIMG2 = getClass().getResource("Images/Ryu2.gif");
    URL wallImg = getClass().getResource("Images/Wall.jpg");
    URL billIMG = getClass().getResource("Images/BillRizer.gif");
    public int numCharacter=1;
    Ryu ryuS;
    Graphics g = this.getGraphics();

    public void setNumCharacter(int numCharacter) {
        this.numCharacter = numCharacter;
    }
    
    public Canvas() {
        initComponents();
       
       
        try {
            init();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public void cargaSprites() throws FileNotFoundException, IOException{
//        ryuArray = new BufferedImage[3];
//        for (int i = 0; i < 3; i++) {
//            ryuArray[i] = ImageIO.read(ryuIMG1);
//        }
//    }
    
//This method start's the logical matrix to play
    public void Start(){
        
            //This is the easy matrix to play
            int[][] easyMaze = {
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 2},
            {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        
            //This is the medium matrix to play
            int[][] mediumMaze = {
            {1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 2}};
            
            //This is the hard matrix to play
            int[][] hardMaze = {
            {1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0},
            {0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 2},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0}};
    }

    //this method marks the path
    public boolean isThereAWay(int rowsP, int columnsP){
        if(rowsP<0||rowsP>=row||columnsP<0||columnsP>=row){
            return false;
        }
        if(maze[rowsP][columnsP]==5||maze[rowsP][columnsP]==1){
            return false;
        }
        return true;   
        
    }
    //this method solves the maze
    public boolean solve(int rowsP, int columnsP) throws InterruptedException {
        boolean stop = false;
        try {
            Thread.sleep(2000);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        maze[rowsP][columnsP] = 5;
        
        if(rowsP==finalRow&&columnsP==finalColumns){
            return true;
        }
        //the character moves down
        if(!stop&&isThereAWay(rowsP+1, columnsP)){
            maze[rowsP+1][columnsP]=e;
            repaint();
            stop=solve(rowsP+1,columnsP);
        }
        //the character moves to the right
          if(!stop&&isThereAWay(rowsP, columnsP+1)){
            maze[rowsP][columnsP+1]=e;
            repaint();
            stop=solve(rowsP,columnsP+1);
        }
          //the character moves to the left
               if(!stop&&isThereAWay(rowsP, columnsP-1)){
            maze[rowsP][columnsP-1]=e;
            repaint();
            stop=solve(rowsP,columnsP-1);
        }
             //the character moves up
               if(!stop&&isThereAWay(rowsP-1, columnsP)){
            maze[rowsP-1][columnsP]=e;
            repaint();
            stop=solve(rowsP-1,columnsP);
        }
        
        return stop;
    }
    
    public void init() throws FileNotFoundException, IOException{
        this.ryuS = new Ryu(0, 0, 0);
        this.ryuS.start();
    }
    
    //@Override
    public void paint(Graphics g){
        try {
            wall = ImageIO.read(wallImg);
//            ryu = ImageIO.read(ryuIMG);
//            bill = ImageIO.read(billIMG);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        maze[0][0] = 5;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                switch (maze[i][j]) {
                    case 0:
                        g.setColor(Color.RED);
                        g.drawRect(j * 93, i * 93, 93, 93);
                        break;
                    case 1:
                        g.drawImage(wall, j * 93, i * 93, 93, 93, this);
                        break;
                    case 5:
                        g.drawImage(this.ryuS.getImage(), j * 93, i * 93, 93, 93, this);
                        break;
                    default:
                        break;
                }
            }
        }      
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
        int xI = evt.getX()/93;
        int yJ = evt.getY()/93;
        switch (maze[yJ][xI]) {
            case 1:
                maze[yJ][xI] = 0;
                break;
            case 0:
                maze[yJ][xI] = 1;
                break;
//            case 5: matrizLaberinto[yJ][xI] = 0;
//                break;
        }
        this.repaint();
    }//GEN-LAST:event_formMouseClicked

    Thread pintando = new Thread() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(500);
                    paint(g);
                    repaint();
                
                } catch (InterruptedException ex) {
                }
            }
        }
    };
    
    @Override
    public void run() {
    pintando.start();
        try {
            if (solve(startRow, starColumns)){
                isOver=1;
                JOptionPane.showMessageDialog(this, "Congratulations");
                
            }else
                                isOver=1;
                              JOptionPane.showMessageDialog(this, "Your lose");
                
        } catch (InterruptedException ex) {
            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
