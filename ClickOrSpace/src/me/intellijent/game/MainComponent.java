package me.intellijent.game;

import me.intellijent.game.font.FontRenderer;
import me.intellijent.game.gui.GuiIngame;
import me.intellijent.game.gui.GuiMainMenu;
import me.intellijent.game.gui.GuiScreen;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class MainComponent 
{
	public static final int WIDTH = /*1280*/1120;
	public static final int HEIGHT = /*720*/640;
	public static final String TITLE = "Game Test";
	public static final double FRAME_CAP = 5000.0;
	public static int FPS = 0;
	
	private static MainComponent mainComponent;
	
	public static MainComponent getInstance() {
		return mainComponent;
	}
	
	private boolean isRunning;
	private Game game;
	
	public FontRenderer fontRenderer;
	private GuiScreen currentScreen;
	private GuiIngame ingameUI;
	
	public void setGuiScreen(GuiScreen guiScreen) {
		this.currentScreen = guiScreen;
	}
	
	public MainComponent()
	{
		mainComponent = this;
		//System.out.println(RenderUtil.getOpenGLVersion());
		RenderUtil.initGraphics();
		fontRenderer = new FontRenderer();
		isRunning = false;
		SoundManager.create();
		game = new Game();
		ingameUI = new GuiIngame();
		setGuiScreen(new GuiMainMenu());
	}
	
	public void start() {
		if(isRunning)
			return;
		
		run();
	}
	
	public void stop() {
		if(!isRunning)
			return;
		
		try {
			SoundManager.playSound("Exit");
			Thread.sleep(750);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		isRunning = false;
	}
	
	private void run() {
		isRunning = true;
		
		int frames = 0;
		long frameCounter = 0;
		
		final double frameTime = 1.0 / FRAME_CAP;
		
		long lastTime = Time.getTime();
		double unprocessedTime = 0;
		
		while(isRunning) {
			boolean render = false;
			
			long startTime = Time.getTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessedTime += passedTime / (double)Time.SECOND;
			frameCounter += passedTime;
			
			while(unprocessedTime > frameTime) {
				render = true;
				
				unprocessedTime -= frameTime;
				
				if(Display.isCloseRequested()) {
					stop();
				}
				
				Time.setDelta(frameTime);
				
				if(currentScreen == null) {
					game.input();
				} else {
					currentScreen.input(Mouse.getX(), Mouse.getY());
				}
				Input.update();
				
				if(currentScreen == null) {
					game.update();
				}
				
				if(frameCounter >= Time.SECOND) {
					MainComponent.FPS = frames;
					frames = 0;
					frameCounter = 0;
				}
			}
			if(render) {
				render();
				frames++;
			}
			else {
				try  {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		cleanUp();
	}
	
	private void render() {
		RenderUtil.clearScreen();
		
		boolean shouldRender = currentScreen == null;
		
		if(currentScreen != null) {
			shouldRender = !currentScreen.doesGuiStopGame();
		}
		
		if(shouldRender) {
			game.render();
			GL11.glPushMatrix();
			GL11.glLoadIdentity();
			ingameUI.draw((float) Time.getDelta());
			GL11.glPopMatrix();
		}
		
		if(currentScreen != null) {
			GL11.glPushMatrix();
			GL11.glLoadIdentity();
			currentScreen.drawScreen(Mouse.getX(), Mouse.getY(), (float) Time.getDelta());
			GL11.glPopMatrix();
		}
		
		Display.update();
	}
	
	private void cleanUp() {
		Display.destroy();
		SoundManager.destroy();
	}
	
	public static void main(String[] args) throws Exception {
		Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
		Display.setTitle(TITLE);
		Display.create();
		
		MainComponent game = new MainComponent();
		
		game.start();
	}
}