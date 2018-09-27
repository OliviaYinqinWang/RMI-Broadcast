package server;

import java.awt.Button;
import java.util.HashMap;

public class GameState {
	HashMap<Integer, Button> buttons;
	HashMap<Integer, Boolean> buttonStatus;
	
	public GameState(HashMap<Integer, Button> buttons,HashMap<Integer, Boolean> buttonStatus) {
		this.buttons = buttons;
		this.buttonStatus = buttonStatus;
	}
	
	

}
