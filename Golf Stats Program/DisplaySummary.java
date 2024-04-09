import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Arrays;
import javax.swing.*;
import java.applet.Applet;
import GolfStats;




public class DisplaySummary{

   public static void main(String[] args){
      Display showStats = new Display();
      showStats.addWindowListener(new WindowAdapter(){ 
         public void windowClosing(WindowEvent e){
            System.exit(0);
         }   
      });
   }      

}






class Display extends Frame implements MouseListener, MouseMotionListener{
   
   Rectangle title,overallSummary, compare, analysis;
   int mX = 0;
   int mY = 0;
   GolfStats stImp = new GolfStats();
   
   
   public Display(){
      //current X,Y value of the mouse
      mX = MouseInfo.getPointerInfo().getLocation().x;
      mY = MouseInfo.getPointerInfo().getLocation().y;

      setSize(1000,600);
      setVisible(true);
      
      title = new Rectangle(90,250,425,50);
      
      overallSummary = new Rectangle(40,100,100,150);
      compare = new Rectangle(40,100,100,300);
      analysis = new Rectangle(40,100,100,450);
   
      addMouseListener(this);
      addMouseMotionListener(this);
      
      repaint();
      
   } 
   
   
   public void paint(Graphics g){
      Color custom1 = new Color(204, 255, 102); //light lime
      Color custom2 = new Color(69,97,47); //dark green
      int choose = 0;
      
      //background
      g.setColor(custom1);
      g.fillRect(0,0,1000,600);
      
      //title
      g.setColor(custom2);
      g.drawRect(90,250,425,50);

      
      //summary buttons (overallSummary, compare, analysis)
      g.drawRect(40,100,100,150);
      g.drawRect(40,100,100,300);
      g.drawRect(40,100,100,450);
      
      switch(choose){
         
         case 1: 
               if(overallSummary.inside(mX,mY)){
                  //call method to display summary
               }
               System.out.println("Overall Summary Displayed");
               break;
               
         case 2:
               if(compare.inside(mX,mY)){
                  //call method to display comparison graph
               } 
               System.out.println("Comparison Graph Displayed");
               break;
               
         case 3: 
               if(analysis.inside(mX,mY)){
                  //call method to display analysis
               }
               System.out.println("Analysis Displayed");
               break;
            
      }   
   }
   
   
   
   public void displaySummary(Graphics g){
      //Background


      //Overall summary

            
      //Putt Summary
            
      
      //Miss Summary
           
      
      //Pitch/approach Summary

         
   }
   
   
   public void displayComparison(Graphics g){}
   
   
   
   
   public void mouseClicked(MouseEvent e){
      int x =  MouseInfo.getPointerInfo().getLocation().x;
      int y = MouseInfo.getPointerInfo().getLocation().y;
      
            
   } 

}