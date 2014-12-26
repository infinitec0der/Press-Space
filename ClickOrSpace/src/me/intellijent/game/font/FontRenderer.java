package me.intellijent.game.font;

import static org.lwjgl.opengl.GL11.*;
import me.intellijent.game.Texture;
import me.intellijent.game.gui.Gui;
public class FontRenderer extends Gui {
	private Texture font;
	
	public FontRenderer() {
		if(font == null) {
			font = new Texture("font.png");
			for(int i=0;i<characters.length;i++) {
				for(int j=0;j<characters[i].length; j++) {
					Character character = characters[i][j];
					
					int startX = 0;
					int startY = i*(character.getHeight()+1)/*12 - i*(character.getHeight()+1)*/;
					
					for(int k=0;k<j;k++) {
						Character character2 = characters[i][k];
						startX += character2.getWidth() + 1;
					}
					
					float[] coords = super.calcTexCoords(font, startX, startY, character.getWidth(), character.getHeight());
					character.setTexCoords(coords);
				}
			}
		}
	}
	
	public boolean contains(char c) {
		for(Character[] chars : characters) {
			for(Character c2 : chars) {
				if(("" + c2.getChar()).equalsIgnoreCase("" + c))
					return true;
			}
		}
		return false;
	}
	
	public float getStringWidth(String s, float scale) {
		int strWidth = 0;
		for(String s1 : s.split("")) {
			char c = s1.toUpperCase().charAt(0);
			Character character = getCharacter(c);
			strWidth += (character.getWidth() + 1) * scale;
		}
		return strWidth;
	}
	
	public float getHeight(float scale) {
		return characters[0][0].getHeight() * scale;
	}
	
	public Character[][] characters = new Character[][] {
		new Character[] {
			new Character('A'),
			new Character('B'),
			new Character('C', 3),
			new Character('D'),
			new Character('E', 3),
			new Character('F', 3),
			new Character('G'),
			new Character('H'),
			new Character('I', 3),
			new Character('J'),
			new Character('K'),
			new Character('L', 3),
			new Character('M', 5),
			new Character('N'),
			new Character('O'),
			new Character('P'),
			new Character('Q'),
			new Character('R'),
			new Character('S'),
			new Character('T', 3),
			new Character('U'),
			new Character('V', 5),
			new Character('W', 5),
			new Character('X'),
			new Character('Y', 5),
			new Character('Z'),
		},
		
		new Character[] {
			new Character('0'),
			new Character('1', 1),
			new Character('2'),
			new Character('3'),
			new Character('4'),
			new Character('5'),
			new Character('6'),
			new Character('7'),
			new Character('8'),
			new Character('9'),
			new Character('!', 1),
			new Character('.', 1),
			new Character(';', 2),
			new Character(':', 1),
			new Character('>', 3),
			new Character('<', 3),
			new Character('#', 5),
			new Character('(', 2),
			new Character(')', 2),
			new Character('[', 2),
			new Character(']', 2),
			new Character('-'),
			new Character('_'),
			new Character('+', 5),
			new Character('='),
			new Character('{', 3),
			new Character('}', 3),
			new Character('|', 1),
			new Character('\\', 5),
			new Character('/', 6),
		},
		
		new Character[] {
			new Character('?', 3),
			new Character(',', 2),
			new Character('"', 3),
			new Character('\'', 2),
			new Character(' ', 2),
		}
	};
	
	public void drawStringWithShadow(String s, float x, float y, float scale) {
		drawStringWithShadow(s, x, y, scale, 0xffffffff);
	}
	
	public void drawString(String s, float x, float y, float scale) {
		this.drawString(s, x, y, scale, 0xffffffff);
	}
	
	public void drawStringWithShadow(String s, float x, float y, float scale, int color) {
		if(s.length() == 0) return;
		float a = (float)(color >> 24 & 255) / 255.0F;
		float r = (float)(color >> 16 & 255) / 255.0F;
		float g = (float)(color >> 8 & 255) / 255.0F;
		float b = (float)(color & 255) / 255.0F;
		glPushMatrix();
		{
			glLoadIdentity();
			font.bind();
			glColor4f(1, 1, 1, 1);
			
			float curX = (x + .5f*scale);
			float curY = (y - .5f*scale);
			
			for(String s1 : s.split("")) {
				char c = s1.toUpperCase().charAt(0);
				Character character = getCharacter(c);
				float[] texCoords = character.getCoords();
				glColor4f(r * .35f, g * .35f, b * .35f, a);
				glBegin(GL_QUADS);
				{
					glTexCoord2f(texCoords[0], texCoords[1]);
			        glVertex2f(curX, curY);
			        glTexCoord2f(texCoords[0], texCoords[3]);
			        glVertex2f(curX, curY + character.getHeight() * scale);
			        glTexCoord2f(texCoords[2], texCoords[3]);
			        glVertex2f(curX + character.getWidth() * scale, curY + character.getHeight() * scale);
			        glTexCoord2f(texCoords[2], texCoords[1]);
			        glVertex2f(curX + character.getWidth() * scale, curY);
				}
				glEnd();
				curX += (character.getWidth() + 1) * scale;
			}
		}
		glPopMatrix();
		drawString(s, x, y, scale, color);
	}
	
	public void drawStringWithShadow(String s, float x, float y, float scale, float alpha, int color) {
		float a = (float)(color >> 24 & 255) / 255.0F;
		float r = (float)(color >> 16 & 255) / 255.0F;
		float g = (float)(color >> 8 & 255) / 255.0F;
		float b = (float)(color & 255) / 255.0F;
		glPushMatrix();
		{
			glLoadIdentity();
			font.bind();
			glColor4f(1, 1, 1, 1);
			
			float curX = (x + .5f*scale);
			float curY = (y - .5f*scale);
			
			for(String s1 : s.split("")) {
				char c = s1.toUpperCase().charAt(0);
				Character character = getCharacter(c);
				float[] texCoords = character.getCoords();
				glColor4f(r * .35f, g * .35f, b * .35f, alpha > a ? a : alpha);
				glBegin(GL_QUADS);
				{
					glTexCoord2f(texCoords[0], texCoords[1]);
			        glVertex2f(curX, curY);
			        glTexCoord2f(texCoords[0], texCoords[3]);
			        glVertex2f(curX, curY + character.getHeight() * scale);
			        glTexCoord2f(texCoords[2], texCoords[3]);
			        glVertex2f(curX + character.getWidth() * scale, curY + character.getHeight() * scale);
			        glTexCoord2f(texCoords[2], texCoords[1]);
			        glVertex2f(curX + character.getWidth() * scale, curY);
				}
				glEnd();
				curX += (character.getWidth() + 1) * scale;
			}
		}
		glPopMatrix();
		drawString(s, x, y, scale, alpha, color);
	}
	
	public void drawString(String s, float x, float y, float scale, float alpha, int color) {
		float a = (float)(color >> 24 & 255) / 255.0F;
		float r = (float)(color >> 16 & 255) / 255.0F;
		float g = (float)(color >> 8 & 255) / 255.0F;
		float b = (float)(color & 255) / 255.0F;
		/*x *= scale;
		y *= scale;*/
		glPushMatrix();
		{
			glLoadIdentity();
			font.bind();
			glColor4f(1, 1, 1, 1);
			
			float curX = x;
			
			for(String s1 : s.split("")) {
				char c = s1.toUpperCase().charAt(0);
				Character character = getCharacter(c);
				float[] texCoords = character.getCoords();
				glColor4f(r, g, b, alpha > a ? a : alpha);
				glBegin(GL_QUADS);
				{
					glTexCoord2f(texCoords[0], texCoords[1]);
			        glVertex2f(curX, y);
			        glTexCoord2f(texCoords[0], texCoords[3]);
			        glVertex2f(curX, y + character.getHeight() * scale);
			        glTexCoord2f(texCoords[2], texCoords[3]);
			        glVertex2f(curX + character.getWidth() * scale, y + character.getHeight() * scale);
			        glTexCoord2f(texCoords[2], texCoords[1]);
			        glVertex2f(curX + character.getWidth() * scale, y);
				}
				glEnd();
				curX += (character.getWidth() + 1) * scale;
			}
		}
		glPopMatrix();
	}
	
	public void drawString(String s, float x, float y, float scale, int color) {
		float a = (float)(color >> 24 & 255) / 255.0F;
		float r = (float)(color >> 16 & 255) / 255.0F;
		float g = (float)(color >> 8 & 255) / 255.0F;
		float b = (float)(color & 255) / 255.0F;
		/*x *= scale;
		y *= scale;*/
		glPushMatrix();
		{
			glLoadIdentity();
			font.bind();
			glColor4f(1, 1, 1, 1);
			
			float curX = x;
			
			for(String s1 : s.split("")) {
				char c = s1.toUpperCase().charAt(0);
				Character character = getCharacter(c);
				float[] texCoords = character.getCoords();
				glColor4f(r, g, b, a);
				glBegin(GL_QUADS);
				{
					glTexCoord2f(texCoords[0], texCoords[1]);
			        glVertex2f(curX, y);
			        glTexCoord2f(texCoords[0], texCoords[3]);
			        glVertex2f(curX, y + character.getHeight() * scale);
			        glTexCoord2f(texCoords[2], texCoords[3]);
			        glVertex2f(curX + character.getWidth() * scale, y + character.getHeight() * scale);
			        glTexCoord2f(texCoords[2], texCoords[1]);
			        glVertex2f(curX + character.getWidth() * scale, y);
				}
				glEnd();
				curX += (character.getWidth() + 1) * scale;
			}
		}
		glPopMatrix();
	}
	
	public Character getCharacter(char c) {
		for(int i=0;i<characters.length;i++) {
			for(int j=0;j<characters[i].length; j++) {
				Character character = characters[i][j];
				if(character.getChar() == c)
					return character;
			}
		}
		return characters[2][0];
	}
}
