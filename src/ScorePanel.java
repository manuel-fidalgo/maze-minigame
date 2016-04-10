import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class ScorePanel extends JComponent{
	public static final String msg_1 = "Score: ";
	public static final String msg_2 = "Time: ";
	public ScorePanel() {
		
	}
	@Override
	public void paintComponent(Graphics g){
		g.setColor(new Color(255,0,0));
		g.drawString("Hola", (int)this.getAlignmentX()+10, (int)this.getAlignmentY()+10);
	}
}
