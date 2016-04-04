import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;



public class MainWindow{

	private JFrame frame;
	private MatrixTiles map;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frame = new JFrame("MiniGame");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.map = new MatrixTiles(15,30);
		frame.setBounds(10, 10, 15*50,15*50);
		frame.setResizable(true);
		frame.getContentPane().add(map, BorderLayout.CENTER);
		frame.addKeyListener(map);
		
	}
	/*
	 oooooxooooooooooooooooooooooo
	 oooooxooooooooooooooooooooooo
	 oooooxooooooooooooooooooooooo
	 oooooxooooooooooooooooooooooo
	 oooooxooooooooooooooooooooooo
	 oooooxooooooooooooooooooooooo
	 oooooxooooooooxxxxxxxxxxxxxxx
	 oooooxooooooooxoooooooooooooo
	 oooooxooooooooxoooooooooooooo
	 oooooxxxxxxxxxxoooooooooooooo
	 ooooooooooooooooooooooooooooo
	 ooooooooooooooooooooooooooooo
	 ooooooooooooooooooooooooooooo
	 
	 */
}