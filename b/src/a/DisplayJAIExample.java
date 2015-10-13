package a;

/*
  * Part of the Java Image Processing Cookbook, please see
  * http://www.lac.inpe.br/~rafael.santos/JIPCookbook.jsp
  * for information on usage and distribution.
  * Rafael Santos (rafael.santos@lac.inpe.br)
  */

  
 import java.awt.BorderLayout;
import java.awt.Container;
  
import java.io.File;

 import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
  

import com.sun.media.jai.widget.DisplayJAI;
  
 /**
  * This application shows how to use the DisplayJAI class with a JScrollPane to display images.
  */
 public class DisplayJAIExample
   {
	 
   /**
    * Entry point for this application.
    * @param args an image file name.
    */
   public static void main(String[] args)
     {
     // Load the image which file name was passed as the first argument to the
     // application.
	   File file = new File("C:\\Users\\Aweel Ahmad\\Desktop\\a\\lena.JPG");
     PlanarImage image = JAI.create("fileload", file.getAbsolutePath());
     // Get some information about the image
     String imageInfo = "Dimensions: "+image.getWidth()+"x"+image.getHeight()+
                        " Bands:"+image.getNumBands();
     System.out.println(imageInfo);
     
     // Create a frame for display.
     JFrame frame = new JFrame();
     frame.setTitle("DisplayExampleJAI");
     // Get the JFrame's ContentPane.
     Container contentPane = frame.getContentPane();
     contentPane.setLayout(new BorderLayout());
     // Create an instance of DisplayJAI.
     DisplayJAI dj = new DisplayJAI(image);
     // Add to the JFrame's ContentPane an instance of JScrollPane containing the
     // DisplayJAI instance.
     contentPane.add(new JScrollPane(dj),BorderLayout.CENTER);
     // Add a text label with the image information.
     contentPane.add(new JLabel(imageInfo),BorderLayout.SOUTH);
     // Set the closing operation so the application is finished.
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(400,400); // adjust the frame size.
     frame.setVisible(true); // show the frame.
    
     }
}