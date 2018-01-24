package fire;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

public class FlameGrabber {

	static BufferedImage Img;
	
	public static Color grabColor(String filepath) throws InterruptedException  {
		
		
		try {
			Img = ImageIO.read(new File(filepath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int h = Img.getHeight();
		int w = Img.getWidth();
		
		PixelGrabber pg = new PixelGrabber(Img, 0, 0, w, h, true);
		pg.grabPixels();
		int[] pix = (int[]) pg.getPixels();

		Arrays.sort(pix);
		
		HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
		for (int p : pix)
		{
			// discard any pixel where all three values are the same
			// or even close
			// TODO without color
			Color c = new Color(p);
			int min = c.getBlue() - 20;
			int max = c.getBlue() + 20;
			if ((c.getRed() > min && c.getRed() < max) && (c.getGreen() > min && c.getGreen() < max))
				continue;
			
			int minLight = 150;
			// discard if values are too dark
			if (c.getBlue() < minLight && c.getGreen() < minLight && c.getRed() < minLight)
				continue;
					
			
			if (hm.get(p) == null)
				hm.put(p, 1);
			else
				hm.put(p, hm.get(p)+1);
	
			//System.out.println(c + " " + p);
		}

		int[] max = {0,0};   // stores only one match but this is okay for us

		
		// look through the hashmap and find the largest count
		for (Entry<Integer,Integer> k : hm.entrySet())
		{
			if (max[1] < k.getValue())
			{
				max[0] = k.getKey();
				max[1] = k.getValue();
			}
		}
		
		return new Color(max[0]);
		
	}
	

}
