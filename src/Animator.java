
public class Animator extends Thread{
	
	MatrixTiles mt;
	public Animator(MatrixTiles t){
		this.mt=t;
	}
	@Override
	public void run() {
		while(true){
			
			try {
				Thread.sleep(300);
				mt.getTileAt(mt.currentFil,mt.currentCol).changeLeg();
				mt.getTileAt(mt.currentFil,mt.currentCol).assignImage();
			} catch (InterruptedException e) {
				System.err.println("Hilo animador interrumpido.");
			}
			
		}
	}
	
}
