package me.intellijent.game.gui;

import me.intellijent.game.Constants;
import me.intellijent.game.MainComponent;
import me.intellijent.game.font.FontRenderer;

public class GuiQuit extends GuiScreen {
	
	private FontRenderer fontRenderer;
	
	public GuiQuit() {
		fontRenderer = new FontRenderer();
		this.components.add(new GuiButton(this, "Yes", MainComponent.WIDTH / 2 - fontRenderer.getStringWidth("Yes", Constants.guiButtonSize()) / 2, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*6));
		this.components.add(new GuiButton(this, "No", MainComponent.WIDTH / 2 - fontRenderer.getStringWidth("No", Constants.guiButtonSize()) / 2, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*7));
	}
	
	public void actionClicked(int component) {
		switch(component) {
			case 0:
				MainComponent.getInstance().stop();
				break;
			case 1:
				MainComponent.getInstance().setGuiScreen(new GuiMainMenu());
				break;
		}
	}
	
	public void input(float x, float y) {
		super.input(x, y);
	}
	
	public void drawScreen(float x, float y, float delta) {
		super.drawScreen(x, y, delta);
		String text = "Are you sure you";
		String text2 = "want to quit?";
		fontRenderer.drawStringWithShadow(text, MainComponent.WIDTH / 2 - fontRenderer.getStringWidth(text, Constants.titleFontSize())/2, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*3, Constants.titleFontSize(), 0xffffffff);
		fontRenderer.drawStringWithShadow(text2, MainComponent.WIDTH / 2 - fontRenderer.getStringWidth(text2, Constants.titleFontSize())/2, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*4.5F, Constants.titleFontSize(), 0xffffffff);
	}
	
}
