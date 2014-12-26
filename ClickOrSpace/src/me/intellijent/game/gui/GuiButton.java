package me.intellijent.game.gui;

import me.intellijent.game.Constants;
import me.intellijent.game.Input;
import me.intellijent.game.MainComponent;
import me.intellijent.game.SoundManager;
import me.intellijent.game.font.FontRenderer;

public class GuiButton extends GuiComponent {
	
	private String text;
	private boolean hovered;
	private FontRenderer fontRenderer;
	
	public GuiButton(GuiScreen parent, String text, float x, float y) {
		super(parent, x, y);
		this.text = text;
		this.fontRenderer = MainComponent.getInstance().fontRenderer;
	}
	
	public void input(float x, float y) {
		float startX = position().x;
		float startY = position().y;
		float endX   = startX + fontRenderer.getStringWidth(text, Constants.guiButtonSize());
		float endY   = startY + fontRenderer.getHeight(Constants.guiButtonSize());
		if(x >= startX && y >= startY && x <= endX && y <= endY) {
			if(!hovered) { //TODO: ON HOVER EVENT
				hovered = true;
				SoundManager.playSound("Hover");
			}
		} else {
			if(hovered) { //TODO: ON LEAVE EVENT
				hovered = false;
			}
		}
		
		if(hovered && Input.getMouseDown(0)) { // TODO: ON CLICK EVENT
			SoundManager.playSound("Click");
			for(int i = 0; i < parent().components.size(); i++) {
				if(parent().components.get(i) == this) {
					parent().actionClicked(i);
					break;
				}
			}
		}
	}
	
	public void render() {
		fontRenderer.drawStringWithShadow(text, position().x, position().y, Constants.guiButtonSize(), hovered ? 0xffa0a0a0 : 0xffffffff);
	}
	
	public void update(float delta) { }
	
}
