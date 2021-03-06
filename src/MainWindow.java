import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;



public class MainWindow{

	private JFrame frame;
	private MatrixTiles map;
	public static final int PIXELS=50;
	public static boolean godmode = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try{
			if(args[0].equalsIgnoreCase("godmode"))
				godmode = true;
			else
				godmode = false;
		}catch(Exception e){
			godmode = false;
		}
		
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
		this.frame.getContentPane().setLayout(new BorderLayout());
		int vision_x,vision_y;
		vision_x=13;
		vision_y=13;
		//godmode = true;
		this.map = new MatrixTiles(vision_x,vision_y,godmode);
		frame.setBounds(10, 10, vision_x*PIXELS, vision_y*PIXELS+30);
		frame.setResizable(true);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(map,BorderLayout.CENTER);
		frame.addKeyListener(map);

	}

}