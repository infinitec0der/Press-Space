package me.intellijent.game.gui;

import org.lwjgl.util.vector.Vector2f;

public abstract class GuiComponent extends Gui {
	
	private Vector2f position;
	private GuiScreen parent;
	
	public GuiComponent(GuiScreen parent, float x, float y) {
		this(parent, new Vector2f(x, y));
	}
	
	public GuiComponent(GuiScreen parent, Vector2f position) {
		this.parent = parent;
		this.position = position;
	}
	
	public abstract void input(float x, float y);
	public abstract void render();
	public abstract void update(float delta);
	
	public Vector2f position() {
		return position;
	}
	
	public GuiScreen parent() {
		return parent;
	}
	
}
