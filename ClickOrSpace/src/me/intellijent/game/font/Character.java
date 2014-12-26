package me.intellijent.game.font;

public class Character {
	private char c;
	private float[] texCoords;
	private int width;
	private int height;
	
	public Character(char c) {
		this(c, 4, 5); // defaults = width: 4 & height: 5
	}
	
	public Character(char c, int width) {
		this(c, width, 5); //defaults = height: 5
	}
	
	public Character(char c, int width, int height) {
		this.c = c;
		this.width = width;
		this.height = height;
	}

	public void setTexCoords(float[] texCoords) {
		if(this.texCoords == null)
			this.texCoords = texCoords;
	}
	
	public char getChar() {
		return c;
	}
	
	public float[] getCoords() {
		return texCoords;
	}

	public float[] getTexCoords() {
		return texCoords;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
}