import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

@SuppressWarnings("unused")
public class Map {
	private char[][] map;
	private static Map singleton_map;
	private static int len;
	public static final char BLOCK='x';
	public static final char NO_BLOCK='o';
	
	private Map(){
		
		BufferedImage image;
		Color aux;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("maze_3.png"));
			len = image.getWidth();
			if(len != image.getHeight()) throw new IOException("Is not a square map");
			
			map = new char[len][len];
			
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					aux = new Color(image.getRGB(j, i));
					if(aux.getBlue()>100 && aux.getRed()>100 && aux.getGreen()>100){
						map[i][j]=NO_BLOCK;
					}else{
						map[i][j]=BLOCK;
					}
				}
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Not found map image.");
			System.exit(-1);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Not found map image.");
			System.exit(-1);
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
	
	@Override 
	public String toString(){
		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				sf.append(map[i][j]);
			}
			sf.append("\n");
		}
		return sf.toString();
	}
}
