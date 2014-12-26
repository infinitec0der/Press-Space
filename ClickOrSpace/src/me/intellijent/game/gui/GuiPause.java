package me.intellijent.game.gui;

import me.intellijent.game.Constants;
import me.intellijent.game.Input;
import me.intellijent.game.MainComponent;
import me.intellijent.game.font.FontRenderer;

public class GuiPause extends GuiScreen {
	
	private FontRenderer fontRenderer;
	
	public boolean doesGuiStopGame() {
		return false;
	}
	
	public GuiPause() {
		fontRenderer = new FontRenderer();
		this.components.add(new GuiButton(this, "Back to game", MainComponent.WIDTH / 2 - fontRenderer.getStringWidth("Back to game", Constants.guiButtonSize()) / 2, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*6));
		this.components.add(new GuiButton(this, "Quit to menu", MainComponent.WIDTH / 2 - fontRenderer.getStringWidth("Quit to menu", Constants.guiButtonSize()) / 2, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*7));
	}
	
	public void actionClicked(int component) {
		switch(component) {
			case 0:
				MainComponent.getInstance().setGuiScreen(null);
				break;
			case 1:
				MainComponent.getInstance().setGuiScreen(new GuiMainMenu());
				break;
		}
	}
	
	public void input(float x, float y) {
		super.input(x, y);
		if(Input.getKeyDown(Input.KEY_ESCAPE)) {
			MainComponent.getInstance().setGuiScreen(null);
		}
	}
	
	public void drawScreen(float x, float y, float delta) {
		super.drawScreen(x, y, delta);
		String text = "Game Paused";
		fontRenderer.drawStringWithShadow(text, MainComponent.WIDTH / 2 - fontRenderer.getStringWidth(text, Constants.titleFontSize())/2, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*3, Constants.titleFontSize(), 0xffffffff);
	}
	
}
