package application.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	private String command = "python3 run.py";
	
	public MainController() {
		super();
	}
	
	@Override
	public void handle(ActionEvent event) {
		String choose = "Choose Photo";
		String run = "Run";
		Button b = (Button)event.getSource();
		System.out.println("Make the file");
		
		File out = new File("result.out");
		try {
			out.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(run.equals(b.getText())) {
			this.output.setText("Running...");
			System.out.println("DEBUG"+command);
			ScriptRunner runner = new ScriptRunner(command);
			runner.callPythonScript();
			String resultName = "result.out";
			File result = new File("result.out");
			///home/jason/eclipse-workspace/SnapperUI.zip_expanded/SnapperUI/Snapper
			String pathname = result.getAbsolutePath();
			int length = pathname.length();
			int length2 = resultName.length();
			String newpath = pathname.substring(0, length - length2);
			newpath = newpath +resultName;
			//System.out.println(pathname);
			String line = null;
			
			try {
				FileReader fileReader = new FileReader(newpath);
				BufferedReader bufferedReader =  new BufferedReader(fileReader);
				int flag = 0;
				
				
				line = bufferedReader.readLine();
	            this.output.setText(line);  
			
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			
			
		}
		else if(choose.equals(b.getText())){
			FileChooser fc = new FileChooser();
			File file = fc.showOpenDialog(Main.getStage());
			this.filename = file.getName();
			command = command + " " + filename;
			System.out.println(command);
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
