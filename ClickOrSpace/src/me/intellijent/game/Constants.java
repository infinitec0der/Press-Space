package me.intellijent.game;

import java.util.Random;

public class Constants {
	public static final float titleFontSize() {
		return 10F;
	}
	
	public static final float guiFontSize() {
		return 4F;
	}
	
	public static final float guiButtonSize() {
		return 5F;
	}
	
	public static final int minKeySize() {
		return 3;
	}
	
	public static final int maxKeySize() {
		return 6;
	}
	
	public static final float blownUpKeySize() {
		return 0F;
	}
	
	public static final float getRand(Random rand, int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}
}
