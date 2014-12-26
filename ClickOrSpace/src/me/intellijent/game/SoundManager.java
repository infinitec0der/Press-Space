package me.intellijent.game;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.codecs.CodecWav;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;

public class SoundManager
{
	private static SoundSystem soundSystem;
	
	public static void create()
	{
		if(soundSystem != null) return;
		
		try
		{
			SoundSystemConfig.addLibrary( LibraryLWJGLOpenAL.class );
			soundSystem = new SoundSystem( LibraryLWJGLOpenAL.class );
			SoundSystemConfig.setSoundFilesPackage("");
			SoundSystemConfig.setCodec("wav", CodecWav.class);
			streamSound("Click", "click", false);
			streamSound("Hover", "hover", false);
			streamSound("Explode", "explode", false);
			streamSound("Kill", "kill", false);
			streamSound("Die", "die", false);
			streamSound("Exit", "close", false);
		} catch (SoundSystemException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void streamSound(String identifier, String name, boolean loop){
		try {
			URL url = new File("res/sfx/" + name + ".wav").toURI().toURL();
			soundSystem.newStreamingSource(false, identifier, url, name + ".wav", loop, 0, 0, 0, 
				SoundSystemConfig.ATTENUATION_NONE, SoundSystemConfig.getDefaultRolloff());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static void bgMusic()
	{
		soundSystem.play("Static");
	}
	
	public static void playSound(String sound)
	{
		soundSystem.play(sound); //TODO: REMOVE THIS TO RE-ENABLE SOUND 
	}
	
	public static void destroy()
	{
		soundSystem.cleanup();
	}
}
