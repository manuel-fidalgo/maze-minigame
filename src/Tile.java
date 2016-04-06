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
	public int type;
	private Image image;

	public Tile(int i){
		this.type = i;
	}
	/*
	public Tile(int i,int fil,int col){
		this.type = i;
		assignImage();

		this.fil = fil;
		this.col = col;
	}
	 */
	public void doBlock(){
		this.type = Tile.BLOCK;
		//assignImage();
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
			image = new ImageIcon(getClass().getResource("FLOORMINI.png")).getImage();
		}
		if(this.type==Tile.BLOCK){
			image = new ImageIcon(getClass().getResource("WALLMINI_.png")).getImage();
		}
		if(this.type==Tile.CHARACTER){
			image = new ImageIcon(getClass().getResource("CHARACTERMINI.png")).getImage();
		}
		repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(this.getImage(),0,0,getWidth(),getHeight(),null);
	}

}
