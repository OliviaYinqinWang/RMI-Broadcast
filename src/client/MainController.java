package client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController implements Initializable {
	//@FXML private Part1Controller part1Controller;
	@FXML private Button mainBtn;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//part1Controller.injectMainController(this);
	}
	
	public void setPart1Text() {
		//part1Controller.setText("Hello From Main Controller");
	}
}
