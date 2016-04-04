import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Tile extends JComponent{
	public static int BLOCK = 1;
	public static int NO_BLOCK = 0;
	public static int CHARACTER = 2;
	public int type;
	private Image image;
	
	public Tile(int i){
		this.setBorder(null);
		this.type = i;
		assignImage();
	}
	public void doBlock(){
		this.type = Tile.BLOCK;
		assignImage();
	}
	public Image getImage(){
		return this.image;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int t){
		this.type=t;
		assignImage();
	}
	public void setImage(Image img){
		this.image = img;
		repaint();
	}
	public void assignImage(){
		if(this.type==Tile.NO_BLOCK){
			image = new ImageIcon(getClass().getResource("NOBLOCK.jpg")).getImage();
		}
		if(this.type==Tile.BLOCK){
			image = new ImageIcon(getClass().getResource("BLOCK.png")).getImage();
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
