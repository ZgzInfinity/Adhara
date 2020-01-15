/*
 *********************************************
 *** Adhara - Algorithm for hard problems ****
 *** Authors: Name - Surname - NIP ***********
 *** Victor Peñasco EStivalez - 741294 *******
 *** Ruben Rodriguez Esteban - 737215 ********
 *** Course: 2019 - 2020 *********************
 *********************************************
 */ 

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		// Check the number of parameters
		if(args.length == 2) {
			String text = "";
			// Compress mode
			if(args[0].contentEquals("-c")) {
				try {
					// Open the file which is going to be compressed
					text = new String(Files.readAllBytes(Paths.get(args[1])), StandardCharsets.UTF_8);
					// Add termination character to file content
					char ETX = 0x03;
					text += ETX;
					// Create the suffixes array
					SuffixArray sa = new SuffixArray(text);
					int[] si = sa.getSuffixIndex();
					System.out.println("suffix");
					// Apply Burrows Wheeler transformation and move to front algorithm to the result
					String transformed = BurrowsWheeler.bwt(text, si);
					System.out.println("bwt");
					String result = MoveToFront.moveToFront(transformed);
					System.out.println("mtf");

					// Write the result in a new file
					BufferedWriter writer = null;
					try {
						// Create the temporary file passed later to Huffman
						File file = new File(args[1] + "BW");
						// Write the content in the file
						writer = new BufferedWriter(new FileWriter(file));
			            writer.write(result);
			            System.out.println("written");
					}
					catch (Exception e) {
						// Throw exception if the opening of the file goes bad
				        e.printStackTrace();
					} 
					finally 
					{
				        try {
							// Close the writing flow
							writer.close();
						} 
						catch (Exception e) {
							// Throw exception if the opening of the file in order to write goes bad
							e.printStackTrace();
				        }
				    }
				}
				catch (IOException e) {
					// Throw exception if the opening of the file in order to read goes bad
					e.printStackTrace();
				}
			}
			else if(args[0].contentEquals("-u")) {
				// Uncompress mode
				try {
					// Read the content of the file which is going to be uncompressed
					text = new String(Files.readAllBytes(Paths.get(args[1] + "BW")), StandardCharsets.UTF_8);
					// Apply move to front inverse algorithm and Burrows Wheeler inverse transformation to the result
					text = MoveToFront.moveToFrontInverse(text);
					String result = BurrowsWheeler.bwtInverse(text);
					BufferedWriter writer = null;
					try {
						// Open the file where the result is going to be write
						File file = new File(args[1].substring(0, args[1].length() - 4));
						writer = new BufferedWriter(new FileWriter(file));
						// Write the content in the file
						writer.write(result);
						// Close the writing flow
			            writer.close();
					}
					catch (Exception e) {
						// Throw exception if the opening of the file in order to write goes bad
				        e.printStackTrace();
				    } 
					finally 
					{
				        try {
							// Close the writing flow 
				            writer.close();
				        }
				        catch (Exception e) {
							// Throw exception if closing writng flow gives error
				        	e.printStackTrace();
				        }
				    }
				}
				catch (IOException e) {
					// Throw exception if the opening of the file in order to read goes bad
					e.printStackTrace();
				}
			}
			else {
				// Bad arguments introduced
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
