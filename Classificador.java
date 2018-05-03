import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import weka.core.Instances;
import weka.classifiers.Classifier;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class Classificador{

	public static void main(String args[]) throws Exception { 

		double clsLabel; 
		String model_file = args[0];
		String descriptors_file = args[1];
		String output_file = args[2];

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(model_file)); 
		Classifier cls = (Classifier) ois.readObject();
		ois.close();

		Instances unlabeled = new Instances(
		                        new BufferedReader(
		                          new FileReader(descriptors_file))); 
		  
		// set class attribute
		unlabeled.setClassIndex(unlabeled.numAttributes() - 1);

		// create copy
		Instances labeled = new Instances(unlabeled);

		// label instances
		for (int i = 0; i < unlabeled.numInstances (); i++) {
			clsLabel = cls.classifyInstance(unlabeled.instance(i));
		 	labeled.instance(i).setClassValue(clsLabel);
		}

		BufferedWriter writer = new BufferedWriter(
		                         new FileWriter(output_file)); 
		for (int i = 0; i < unlabeled.numInstances(); i++) {
			clsLabel = labeled.instance(i).classValue();
			writer.write(labeled.classAttribute().value((int)clsLabel) + "\n");
		 }
		writer.flush();
		writer.close ();

		// save labeled data in an arff file
		// BufferedWriter writer = new BufferedWriter(
//		                          new FileWriter("labeled.arff"));
		// writer.write(labeled.toString());
		// writer.newLine ();
		// writer.flush();
		// writer.close();

	}
                
}