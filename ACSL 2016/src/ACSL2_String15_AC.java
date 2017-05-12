import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
/**
 * ACSL Contest 2: String
 * @author Alison Chen
 *
 */
public class ACSL2_String15_AC {

	public static void main(String[] args) throws IOException {
		//declarations:
		String filein = "testdata/String_TestData";
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
			String answer = solve(scan.nextLine());
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
	
	//if started with a + in front, make sure to add back?
	static String solve(String input) {
		//take input and parse
		String[] data = input.split(",[ ]*");
		int plus = 0;
		String floatex = data[0];
		if(floatex.contains("+")) {
			plus=1;
		}
		double number = Double.parseDouble(data[0]);
		int length = Integer.parseInt(data[1]);
		int decimal = Integer.parseInt(data[2]);
		String rounded;
		String finished = null;
		
		//round
		//if negative number ends in 5, round down else normally round
		double factor = Math.pow(10, decimal);
		String given = Double.toString(number);
		String[] num = given.split("");
		int lastdig = Integer.parseInt(num[num.length-1]);
		if(number < 0 && lastdig ==5) {
			rounded = Double.toString((-1)*Math.round(Math.abs(number*factor))/factor);
		}
		else rounded = Double.toString(Math.round(number*factor)/factor);
		
		
		if(plus==1) {
			rounded = "+" + rounded;
		}
		
//		System.out.println(length + " and " + rounded.length());
		//compare to desired length
		if(length<rounded.length()) {
			//print just hastags
			//make sure that the decimal is in the right place
			//make char array???
			char[] fin = new char[length];
			for (int i = 0; i < fin.length; i++) {
				if(i==fin.length-decimal-1) {
					fin[i]='.';
				}
				else fin[i]='#';
			}
			finished = new String(fin);
		}
		else {
			char[] tags = new char[length-rounded.length()];
			Arrays.fill(tags, '#');
			finished = new String(tags) + rounded;
		}

		return finished;
	}
}

