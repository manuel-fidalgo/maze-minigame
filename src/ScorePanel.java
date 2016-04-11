import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class ScorePanel extends JComponent{
	public int points;
	public static final String msg_1 = "Score: ";
	public static final String msg_2 = "Time: ";
	public static ScorePanel panel_singleton;
	private Image back;
	private GraphicsEnvironment ge;
	private Font current_font;
	
	private ScorePanel() {
		this.points=0;
		try{
		back = new ImageIcon(getClass().getResource("BACKGROUND.png")).getImage();
		current_font = Font.createFont(Font.TRUETYPE_FONT, new File("bin\\FONT.ttf")).deriveFont(34f);
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("bin\\FONT.ttf")));
		} catch (FontFormatException | IOException e) {
			System.out.println("Font not found");
			e.printStackTrace();
		}
		
	}
	public static ScorePanel getScorePanel(){
		if(panel_singleton==null){
			panel_singleton = new ScorePanel();
		}
		return panel_singleton;
	}
	public void addPoint(){
		this.points++;
		repaint();
	}
	@Override
	public void paintComponent(Graphics g){
		g.setFont(current_font);
		g.setColor(new Color(0x00000));
		g.drawImage(back,(int)this.getAlignmentX(),(int)this.getAlignmentY(),(int)(this.getWidth()),(int)(this.getHeight()),null);
		g.drawString("Points: "+this.points, (int)this.getAlignmentX()+10, (int)this.getAlignmentY()+100);
	}
}
