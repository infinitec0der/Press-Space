package me.intellijent.game.gui;

import java.util.ArrayList;

import me.intellijent.game.MainComponent;

public class GuiScreen extends Gui {
	
	protected ArrayList<GuiComponent> components;
	
	public GuiScreen() {
		components = new ArrayList<GuiComponent>();
	}
	
	public void actionClicked(int component) { }
	
	public boolean doesGuiStopGame() {
		return true;
	}
	
	public void input(float x, float y) {
		for(GuiComponent component : components) {
			component.input(x, y);
		}
	}
	
	public void drawScreen(float x, float y, float delta) {
		this.drawDefaultBackground();
		for(GuiComponent component : components) {
			component.update(delta);
			component.render();
		}
	}
	
	public void drawDefaultBackground() {
		super.drawRect(0, 0, MainComponent.WIDTH, MainComponent.HEIGHT, 0xB3000000);
	}
	
}
