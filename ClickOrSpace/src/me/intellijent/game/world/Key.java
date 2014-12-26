package me.intellijent.game.world;

import me.intellijent.game.Constants;
import me.intellijent.game.Input;
import me.intellijent.game.SoundManager;
import me.intellijent.game.font.FontRenderer;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

public class Key {
	
	private static FontRenderer fontRenderer = new FontRenderer();
	private String keyName;
	private int keyCode;
	private Vector2f position;
	private float scale;
	private float alpha;
	private boolean beingPressed = false;
	private boolean destroyed = false;
	private boolean pressedBefore = false;
	private boolean dead = false;
	private float aliveTime;
	
	public Key(String key, float x, float y, float scale) {
		this(key, scale, new Vector2f(x, y));
	}
	
	public Key(String key, float scale, Vector2f position) {
		this.keyCode = (key == " ") ? Keyboard.KEY_SPACE : Keyboard.getKeyIndex(key);
		this.keyName = (key == " ") ? "Space" : Keyboard.getKeyName(this.keyCode);
		this.alpha = 1;
		this.position = position;
		this.scale = scale;
	}
	
	public void input() {
		if(Input.getKey(keyCode)) {
			beingPressed = true;
			pressedBefore = true;
		} else {
			beingPressed = false;
		}
	}
	
	public void update(float delta) {
		aliveTime += delta;
		if(beingPressed) {
			scale += delta * 10;
			alpha -= delta / 1.5F;
			if(alpha <= Constants.blownUpKeySize() && !destroyed) { //TODO: ON KEY DESTROY EVENT
				destroyed = true;
				SoundManager.playSound("Kill");
			}
		} else {
			if(pressedBefore) {
				scale -= delta * 5;
				if(scale <= 0 && !dead) { //TODO: ON DEATH EVENT
					dead = true;
					SoundManager.playSound("Die");
				}
			}
		}
	}
	
	public void render() {
		String text = "Press " + keyName;
		fontRenderer.drawStringWithShadow(text, position.x - (fontRenderer.getStringWidth(text, scale)/2), position.y, scale, alpha, beingPressed ? 0xa6ffff55 : 0xa6ffffff);
	}
	
	public boolean dead() {
		return dead;
	}
	
	public boolean destroyed() {
		return destroyed;
	}
	
	public float getAliveTime() {
		return aliveTime;
	}
	
	
}
