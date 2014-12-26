package me.intellijent.game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

public class RenderUtil {
	
	public static void clearScreen() {
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
	}

	public static void initGraphics() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glDisable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		//glClearColor(.5f, .8f, 1f, 1);
	}
	
	public static String getOpenGLVersion() {
		return glGetString(GL_VERSION);
	}
	
}
