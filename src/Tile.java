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
	private boolean right_leg;
	public int type;
	private Image image;

	public Tile(int i){
		this.type = i;
	}
	public void doBlock(){
		this.type = Tile.BLOCK;
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
			if(right_leg)
				image = new ImageIcon(getClass().getResource("CHARACTER_MINI_1.png")).getImage();
			else
				image = new ImageIcon(getClass().getResource("CHARACTER_MINI_0.png")).getImage();
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

}
