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
	private boolean right_leg;
	private int current_image;
	private boolean up;
	public int type;
	private Image image;

	public Tile(int i){
		this.current_image=0;
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
	public void assignImage(){
		if(this.type==Tile.NO_BLOCK){
			image = new ImageIcon(getClass().getResource("FLOOR_MINI.png")).getImage();
		}
		if(this.type==Tile.BLOCK){
			image = new ImageIcon(getClass().getResource("NEWTREE_MINI.png")).getImage();
		}
		if(this.type==Tile.CHARACTER){
			switch(current_image){
			case 0:
				image = new ImageIcon(getClass().getResource("0.png")).getImage();
				break;
			case 1:
				image = new ImageIcon(getClass().getResource("1.png")).getImage();
				break;
			case 2:
				image = new ImageIcon(getClass().getResource("2.png")).getImage();
				break;
			case 3:
				image = new ImageIcon(getClass().getResource("3.png")).getImage();
				break;
				default:
					System.err.println("Error assingning image");
			}
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
	public void changeLeg() {
		this.right_leg = !this.right_leg;
		assignImage();
	}
	public void changeImage(){
		this.current_image = (++this.current_image)%4;
		assignImage();
	}
	public void changePosition() {
		this.up = !this.up;
		assignImage();
	}

}
