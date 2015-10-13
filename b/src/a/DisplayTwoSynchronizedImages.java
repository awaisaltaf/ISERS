package a;

import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.sun.media.jai.widget.DisplayJAI;

/**
 * 19 * This class represents a JPanel which contains two scrollable instances
 * of  DisplayJAI. The scrolling bars of both images are synchronized so
 * scrolling one image will automatically scroll the other. 22
 */
public class DisplayTwoSynchronizedImages extends JPanel implements
		AdjustmentListener {
	/** The DisplayJAI for the first image. */
	protected DisplayJAI dj1;
	/** The DisplayJAI for the second image. */
	protected DisplayJAI dj2;
	/** The JScrollPane which will contain the first of the images */
	protected JScrollPane jsp1;
	/** The JScrollPane which will contain the second of the images */
	protected JScrollPane jsp2;

	/**
	 * 35 * Creates an instance of this class, setting the components' layout,
	 * creating 36 * two instances of DisplayJAI for the two images and
	 * creating/registering 37 * event handlers for the scroll bars. 38 * 39 * @param
	 * im1 the first image (left side) 40 * @param im2 the second image (right
	 * side) 41
	 */
	public DisplayTwoSynchronizedImages(RenderedImage im1, RenderedImage im2) {
		super();
		setLayout(new GridLayout(1, 2));
		dj1 = new DisplayJAI(im1); // Instances of DisplayJAI for the
		dj2 = new DisplayJAI(im2); // two images
		jsp1 = new JScrollPane(dj1); // JScrollPanes for the both
		jsp2 = new JScrollPane(dj2); // instances of DisplayJAI
		add(jsp1);
		add(jsp2);
		// Retrieve the scroll bars of the images and registers adjustment
		// listeners to them.
		// Horizontal scroll bar of the first image.
		jsp1.getHorizontalScrollBar().addAdjustmentListener(this);
		// Vertical scroll bar of the first image.
		jsp1.getVerticalScrollBar().addAdjustmentListener(this);
		// Horizontal scroll bar of the second image.
		jsp2.getHorizontalScrollBar().addAdjustmentListener(this);
		// Vertical scroll bar of the second image.
		jsp2.getVerticalScrollBar().addAdjustmentListener(this);
	}

	/**
	 * This method changes the first image to be displayed. 
	 *  * @param
	 * newImage the new first image. 
	 */
	public void setImage1(RenderedImage newimage) {
		dj1.set(newimage);
		repaint();
	}

	/**
	 * 74 * This method changes the second image to be displayed. 75 * @param
	 * newImage the new second image. 76
	 */
	public void setImage2(RenderedImage newimage) {
		dj2.set(newimage);
		repaint();
	}

	/**
	 * 84 * This method returns the first image. 
	 * @return the first image.
	 * 86
	 */
	public RenderedImage getImage1() {
		return dj1.getSource();
	}

	/**
	 * 93 * This method returns the second image. 
	 * @return the second image.
	 * 95
	 */
	public RenderedImage getImage2() {
		return dj2.getSource();
	}

	/**
	 * 102 * This method will be called when any of the scroll bars of the
	 * instances of  DisplayJAI are changed. The method will adjust the
	 * scroll bar of the other DisplayJAI as needed. 
	 *  @param e
	 * the AdjustmentEvent that occurred (meaning that one of the scroll bars position has changed
	 */
	public void adjustmentValueChanged(AdjustmentEvent e) {
		// If the horizontal bar of the first image was changed...
		if (e.getSource() == jsp1.getHorizontalScrollBar()) {
			// We change the position of the horizontal bar of the second image.
			jsp2.getHorizontalScrollBar().setValue(e.getValue());
		}
		// If the vertical bar of the first image was changed...
		if (e.getSource() == jsp1.getVerticalScrollBar()) {
			// We change the position of the vertical bar of the second image.
			jsp2.getVerticalScrollBar().setValue(e.getValue());
		}
		// If the horizontal bar of the second image was changed...
		if (e.getSource() == jsp2.getHorizontalScrollBar()) {
			// We change the position of the horizontal bar of the first image.
			jsp1.getHorizontalScrollBar().setValue(e.getValue());
		}
		// If the vertical bar of the second image was changed...
		if (e.getSource() == jsp2.getVerticalScrollBar()) {
			// We change the position of the vertical bar of the first image.
			jsp1.getVerticalScrollBar().setValue(e.getValue());
		}
	} // end adjustmentValueChanged
	
	
}
