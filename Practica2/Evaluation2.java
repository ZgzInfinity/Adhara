package org.apache.lucene.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Evaluation {
	
	// Hashmap that stores the judgments of relevance
	private static HashMap<String, HashMap<String, String>> qrelsMap = new HashMap<>();
	
	// Hashmap that stores the documents retrieved for each information need
	private static HashMap<String, List<String>> resultsMap = new HashMap<>();
	
	// Hashmap that stores the MAP metric for each information need
	private static HashMap<Float, Float> recall_precisionMap = new HashMap<>();
	
	// Hashmap taht stores the interpolated exhaustiveness-precision points
	private static TreeMap<Float, List<Float>> fixedRecallList = new TreeMap<>();
	
	// List that contains the average precision metric for each information need
	private static List<Float> average_precisionList = new ArrayList<>();
	
	// List that stores the precision for each information need
	private static List<Float> precisionList = new ArrayList<>();
	
	// List that stores the recall for each information need
	private static List<Float> recallList = new ArrayList<>();
	
	// List that stores the prec@10 for each information need
	private static List<Float> precAt10List = new ArrayList<>();
	
	// List that stores the precision interpolated for each information need
	private static List<List<Float>> precisionInterpolatedList = new ArrayList<>();
	
	
	/*
	 * Evaluation metrics 
	 * tp = true positive
	 * fp = false positive
	 * fn = false negatives
	 */
	private static float tp, fp, fn, precision, recall, f1balanced;
	
	
	// List that contains the docIds of the documents retrieved
	private static List<Integer> relDocumentsList;

	public static void main(String[] args) {
		String usage = "java org.apache.lucene.demo.IndexFiles"
				+ " [-qrels QRELS_FILE_NAME] [-results RESULTS_FILE_NAME] [-output OUTPUT_FILE_NAME]\n\n"
				+ "This uses qrels for evaluating the results, writting the output to the output file";
		
		// Paths of the files
		String qrelsPath = null, resultsPath = null, outputPath = null;
		
	    // Processing of the parameters 
		for (int i = 0; i < args.length; i++) {
			// File which contains the relevancy judgements file
			if ("-qrels".equals(args[i])) {
				qrelsPath = args[i + 1];
				i++;
			}
			// File which contains the documents retrieved for each information need
			else if ("-results".equals(args[i])) {
				resultsPath = args[i + 1];
				i++;
			} 
			// File which will contains the evaluation metrics for each information need
			else if ("-output".equals(args[i])) {
				outputPath = args[i + 1];
			}
		}

		// Notify if there is an error finding any of the files
		if (qrelsPath == null || resultsPath == null || outputPath == null) {
			System.err.println("Usage: " + usage);
			System.exit(1);
		}
		
		// Load data from qrels and results files
		loadInputData(qrelsPath, resultsPath);

		// Writes evaluation metrics to output file
		evaluate(outputPath);

	}
	
	
	
	/*
	 * Pre: <<qrelsPath>> is the path of judgments of relevance file
	 *      and <<resultsPath>> is the path of the documents retrieved for each
	 *      information need
	 * Post: It has loaded the judgments of relevance  and the documents retrieved
	 *       for each of them in their correspond hashmaps
	 */
	private static void loadInputData(String qrelsPath, String resultsPath) {
		// Stored the path of the files
		File qrelsFile = new File(qrelsPath);
		File resultsFile = new File(resultsPath);
		
		try {
			// Opening the files
			BufferedReader qrelsBr = new BufferedReader(new FileReader(qrelsFile));
			BufferedReader resultsBr = new BufferedReader(new FileReader(resultsFile));

			String line;
			
			// reading line by line of the file of judgments of relevance
			while ((line = qrelsBr.readLine()) != null) {
				String[] elements = line.split("\\s+");
				// Verify if the information need key exists
				if (!qrelsMap.containsKey(elements[0])) {
					// Stores the key of the information need
					qrelsMap.put(elements[0], new HashMap<>());
				}
				// Stores the document and if it is relevant or not
				qrelsMap.get(elements[0]).put(elements[1], elements[2]);
			}
			
			
			// reading line by line of the file of documents retrieved
			while (((line = resultsBr.readLine()) != null)) {
				String[] elements = line.split("\\s+");
				// Verify if the information need key exists
				if (!resultsMap.containsKey(elements[0])) {
					// Stores the key of the information need
					resultsMap.put(elements[0], new ArrayList<>());
				}
				// Add the document retrieved for the information need
				resultsMap.get(elements[0]).add(elements[1]);
			}

			// Close the reading flows
			qrelsBr.close();
			resultsBr.close();

		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*
	 * Pre: <outputPath>> is the path of the file which is going
	 *      to contain the evaluation metrics
	 * Post: Has written in the file <<outputPath>> the 
	 *       evaluation metrics for each information and
	 *       finally the total of all pf them
	 */
	private static void evaluate(String outputPath) {
		// Path of the file
		File outputFile = new File(outputPath);
		FileWriter outputWriter;
		try {
			
			// Opening of the file and association of the writting flow
			outputWriter = new FileWriter(outputFile);
			PrintWriter pw = new PrintWriter(outputWriter);
			
			// the interpolated exhaustiveness-precision points
			// {0.0, 0.1, ...., 1.0}
			for(int i = 0; i <= 10; i++) {
				fixedRecallList.put(((float) i) / 10.f, new ArrayList<>());
			}
			
			
			// For each information need stored in the hashmap
			for (Entry<String, HashMap<String, String>> entry : qrelsMap.entrySet()) {
				
				// Initialize basic metrics values
				tp = 0;
				fp = 0;
				fn = 0;
				precision = 0;
				recall = 0;
				f1balanced = 0;
				relDocumentsList =  new ArrayList<>();
				
				// Calculate the tp, fp and fn values
				calculateBasicMetrics(entry.getKey());
				
				// Calculate the precision, recall and f1balanced of the
				// actual information need
				precision();
				recall();
				f1balanced();
				
				// Write the results obtained in the file
			    pw.println("INFORMATION_NEED " + entry.getKey());
			    pw.println("precision " + String.valueOf(precision));
			    pw.println("recall " + String.valueOf(recall));
			    pw.println("F1 " + String.valueOf(f1balanced));
			    pw.println("prec@10 " + precAt10(entry.getKey()));
			    pw.println("average_precision " + average_precision(entry.getKey()));
			    pw.println("recall_precision");
			    recall_precision(entry.getKey(), pw);
			    pw.println("interpolated_recall_precision");
			    interpolated_recall_precision(entry.getKey(), pw);
			    pw.println();
			    
			}
			
			// Calculation of the global metrics   
			pw.println("TOTAL");
			// Average of the precisions obtained for each information need
			precision = (float) precisionList.stream().mapToDouble(val -> val).average().getAsDouble();
			pw.format("%s%.3f%s", "precision ", precision, "\n");
			// Average of the recalls obtained for each information need
			recall = (float) recallList.stream().mapToDouble(val -> val).average().getAsDouble();
		    pw.format("%s%.3f%s", "recall ", recall, "\n");
		    // Caculation of the F1 value using the precision and recall calculated before
		    pw.format("%s%.3f%s", "F1 ", (2 * precision * recall) / (precision + recall), "\n");
			// Average of the prec@10 obtained for each information need
		    pw.format("%s%.3f%s", "prec@10 ", precAt10List.stream().mapToDouble(val -> val).average().getAsDouble(), "\n");
			// Average of the MAP obtained for each information need
		    pw.format("%s%.3f%s", "MAP ", average_precisionList.stream().mapToDouble(val -> val).average().getAsDouble(), "\n");
			// Average of the interpolated_recall_precisions obtained for each information need
		    pw.println("interpolated_recall_precision");
		    for (Entry<Float, List<Float>> entry : fixedRecallList.entrySet()) {
		    	pw.format("%.3f%s%.3f%s",entry.getKey(), " ",  entry.getValue().stream().mapToDouble(val -> val).average().getAsDouble(), "\n");
		    }
		    pw.println();
			
		    // Closing the writting flow
			outputWriter.close();
						
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*
	 * Pre: <<infNeed>> is the identifier of a information need
	 * Post: Calcualtes the values of the tp, fn and fp variables
	 */
	private static void calculateBasicMetrics(String infNeed) {
		// Verifys if exists an information need with the key <<infoNeed>>
		if (resultsMap.containsKey(infNeed)) {
			// If system returned any docs for infNeed gets the 
			HashMap<String, String> qrelsInnerMap = qrelsMap.get(infNeed);
			List<String> resultsList = resultsMap.get(infNeed);
			int docIndex = 1;
			// For each document retrieved
			for (String docId: resultsList) {
				// Check if the document has been evaluated
				if (qrelsInnerMap.containsKey(docId)) {
					// Check if the document is relevant 
					if(qrelsInnerMap.get(docId).equals("1")) { 
						// The document is added to the list relevant documents
						tp++;
						relDocumentsList.add(docIndex);
					}
					else {
						// The document has been retrieved but it's not relevant
						fp++;
					}
				}
				else {
					// The document has not been evaluated
					fp++;
				}
				// Increasing the index of the 
				docIndex++;
		    }
			
			// For each judgment of relevance
			for (Entry<String, String> entry : qrelsInnerMap.entrySet()) {
				// Check if the there are relevant documents which have not been retrieved
				if(entry.getValue().equals("1") && !resultsList.contains(entry.getKey())) {
					fn++;
				}				
			}
		}
		
		
	}
	
	
	
	/*
	 * Pre: ---
	 * Post: Calculation of the precision metric
	 */
	private static void precision() {
		precision = tp / (tp + fp);
		precisionList.add(precision);
	}
	
	
	
	/*
	 * Pre: ---
	 * Post: Calculation of the recall metric
	 */
	private static void recall() {
		recall = tp / (tp + fn);
		recallList.add(recall);
	}
	
	
	
	/*
	 * Pre:
	 * Post: Calculation of the f1balanced metric
	 */
	private static void f1balanced() {
		f1balanced = (2 * precision * recall) / (precision + recall);
	}
	
	
	
	/*
	 * Pre: <<infNeed>> is the identifier of a information need
	 * Post: Calculates the prec@10 metric
	 */
	private static String precAt10(String infNeed) {
		float tp10 = 0;
		// Verifys if exists an information need with the key <<infoNeed>>
		if (resultsMap.containsKey(infNeed)) {
			// If system returned any docs for infNeed 
			HashMap<String, String> qrelsInnerMap = qrelsMap.get(infNeed);
			// Obtains the list of the documents retrieved
			List<String> resultsList = resultsMap.get(infNeed);
			for (int i = 0; i < resultsList.size() && i < 10; i++) {
				// Check if the document i is contained in the list of retrieved documents
				if (qrelsInnerMap.containsKey(resultsList.get(i))) {
					// Check if the document is relevant
					if(qrelsInnerMap.get(resultsList.get(i)).equals("1")) {
						tp10++;
					}
				}
		    }
		}
		// Calculate the prec@10 metric
		precAt10List.add(tp10 / 10);
		return String.valueOf(tp10 / 10);
	}

	
	
	/*
	 * Pre: <<infNeed>> is the identifier of a information need
	 * Post: Calculates the average_precision metric
	 */
	private static String average_precision(String infNeed) {
		// Average precision
		float meanPrecision = 0;
		// Total of the relevant documents retrieved
		float numRelDocs = 0;
		// Verifys if exists an information need with the key <<infoNeed>>
		if (resultsMap.containsKey(infNeed)) {
			// If system returned any docs for infNeed
			HashMap<String, String> qrelsInnerMap = qrelsMap.get(infNeed);
			// Obtaining the list of the documents retrieved
			List<String> resultsList = resultsMap.get(infNeed);
			// For each document of the list of results
			for (int i = 0; i < resultsList.size(); i++) {
				// Check if the document i is in the list of documents retrieved
				if (qrelsInnerMap.containsKey(resultsList.get(i))) {
					// Check if the document is relevant
					if(qrelsInnerMap.get(resultsList.get(i)).equals("1")) {
						// Update the average precision
						meanPrecision += precAtK(infNeed, i + 1);
						// Increasing the number of relevant documents
						numRelDocs++;
					}
				}
		    }
		}
		// Calculate the average precision
		average_precisionList.add(meanPrecision / numRelDocs);
		return String.valueOf(meanPrecision / numRelDocs);
		
	}
	
	
	
	/*
	 * Pre: <<infNeed>> is the identifier of a information need 
	 *      and <<k>> is the number of documents used to calculate
	 *      the precision
	 * Post: Calcultes the precAtk metric
	 */
	private static float precAtK(String infNeed, int k) {
		float tpk = 0;
		// Verifys if exists an information need with the key <<infoNeed>>
		if (resultsMap.containsKey(infNeed)) {
			// If system returned any docs for infNeed
			HashMap<String, String> qrelsInnerMap = qrelsMap.get(infNeed);
			// List of the documents retrieved
			List<String> resultsList = resultsMap.get(infNeed);
			// For each of the k documents
			for (int i = 0; i < k; i++) {
				// Check if the document i is in the list of documents retrieved
				if (qrelsInnerMap.containsKey(resultsList.get(i))) {
					// Check if the document is relevant
					if(qrelsInnerMap.get(resultsList.get(i)).equals("1")) { 
						tpk++;
					}
				}
		    }
		}
		// Calculate thre precAtK metric
		return tpk / k;
	}
	
	
	
	/*
	 * Pre: <<infNeed>> is the identifier of a information need 
	 *      and <<pw>> is the writting flow associated to the evaluation 
	 *      metric files
	 * Post: Calculates the recall_precision metric
	 */
	private static void recall_precision(String infNeed, PrintWriter pw) {
		// Calculate the recall per document
		float recallPerDocument = recall / relDocumentsList.size();
		// Acumulated recall value
		float acumulatedRecall = 0;
		recall_precisionMap = new HashMap<>();
		// For each relevant document
		for(Integer docIndex : relDocumentsList) {
			// Sum the acumulated recall vlue 
			acumulatedRecall += recallPerDocument;
			// Write the recall_precision value acumulated until the document whose docId is <<docIndex>>
			recall_precisionMap.put(acumulatedRecall, precAtK(infNeed, docIndex));
			pw.format("%.3f%s%.3f%s", acumulatedRecall, " ", precAtK(infNeed, docIndex), "\n");
		}
	}
	
	
	
	/*
	 * Pre: <<infNeed>> is the identifier of a information need 
	 *      and <<pw>> is the writting flow associated to the evaluation 
	 *      metric files 
	 * Post: Calculates the interpolated_recall_precision metric
	 */
	private static void interpolated_recall_precision(String infNeed, PrintWriter pw) {
		// Value of the max precision found 
		Float maxPrecision;
		precisionInterpolatedList.add(new ArrayList<>());
		// For each interpolated exhaustiveness-precision points
		for (Entry<Float, List<Float>> entry : fixedRecallList.entrySet()) {
			// Initialize the max precision found
			maxPrecision = 0.0F;
			// For each information need MAP metric
			for (Entry<Float, Float> entryMap : recall_precisionMap.entrySet()) {
				// Check if the interpolated exhaustiveness-precision point is lower or equal than the MAP value
				if(entry.getKey() <= entryMap.getKey()) {
					if(entryMap.getValue() > maxPrecision) {
						// Update de max precision
						maxPrecision = entryMap.getValue();
					}
				}
			}
			// Write the max precision until the 
			pw.format("%.3f%s%.3f%s", entry.getKey(), " ", maxPrecision, "\n");
			entry.getValue().add(maxPrecision);
		}
	}

}
