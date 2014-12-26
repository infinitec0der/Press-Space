package me.intellijent.game.gui;

import static org.lwjgl.opengl.GL11.*;
import me.intellijent.game.Texture;

public class Gui {
	
	protected float[] calcTexCoords(Texture tex, float x, float y, float width, float height) {
		float srcX = x;
		float srcY = y;
		float srcWidth = width;
		float srcHeight = height;
		
		float u = srcX / tex.getWidth();
		float v = srcY / tex.getHeight();
		float u2 = (srcX + srcWidth) / tex.getWidth();
		float v2 = (srcY + srcHeight) / tex.getHeight();
		
		return new float[] {u, v2, u2, v};
	}
	
	protected void drawRect(float x, float y, float width, float height, int color) {
		float a = (float)(color >> 24 & 255) / 255.0F;
		float r = (float)(color >> 16 & 255) / 255.0F;
		float g = (float)(color >> 8 & 255) / 255.0F;
		float b = (float)(color & 255) / 255.0F;
		
		glPushMatrix();
		glColor4f(1, 1, 1, 1);
		glBindTexture(GL_TEXTURE_2D, 0);
		glDisable(GL_TEXTURE_2D);
		glColor4f(r, g, b, a);
		glBegin(GL_QUADS);
			glVertex2f(x, y);
			glVertex2f(x, height);
			glVertex2f(width, height);
			glVertex2f(width, y);
		glEnd();
		glEnable(GL_TEXTURE_2D);
		glPopMatrix();
	}
	
	protected void drawTexturedRect(float x, float y, float width, float height, float scale, Texture texture, float[] texCoords) {
		this.drawTexturedRect(x, y, x + width * scale, y + height * scale, texture, texCoords);
	}
	
	protected void drawFlippedXTexturedRect(float x, float y, float width, float height, float scale, Texture texture, float[] texCoords) {
		this.drawTexturedRect(x, y, x + width * scale, y + height * scale, texture, new float[] {texCoords[2], texCoords[1], texCoords[0], texCoords[3]});
	}
	
	protected void drawFlippedXTexturedRect(float x, float y, float scale, Texture texture) {
		this.drawTexturedRect(x, y, x + texture.getWidth() * scale, y + texture.getHeight() * scale, texture, new float[] {1, 1, 0, 0});
	}
	
	protected void drawTexturedRect(float x, float y, float scale, Texture texture) {
		this.drawTexturedRect(x, y, x + texture.getWidth() * scale, y + texture.getHeight() * scale, texture, new float[] {0, 1, 1, 0});
	}
	
	protected void drawTexturedRect(float x, float y, Texture texture) {
		this.drawTexturedRect(x, y, x + texture.getWidth(), y + texture.getHeight(), texture, new float[] {0, 0, 1, 1});
	}
	
	protected void drawTexturedRect(float x, float y, float width, float height, Texture texture, float[] texCoords) {
		glPushMatrix();
		{
			glLoadIdentity();
			texture.bind();
			glColor4f(1, 1, 1, 1);
			
			glBegin(GL_QUADS);
			{
				glTexCoord2f(texCoords[0], texCoords[1]);
		        glVertex2f(x, y);
		        glTexCoord2f(texCoords[0], texCoords[3]);
		        glVertex2f(x, height);
		        glTexCoord2f(texCoords[2], texCoords[3]);
		        glVertex2f(width, height);
		        glTexCoord2f(texCoords[2], texCoords[1]);
		        glVertex2f(width, y);
			}
			glEnd();
		}
		glPopMatrix();
	}
	
}
