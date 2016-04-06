import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.PaintContext;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MatrixTiles extends JPanel implements KeyListener{

	private Tile[][] tiles_matrix;
	private int currentFil,currentCol;
	private int vision_xmaplength,vision_ymaplength;
	private int xmaplength,ymaplength;

	GridLayout g;
	/**
	 * @param x vision x length
	 * @param y vision y length
	 */
	public MatrixTiles(int x, int y){

		this.vision_xmaplength = x;
		this.vision_ymaplength = y;
		/*Cosntraints*/
		this.xmaplength = Map.getLengh();
		this.ymaplength = Map.getLengh();


		g = new GridLayout(this.vision_xmaplength,this.vision_ymaplength);
		this.setLayout(g);

		this.currentCol = 185;
		this.currentFil = 740;
		
		try {
			loadMap(Map.getMap());
		} catch (IOException e) {
			System.err.println("Loading map error.");
		}
		
		loadPortion(currentCol,currentFil);
	}
	
	public void loadMap(Map p) throws IOException{
		this.tiles_matrix = new Tile[Map.getLengh()][Map.getLengh()];
		//map and tiles_matrix have the same length
		for (int i = 0; i < tiles_matrix.length; i++) {
			for (int j = 0; j < tiles_matrix.length; j++) {
				switch(p.getAt(i, j)){
				case Map.BLOCK:
					tiles_matrix[i][j] = new Tile(Tile.BLOCK);
					break;
				case Map.NO_BLOCK:
					tiles_matrix[i][j] = new Tile(Tile.NO_BLOCK);
					break;
				case Map.CHARACTER:
					tiles_matrix[i][j] = new Tile(Tile.CHARACTER);
					break;
				default:
					throw new IOException();
				}
			}
		}
		this.tiles_matrix[currentCol][currentFil].setType(Tile.CHARACTER);
		
	}
	
	public void loadPortion(int fil, int col){
		removeAll();
		for (int i = fil-(vision_xmaplength-1)/2; i <= fil+(vision_xmaplength-1)/2; i++) {
			for (int j = col-(vision_ymaplength-1)/2; j <= col+(vision_ymaplength-1)/2; j++) {
				try{
				tiles_matrix[i][j].assignImage();
				this.add(tiles_matrix[i][j]);
				}catch(IndexOutOfBoundsException w){
					System.err.println("Mapa mal planteado\n");
				}
			}
		}
		validate();
		repaint();
		
	}
	
	public void blockOn(int x, int y){
		this.tiles_matrix[x][y].doBlock();
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			moveUp();
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			moveDown();
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			moveRight();
			break;
		case KeyEvent.VK_SPACE:
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
		loadPortion(currentFil, currentCol);
	}
	private void moveLeft() {
		if(tiles_matrix[currentFil][currentCol-1].getType()==Tile.NO_BLOCK){
			tiles_matrix[currentFil][currentCol-1].setType(Tile.CHARACTER);
			tiles_matrix[currentFil][currentCol].setType(Tile.NO_BLOCK);
			currentCol--;
		}
		loadPortion(currentFil, currentCol);
	}
	private void moveDown() {
		if(tiles_matrix[currentFil+1][currentCol].getType()==Tile.NO_BLOCK){
			tiles_matrix[currentFil+1][currentCol].setType(Tile.CHARACTER);
			tiles_matrix[currentFil][currentCol].setType(Tile.NO_BLOCK);
			currentFil++;
		}
		loadPortion(currentFil, currentCol);
	}
	private void moveUp() {
		if(tiles_matrix[currentFil-1][currentCol].getType()==Tile.NO_BLOCK){
			tiles_matrix[currentFil-1][currentCol].setType(Tile.CHARACTER);
			tiles_matrix[currentFil][currentCol].setType(Tile.NO_BLOCK);
			currentFil--;
		}
		loadPortion(currentFil, currentCol);
	}
	@Override
	public void keyReleased(KeyEvent arg0) {

	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
