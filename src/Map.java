import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Map {
	private char[][] map;
	private static Map singleton_map;
	private static int len = M.size;
	public static final char BLOCK='x';
	public static final char NO_BLOCK='o';
	public static final char CHARACTER='n';
	
	private Map(){
		map = new char[len][len];
		try {
			BufferedReader bf = new BufferedReader(new FileReader("bin\\map.txt"));
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					map[i][j] = (char) bf.read();
				}
			}
			bf.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found exception.");
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("IO error");
		}
	}
	public static Map getMap(){
		if(singleton_map==null){
			singleton_map=new Map();
		}
		return singleton_map;
	}
	public static int getLengh(){
		return len;
	}
	public char getAt(int x, int y){
		return this.map[x][y];
	}
}
