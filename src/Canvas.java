
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
    int maze[][] = new int[8][11];
    int f = 0;
    Thread thread;
    static int e=3, s=4, row=10, columns=10, finalRow=8,finalColumns=9, startRow=0,starColumns=0;
    int x=0, y=0, isOver=0,Character1=5;
    //images
    BufferedImage character,wall,ryu,bill;
    BufferedImage[] ryuArray;
    
    int a = 0;
    int b = 0;
    
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
       Start(3);
       
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
    public void Start(int n){
        if (n==1) {
            //This is the easy matrix to play
            int[][] easyMaze = {
            {5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 2},
            {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
            maze = easyMaze;
        
        }if (n==2) {
            //This is the medium matrix to play
            int[][] mediumMaze = {
            {5, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 2}};
            maze = mediumMaze;
            
        }if (n==3) {
            //This is the hard matrix to play
            int[][] hardMaze = {
            {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0},
            {0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 2},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0}};
            maze = hardMaze;
        }
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
//        maze[rowsP][columnsP] = 5;
        
        if(rowsP==finalRow&&columnsP==finalColumns){
            return true;
        }
        //the character moves down
        if(!stop&&isThereAWay(rowsP+1, columnsP)){
            maze[rowsP+1][columnsP]=1;
            repaint();
            stop=solve(rowsP+1,columnsP);
        }
        //the character moves to the right
          if(!stop&&isThereAWay(rowsP, columnsP+1)){
            maze[rowsP][columnsP+1]=1;
            repaint();
            stop=solve(rowsP,columnsP+1);
        }
          //the character moves to the left
               if(!stop&&isThereAWay(rowsP, columnsP-1)){
            maze[rowsP][columnsP-1]=1;
            repaint();
            stop=solve(rowsP,columnsP-1);
        }
             //the character moves up
               if(!stop&&isThereAWay(rowsP-1, columnsP)){
            maze[rowsP-1][columnsP]=1;
            repaint();
            stop=solve(rowsP-1,columnsP);
        }
        repaint();
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
        
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                switch (maze[i][j]) {
                    case 0:
                        g.drawImage(wall, j * 80, i * 80, 80, 80, this);
                        break;
                    case 1:
                        g.setColor(Color.RED);
                        g.drawRect(j * 80, i * 80, 80, 80);
                        break;
                    case 2:
                        g.setColor(Color.GREEN);
                        g.fillRect(j * 80, i * 80, 80, 80);
                        break;
                    case 5:
                        g.drawImage(this.ryuS.getImage(), j * 80, i * 80, 80, 80, this);
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

        jToggleButton1 = new javax.swing.JToggleButton();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jToggleButton1.setText("jToggleButton1");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 945, Short.MAX_VALUE)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToggleButton1)
                .addGap(0, 627, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        int xI = evt.getX()/80;
        int yJ = evt.getY()/80;
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

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
//        try {
        
        if (maze[a+1][b]==1) {
                maze[a][b]=1;
                maze[a+1][b]=5;
                a++;
                repaint();
        }else
            if (maze[a][b+1]==1) {
                maze[a][b]=1;
                maze[a][b+1]=5;
                b++;
                repaint();
        }else
            if (maze[a-1][b]==1) {
                maze[a][b]=1;
                maze[a-1][b]=5;
                a--;
                repaint();
        }else
            if (maze[a][b-1]==1) {
                maze[a][b]=1;
                maze[a][b-1]=5;
                b--;
                repaint();
        }if (maze[a+1][b]==2) {
                maze[a][b]=1;
                maze[a+1][b]=5;
                JOptionPane.showMessageDialog(null, "GANASTE");
                repaint();
        }else
            if (maze[a][b+1]==2) {
                maze[a][b]=1;
                maze[a][b+1]=5;
                JOptionPane.showMessageDialog(null, "GANASTE");
                repaint();
        }else
            if (maze[a-1][b]==2) {
                maze[a][b]=1;
                maze[a-1][b]=5;
                JOptionPane.showMessageDialog(null, "GANASTE");
                repaint();
        }else
            if (maze[a][b-1]==2) {
                maze[a][b]=1;
                maze[a][b-1]=5;
                JOptionPane.showMessageDialog(null, "GANASTE");
                repaint();
        }
//        solve(0, 0);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

//    Thread pintando = new Thread() {
//        @Override
//        public void run() {
//            while (true) {
//                try {
//                    Thread.sleep(500);
//                    paint(g);
//                    repaint();
//                
//                } catch (InterruptedException ex) {
//                }
//            }
//        }
//    };
    
    @Override
    public void run() {
//    pintando.start();
//        try {
//            if (solve(startRow, starColumns)){
//                isOver=1;
//                JOptionPane.showMessageDialog(this, "Congratulations");
//                
//            }else
//                                isOver=1;
//                              JOptionPane.showMessageDialog(this, "Your lose");
//                
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
