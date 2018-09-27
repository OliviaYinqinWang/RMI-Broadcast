package client;

import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.beans.binding.StringBinding;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import remote.IRemoteScrambble;

public class GridController{
	@FXML private Button voteBtn;
	@FXML private GridPane container;
	@FXML private Button submitBtn; //same name as in the FXML
	@FXML private TextField infoText;
	@FXML private MainController main;
	@FXML private Button highlightBtn;
	@FXML private Button typetBtn;
	@FXML private Button deleteBtn;
	@FXML private Button acceptBtn;
	@FXML private Button declineBtn;
	@FXML private TextArea systemInfoText;

	private HashMap<Integer,Button> buttons;
	private HashMap<Integer,Boolean> buttonLocked; //contain the locked buttons
	private ArrayList<String> highlightedWord;
	//private int buttonId = -1;
	private String wordToVote; 
	private int playerScore = 0; //player score
	public enum PlayerState { TYPING, WAITING, CONFIRMING, DELETING, HIGHLIGHTING, VOTING } //containing states for the player
	public PlayerState current_state;
	@FXML private void initialize() {
	
		current_state = PlayerState.WAITING;
		// TODO Auto-generated method stub
		buttons = new HashMap<Integer, Button>();
		buttonLocked = new HashMap<Integer, Boolean>();
		int count = 0;
		//add the 20*20 grids to the gridpane
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				Button b = new Button();
				b.setId(String.valueOf(count++));
				b.setMaxWidth(Double.MAX_VALUE);
				b.setMaxHeight(Double.MAX_VALUE);
				buttons.put(Integer.parseInt((b.getId())),b);
				buttonLocked.put(Integer.parseInt((b.getId())), false);
				
				//here are two simple demo on events
				//detect the drag action on the button
				b.setOnDragDetected(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						// TODO Auto-generated method stub
						System.out.println("onDragDetected");
					}
					
				});
				//detect keyboard action on the button
				b.setOnKeyTyped(new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent arg0) {
						// TODO Auto-generated method stub
						System.out.println(arg0.getCharacter());
						
						//the player can't type into that button if it was locked
						if(!buttonLocked.get(Integer.parseInt(((Node)arg0.getSource()).getId()))
								&& current_state == PlayerState.TYPING) { //when the player want to type a character
							b.setText(arg0.getCharacter());
							//buttonId = Integer.parseInt(((Node)arg0.getSource()).getId());
						}
						System.out.println("Id: " + ((Node)arg0.getSource()).getId());
					}
				});
				
				b.setOnMouseClicked(new EventHandler<MouseEvent>() {
				    @Override public void handle(MouseEvent e) {
				        if(current_state == PlayerState.HIGHLIGHTING) { //highlight a character
				        	b.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;");
				        	//System.out.println(b.getText());
				        	if(b.getText()=="") {
				        		highlightedWord.add("?");
				        	}else {
				        		highlightedWord.add(b.getText());
				        	}
				        	System.out.println(highlightedWord);
				        }else if(current_state == PlayerState.DELETING) {//when the player want to delete a character
							b.setText("");
						}else if(current_state == PlayerState.CONFIRMING) {//when the player want to confirm a character
							buttonLocked.put(Integer.parseInt(b.getId()), true);
							current_state = PlayerState.WAITING;
						}
				    }
				});
				//gridpane.add(element, column, row)
				//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html
				container.add(b,i,j);
			}
		}
	}
	//when the type button pressed
	public void typePressed() {
		current_state = PlayerState.TYPING;
	}
	
	//when the delete button pressed
	public void deletePressed() {
		current_state = PlayerState.DELETING;
	}
	
	//when the confirm button pressed
	public void submitPressed() {
		infoText.setText("Submit button pressed!");
		current_state = PlayerState.CONFIRMING;
//		if(buttonId!=-1) {
//			//buttons.get(buttonId).setDisable(true);
//			buttonLocked.put(buttonId, true);
//			current_state = PlayerState.WAITING;
//		}
	}
	
	//when the highlight button pressed
	public void highlightPressed() {
		current_state = PlayerState.HIGHLIGHTING;
		highlightedWord = new ArrayList<String>();
	}
	
	//when the vote button pressed
	public void votePressed() {
		wordToVote = "";
		current_state = PlayerState.VOTING;
		
		//transfer the highlighted arrayList into a String
		for(String str:highlightedWord) {
			wordToVote+=str+"\t";
		}
		System.out.println(highlightedWord);
		systemInfoText.appendText("Word to vote: " + wordToVote +"\n");
	}
	
	//when the accept button pressed
	public void acceptPressed() {
		if(current_state == PlayerState.VOTING) {
			current_state = PlayerState.WAITING;
			//player score increase as the length of the arrayList
			playerScore+=highlightedWord.size();
			systemInfoText.appendText("Vote success! player score is now changed to: " + playerScore +"\n");
		}
	}
	
	//when the decline button pressed
	public void declinePressed() {
		if(current_state == PlayerState.VOTING) {
			current_state = PlayerState.WAITING;
			//player score remain the same
			systemInfoText.appendText("Vote failed, player score is unchanged" +"\n");
		}
	}
	
//	public void injectMainController(MainController mainController) {
//		this.main = mainController;
//	}
//	
//	public void setInfo(String s) {
//		infoText.setText(s);
//	}
	
	public HashMap<Integer,Button> getButtons() {
		return buttons;
	}
	
	public HashMap<Integer,Boolean> getStatus() {
		return buttonLocked;
	}
	
}
