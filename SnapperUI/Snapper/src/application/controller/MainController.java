package application.controller;

import java.io.File;
import java.net.MalformedURLException;
import application.Main;
import application.model.ScriptRunner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class MainController implements EventHandler<ActionEvent> {
	
	@FXML
	private Label output;
	@FXML
	private Label picturename;
	@FXML
	private ImageView image;
	private Image selectedImage;
	private String filename;
	
	public MainController() {
		super();
	}
	
	@Override
	public void handle(ActionEvent event) {
		String choose = "Choose Photo";
		String command = "python3 foo.py";
		String run = "Run";
		Button b = (Button)event.getSource();
		if(run.equals(b.getText())) {
			ScriptRunner runner = new ScriptRunner(command);
			runner.callPythonScript();
			this.output.setText("Running...");
			
			/*
			 * After the program displays "running" the UI needs
			 * to wait a while for data to be written to the file
			 * but for now, since we dont have any tensorflow scripts from
			 * the others, it needs to print the output correctly. It shouldnt
			 * run passed its bounds of the box and give ellipses, it should print basically
			 * three lines, the Species, its bag limit and its size limit. Assuming that the 
			 * python script writes to the file like that, you just need to print the three
			 * lines above the buttons, the variable to use is the "this.output.setText(String)"
			 * function to set text, I'm thinking that reading in each line as one 
			 * string would work, and making sure there are '\n' where there would be new lines
			 */
			
			
			
		}
		else if(choose.equals(b.getText())){
			FileChooser fc = new FileChooser();
			File file = fc.showOpenDialog(Main.getStage());
			filename = file.getName();
			try {
				this.selectedImage = new Image(file.toURI().toURL().toExternalForm());
				this.image.setImage(selectedImage);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			this.picturename.setText(filename);
			
			
		}
	
	}
}