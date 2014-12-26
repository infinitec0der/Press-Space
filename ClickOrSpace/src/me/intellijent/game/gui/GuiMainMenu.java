package me.intellijent.game.gui;

import me.intellijent.game.Constants;
import me.intellijent.game.MainComponent;
import me.intellijent.game.font.FontRenderer;

public class GuiMainMenu extends GuiScreen {
	
	private FontRenderer fontRenderer;
	
	public GuiMainMenu() {
		fontRenderer = new FontRenderer();
		this.components.add(new GuiButton(this, "Play", MainComponent.WIDTH / 2 - fontRenderer.getStringWidth("Play", Constants.guiButtonSize()) / 2, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*5));
		this.components.add(new GuiButton(this, "Quit", MainComponent.WIDTH / 2 - fontRenderer.getStringWidth("Quit", Constants.guiButtonSize()) / 2, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*6));
	}
	
	public void actionClicked(int component) {
		switch(component) {
			case 0:
				MainComponent.getInstance().setGuiScreen(null);
				break;
			case 1:
				MainComponent.getInstance().setGuiScreen(new GuiQuit());
				break;
		}
	}
	
	public void input(float x, float y) {
		super.input(x, y);
	}
	
	float f = 0;
	
	public void drawScreen(float x, float y, float delta) {
		super.drawScreen(x, y, delta);
		
		f += delta * 20;
		
		String text = "Press space";
		fontRenderer.drawStringWithShadow(text, MainComponent.WIDTH / 2 - fontRenderer.getStringWidth(text, Constants.titleFontSize())/2, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*3, Constants.titleFontSize(), 0xffffffff);
		
		fontRenderer.drawStringWithShadow("insert coin", MainComponent.WIDTH / 2 - fontRenderer.getStringWidth("insert coin", 3)/2, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*4, 3, (float)Math.sin(f), 0xffffff55);
		
		String highestScore = "Highest score:";
		String name = "Name: stan";
		String score = "Score: 1000";
		
		float hS = fontRenderer.getStringWidth(highestScore, 5);
		float n = fontRenderer.getStringWidth(name, 5);
		float s = fontRenderer.getStringWidth(score, 5);
		
		float width = hS;
		if(n > width) width = n;
		if(s > width) width = s;
		
		this.drawRect(0, 0, width + 5, fontRenderer.getHeight(5)*3 + 5*3 + 5, 0xFF242424);
		
		fontRenderer.drawStringWithShadow(highestScore, 5, 5*3 + fontRenderer.getHeight(5)*2, 5, 0xffffffff);
		fontRenderer.drawStringWithShadow(name, 5, 5*2 + fontRenderer.getHeight(5), 5, 0xffffffff);
		fontRenderer.drawStringWithShadow(score, 5, 5, 5, 0xffffffff);
	}
	
}
