import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * ACSL Contest 4: Reg Exp
 * @author Alison Chen
 *
 */
public class ACSL4_RegExp15_AC {

	public static void main(String[] args) throws IOException {
		//declarations:
		String filein = "testdata/RegExp_TestData";
		Scanner scan;
		FileOutputStream fop = null;

		try{
			scan = new Scanner(new BufferedReader(new FileReader(filein)));
			fop = new FileOutputStream(new File(filein+"-out"));
		} catch(FileNotFoundException e) {
			//switch to regular terminal input
			scan = new Scanner(System.in);
		}

		//main loop:
		while(scan.hasNext()) {
			String[] data = new String[6];
			for (int i = 0; i < data.length; i++) {
				data[i] = scan.nextLine();
			}
			String answer = solve(data);
			System.out.println(answer);
			if(fop!=null) fop.write((answer+"\n").getBytes());
		}

		fop.flush();
		fop.close();
		scan.close();
	}

	/**
	 * solve for a specific input
	 * @param s the input
	 * @return the output 
	 */
	static String solve(String[] input) {
		//parse first line
		String[] data = input[0].split(",[ ]*");
		String[] result1 = new String[10];
		String[] result2 = new String[10];
		String[] result3 = new String[10];
		String[] result4 = new String[10];
		String[] result5 = new String[10];

		for (int j = 0; j < data.length; j++) {
			if(data[j].matches(input[1])) result1[j] = data[j];
			if(data[j].matches(input[2])) result2[j] = data[j];
			if(data[j].matches(input[3])) result3[j] = data[j];
			if(data[j].matches(input[4])) result4[j] = data[j];
			if(data[j].matches(input[5])) result5[j] = data[j];
		}
		
		String r1 = "";
		String r2 = "";
		String r3 = "";
		String r4 = "";
		String r5 = "";
		
		for (int i = 0; i < result1.length; i++) {
			if(result1[i] != null && !result1[i].isEmpty()) r1 = r1 + result1[i] + ", ";
		}
		for (int i = 0; i < result2.length; i++) {
			if(result2[i] != null && !result2[i].isEmpty()) r2 = r2 + result2[i] + ", ";
		}
		for (int i = 0; i < result3.length; i++) {
			if(result3[i] != null && !result3[i].isEmpty()) r3 = r3  + result3[i] + ", ";
		}
		for (int i = 0; i < result4.length; i++) {
			if(result4[i] != null && !result4[i].isEmpty()) r4 = r4  + result4[i] + ", ";
		}
		for (int i = 0; i < result5.length; i++) {
			if(result5[i] != null && !result5[i].isEmpty()) r5 = r5  + result5[i] + ", ";
		}
		
		r1 = r1.replaceAll(", $", "");
		r2 = r2.replaceAll(", $", "");
		r3 = r3.replaceAll(", $", "");
		r4 = r4.replaceAll(", $", "");
		r5 = r5.replaceAll(", $", "");
		
		return r1 + "\n" + r2 + "\n" + r3 + "\n" + r4 + "\n" + r5 + "\n";

	}
}
