import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class playSound {

	private InputStream in;
	private AudioInputStream as;

	public playSound(String path) {
		BufferedInputStream myStream = new BufferedInputStream(getClass().getResourceAsStream(path)); 
		AudioInputStream audio;
		try {
			audio = AudioSystem.getAudioInputStream(myStream);
			Clip clip = AudioSystem.getClip();
			try {
				clip.open(audio);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
