package me.intellijent.game.gui;

import java.text.DecimalFormat;

import me.intellijent.game.Constants;
import me.intellijent.game.Game;
import me.intellijent.game.MainComponent;
import me.intellijent.game.font.FontRenderer;

public class GuiIngame extends Gui {
	
	private DecimalFormat format;
	
	public GuiIngame() {
		format = new DecimalFormat("0000000");
	}
	
	public void draw(float delta) {
		FontRenderer fontRenderer = MainComponent.getInstance().fontRenderer;
		fontRenderer.drawStringWithShadow("Score: " + format.format(Game.score), 5, MainComponent.HEIGHT - fontRenderer.getHeight(Constants.guiButtonSize()) - 5, Constants.guiButtonSize());
	}
	
}
