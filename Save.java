import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
	
	
	private File file;
	
	private BufferedWriter br;
	
	public Save(String path) throws IOException {
		file = new File(path);	
	}
	
	public void saveGame(double number) {
		try {
			FileWriter fwrite = new FileWriter(file);
			br = new BufferedWriter(fwrite);
			System.out.println(number);
			br.write("" + number);
			br.flush();
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
