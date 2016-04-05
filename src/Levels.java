
public class Levels {
	public Level[][] lvs;
	int tmat;
	
	public Levels(){
		tmat = 6;
		Level l = new Level();
		lvs = new Level[tmat][tmat];
		for (int i = 0; i < tmat; i++) {
			for (int j = 0; j < tmat; j++) {
				lvs[i][j] = l.getLevel();
			}
		}
	}

}
