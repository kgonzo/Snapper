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
			
			String resultName = "result.out";
			File result = new File("result.out");
			///home/jason/eclipse-workspace/SnapperUI.zip_expanded/SnapperUI/Snapper
			String pathname = result.getAbsolutePath();
			int length = pathname.length();
			int length2 = resultName.length();
			String newpath = pathname.substring(0, length - length2);
			newpath = newpath +"/SnapperUI/Snapper/"+resultName;
			
			String line = null;
			try {
				FileReader fileReader = new FileReader(newpath);
				BufferedReader bufferedReader =  new BufferedReader(fileReader);
				int flag = 0;
				while(true) {
					
				while((line = bufferedReader.readLine()) != null) {
					flag = 1;
	                this.output.setText(line);
	            }   
					if(flag == 1)
						break;
				}
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			
			
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
