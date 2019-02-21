import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Load {
	public double lClicks;
	
	
	public Load(String path, String name) {
		
		try {
			File file = new File(path);
			File directory = new File("CC_lib");
			File datadir = new File("CC_lib/data");
			if(!directory.exists()) {
				directory.mkdir();
				datadir.mkdir();
				if(!file.exists()) {
					file.getParentFile();
					file.createNewFile();
				}
			}
			else if(!datadir.exists()) {
				datadir.mkdir();
				if(!file.exists()) {
					file.getParentFile();
					file.createNewFile();
				}
			}
			BufferedReader br = new BufferedReader(new FileReader(file));
			String num = br.readLine();
			if(num == null) {
				num = "" + 0;
			}
			
				try {
//					System.out.println(num + "fefe");
					lClicks = Double.parseDouble(num);
				}
				catch(NumberFormatException e) {
					lClicks = 0;
					e.printStackTrace();
				}
				
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public double getClicks() {
		return lClicks;
	}
	
}
