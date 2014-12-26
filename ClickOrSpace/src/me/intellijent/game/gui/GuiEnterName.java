package me.intellijent.game.gui;

import me.intellijent.game.Constants;
import me.intellijent.game.MainComponent;
import me.intellijent.game.font.FontRenderer;

public class GuiEnterName extends GuiScreen {
	
	private FontRenderer fontRenderer;
	
	public GuiEnterName() {
		fontRenderer = new FontRenderer();
		this.components.add(new GuiButton(this, "Play Again", MainComponent.WIDTH / 2 - fontRenderer.getStringWidth("Play Again", Constants.guiButtonSize()) / 2, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*11));
		this.components.add(new GuiTextField(this, MainComponent.WIDTH / 2 - 100, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*5));
	}
	
	public void actionClicked(int component) {
		switch(component) {
			case 0:
				MainComponent.getInstance().setGuiScreen(new GuiGameOver(((GuiTextField)this.components.get(1)).getText()));
				break;
		}
	}
	
	public void input(float x, float y) {
		super.input(x, y);
	}
	
	public void drawScreen(float x, float y, float delta) {
		super.drawScreen(x, y, delta);
		String text = "Enter Name:";
		fontRenderer.drawStringWithShadow(text, MainComponent.WIDTH / 2 - fontRenderer.getStringWidth(text, Constants.titleFontSize())/2, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*1.5F, Constants.titleFontSize(), 0xffffffff);
	}
	
}
