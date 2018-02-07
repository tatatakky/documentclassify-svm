import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import net.moraleboost.mecab.Lattice;
//import net.moraleboost.mecab.Node;
//import net.moraleboost.mecab.Tagger;
//import net.moraleboost.mecab.impl.StandardTagger;

public class TFIDFDemoE {

	public static void main (String[] args)throws IOException{
	    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		List<String> documents = new ArrayList<String>();
		int j=0;
		int jj=1;
		int jjj=0;
		while(true){
		    j++;
		    String s = new String(in.readLine());
		    documents.add(s);
		    s = null;
		    if(j == 60) break;
		}

		FeatureVectorGeneratorE generator = new FeatureVectorGeneratorE();
		Map<String, double[]> featureVectors = generator.generateTFIDFVectors(documents);

		for(Map.Entry<String, double[]> entry : featureVectors.entrySet()){

			// System.out.println("--- Document ---");
			// System.out.println(entry.getKey());

			// System.out.println("--- Feature Vector ---");

			double[] featureVector = entry.getValue();
			System.out.print("");
			if(jj<= 30) System.out.print("1 ");
			if(jj > 30) System.out.print("-1 ");
			jj++;
			for(int i=0; i < featureVector.length; i++){
			    System.out.print(i+1);
			    System.out.print(":");
			    System.out.print(String.format("%.2f", featureVector[i]));
			    if(i != featureVector.length-1){
				System.out.print(" ");
			    }
			}
			System.out.println("");
		}
	}
}
