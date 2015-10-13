package a;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.awt.image.ComponentColorModel;

import javax.media.jai.IHSColorSpace;
import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.TiledImage;
import javax.swing.JFrame;

/*
 15  * This class demonstrates the DisplayTwoSynchronizedImages component. It loads an
 16  * image, applies a convolve operator and displays the original and convolved image
 17  * side by side.\section{}
 18  
 19  */
public class DemoDisplayTwoSynchronizedImages {
	/**
	 * 23 * The application entry point. * @param args the image file name. 25
	 */
	public static void main(String[] args) {
		// We need one argument - the image file name.

		File file = new File("C:\\Users\\Aweel Ahmad\\Desktop\\a\\lena.JPG");
		// Read the image.
		PlanarImage input = JAI.create("fileload", file.getAbsolutePath());

		// RGB to gresclae
		// BufferedImage bimg=new
		// BufferedImage(input.getWidth(),input.getHeight(),BufferedImage.TYPE_INT_RGB);
		// bimg=input.getAsBufferedImage();
		 Operations op=new Operations();
		// bimg=op.getGreyscaleImage(bimg);
		// PlanarImage output=PlanarImage.wrapRenderedImage(bimg);

		// RGB to IHS
		// ParameterBlock pb = new ParameterBlock();
		// IHSColorSpace ihs= IHSColorSpace.getInstance();
		// ColorModel IHSColorModel =new ComponentColorModel(ihs,new int
		// []{8,8,8},false,false,Transparency.OPAQUE,DataBuffer.TYPE_BYTE);
		// pb.addSource(input);
		// pb.add(IHSColorModel);
		// RenderedImage imageIHS = JAI.create("colorconvert", pb);
		// output=PlanarImage.wrapRenderedImage(imageIHS);

		// RGB to HSV
		BufferedImage bimg = new BufferedImage(input.getWidth(),
				input.getHeight(), BufferedImage.TYPE_INT_RGB);
		bimg = input.getAsBufferedImage();
		double[] hsv = new double[3];
		for (int x = 0; x < bimg.getWidth(); ++x) {
			for (int y = 0; y < bimg.getHeight(); ++y) {
				Color c = new Color(bimg.getRGB(x, y));
				hsv = op.RGBtoHSV(c.getRed(), c.getGreen(), c.getBlue());
				System.out.println(hsv[0] + " "+hsv[1]+" "+hsv[2] );
				int a=c.getAlpha();
				int h = (int) Math.round(hsv[0]);
				int s = (int) Math.round(hsv[1]);
				int v = (int) Math.round(hsv[2]);
				int HSV =(h << 16) | (s << 8) | v;
				bimg.setRGB(x, y, HSV);
			}
		}
		PlanarImage output = PlanarImage.wrapRenderedImage(bimg);

		// BufferedImage bimg=new
		// BufferedImage(input.getWidth(),input.getHeight(),BufferedImage.TYPE_INT_RGB);
		// bimg=input.getAsBufferedImage();
		// Operations op=new Operations();
		// bimg=op.getBlueImage(bimg);
		// PlanarImage output=PlanarImage.wrapRenderedImage(bimg);

		// Create a JFrame for displaying the results.
		JFrame frame = new JFrame();
		// Add to the JFrame's ContentPane an instance of
		// DisplayTwoSynchronizedImages, which will contain the original and
		// processed image.
		frame.getContentPane().add(
				new DisplayTwoSynchronizedImages(input, output));
		// Set the closing operation so the application is finished.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack(); // adjust the frame size using preferred dimensions.
		frame.setVisible(true); // show the frame.
	}
}