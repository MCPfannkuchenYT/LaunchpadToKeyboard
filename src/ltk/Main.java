package ltk;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;

public class Main {

	public static int getKey(byte in) {
		switch (in) {
			case 36: return KeyEvent.VK_A;
			case 37: return KeyEvent.VK_S;
			case 38: return KeyEvent.VK_D;
			case 40: return KeyEvent.VK_E;
			case 41: return KeyEvent.VK_W;
			case 39: return KeyEvent.VK_SPACE;
		}
		return 0;
	}
	
	public static void main(String[] args) throws MidiUnavailableException, AWTException {
		MidiDevice d = MidiSystem.getMidiDevice(MidiSystem.getMidiDeviceInfo()[5]);
		final Robot r = new Robot();
		d.getTransmitter().setReceiver(new Receiver() {
			
			@Override
			public void send(MidiMessage message, long timeStamp) {
				try {
					byte key = message.getMessage()[1];
					if (message.getMessage()[2] == 0) {
						System.out.println("release " + key);
						r.keyRelease(getKey(key));
					} else {
						System.out.println("press " + key);
						r.keyPress(getKey(key));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void close() {
				
			}
		});
		d.open();
		while (true) {
			
		}
	}
	
}
