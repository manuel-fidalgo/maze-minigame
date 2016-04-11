import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MatrixTiles extends JPanel implements KeyListener{

	private Tile[][] tiles_matrix;
	public int currentFil,currentCol;
	public int finalFil,finalCol;
	private int vision_xmaplength,vision_ymaplength;
	public boolean godMode;
	ArrayList<Box> cherry_boxes;
	@SuppressWarnings("unused") private int xmaplength,ymaplength;
	
	Animator anim;
	GridLayout g;
	//ScorePanel implements singleton pattern
	/**
	 * @param x vision x length
	 * @param y vision y length
	 */
	public MatrixTiles(int x, int y,boolean gmode){
		godMode=gmode;

		this.vision_xmaplength = x;
		this.vision_ymaplength = y;
		cherry_boxes = new ArrayList<Box>();

		/*Cosntraints*/
		this.xmaplength = Map.getLengh();
		this.ymaplength = Map.getLengh();


		g = new GridLayout(this.vision_xmaplength,this.vision_ymaplength);
		this.setLayout(g);

		this.currentFil = 9;
		this.currentCol = 92;
		this.finalFil=92;
		this.finalCol=9;

		try {
			loadMap(Map.getMap());
		} catch (IOException e) {
			System.err.println("Loading map error.");
		}
		anim = new Animator(this);
		anim.start();
		loadPortion(currentCol,currentFil);
	}
	public Tile getTileAt(int i, int j){
		return this.tiles_matrix[i][j];
	}
	public void loadMap(Map p) throws IOException{
		this.tiles_matrix = new Tile[Map.getLengh()][Map.getLengh()];
		//map and tiles_matrix have the same length
		for (int i = 0; i < tiles_matrix.length; i++) {
			for (int j = 0; j < tiles_matrix[i].length; j++) {
				switch(p.getAt(i, j)){
				case Map.BLOCK:
					tiles_matrix[i][j] = new Tile(Tile.BLOCK);
					break;
				case Map.NO_BLOCK:
					tiles_matrix[i][j] = new Tile(Tile.NO_BLOCK);
					if(((Math.random()*1000))<3) tiles_matrix[i][j].doCherry();
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
		cherry_boxes.clear();
		for (int i = fil-(vision_xmaplength-1)/2; i <= fil+(vision_xmaplength-1)/2; i++) {
			for (int j = col-(vision_ymaplength-1)/2; j <= col+(vision_ymaplength-1)/2; j++) {
				try{
					tiles_matrix[i][j].assignImage();
					if(tiles_matrix[i][j].getType()==Tile.CHERRY){
						cherry_boxes.add(new Box(i,j));
					}
					this.add(tiles_matrix[i][j]);
				}catch(IndexOutOfBoundsException w){
					System.err.println("OutOfmap\n");
				}
			}
		}
		validate();
		repaint();
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
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		default:
			break;
		}
		if(this.currentCol==this.finalCol&&this.currentFil==this.finalFil){
			JOptionPane.showMessageDialog(getParent(), "You win!");
			System.exit(0);
		}
	}
	private void moveRight() {
		if(godMode || tiles_matrix[currentFil][currentCol+1].getType()!=Tile.BLOCK){
			if(tiles_matrix[currentFil][currentCol+1].getType()==Tile.CHERRY) ScorePanel.getScorePanel().addPoint();
			tiles_matrix[currentFil][currentCol+1].setType(Tile.CHARACTER);
			tiles_matrix[currentFil][currentCol].setType(Tile.NO_BLOCK);
			currentCol++;
		}
		loadPortion(currentFil, currentCol);
	}
	private void moveLeft() {
		if(godMode || tiles_matrix[currentFil][currentCol-1].getType()!=Tile.BLOCK ){
			if(tiles_matrix[currentFil][currentCol-1].getType()==Tile.CHERRY) ScorePanel.getScorePanel().addPoint();
			tiles_matrix[currentFil][currentCol-1].setType(Tile.CHARACTER);
			tiles_matrix[currentFil][currentCol].setType(Tile.NO_BLOCK);
			currentCol--;
		}
		loadPortion(currentFil, currentCol);
	}
	private void moveDown() {
		if(godMode || tiles_matrix[currentFil+1][currentCol].getType()!=Tile.BLOCK){
			if(tiles_matrix[currentFil+1][currentCol].getType()==Tile.CHERRY)ScorePanel.getScorePanel().addPoint();
			tiles_matrix[currentFil+1][currentCol].setType(Tile.CHARACTER);
			tiles_matrix[currentFil][currentCol].setType(Tile.NO_BLOCK);
			currentFil++;
		}
		loadPortion(currentFil, currentCol);
	}
	private void moveUp() {
		if(godMode || tiles_matrix[currentFil-1][currentCol].getType()!=Tile.BLOCK){
			if(tiles_matrix[currentFil-1][currentCol].getType()==Tile.CHERRY)ScorePanel.getScorePanel().addPoint();
			tiles_matrix[currentFil-1][currentCol].setType(Tile.CHARACTER);
			tiles_matrix[currentFil][currentCol].setType(Tile.NO_BLOCK);
			currentFil--;
		}
		loadPortion(currentFil, currentCol);
	}
	/*Not Used*/
	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
}
