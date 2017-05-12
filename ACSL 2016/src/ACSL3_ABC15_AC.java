import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
/**
 * ACSL Contest 3: ABC
 * @author Alison Chen
 *
 */
public class ACSL3_ABC15_AC {
	public static void main(String[] args) throws IOException {
		//declarations:
		String filein = "testdata/ABC_TestData";
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

	//type all code here
	static String solve(String input) {
		//make char array
		String[][] abc = new String[6][6];
		//loop through array and fill with numbers
		int num = 1;
		for (int i = 0; i < abc.length; i++) {
			for (int j = 0; j < abc.length; j++) {
				abc[i][j] = Integer.toString(num);
				num++;
				//				System.out.print(abc[i][j] + "	");
			}
			//			System.out.println();
		}
		//		System.out.println();

		//parse filled and add to array
		String[] data = input.split(",[ ]*");
		String fill1 = data[0];
		String fill2 = data[1];
		String fill3 = data[2];
		String fill4 = data[3];

		//add plus signs and print
		for (int i = 0; i < abc.length; i++) {
			for (int j = 0; j < abc.length; j++) {
				if(abc[i][j].equals(fill1)) {
					abc[i][j] = "+";
				}
				else if(abc[i][j].equals(fill2)) {
					abc[i][j] = "+";
				}
				else if(abc[i][j].equals(fill3)) {
					abc[i][j] = "+";
				}
				else if(abc[i][j].equals(fill4)) {
					abc[i][j] = "+";
				}
				//				System.out.print(abc[i][j] + "	");
			}
			//			System.out.println();
		}
		//		System.out.println();

		//add predetermined letters and print
		for (int a = 5; a < data.length; a=a+2) {
			for (int i = 0; i < abc.length; i++) {
				for (int j = 0; j < abc.length; j++) {
					if(abc[i][j].equals(data[a+1])) {
						abc[i][j] = data[a];
					}
					//					System.out.print(abc[i][j] + "	");
				}
				//				System.out.println();
			}
			//			System.out.println();
		}

		//push letters in and print
		for (int i = 0; i < abc.length; i++) {
			for (int j = 0; j < abc.length; j++) {
				//check if is a letter
				boolean letter = false;
				if(abc[i][j].equals("A")) letter = true;
				else if(abc[i][j].equals("B")) letter = true;
				else if(abc[i][j].equals("C")) letter = true;

				if(letter==true) {
					if(i==0) {
						if(abc[1][j].equals("+")) abc[i+2][j] = abc[i][j];
						else abc[i+1][j] = abc[i][j];
					}
					if(i==5) {
						if(abc[4][j].equals("+")) abc[i-2][j] = abc[i][j];
						else abc[i-1][j] = abc[i][j];
					}
					if(j==0) {
						if(abc[i][1].equals("+")) abc[i][j+2] = abc[i][j];
						else abc[i][j+1] = abc[i][j];
					}
					if(j==5) {
						if(abc[i][4].equals("+")) abc[i][j-2] = abc[i][j];
						else abc[i][j-1] = abc[i][j];
					}
					else abc[i][j]=abc[i][j];
				}
				else abc[i][j] = abc[i][j];
				//				System.out.print(abc[i][j] + "	");
			}
			//			System.out.println();
		}
		//		System.out.println();

		//print current version
		for (int i = 0; i < abc.length; i++) {
			for (int j = 0; j < abc.length; j++) {

				//				System.out.print(abc[i][j] + "	");
			}
			//			System.out.println();
		}

		//solve the puzzle
		int n = 0;
		while(n<5) {
			for (int i = 1; i < abc.length-1; i++) {
				for (int j = 1; j < abc.length-1; j++) {
					if(abc[i][j].equals("A")) abc[i][j] = abc[i][j];
					else if(abc[i][j].equals("B")) abc[i][j] = abc[i][j];
					else if(abc[i][j].equals("C")) abc[i][j] = abc[i][j];
					else if(abc[i][j].equals("+")) abc[i][j] = abc[i][j];
					else {
						char[] set = {'A', 'B', 'C'};
						//loop items in row
						for (int c = 1; c < abc.length-1; c++) {
							if(abc[i][c].equals("A")) set[0] = '0';
							else if(abc[i][c].equals("B")) set[1] = '0';
							else if(abc[i][c].equals("C")) set[2] = '0';
						}
						//loop items in column
						for (int r = 1; r < abc.length-1; r++) {
							if(abc[r][j].equals("A")) set[0] = '0';
							else if(abc[r][j].equals("B")) set[1] = '0';
							else if(abc[r][j].equals("C")) set[2] = '0';
						}
						for (int j2 = 0; j2 < set.length; j2++) {
							if(set[j2]=='0') {
								for (int k1 = j2+1; k1 < set.length; k1++) {
									if(set[k1]=='0') abc[i][j] = String.valueOf(set[set.length-k1-j2]);
								}
							}
						}
					}
				}
			}
			n++;
		}

		String result = "";

		for (int i = 1; i < abc.length-1; i++) {
			for (int j = 1; j < abc.length-1; j++) {
				boolean letter = false;
				if(abc[i][j].equals("A")) letter = true;
				else if(abc[i][j].equals("B")) letter = true;
				else if(abc[i][j].equals("C")) letter = true;

				if(letter==true) result = result + abc[i][j];
			}
		}

		//convert to string form
		return result;
	}
}

