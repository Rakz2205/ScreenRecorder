package recorder;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
 
	public class PopUpBox_RecordingCreationStart extends JFrame implements ActionListener {
		
		private static final long serialVersionUID = 1L;
		JOptionPane pane; 
		JDialog jd;
		JFrame frameCreateRecordingImage = new JFrame();
		Timer t = new Timer(2000,this);
		
	public void DisplayCreateRecording() {
	  t.start(); 
	  frameCreateRecordingImage.setSize(635,188);              
	  frameCreateRecordingImage.setResizable(false);
	  frameCreateRecordingImage.setBackground(Color.BLACK);      
	  frameCreateRecordingImage.setAlwaysOnTop(true);
	  frameCreateRecordingImage.setUndecorated(true);
	  frameCreateRecordingImage.setLocationRelativeTo(null);
      JLabel label = new JLabel();
      URL Start_Active_Path = CTScreenRecorderUI.class.getResource("/resources/CreatingRecording.jpg");
      ImageIcon imgThisImg = new ImageIcon(Start_Active_Path);
      label.setIcon(imgThisImg);      
      label.setBackground(Color.BLACK);
      frameCreateRecordingImage.add(label);	  
      frameCreateRecordingImage.setVisible(true);
      frameCreateRecordingImage.setBackground(Color.BLACK);                  
	  }	  
	  
	  public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == t) { 
	    	frameCreateRecordingImage.dispose();	    
	    t.stop();   
	    System.exit(0);
	    }
	  }
	
}	

	

	