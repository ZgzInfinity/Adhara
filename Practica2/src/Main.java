import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		if(args.length == 2) {
			String text;
			if(args[0].contentEquals("-c")) {
				// Open file
				try {
					text = new String(Files.readAllBytes(Paths.get(args[1])), StandardCharsets.UTF_8);
					char ETX = 0x03;
					text += ETX;
					SuffixArray sa = new SuffixArray(text);
					List<Integer> si = sa.getSuffixIndex();
					String transformed = BurrowsWheeler.bwt(text, si);
					String result = MoveToFront.moveToFront(transformed);

					BufferedWriter writer = null;
					try {
						File file = new File(args[1]);
						writer = new BufferedWriter(new FileWriter(file));
			            writer.write(result);
					}
					catch (Exception e) {
				        e.printStackTrace();
				    } finally {
				        try {
				            writer.close();
				        } catch (Exception e) {
				        }
				    }
				}
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else if(args[0].contentEquals("-u")) {
				try {
					text = new String(Files.readAllBytes(Paths.get(args[1])), StandardCharsets.UTF_8);
					text = MoveToFront.moveToFrontInverse(text);
					String result = BurrowsWheeler.bwtInverse(text);
					BufferedWriter writer = null;
					try {
						File file = new File(args[1]);
						writer = new BufferedWriter(new FileWriter(file));
			            writer.write(result);
			            writer.close();
					}
					catch (Exception e) {
				        e.printStackTrace();
				    } 
					finally {
				        try {
				            writer.close();
				        }
				        catch (Exception e) {
				        	e.printStackTrace();
				        }
				    }
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				System.err.println("Unknown parameter");
				System.exit(1);
			}
		}
		else {
			System.err.println("Wrong number of parameters");
			System.exit(1);
		}
		
		
	}

}
