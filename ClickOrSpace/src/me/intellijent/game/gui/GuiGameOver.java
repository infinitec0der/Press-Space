package me.intellijent.game.gui;

import java.text.DecimalFormat;

import me.intellijent.game.Constants;
import me.intellijent.game.Game;
import me.intellijent.game.MainComponent;
import me.intellijent.game.font.FontRenderer;

public class GuiGameOver extends GuiScreen {
	
	private FontRenderer fontRenderer;
	
	private DecimalFormat format;
	
	float score;
	
	public GuiGameOver(String name) {
		score = Game.score;
		Game.score = 0;
		fontRenderer = new FontRenderer();
		this.components.add(new GuiButton(this, "Play Again", MainComponent.WIDTH / 2 - fontRenderer.getStringWidth("Play Again", Constants.guiButtonSize()) / 2, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*11));
		this.components.add(new GuiButton(this, "Main Menu", MainComponent.WIDTH / 2 - fontRenderer.getStringWidth("Main Menu", Constants.guiButtonSize()) / 2, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*12));
		format = new DecimalFormat("00000");
		Game.hsManager.addScore(name, (int) score);
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
	}
	
	public void drawScreen(float x, float y, float delta) {
		super.drawScreen(x, y, delta);
		String text = "Game Over";
		String text2 = "Final Score: " + format.format(score);
		fontRenderer.drawStringWithShadow(text, MainComponent.WIDTH / 2 - fontRenderer.getStringWidth(text, Constants.titleFontSize())/2, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*1.5F, Constants.titleFontSize(), 0xffffffff);
		fontRenderer.drawStringWithShadow(text2, MainComponent.WIDTH / 2 - fontRenderer.getStringWidth(text2, Constants.titleFontSize() - 1)/2, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.titleFontSize())*3, Constants.titleFontSize() - 1, 0xffffffff);
		fontRenderer.drawStringWithShadow(Game.hsManager.getHighscore(1), 0, 0, 3);
	}
	
}
