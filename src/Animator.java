
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
				for(Box i : mt.cherry_boxes){
					mt.getTileAt(i.fil, i.col).changePosition();
				}
			} catch (InterruptedException e) {
				System.err.println("thread interrupted.");
			}catch(java.util.ConcurrentModificationException ex){
				System.err.println("Concurrent modificaiton exception");
			}
			
		}
	}
	
}
