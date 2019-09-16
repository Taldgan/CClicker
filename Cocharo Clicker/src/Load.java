import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Load {
	public ArrayList<Double> data = new ArrayList<Double>();
	
	private double obj;
	
	
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
			do {
				if(num == null) {
					System.out.println("nulllll");
					num = "" + 0;
				}
				
					try {
						System.out.println("Loading " + obj);
						obj = Double.parseDouble(num);
						data.add(obj);
					}
					catch(NumberFormatException e) {
						obj = 0;
						e.printStackTrace();
					}	
			} while((num = br.readLine()) != null);
				
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public ArrayList<Double> getData() {
		System.out.println(data.toString());
		return data;
	}
	
}
