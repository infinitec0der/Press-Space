package me.intellijent.game;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;

public class Texture
{
	private int id;
	private int width;
	private int height;
	
	public Texture(String fileName)
	{
		loadTexture(fileName);
	}
	
	public Texture(int id)
	{
		this.id = id;
	}
	
	public void bind()
	{
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public int getID()
	{
		return id;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	private void loadTexture(String fileName)
	{
		try {
		IntBuffer ib = BufferUtils.createIntBuffer(1);
	      
	      glGenTextures(ib);
	      int id = ib.get(0);
	      
	      glBindTexture(GL_TEXTURE_2D, id);
	      
	      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
          glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
          glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
          glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		
		  BufferedImage img = ImageIO.read(new File("./res/textures/" + fileName));
	      int w = img.getWidth();
	      int h = img.getHeight();
	      
	      this.width = w;
	      this.height = h;
	      
	      ByteBuffer pixels = BufferUtils.createByteBuffer(w * h * 4);
	      int[] rawPixels = new int[w * h];
	      img.getRGB(0, 0, w, h, rawPixels, 0, w);
	      
	      /*int[] temp = new int[rawPixels.length];
			
			for(int i = 0; i < w; i++)
				for(int j = 0; j < h; j++)
					temp[i + j * w] = rawPixels[i + (h - j - 1)  * w];
			
			rawPixels = temp;*/
	      
	      for (int i = 0; i < rawPixels.length; i++)
	      {
	        int a = rawPixels[i] >> 24 & 0xFF;
	        int r = rawPixels[i] >> 16 & 0xFF;
	        int g = rawPixels[i] >> 8 & 0xFF;
	        int b = rawPixels[i] & 0xFF;
	        
	        rawPixels[i] = (a << 24 | b << 16 | g << 8 | r);
	      }
	      pixels.asIntBuffer().put(rawPixels);
	      glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
	      this.id = id;
		} catch(Exception e)
		{
			e.printStackTrace();
			this.id = 0;
		}
	}
	
}