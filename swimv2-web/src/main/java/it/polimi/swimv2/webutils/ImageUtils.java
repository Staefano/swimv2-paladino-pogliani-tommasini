package it.polimi.swimv2.webutils;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtils {

	/**
	 * Resize an image, if needed, to fit a maxWidth x maxHeight space. If the
	 * proportions are not correct, then cropping will occur
	 * 
	 * @throws IOException
	 */
	public static byte[] getScaledInstance(int maxWidth, int maxHeight,
			InputStream image) throws IOException {
		BufferedImage img = ImageIO.read(image);
		int originalWidth = img.getWidth();
		int originalHeight = img.getHeight();

		// resize
		double scaleFactor = (float) maxWidth / (float) originalWidth;

		BufferedImage after = new BufferedImage(maxWidth,
				(int) (originalHeight * scaleFactor),
				BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(scaleFactor, scaleFactor);
		AffineTransformOp scaleOp = new AffineTransformOp(at,
				AffineTransformOp.TYPE_BILINEAR);
		after = scaleOp.filter(img, after);

		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		
		if (after.getHeight() > maxHeight) {
			BufferedImage newimg = new BufferedImage(maxWidth, maxHeight,
					BufferedImage.TYPE_INT_ARGB);
			int offset = (after.getHeight() - maxHeight) / 2;
			newimg.getGraphics().drawImage(after, 0, 0, maxWidth, maxHeight, 0,
					offset, maxWidth, offset + maxHeight, null);
			ImageIO.write(newimg, "png", bytestream);
			
		} else {
			ImageIO.write(after, "png", bytestream);
		}

		return bytestream.toByteArray();
	}
}
