import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class ScorePanel extends JComponent{
	public int points;
	public static final String msg_1 = "Score: ";
	public static final String msg_2 = "Time: ";
	public static ScorePanel panel_singleton;
	
	private ScorePanel() {
		this.points=0;
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
		g.setColor(new Color(255,0,0));
		g.drawString("Points", (int)this.getAlignmentX()+10, (int)this.getAlignmentY()+10);
	}
}
