package it.polimi.swimv2.webutils;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {
	
	private ImageUtils() {  }

	/**
	 * Resize an image, if needed, to fit a maxWidth x maxHeight space. If the
	 * proportions are not correct, then cropping will occur
	 * 
	 * @throws IOException
	 */
	public static byte[] getScaledInstance(int width, int height, BufferedImage img) throws IOException {
		
		double mr = width / height;
		double or = img.getWidth() / img.getHeight();
		double scaleFactor = 0;
		if(mr > or) {
			scaleFactor = (float) width / (float) img.getWidth();
		} else {
			scaleFactor = (float) height / (float) img.getHeight();
		}

		BufferedImage after = scale(img, scaleFactor);
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();		
		BufferedImage newimg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		
		if(mr > or) {
			int offset = (after.getHeight() - height) / 2;
			newimg.getGraphics().drawImage(after, 0, 0, width, height, 0,
					offset, width, offset + height, null);
		} else {
			int offset = (after.getWidth() - width) / 2;
			newimg.getGraphics().drawImage(after, 0, 0, width, height, offset,
					0, offset + width, height, null);
		}
		
		ImageIO.write(newimg, "png", bytestream);
		return bytestream.toByteArray();
	}
	
	private static BufferedImage scale(BufferedImage original, double scaleFactor) {
		BufferedImage after = new BufferedImage((int) (original.getWidth() * scaleFactor),
				(int) (original.getHeight() * scaleFactor),
				BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(scaleFactor, scaleFactor);
		AffineTransformOp scaleOp = new AffineTransformOp(at,
				AffineTransformOp.TYPE_BILINEAR);
		return scaleOp.filter(original, after);
	}
}
