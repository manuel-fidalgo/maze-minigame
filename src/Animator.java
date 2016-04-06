
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
			} catch (InterruptedException e) {
				System.err.println("thread interrupted.");
			}
			
		}
	}
	
}
