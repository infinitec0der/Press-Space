package me.intellijent.game.gui;

import me.intellijent.game.Constants;
import me.intellijent.game.Input;
import me.intellijent.game.MainComponent;
import me.intellijent.game.SoundManager;
import me.intellijent.game.font.FontRenderer;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

public class GuiTextField extends GuiComponent {
	
	private String text;
	private boolean hovered;
	private boolean clicked = false;
	private FontRenderer fontRenderer;
	private Vector2f size;
	
	public GuiTextField(GuiScreen parent, float x, float y) {
		super(parent, x, y);
		this.text = "";
		this.fontRenderer = MainComponent.getInstance().fontRenderer;
		this.size = new Vector2f(200, fontRenderer.getHeight(Constants.guiButtonSize()) + 10);
	}
	
	public void input(float x, float y) {
		float startX = position().x;
		float startY = position().y;
		float endX   = startX + size.x;
		float endY   = startY + size.y;
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
		
		if(Input.getMouseDown(0) && !hovered && clicked) { //TODO: ON EXIT EVENT
			clicked = false;
		}
		
		if(hovered && Input.getMouseDown(0) && !clicked) { // TODO: ON CLICK EVENT
			SoundManager.playSound("Click");
			clicked = true;
		}
	}
	
	public void render() {
		this.drawRect(position().x, position().y, position().x + size.x, position().y + size.y, 0xFF242424);
		fontRenderer.drawStringWithShadow(text, position().x, position().y, Constants.guiButtonSize(), (hovered && !clicked) ? 0xffa0a0a0 : 0xffffffff);
	}
	
	public void update(float delta) {
		if(clicked) {
			while (Keyboard.next()) {
			    if (Keyboard.getEventKeyState()) {
			    	char c = Keyboard.getEventCharacter();
			    	if(Keyboard.getEventKey() == Keyboard.KEY_BACK) {
			    		if(text.length() != 0)
							text = text.substring(0, text.length() - 1);
			    	}
			    	if(fontRenderer.contains(c))
			    		text += c;
			    }
			}
		}
	}

	public String getText() {
		return text;
	}
	
}
