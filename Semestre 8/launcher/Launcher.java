package launcher;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;

import testConsole.Test;

public class Launcher {

	public static void main(String[] args) throws Exception {
		try {
			Setup.main(null);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Test.main(null);
	}
}
