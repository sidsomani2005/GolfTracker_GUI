import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Arrays;
import javax.swing.*;
import java.applet.Applet;







public class GolfStats {

   int s; //variable for score of the hole
   int p; //variable for par of the hole  
   static int score; //score of the round 
   static int girCount;
   static int fwCount;
   static int totalfwCount;
   
   static int UnDCount;
   static int totalUnD;
   
   static int chipUnD;
   static int totalChips;
   static boolean isChip;
   
   static int pitchUnD;
   static int pitchCount;
   static boolean isPitch;
   
   static int bunkerUnD;
   static int bunkerCount;
   
   static String lie;
   
   static int penaltyCount;
   static int lagPuttCount; //putts greater than 25ft
   static int shortPuttsMissed;
   static int totalShortPutts;
   static int shortPuttsMade;
   static int shortPuttsPercentage;
   static int threePutts;
   static int totalPutts;
   static int numDoublesOrMore;
   
   //ArrayList<Integer> finalPutt = new ArrayList<Integer>(); 
   Hole h;
   static ArrayList<String> misses = new ArrayList<String>();
   static ArrayList<Hole> holes = new ArrayList<Hole>();
   static ArrayList<Integer> approachYardages = new ArrayList<Integer>();
   
   //common miss variables
   static String cm = "";
   static int Lcount = 0;
   static int Rcount = 0;
   
   
   //count of number of pitches
   static int f20to30 = 0;
   static int f30to40 = 0;
   static int f40to50 = 0;
   static int f50to60 = 0;
   static int f60to70 = 0;
   static int f70to80 = 0;
   static int f80to90 = 0;
   static int f90to100 = 0;
   static int f100to110 = 0;
   static int commonPitchYardage = 0;
   static int totalPitches = f20to30+f30to40+f40to50+f50to60+f60to70+f70to80+f80to90+f90to100+f100to110;
   static int pitchingUnDCount = 0;
   static int chippingUnDCount = 0;
   
   //arraylist of proximities of each respective distance
   static ArrayList<Integer> p20to30 = new ArrayList<Integer>();
   static ArrayList<Integer> p30to40 = new ArrayList<Integer>();
   static ArrayList<Integer> p40to50 = new ArrayList<Integer>();
   static ArrayList<Integer> p50to60 = new ArrayList<Integer>();
   static ArrayList<Integer> p60to70 = new ArrayList<Integer>();
   static ArrayList<Integer> p70to80 = new ArrayList<Integer>();
   static ArrayList<Integer> p80to90 = new ArrayList<Integer>();
   static ArrayList<Integer> p90to100 = new ArrayList<Integer>();
   static ArrayList<Integer> p100to110 = new ArrayList<Integer>();
   



   
   
     
   public static void main(String[] args){  
      
      Scanner sc = new Scanner(System.in); 
      GolfStats gstats = new GolfStats(); 
      
      System.out.println("Total Score: ");
      score = sc.nextInt();
      
      
      for(int i=1; i<=5; i++){
         System.out.println("HOLE "+i+"------------" );        
         System.out.println("Par of the hole: ");
         gstats.p = sc.nextInt();               
         System.out.println("Score of the hole: ");
         gstats.s = sc.nextInt();
         
         //calculating number of doubles or more per round
         if((gstats.s - gstats.p) >= 2){
            numDoublesOrMore++;
         }
      
      
         gstats.h = new Hole(i, gstats.s, gstats.p);
         gstats.holes.add(gstats.h);         
         System.out.println();
         
         System.out.println("Hole "+i+": "+gstats.h.toString());
         System.out.println("FW Count: "+fwCount);
         System.out.println("GIR Count: "+girCount);
        
         System.out.println();
         System.out.println();
         System.out.println();
         System.out.println();
         System.out.println();
               
      }
      
      
      
      //START OF SUMMARY -----------------------------------------------------------------------------------------------------------      
      System.out.println("SUMMARY >>>>"); 
      //System.out.println("FW's hit: "+fwCount+" / "+totalfwCount); //FW's
      //System.out.println("GIR's hit: "+girCount+" / 18"); //GIR's
      //System.out.println("UP and Downs: "+UnDCount+" / "+totalUnD); //up and downs
      //System.out.println("Misses: ");
      //gstats.iterate(misses); 
      //System.out.println(gstats.commonMiss(misses)); //common miss
      //System.out.println();
      //System.out.println("Penalties: "+penaltyCount); //penalties
      gstats.approachSummary(approachYardages); //pitch yardages
      //END OF SUMMARY -------------------------------------------------------------------------------------------------------------
      
      System.out.println();
      System.out.println();
      
      
      System.out.println("Short putts made: "+shortPuttsMade);
      System.out.println("Total Short Putts: "+totalShortPutts);
      gstats.shortPuttMakePercentage(shortPuttsMade, totalShortPutts);
      System.out.println("Make % 3-6ft: "+shortPuttsPercentage);
      
      System.out.println();
      System.out.println();
      
      System.out.println("Pitching UnD: "+pitchUnD+"/"+pitchCount);
      System.out.println("Chipping UnD: "+chipUnD+"/"+totalChips);
      System.out.println("Bunker UnD: "+bunkerUnD+"/"+bunkerCount);
      
      
      //START OF GRAPHICS DISPLAY --------------------------------------------------------------------------------------------------
       
      
      Display showStats = new Display();
      
       
      showStats.addWindowListener(new WindowAdapter(){ 
         public void windowClosing(WindowEvent e){
            System.exit(0);
         }   
      });
      
      //System.out.println("fwP: "+showStats.fwP);
      //System.out.println("girP: "+showStats.girP);
      //END OF GRAPHICS DISPLAY ----------------------------------------------------------------------------------------------------
           
      
   }
   
   
   
   
   //Iterates through ArrayList
   public void iterate(ArrayList l){
      String out = "";  
      
      for(int i=0; i<l.size(); i++){
         System.out.println("=> "+l.get(i));
      }      
   }
   
   
   
   
   //Common Miss
   public String commonMiss(ArrayList m){ 
      for(int i=0; i<m.size(); i++){
         if(m.get(i).equals("R")){
            Rcount++;
         } else if(m.get(i).equals("L")){
            Lcount++;
         }
      }
      
      if(Rcount>Lcount){
         cm = "Right";
      } else if(Lcount>Rcount){
         cm = "Left";
      } else if(Lcount==Rcount){
         cm = "Equal";
      }
   
      return cm;
   }
 
 
 
 
   //Short Putt(<6ft) Make Percentage
   public void shortPuttMakePercentage(int spm, int tsp){
      int percent = 0;
      
      System.out.println("Percent before: "+percent);
      if(tsp>0){
         percent = (int)((((double)spm)/((double)tsp))*100);
      }
      System.out.println("Percent after: "+percent);

      shortPuttsPercentage = percent;   
   }
   
   
 
 
 
 
   public void approachSummary(ArrayList<Integer> s){
   
      for(int i=0; i<s.size(); i++){
         if(s.get(i)>=20 && s.get(i)<=30){
            f20to30++;
         } else if(s.get(i)>30 && s.get(i)<=40){
            f30to40++;
         } else if(s.get(i)>40 && s.get(i)<=50){
            f40to50++;
         } else if(s.get(i)>50 && s.get(i)<=60){
            f50to60++;
         } else if(s.get(i)>60 && s.get(i)<=70){
            f60to70++;
         } else if(s.get(i)>70 && s.get(i)<=80){
            f70to80++;
         } else if(s.get(i)>80 && s.get(i)<=90){
            f80to90++;
         } else if(s.get(i)>90 && s.get(i)<=100){
            f90to100++;
         } else if(s.get(i)>100 && s.get(i)<=110){
            f100to110++;
         }
         
      }
      
      int[] pitchYardages = {f20to30,f30to40,f40to50,f50to60,f60to70,f70to80,f80to90,f90to100,f100to110};
      Arrays.sort(pitchYardages);
      
      System.out.println("Most common pitch yardages: Ascending order");
      int count=0;
      for(int j=pitchYardages.length-1; j>=0; j--){
         System.out.println(count+". "+pitchYardages[j]);
         count++;
      }   

   } 
 
 
 
 
   
   public int proximityAvg(ArrayList<Integer> x){
      int sum=0;
      int avg=0;
      
      if(x.size()>0){
         
         for(int i=0; i<x.size(); i++){
            sum+=x.get(i);
         }
      
         avg = sum/(x.size());
      
      }
      
      return avg;
      
   }
 
 

}















class Hole {
   
   int numHole; //number of the hole
   int Score; //the players score on the specific hole
   int par; //the par of the hole (3,4,5)
   boolean finishedHole;
   boolean hitGreen;
   int puttCount;
   
   
   
   //int shotCounter;
   
   int finalPuttL;
   
   static GolfStats gstats2 = new GolfStats();
    
   static int approachYardage=0;
       
       
   public Hole(int holeNum, int numScore, int holePar){   
      
      Scanner sc2 = new Scanner(System.in);
      
      this.numHole = holeNum;
      this.Score = numScore;
      this.par = holePar;

      
      //PAR 3 CODE ---------------------------------------------------
      if(this.par==3){        
         System.out.println("TEE SHOT => yardage\n>");
         gstats2.approachYardages.add(sc2.nextInt());
         
         System.out.println("Result? (L, R, or GIR)\n>");      
         String result = sc2.next();
         
         if(result.equals("L")){
            hitGreen = false;
            gstats2.misses.add("L");                                                        
         } else if(result.equals("R")){
            hitGreen = false;
            gstats2.misses.add("R");                       
         } else {
            hitGreen = true;
            gstats2.girCount+=1;
            System.out.println(hitGreen);                    
         }  
   
      
      
      } else if(this.par==4){       //PAR 4 CODE --------------------------------------------------------
         
         gstats2.totalfwCount++;
         
         System.out.println("Hole yardage => ");
         gstats2.approachYardages.add(sc2.nextInt());
         
         System.out.println("TEE SHOT Result? (L, R, or FW)\n>");
         String tResult = sc2.next();
           
         if(tResult.equals("L")){
            gstats2.misses.add("L");          
         } else if(tResult.equals("R")){
            gstats2.misses.add("R");              
         } else {
            gstats2.fwCount++;
         }
         
               
                
         System.out.println("Approach Yardage: ");         
         
         approachYardage = sc2.nextInt();         
         gstats2.approachYardages.add(approachYardage);
         checkUnD(approachYardage);
         
         
         System.out.println("Result? (L, R, or GIR)\n>");
         String checkTheMiss = sc2.next();
                 
         if(checkTheMiss.equals("L")){
            hitGreen = false;
            gstats2.misses.add("L");                                
         } else if(checkTheMiss.equals("R")){
            hitGreen = false;
            gstats2.misses.add("R");
            //System.out.println(hitGreen);
         } else {
            hitGreen = true;
            gstats2.girCount++;
            
            //System.out.println(hitGreen);        
         } 
         
      
      } else if(this.par==5){       // PAR 5 CODE -------------------------------------------------
         gstats2.totalfwCount++;
         
         System.out.println("Hole yardage => ");
         gstats2.approachYardages.add(sc2.nextInt());
         
         System.out.println("TEE SHOT Result? (L, R, or FW)\n>");
         String tCheck = sc2.next();
           
         if(tCheck.equals("L")){
            gstats2.misses.add("L");          
         } else if(tCheck.equals("R")){
            gstats2.misses.add("R");               
         } else {
            gstats2.fwCount++;
         }
         
         
         //layup code
         System.out.println("Layup? (Y or N)\n>");
         if(sc2.next().equals("Y")){            
            System.out.println("Where did it end? (L, R, or FW)\n>");
            
            if(sc2.nextLine().equals("L")){
               gstats2.misses.add("L");
            } else if(sc2.nextLine().equals("R")){
               gstats2.misses.add("R");
            }
            
            System.out.println("Approach Yardage (after layup): ");
            approachYardage = sc2.nextInt();        
            gstats2.approachYardages.add(approachYardage);
            checkUnD(approachYardage);
                    
  
         } else {
            System.out.println("Approach Yardage (when going for the green): ");
            System.out.println(sc2.nextInt()+"Yards");
          
         }
         
         
         
         System.out.println("Approach Result? (L, R, or GIR)\n>");
         String checkMiss = sc2.next();
            
         
         if(checkMiss.equals("L")){
            hitGreen = false;
            gstats2.misses.add("L");
                                    
         } else if(checkMiss.equals("R")){
            hitGreen = false;
            gstats2.misses.add("R");
            //System.out.println(hitGreen);
   
         } else {
            hitGreen = true;
            gstats2.girCount++;
            //System.out.println(hitGreen);
            
            }    

      }
         
   
      //approach result code
      if(hitGreen==true){
         System.out.println("How many putts: ");
         puttCount = sc2.nextInt();
         
         if(puttCount>=3){
            gstats2.threePutts++;
         }    
         
         //UP AND DOWN CODE ------------------------- 
         //if((approachYardage>=20 || approachYardage<=110) && puttCount=1){
         //   gstats2.pitchingUnDCount++;
         //} else if(approachYardage<20 && puttCount=1){
         //   gstats2.chippingUnDCount++;
         //}
         //------------------------------------------
         
      } else {           
         System.out.println("How many approaches : ");
         int add = sc2.nextInt();
         gstats2.totalUnD+=add;
            
         System.out.println("Yardage of approach: ");
         approachYardage = sc2.nextInt();        
         gstats2.approachYardages.add(approachYardage);
         
         
         
         //check if shot is bunker or not based on the lie   
         System.out.println("FW, R(rough), or B(bunker): ");         
         gstats2.lie = sc2.next();
         System.out.println(gstats2.lie);
         
         if(gstats2.lie.equals("B")){
            gstats2.bunkerCount++;
         }
         
         System.out.println("How many putts: ");
         puttCount = sc2.nextInt();
         
         //check if shot is pitch or chip
         checkUnD(approachYardage, gstats2.lie);
      
       
         
      }
      
      //total putts
      gstats2.totalPutts+=puttCount;

 
      //putting code
      System.out.println("Number of Putts: "+puttCount);
         
      if(hitGreen==false && puttCount==1){
         gstats2.UnDCount++;
         if(gstats2.isChip==true && (gstats2.lie.equals("R") || gstats2.lie.equals("FW"))){
            gstats2.chipUnD++;
         } else if(gstats2.isPitch==true && (gstats2.lie.equals("R") || gstats2.lie.equals("FW"))){
            gstats2.pitchUnD++;
         } else if(gstats2.lie.equals("B")){
            gstats2.bunkerUnD++;
         }
      }
         
         
      
      for(int i=0; i<puttCount; i++){
         System.out.println("Length of Putt: ");
         int puttlength = sc2.nextInt(); 
         
         
         if(i==0){
            //method to check approach yardage and add proximity to specific arrayList
            checkProximity(approachYardage,puttlength);
         }
         
         if(puttlength >= 25){
            gstats2.lagPuttCount++;
         }
               
         if(puttlength<=6 && puttlength>=3){
            gstats2.totalShortPutts++;           
            if(i<puttCount-1){
               gstats2.shortPuttsMissed++;
            }
                       
         }
               
         if(i==(puttCount-1)){
            finalPuttL = puttlength;
            System.out.println("Final Putt Length: "+finalPuttL);
            
            if(finalPuttL>=3 && finalPuttL<=6){
               gstats2.shortPuttsMade++;
            }
         } 
         
                
      }
          
          
      gstats2.totalPutts+=puttCount;
            
      System.out.println("How many penalties on this hole?\n>");
      gstats2.penaltyCount += sc2.nextInt();
     
      
   
   } //END OF CONSTRUCTOR -----------------------------------------------------------------------------------------------

   
   public void checkUnD(int shotYardage){
      if(shotYardage<20){
         gstats2.totalChips++;
         gstats2.isChip=true;
      } else if(shotYardage>=20 && shotYardage<=110){
         gstats2.pitchCount++;
         gstats2.isPitch=true;
      } else {
         gstats2.isChip=false;
         gstats2.isPitch=false;
      }
   
   }
    
   public void checkUnD(int shotYardage, String lie){
      if(shotYardage<20 && (lie.equals("FW") || lie.equals("R"))){
         gstats2.totalChips++;
         gstats2.isChip=true;
      } else if((shotYardage>=20 && shotYardage<=110) && (lie.equals("FW") || lie.equals("R"))){
         gstats2.pitchCount++;
         gstats2.isPitch=true;
      } else {
         gstats2.isChip=false;
         gstats2.isPitch=false;
      }
   }
   
   public void checkProximity(int y, int pl){
         if(y>=20 && y<=30){
            gstats2.p20to30.add(pl);
         } else if(y>30 && y<=40){
            gstats2.p30to40.add(pl);
         } else if(y>40 && y<=50){
            gstats2.p40to50.add(pl);
         } else if(y>50 && y<=60){
            gstats2.p50to60.add(pl);
         } else if(y>60 && y<=70){
            gstats2.p60to70.add(pl);
         } else if(y>70 && y<=80){
            gstats2.p70to80.add(pl);
         } else if(y>80 && y<=90){
            gstats2.p80to90.add(pl);
         } else if(y>90 && y<=100){
            gstats2.p90to100.add(pl);
         } else if(y>100 && y<=110){
            gstats2.p100to110.add(pl);
         }

   }
      
  
   
   public String toString(){
      
      return("{ " +this.numHole+ ", " +this.par+", "+this.Score+ " }");
   } 



} 





















class Display extends Frame implements MouseListener, MouseMotionListener{
   
   Rectangle title, overallSummary, compare, analysis;
   int mX;
   int mY;
   boolean isClick;
   static int choose;
   
   static GolfStats gstats3;
   
   static int pPutts = 27;
   static int pFWsP = 77;
   static int pPitchP = 40;
   static int pChipP = 81;
   static int pBunkerP = 72;
   static int pGIRs = 74;
   static int pPenalties = 1;
   static int p3Putts = 1;
   static int pDoublesOrGreater = 1;
   static int pShortPuttsPercentage = 90;
   
   
   static int fwP;
   static int pitchP;
   static int chipP;
   static int bunkerP;
   static int girP;
   
   
   
   
   
  
   public Display(){
   
      gstats3 = new GolfStats();
      
      
      fwP = (int)(((double)gstats3.fwCount)/((double)gstats3.totalfwCount) * 100);
      girP = (int)(((double)gstats3.girCount)/((double)18) * 100);
      
      pitchP = (int)(((double)gstats3.pitchUnD)/((double)gstats3.pitchCount)*100);
      chipP = (int)(((double)gstats3.chipUnD)/((double)gstats3.totalChips)*100);
      bunkerP = (int)(((double)gstats3.bunkerUnD)/((double)gstats3.bunkerCount)*100);
      
      
      
      //current X,Y value of the mouse
      mX = MouseInfo.getPointerInfo().getLocation().x;
      mY = MouseInfo.getPointerInfo().getLocation().y;
      choose=0;
     
      setSize(1300,800);
      setVisible(true);
      
      overallSummary = new Rectangle(200,300,100,200);
      compare = new Rectangle(200,300,400,200);
      analysis = new Rectangle(200,300,700,200);
      
      
      isClick=false;
   
      addMouseListener(this);
      addMouseMotionListener(this);
      
      repaint();
            
   } 
   
   
   
  
   
   public void paint(Graphics g){  
             
      //background
      g.setColor(Color.gray);
      g.fillRect(0,0,1300,800);
      displayMenu(g);    
          
      switch(choose){         
         case 1:                                
                  displaySummary(g);
               break;               
         case 2:  
                  displayComparison(g);
               break;        
         case 3: 
                  displayMenu(g);
               break;
      }  
      repaint();
   }
   
   
   
   
   
   
   
   public int calculatePercentage(int n1, int n2){
      int percentage = 0;
      if(n2>0){
         percentage = (int)(n1/n2*100);
      }
      return percentage;
   }
   
   
   
   
   
   
   
   
   
   
   public static void displayMenu(Graphics g){
      //summary buttons (overallSummary, compare, analysis)
      g.setColor(Color.black);
      g.drawString("MENU",615,70);
      
      g.drawRect(85,200,300,200);
      g.drawRect(885,200,300,200);
      //g.drawRect(885,200,300,200);
      
      
      
      Color azure = new Color(240,255,255);
      g.setColor(azure);
      
      g.fillRect(87,202,298,198);  
      g.fillRect(887,202,298,198);
      //g.fillRect(887,202,298,198);
      
      g.setColor(Color.black);
      g.drawString("Summary",200,300);
      g.drawString("Comparison",1000,300);
      //g.drawString("Analysis",1010,300);
   
   }
   
   
   
   
   
   public void displaySummary(Graphics g){
               
               Color lightgray = new Color(210,210,210);
               g.setColor(lightgray);
               g.fillRect(0,0,1300,800);
               
               g.setColor(Color.black);
               
               //Overall summary
               g.drawString("SUMMARY:",460,50);
               g.drawRect(30,100,200,200);
               g.drawString("Overall Summary--------- ",32,118);
               
               g.drawString("Score: "+gstats3.score,32,140);
               g.drawString("FW: "+gstats3.fwCount+" / "+gstats3.totalfwCount, 32,162);
               g.drawString("GIR: "+gstats3.girCount+" / 18",32,184);
               
               
               g.drawString("U/D (total): "+gstats3.UnDCount+" / "+gstats3.totalUnD, 32,206);
               g.drawString("Putts: "+gstats3.totalPutts/2, 32, 228);
               g.drawString("Penalties: "+gstats3.penaltyCount,32,250);
               
               //Putt Summary
               g.drawRect(30,330,250,200);
               g.drawString("Putting---------",32,348);
               g.drawString("Total Putts: "+gstats3.totalPutts/2,32,375);
               g.drawString("3-Putts: "+gstats3.threePutts,32,395);
               g.drawString("Putts missed 3-6ft: "+gstats3.shortPuttsMissed+" / "+gstats3.totalShortPutts,32,415);
               g.drawString("Number of lag putts(<25ft): "+gstats3.lagPuttCount,32,435);
      
      
               //Pitch/approach Summary
               g.drawRect(300,100,250,350);
               g.drawString("Pitching/Approaches---------",302,118);
               g.drawString("Avg Proximity + Frequency (P,F)==>>",305,140);
               g.drawString("20-30yd: "+gstats3.proximityAvg(gstats3.p20to30)+"ft, "+gstats3.f20to30,310,162);
               g.drawString("30-40yd: "+gstats3.proximityAvg(gstats3.p30to40)+"ft, "+gstats3.f30to40, 310, 184);
               g.drawString("40-50yd: "+gstats3.proximityAvg(gstats3.p40to50)+"ft, "+gstats3.f40to50, 310, 206);
               g.drawString("50-60yd: "+gstats3.proximityAvg(gstats3.p50to60)+"ft, "+gstats3.f50to60, 310, 228);
               g.drawString("60-70yd: "+gstats3.proximityAvg(gstats3.p60to70)+"ft, "+gstats3.f60to70, 310, 250);
               g.drawString("70-80yd: "+gstats3.proximityAvg(gstats3.p70to80)+"ft, "+gstats3.f70to80, 310, 272);
               g.drawString("80-90yd: "+gstats3.proximityAvg(gstats3.p80to90)+"ft, "+gstats3.f80to90, 310, 294);
               g.drawString("90-100yd: "+gstats3.proximityAvg(gstats3.p90to100)+"ft, "+gstats3.f90to100, 310, 316);
               g.drawString("100-110yd: "+gstats3.proximityAvg(gstats3.p100to110)+"ft, "+gstats3.f100to110, 310, 338);
               
               //g.drawString("Best Yardage: ",305,380);
               //g.drawString("Worst Yardage: ",305,400);
      
      
      
               //Miss Summary
               g.drawRect(310,480,250,150);
               g.drawString("Misses---------",313,498);
               g.drawString("Left Miss Count: "+gstats3.Lcount,313,520);
               g.drawString("Right Miss Count: "+gstats3.Rcount,313,540);
               g.drawString("Common Miss: "+gstats3.cm,313,560);
               g.drawString("Plan to improve: FIX COMMON MISS",313,600);
               
               
               
               //Back Button
               BackBtn(g);

               

   }
   
   
   public void displayComparison(Graphics g){
                             
               Color lightgray = new Color(210,210,210);
               g.setColor(lightgray);
               g.fillRect(0,0,1300,800);
               
               g.setColor(Color.black);
               
               
               //COMPARISON
               g.drawString("COMPARISON",460,50);
               
               Color proC = new Color(255,215,0,50);
               Color userC = new Color(255,0,0,50);
               
               //FWS               
               g.drawLine(50,300,200,300);
               g.drawString("FW's hit per round",65,320);
               
               g.setColor(proC);
               g.fillRect(55, (300-(pFWsP*2)), 50, (pFWsP*2));
               g.setColor(userC);
               g.fillRect(145, (300-(fwP*2)), 50, (fwP*2));
               g.setColor(Color.black);
               g.drawString(pFWsP+"%", 67, (300-(pFWsP*2)-5));
               g.drawString(fwP+"%", 158, (300-(fwP*2)-5));
                
                
                
                                                                                      
               //GIRS
               g.drawLine(50,700,200,700);
               g.drawString("GIR's per round",75,720);
               
               g.setColor(proC);
               g.fillRect(55, (700-(pGIRs*2)), 50, (pGIRs*2));
               g.setColor(userC);
               g.fillRect(145, (700-(girP*2)), 50, (girP*2));
               g.setColor(Color.black);
               g.drawString(pGIRs+"%", 67, (700-(pGIRs*2)-5));
               g.drawString(girP+"%", 158, (700-(girP*2)-5));
             
               
               
               
               //PUTTS
               g.drawLine(300,300,450,300);
               g.drawString("Putts per round",325,320);
               g.setColor(proC);
               g.fillRect(305, (300-(pPutts*5)), 50, (pPutts*5));
               g.setColor(userC);
               g.fillRect(395, (300-(gstats3.totalPutts/2*5)), 50, ((gstats3.totalPutts/2)*5));
               g.setColor(Color.black);
               g.drawString(""+pPutts, 320, (300-(pPutts*5)-5));
               g.drawString(""+(gstats3.totalPutts/2), 410, (300-((gstats3.totalPutts/2)*5)-5));
               
               
               
               
               //UnDs
               g.drawLine(350,740,770,740);
               g.drawString("U/D's per round",520,760);
               
               g.drawLine(360,700,480,700);
               g.drawString("Pitching",390,720);
               g.setColor(proC);
               g.fillRect(365, (700-(pPitchP*2)), 35, (pPitchP*2));
               g.setColor(userC);
               g.fillRect(445, (700-(pitchP*2)), 35, (pitchP*2));
               g.setColor(Color.black);
               g.drawString(pPitchP+"%", 377, (700-(pPitchP*2)-5));
               g.drawString(pitchP+"%", 457, (700-(pitchP*2)-5));
               
               
               g.drawLine(500,700,620,700);
               g.drawString("Chipping",525,720);
               g.setColor(proC);
               g.fillRect(505, (700-(pChipP*2)), 35, (pChipP*2));
               g.setColor(userC);
               g.fillRect(585, (700-(chipP*2)), 35, (chipP*2));
               g.setColor(Color.black);
               g.drawString(pChipP+"%", 517, (700-(pChipP*2)-5));
               g.drawString(chipP+"%", 597, (700-(chipP*2)-5));
             
               
               g.drawLine(640,700,760,700);
               g.drawString("Bunker",680,720);
               g.setColor(proC);
               g.fillRect(645, (700-(pBunkerP*2)), 35, (pBunkerP*2));
               g.setColor(userC);
               g.fillRect(720, (700-(bunkerP*2)), 35, (bunkerP*2));
               g.setColor(Color.black);
               g.drawString(pBunkerP+"%", 657, (700-(pBunkerP*2)-5));
               g.drawString(bunkerP+"%", 730, (700-(bunkerP*2)-5));
               
               
               
               //PENALTIES
               g.drawLine(1030,700,1180,700);
               g.drawString("Penalties per round",1045,720);
               g.setColor(proC);
               g.fillRect(1035, (700-(pPenalties*40)), 50, (pPenalties*40));
               g.setColor(userC);
               g.fillRect(1125, (700-(gstats3.penaltyCount*40)), 50, (gstats3.penaltyCount*40));
               g.setColor(Color.black);
               g.drawString(""+pPenalties, 1055, (700-(pPenalties*40)-5));
               g.drawString(""+gstats3.penaltyCount, 1145, (700-(gstats3.penaltyCount*40)-5));
               
               
               
               //3-PUTTS
               g.drawLine(560,300,710,300);
               g.drawString("3-putts per round", 577,320);
               g.setColor(proC);
               g.fillRect(565, (300-(p3Putts*40)), 50, (p3Putts*40));
               g.setColor(userC);
               g.fillRect(655, (300-(gstats3.threePutts*40)), 50, (gstats3.threePutts*40));
               g.setColor(Color.black);
               g.drawString(""+p3Putts, 585,(300-(p3Putts*40)-5));
               g.drawString(""+gstats3.threePutts, 675,(300-(gstats3.threePutts*40)-5));
               
               
               //DOUBLES>>
               g.drawLine(820,300,970,300);
               g.drawString("Doubles or greater per round",807,320);
               g.setColor(proC);
               g.fillRect(825, (int)(300-(pDoublesOrGreater*40)), 50, (int)(pDoublesOrGreater*40));
               g.setColor(userC);
               g.fillRect(915, (int)(300-(gstats3.numDoublesOrMore*40)), 50, (int)(gstats3.numDoublesOrMore*40));
               g.setColor(Color.black);
               g.drawString(""+pDoublesOrGreater, 845,(int)(300-(pDoublesOrGreater*40)-5));
               g.drawString(""+gstats3.numDoublesOrMore, 935,(int)(300-(gstats3.numDoublesOrMore*40)-5));
               
               
               //SHORT PUTTS
               g.drawLine(1080,300,1230,300);
               g.drawString("Make % 3-6ft",1117,320);
               g.setColor(proC);
               g.fillRect(1085, (300-(pShortPuttsPercentage*2)), 50, (pShortPuttsPercentage*2));
               g.setColor(userC);
               g.fillRect(1175, (300-(gstats3.shortPuttsPercentage*2)), 50, (gstats3.shortPuttsPercentage*2));
               g.setColor(Color.black);
               g.drawString(pShortPuttsPercentage+"%", 1097,(300-(pShortPuttsPercentage*2)-5));
               g.drawString(gstats3.shortPuttsPercentage+"%", 1187,(300-(gstats3.shortPuttsPercentage*2)-5));
               
               
               
               
               //Back Button
               BackBtn(g);
               
   
   }
   
   
   public static void BackBtn(Graphics g){
      
      g.setColor(Color.black);
      g.drawRect(30,30,60,30);
      
      Color az = new Color(240,255,255);
      g.setColor(az);
      g.fillRect(32,32,58,28);

      
      g.setColor(Color.black);
      g.drawString("Menu", 43,50);
      
   }

   
   
   
   public void mouseClicked(MouseEvent e){
      
      mX =  MouseInfo.getPointerInfo().getLocation().x;
      mY = MouseInfo.getPointerInfo().getLocation().y;
      isClick=true;
           
      
      //System.out.println(mX+", "+mY);
      
      if((mX>170 && mX<470) && (mY>230 && mY<430)){
         choose=1;         
      
      } else if((mX>870 && mX<1170) && (mY>230 && mY<430)){
         choose=2;
         
      } else if((mX>115 && mX<175) && (mY>60 && mY<85)){
         choose=3;
      }
      
      
      //System.out.println("Clicked after: "+choose);
      
            
   } 
   
   public void mouseEntered(MouseEvent e){ }
   public void mouseExited(MouseEvent e){ }
   public void mousePressed(MouseEvent e){ }
   public void mouseReleased(MouseEvent e){ isClick=false; }
   public void mouseDragged(MouseEvent e){ }
   public void mouseMoved(MouseEvent e){ }
   
   
      
}





