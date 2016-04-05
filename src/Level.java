import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Level {
	static int i=0;
	BufferedReader bf;
	String[] lvs;
	public Level(){
		try {
		bf = new BufferedReader(new FileReader("levels.txt"));
		} catch (FileNotFoundException e) {
			System.err.println("Archivo no encontrado");
		}
	}

	public Level getLevel() {
		return null;
	}
}
