import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MatrixTiles extends JPanel implements KeyListener{

	private Tile[][] tiles_matrix;
	private int currentFil,currentCol;
	private int vision_xmaplength,vision_ymaplength;
	//private int xmaplength,ymaplength;
	
	GridLayout g;

	public MatrixTiles(int x, int y){
		
		this.vision_xmaplength = x;
		this.vision_ymaplength = y;
		//this.xmaplength = x*2;
		//this.ymaplength = y*2;
		
	
		g = new GridLayout(this.vision_xmaplength,this.vision_ymaplength,0,0);
		this.setLayout(g);
		
		tiles_matrix = new Tile[vision_xmaplength][vision_ymaplength];
		
		initMap();
		
		currentCol = (int)vision_ymaplength/2;
		currentFil = (int)vision_xmaplength/2;
		
		tiles_matrix[currentFil][currentCol].setType(Tile.CHARACTER);
		loadMap();
		//loadPortion(currentCol,currentFil);
	}
	private void initMap() {
		for (int i = 0; i < vision_xmaplength; i++) {
			for (int j = 0; j < vision_ymaplength; j++) {
				if(i==0 || j==0 || i==vision_xmaplength-1 || j==vision_ymaplength-1){
					tiles_matrix[i][j] = new Tile(Tile.BLOCK);
				}else{
					tiles_matrix[i][j] = new Tile(Tile.NO_BLOCK);
				}
			}
		}
	}
	public void loadMap(){
		for (int i = 0; i < vision_xmaplength; i++) {
			for (int j = 0; j < vision_ymaplength; j++) {
				this.add(tiles_matrix[i][j]);
			}
		}
		repaint();
	}
	/*
	public void loadPortion(int fil, int col){
		this.removeAll();
		for (int i = currentFil-7; i <= currentFil+7; i++) {
			for (int j = currentCol-7; j <= currentCol+7; j++) {
				this.add(tiles_matrix[i][j]);
			}
		}
		repaint();
	}
	*/
	public void blockOn(int x, int y){
		this.tiles_matrix[x][y].doBlock();
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_W:
			moveUp();
			break;
		case KeyEvent.VK_S:
			moveDown();
			break;
		case KeyEvent.VK_A:
			moveLeft();
			break;
		case KeyEvent.VK_D:
			moveRight();
			break;
		default:
			break;
		}	
	}
	private void moveRight() {
		if(tiles_matrix[currentFil][currentCol+1].getType()==Tile.NO_BLOCK){
			tiles_matrix[currentFil][currentCol+1].setType(Tile.CHARACTER);
			tiles_matrix[currentFil][currentCol].setType(Tile.NO_BLOCK);
			currentCol++;
		}
	}
	private void moveLeft() {
		if(tiles_matrix[currentFil][currentCol-1].getType()==Tile.NO_BLOCK){
			tiles_matrix[currentFil][currentCol-1].setType(Tile.CHARACTER);
			tiles_matrix[currentFil][currentCol].setType(Tile.NO_BLOCK);
			currentCol--;
		}
	}
	private void moveDown() {
		if(tiles_matrix[currentFil+1][currentCol].getType()==Tile.NO_BLOCK){
			tiles_matrix[currentFil+1][currentCol].setType(Tile.CHARACTER);
			tiles_matrix[currentFil][currentCol].setType(Tile.NO_BLOCK);
			currentFil++;
		}
	}
	private void moveUp() {
		if(tiles_matrix[currentFil-1][currentCol].getType()==Tile.NO_BLOCK){
			tiles_matrix[currentFil-1][currentCol].setType(Tile.CHARACTER);
			tiles_matrix[currentFil][currentCol].setType(Tile.NO_BLOCK);
			currentFil--;
		}
	
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tiles_matrix.length; i++) {
			for (int j = 0; j < tiles_matrix[i].length; j++) {
				if(tiles_matrix[i][j].getType()==Tile.BLOCK)
					sb.append("x");
				else{
					sb.append("o");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
		

	}
}
