import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Tile extends JComponent{
	public int fil,col;

	public static int NO_BLOCK = 0;
	public static int BLOCK = 1;
	public static int CHARACTER = 2;
	public static int CHERRY = 3;
	private boolean up;
	public int type;
	private Image image;

	public static int direction;

	/*Direction constraints*/
	public static final int NORTH = 0;
	public static final int SOUTH = 1;
	public static final int EAST = 2;
	public static final int WEST = 3;
	

	public static int currentInUp;
	public static int currentInDown;
	public static int currentInRight;
	public static int currentInLeft;
	


	public Tile(int i){
		this.type = i;
	}
	public void doCherry(){
		this.type = Tile.CHERRY;
		this.up = true;
	}
	public Image getImage(){
		return this.image;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int t){
		this.type=t;
	}
	public synchronized void assignImage(){
		if(this.type==Tile.NO_BLOCK){
			image = new ImageIcon(getClass().getResource("FLOOR_MINI.png")).getImage();
		}
		if(this.type==Tile.BLOCK){
			image = new ImageIcon(getClass().getResource("NEWTREE_MINI.png")).getImage();
		}
		if(this.type==Tile.CHARACTER){
			changeFrame();
		}
		if(this.type==Tile.CHERRY){
			if(up)
				image = new ImageIcon(getClass().getResource("CHERRY_UP.png")).getImage();
			else
				image = new ImageIcon(getClass().getResource("CHERRY_DOWN.png")).getImage();
		}
		repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(this.getImage(),0,0,getWidth(),getHeight(),null);
	}
	/*Depending of the direction and the current frame*/
	public synchronized void changeFrame(){
		switch(direction){
		case NORTH:
			image = new ImageIcon(getClass().getResource(ImageContainter.images[7])).getImage();
			break;
		case EAST:
			image = new ImageIcon(getClass().getResource(ImageContainter.images[3])).getImage();
			break;
		case WEST:
			image = new ImageIcon(getClass().getResource(ImageContainter.images[6])).getImage();
			break;
		case SOUTH:
			image = new ImageIcon(getClass().getResource(ImageContainter.images[0])).getImage();
			break;
		default:
			System.err.println("No matching direction.");

		}
	}

	/*Used for animate the cherries*/
	public synchronized void changePosition() {
		this.up = !this.up;
		assignImage();
	}

}
