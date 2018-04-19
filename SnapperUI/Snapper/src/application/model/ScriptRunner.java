package application.model;

import java.io.IOException;

public class ScriptRunner {
	
    private String executable;

	public ScriptRunner(String arg) {
		this.executable = arg;
	}
	
	public void callPythonScript() {
		try {
			Process process = Runtime.getRuntime().exec(this.executable);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
