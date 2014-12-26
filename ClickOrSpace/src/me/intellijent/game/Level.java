package me.intellijent.game;

import java.util.ArrayList;
import java.util.Random;

import me.intellijent.game.gui.Gui;
import me.intellijent.game.gui.GuiEnterName;
import me.intellijent.game.gui.GuiPause;
import me.intellijent.game.world.Key;

import org.lwjgl.input.Keyboard;

public class Level extends Gui {
	
	private String characters = 
	"QWERTYUIOPASDFGHJKLZXCVBNM ";
	
	private ArrayList<Key> keys = new ArrayList<>();
	private Random rand;
	public static int killed = 0;
	private float difficulty = 1;
	
	public Level() {
		rand = new Random();
		keys.add(new Key(" ", MainComponent.WIDTH/2, MainComponent.HEIGHT/2, Constants.maxKeySize() - 1));
	}
	
	public void update() {
		ArrayList<Key> destroyed = new ArrayList<>();
		
		boolean reset = false;
		
		for(Key key : keys) {
			key.update((float) Time.getDelta());
			if(key.dead()) {
				reset = true;
			}
			if(key.destroyed())
				destroyed.add(key);
		}
		for(Key key : destroyed) {
			Game.score += (int) ((1/key.getAliveTime()) * 10);
			killed += 1;
			keys.remove(key);
		}
		
		if(reset) {
			keys.clear();
			destroyed.clear();
			keys.add(new Key(" ", MainComponent.WIDTH/2, MainComponent.HEIGHT/2, Constants.maxKeySize() - 1));
			GuiEnterName gen = new GuiEnterName();
			killed = 0;
			difficulty = 1;
			MainComponent.getInstance().setGuiScreen(gen);
		}
		
		if(!destroyed.isEmpty()) {
			difficulty += .5F;
			spawnNewKey();
			if(rand.nextInt(100) < difficulty) {
				for(int i = 0; i < Constants.getRand(rand, 1, (int) difficulty); i++) {
					spawnNewKey();
				}
			}
		}
		
		destroyed.clear();
	}
	
	private void spawnNewKey() {
		String keyName = "" + characters.charAt(rand.nextInt(characters.length() - 1));
		float scale = Constants.getRand(rand, Constants.minKeySize(), Constants.maxKeySize());
		float sizeX = MainComponent.getInstance().fontRenderer.getStringWidth("Press " + ((keyName == " ") ? "Space" : Keyboard.getKeyName(Keyboard.getKeyIndex(keyName))), scale);
		float maxBoundsX = MainComponent.WIDTH - sizeX*2;
		float maxBoundsY = MainComponent.HEIGHT - MainComponent.getInstance().fontRenderer.getHeight(scale)*2;
		float x = Constants.getRand(rand, (int)sizeX/2, MainComponent.WIDTH) - 20;
		float y = Constants.getRand(rand, (int)MainComponent.getInstance().fontRenderer.getHeight(scale), MainComponent.HEIGHT) - 20;
		if(y < MainComponent.HEIGHT - 10 && x > MainComponent.WIDTH - 100)
			y = MainComponent.HEIGHT - 30;
		if(x > maxBoundsX)
			x = maxBoundsX;
		if(y > maxBoundsY)
			y = maxBoundsY;
		keys.add(new Key(keyName, x, y, scale));
	}
	
	public void input() {
		if(Input.getKeyDown(Input.KEY_ESCAPE)) {
			MainComponent.getInstance().setGuiScreen(new GuiPause());
		}
		
		for(Key key : keys)
			key.input();
		/*if(Input.getKeyDown(Keyboard.getKeyIndex("A"))) {
			System.out.println("A down.");
		}*/
	}
	public void render() {
		for(Key key : keys)
			key.render();
	}
	
}
