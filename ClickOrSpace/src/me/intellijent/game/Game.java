package me.intellijent.game;

import me.intellijent.game.score.HighscoreManager;

public class Game {
	
	private Level level;
	public static HighscoreManager hsManager;
	public static float score;
	
	public Game() {
		hsManager = new HighscoreManager();
		level = new Level();
	}
	
	public void input() {
		level.input();
	}
	
	public void update() {
		level.update();
	}
	
	public void render() {
		level.render();
	}
	
}
