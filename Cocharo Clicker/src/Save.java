import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Save {
	
	
	private File file;
	
	private BufferedWriter br;
	
	public Save(String path) throws IOException {
		file = new File(path);	
	}
	
	public void saveGame(ArrayList<Double> number) {
		try {
			FileWriter fwrite = new FileWriter(file);
			br = new BufferedWriter(fwrite);
			if(number.size() > 1) {
				for(int i = 0; i < number.size()-1; i++) {
					br.write("" + number.get(i) + "\n");
				}	
				br.write("" + number.get(number.size()-1));
			}
			else {
				br.write("" + number.get(0));
			}
			br.flush();
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
