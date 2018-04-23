package application.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScriptRunner {
	
    private String command;
    private String execParts[];

	public ScriptRunner(String command) {
		this.command = command;
	}
	
	public void callPythonScript() {
		try {
			System.out.println("Running: "+command);
			execParts = command.split(" ");
			ProcessBuilder ps = new ProcessBuilder(execParts[0], execParts[1], execParts[2]);
			ps.redirectErrorStream(true);
			Process pr = ps.start();  
			BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
			    System.out.println(line);
			}
			pr.waitFor();
			System.out.println("Finished running.");
			in.close();
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Incorrect arguments.");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to open process.");
			e.printStackTrace();
		}
	}

}
