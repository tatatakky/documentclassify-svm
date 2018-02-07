
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
/*
import net.moraleboost.mecab.Lattice;
import net.moraleboost.mecab.Node;
import net.moraleboost.mecab.Tagger;
import net.moraleboost.mecab.impl.StandardTagger;
*/

public class FeatureVectorGeneratorE {

	public Map<String, double[]> generateTFIDFVectors(List<String> documents){

		// List<word>
		List<String> words = new ArrayList<String>();

		// Map<document, Map<word, tf>>
		Map<String, Map<String, Integer>> tfMap = new LinkedHashMap<String, Map<String, Integer>>();

		// Map<word, df>
		Map<String, Integer> dfMap = new LinkedHashMap<String, Integer>();

		for(String document : documents){

			List<String> parseResult = parse(document);

			Map<String, Integer> docTFMap = new LinkedHashMap<String, Integer>();
			for(String word : parseResult){


				if(docTFMap.containsKey(word)){
					int tf = docTFMap.get(word);
					docTFMap.put(word, tf+1);
				} else{
					docTFMap.put(word, 1);
				}


				if(!words.contains(word)){
					words.add(word);
				}
			}

			tfMap.put(document, docTFMap);


			for(String word : docTFMap.keySet()){
				if(dfMap.containsKey(word)){
					int df = dfMap.get(word);
					dfMap.put(word, df+1);
				} else{
					dfMap.put(word, 1);
				}
			}
		}


		Map<String, double[]> featureVectors = new LinkedHashMap<String, double[]>();
		for(String document : documents){


			Map<String, Double> docTFIDFMap = new LinkedHashMap<String, Double>();


			Map<String, Integer> docTFMap = tfMap.get(document);
			for(String word : docTFMap.keySet()){

				double tf = (double)docTFMap.get(word);
				double df = (double)dfMap.get(word);
				double N = (double)documents.size();

				double tfidf = tf * Math.log(N/df);

				docTFIDFMap.put(word, tfidf);
			}


			double[] featureVector = new double[words.size()];
			for(int i=0; i < words.size(); i++){
				String word = words.get(i);
				if(docTFIDFMap.containsKey(word)){
					featureVector[i] = docTFIDFMap.get(word);
				} else{
					featureVector[i] = 0;
				}
			}
			featureVectors.put(document, featureVector);
		}


		// System.out.println("=== word list ===");
		// System.out.print("(");
		for(int i=0; i < words.size(); i++){
		    //System.out.print(words.get(i));
			if(i != words.size()-1){
			    //System.out.print(", ");
			}
		}
		// System.out.println(")");
		// System.out.println("");

		return featureVectors;
	}

	private List<String> parse(String str){

		String[] splittedWords = str.split(" ");

		List<String> result = new ArrayList<String>();
		for(String word : splittedWords) {
			result.add(word);
		}
		return result;
	}
}
