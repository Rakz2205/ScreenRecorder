package recorder;

import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Rectangle;	
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.concurrent.atomic.AtomicBoolean;


@SuppressWarnings("serial")
public class CTScreenRecorderUI extends JDialog implements Runnable{
       
	   static UpdatedPath LatestPath = new UpdatedPath();
	   static CreateFolder FCreation = new CreateFolder();
	   static CreateVideoFinal VideoCreation = new CreateVideoFinal();
	   static DeleteFolder FolderDelete = new DeleteFolder();
	   static PopUpBox_RecordingCreationStart CreateRecording = new PopUpBox_RecordingCreationStart();
	   
	   
	   //String fileName = FolderPath;
	   public static String FolderPathMain;
	   public static String FolderPath;
	   public static String FolderPathTemp;
	   public static String RecordingPath;
	   public static String ResolutionType;
	   public static int  tempResolution;
	
	
          private Thread worker;
          private final AtomicBoolean running = new AtomicBoolean(false);
          private int interval;
          
          public boolean isCapture;
          public int counter=0;
          public static CTScreenRecorderUI threadClass;
   
          
    public void run(){  
                 
           //System.out.println("My thread is in running state.");  
           running.set(true);
        while (running.get()) {
               
                     //System.out.println(counter++);
                     //Thread.sleep(50);
              
              try {
                           startCapture();
                     } catch (InterruptedException e1) {
                           // TODO Auto-generated catch block
                           e1.printStackTrace();
                     }
            try { 
                Thread.sleep(interval); 
            } catch (InterruptedException e){ 
                Thread.currentThread().interrupt();
                
            }
            // do something here 
         } 
         } 
   
          public CTScreenRecorderUI(String str){
                    //multi threding
                 }
          
   public void ControlSubThread(int sleepInterval) {
       interval = sleepInterval;
   }
   
   public void start() {
       worker = new Thread(this);
       worker.start();
   }
   
   public void stop(){
              
                     stopCapture();
              running.set(false);
              
          }
       
   public void startCapture() throws InterruptedException
   {
          isCapture=true;
          while(isCapture==true)
          {
        	 
        	 // @SuppressWarnings("unused")
			//CaptureScreen cs = new CaptureScreen();
        	  //ScreenCapture.ScreenShotsSaveMain(FolderPath);
        	  
        	  try {
  			Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
  			URL cursor_img = CTScreenRecorderUI.class.getResource("/resources/cursor.jpg");
  			Image cursor = Toolkit.getDefaultToolkit().getImage(cursor_img);
  			int x = MouseInfo.getPointerInfo().getLocation().x;
  			int y = MouseInfo.getPointerInfo().getLocation().y;
  			long timeNow = System.currentTimeMillis();  			
  			BufferedImage screenCapture = new Robot().createScreenCapture(screen);
  			Graphics2D graphics2D = screenCapture.createGraphics();
  			graphics2D.drawImage(cursor, x, y, 30, 30, null); // cursor.gif is 16x16 size.
  			ImageIO.write(screenCapture, "jpeg",  new File(FolderPath + timeNow +".jpeg"));
  			
  			
  			if (tempResolution == 0)  {  				
  				Thread.sleep(72);	
  			}
  			
  			
        	  }
        	  catch (Exception e) {
      			e.printStackTrace();
      		}
        	  
        	  
        	  
        	  /*
      		try {
      			long timeNow = System.currentTimeMillis();
    			Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    			BufferedImage screenCapture = new Robot().createScreenCapture(screen);
    			
    			Image cursor = ImageIO.read(new File("C:\\Users\\siddheshs\\Desktop\\Images\\cursor.png"));
    			int x = MouseInfo.getPointerInfo().getLocation().x;
    			int y = MouseInfo.getPointerInfo().getLocation().y;
    			
    			Graphics2D graphics2D = screenCapture.createGraphics();
    			graphics2D.drawImage(cursor, x, y, 16, 16, null); // cursor.gif is 16x16 size.
    			ImageIO.write(screenCapture, "jpeg", new File(FolderPath + timeNow +".jpeg"));
    			
    			
    			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    			Rectangle screenRectangle = new Rectangle(screenSize);
    			Robot robot = new Robot();
    			BufferedImage image = robot.createScreenCapture(screenRectangle);  
    			long timeNow = System.currentTimeMillis();
    			
		        //System.out.println("Current Date Time in specified format: "+newFormat);
				ImageIO.write(image, "jpeg", new File(FolderPath + timeNow +".jpeg"));

    		} catch (Exception e) {
    			e.printStackTrace();
    		}

          
          */
          }
   }
   
   public void stopCapture()
   {
          isCapture=false;
          
          
   }
       static int resumeCounter=1;
       static boolean startFlag=false;
	
       public CTScreenRecorderUI(JFrame frame, String str) {
        super(frame, str);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
            	
            	FolderDelete.DeleteFolderCreated(FolderPath);
            	FolderDelete.DeleteFolderCreated(FolderPathTemp);
            	FolderDelete.DeleteFolderCreated(FolderPathMain);
                System.exit(0);
                
            }
        });
    }
       @SuppressWarnings({ "deprecation", "unchecked", "static-access" })
	public static void main(String[] args) 
       {
    	   
              threadClass=new CTScreenRecorderUI("MultiThreading");
              
              //UAT Changes - Updating CT Recorders
              //URL ctlogoPath = CTScreenRecorderUI.class.getResource("/resources/citiustech-squarelogo-Customized.png");
              URL ctlogoPath = CTScreenRecorderUI.class.getResource("/resources/UAT-Logo.jpg");
              URL Start_Active_Path = CTScreenRecorderUI.class.getResource("/resources/StartActive.jpg");
              URL Start_Inactive_Path = CTScreenRecorderUI.class.getResource("/resources/StartInactive.jpg");
              URL Pause_Inactive_Path = CTScreenRecorderUI.class.getResource("/resources/PauseInactive.jpg");
              URL Pause_active_Path = CTScreenRecorderUI.class.getResource("/resources/PauseActive.jpg");
              URL Stop_Inactive_Path = CTScreenRecorderUI.class.getResource("/resources/StopInactive.jpg");
              URL Stop_active_Path = CTScreenRecorderUI.class.getResource("/resources/StopActive.jpg");
              URL Resume_active_Path = CTScreenRecorderUI.class.getResource("/resources/ResumeActive.jpg");
              URL url = CTScreenRecorderUI.class.getResource("/resources/Compressed_Resized.gif");
              URL InitialPlainLine = CTScreenRecorderUI.class.getResource("/resources/Plain_Text_Final.png");
          
              // UAT Changes - Updating myHeader value
              //String myheader = "CT Screen Recorder";
              String myheader = "Screen Recorder";
              JFrame frame = new JFrame(myheader);
              FlowLayout experimentLayout = new FlowLayout();
              
              Icon icon = new ImageIcon(url);
              JLabel label2 = new JLabel(icon);
              
              
              Icon PlainLineIcon = new ImageIcon(InitialPlainLine);
              JLabel label3 = new JLabel(PlainLineIcon);
              
              
              experimentLayout.setHgap(0);
              experimentLayout.setVgap(0);
              experimentLayout.setAlignment(experimentLayout.LEFT);
              
              
              frame.add(label3);
              //frame.getContentPane().add(label2);
              //frame.setSize(356,102);
              frame.setSize(359,103);
              frame.setResizable(false);
              frame.setBackground(Color.BLACK);
              frame.setLayout(experimentLayout);
              frame.setAlwaysOnTop(true);
              frame.setLocation(40,40);
              frame.setUndecorated(false);
              frame.setLocationRelativeTo(null);
              
              frame.addWindowListener(new WindowAdapter() {
              public void windowClosing(WindowEvent evt) {
            	FolderDelete.DeleteFolderCreated(FolderPath);
            	FolderDelete.DeleteFolderCreated(FolderPathTemp);
            	FolderDelete.DeleteFolderCreated(FolderPathMain);
            	
                System.exit(0);
            }
        });
              
              
              Image img = Toolkit.getDefaultToolkit().getImage(ctlogoPath);
              frame.setIconImage(img);
              
              
              
              
              JButton startButton= new JButton();
              JButton pauseButton= new JButton();
              JButton stopButton= new JButton();
              
              startButton.setBackground(Color.BLACK);
              startButton.setForeground(Color.BLACK);
              startButton.setIcon(new javax.swing.ImageIcon(Start_Active_Path));
              startButton.setSize(15, 15);
              startButton.setBorderPainted(true);
              startButton.setFocusPainted(false);
              startButton.setToolTipText("Start Button");
              
              pauseButton.setBackground(Color.BLACK);              
              pauseButton.setForeground(Color.BLACK);
              pauseButton.setIcon(new javax.swing.ImageIcon(Pause_Inactive_Path));
              pauseButton.setSize(15,15);
              pauseButton.setBorderPainted(true);
              pauseButton.setFocusPainted(false);
              pauseButton.disable();
              //pauseButton.setEnabled(false);
              pauseButton.setToolTipText("Pause Button");
              
              stopButton.setBackground(Color.BLACK);
              stopButton.setForeground(Color.BLACK);
              stopButton.setIcon(new javax.swing.ImageIcon(Stop_Inactive_Path));
              stopButton.setSize(15,15);
              stopButton.setBorderPainted(true);
              stopButton.setFocusPainted(false);
              stopButton.disable();
              stopButton.setToolTipText("Stop Button");
              
              frame.add(startButton);
              frame.add(pauseButton);
              frame.add(stopButton);
              
              
            
              
              
              //Combo box
              String resolution[]={"Low Resolution","Medium Resolution","High Resolution"};        
           @SuppressWarnings("rawtypes")
		JComboBox cb=new JComboBox(resolution);    
           
           cb.setFont(new Font("Monotype Corsiva",Font.BOLD , 18));
           cb.setFocusable(true);
           cb.setBackground(Color.decode("#48b7c5"));//
           cb.setForeground(Color.decode("#030a0a"));
           cb.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
           cb.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
           frame.add(cb);
           
           frame.setBackground(Color.BLACK);
          // frame.getContentPane().setBackground(Color.BLACK);
              frame.setVisible(true);
              
              	FolderPathMain = LatestPath.ModifiedPathMain();
              	FolderPath = LatestPath.ModifiedPath();
              	FolderPathTemp = LatestPath.ModifiedPathTemp();
         	   	RecordingPath = LatestPath.RecordingPath();
         	   	
         	   	//FolderDelete.DeleteFolderCreated(FolderPath);
         	   	
              
              
              //Start Button Action Listener
              startButton.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                       if(!startFlag)
                       {
                              startFlag=true;
                              frame.remove(label3);
                              frame.add(label2);
                              
                              frame.add(startButton);
                              frame.add(pauseButton);
                              frame.add(stopButton);
                              frame.add(cb);
                              
                              startButton.disable();
                              startButton.setIcon(new javax.swing.ImageIcon(Start_Inactive_Path));
                           
                              pauseButton.enable();
                              pauseButton.setIcon(new javax.swing.ImageIcon(Pause_active_Path));
                             
                              stopButton.enable();
                              stopButton.setIcon(new javax.swing.ImageIcon(Stop_active_Path));
                             frame.setState(Frame.ICONIFIED);
                              //cb.hide();
                              //frame.setSize(185, 62); 
                              //cb.setBackground(Color.BLACK);
                              
                               tempResolution=cb.getSelectedIndex();
                              ResolutionType="Low Resolution";
                              if(tempResolution==0)
                            	  ResolutionType="Low Resolution";
                              else if(tempResolution==1)
                            	  ResolutionType="Medium Resolution";
                              else if(tempResolution==2)
                            	  ResolutionType="High Resolution";
                            
                            
                            FCreation.FolderCreation(RecordingPath); // UAT Changes - Sequence from Last to First to create sub folders inside
                            FCreation.FolderCreation(FolderPathMain);
                            FCreation.FolderCreation(FolderPath);
                            FCreation.FolderCreation(FolderPathTemp);
                       	   	
                            threadClass.start();
                       }
                       
                 }
              });
              
              //pause Button Action Listener
              pauseButton.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                       
                       if(startFlag)
                       {
                              resumeCounter++;
                              if(resumeCounter%2 ==0)
                              {
                                     pauseButton.setIcon(new javax.swing.ImageIcon(Resume_active_Path));
                                     pauseButton.setToolTipText("Resume Button");
                                     threadClass.stop();
                                     
                                     frame.remove(label2);
                                     frame.add(label3);
                                     
                                     frame.add(startButton);
                                     frame.add(pauseButton);
                                     frame.add(stopButton);
                                     frame.add(cb);
                              }
                              else
                              {
                                     pauseButton.setIcon(new javax.swing.ImageIcon(Pause_active_Path));
                                     pauseButton.setToolTipText("Pause Button");
                                     frame.setState(Frame.ICONIFIED);
                                     threadClass.start();
                                     frame.remove(label3);
                                     frame.add(label2);
                                     
                                     frame.add(startButton);
                                     frame.add(pauseButton);
                                     frame.add(stopButton);
                                     frame.add(cb);
                                     
                              }
                       } 
                 }
              });
                           
              //stop Button Action Listener
              stopButton.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                	 
                       if(startFlag)
                       {
                    	   
                              threadClass.stop();
                              frame.dispose();                              
                              try {
                        		  @SuppressWarnings("unused")
								String Resolutiontype = null;                        		
                        		VideoCreation.CreateVideo(ResolutionType);
                        	} catch (IOException error) {
                        		// TODO Auto-generated catch block
                        		error.printStackTrace();
                        	}
                              
                              FolderDelete.DeleteFolderCreated(FolderPath);
                              FolderDelete.DeleteFolderCreated(FolderPathTemp);
                              FolderDelete.DeleteFolderCreated(FolderPathMain);
                              CreateRecording.DisplayCreateRecording();
                       }
                 }
              });
       }

	

}
