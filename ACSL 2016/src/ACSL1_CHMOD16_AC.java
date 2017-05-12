import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * ACSL Contest 1: CHMOD
 * @author Alison Chen
 * 
 */
public class ACSL1_CHMOD16_AC {
	
	public static void main(String[] args) throws IOException {
		//declarations:
		String filein = "testdata/CHMOD_TestData";
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
		//take input and parse
		String[] data = input.split(",[ ]*");
		int special = Integer.parseInt(data[0]);
		int user = Integer.parseInt(data[1]);
		int group = Integer.parseInt(data[2]);
		int others = Integer.parseInt(data[3]);

		//converts to base 2 from octal
		String buser = Integer.toBinaryString(user);
		String bgroup = Integer.toBinaryString(group);
		String bothers = Integer.toBinaryString(others);
		
		//converts to int and adds 0s
		int f_user = Integer.parseInt(buser);
		int f_group = Integer.parseInt(bgroup);
		int f_others = Integer.parseInt(bothers);
		String b_user = String.format("%03d", f_user);
		String b_group = String.format("%03d", f_group);
		String b_others = String.format("%03d", f_others);
		
		//parse individual binaries
		String[] rwx_user = b_user.split("");
		int ruser = Integer.parseInt(rwx_user[0]);
		int wuser = Integer.parseInt(rwx_user[1]);
		int xuser = Integer.parseInt(rwx_user[2]);
		
		String[] rwx_group = b_group.split("");
		int rgroup = Integer.parseInt(rwx_group[0]);
		int wgroup = Integer.parseInt(rwx_group[1]);
		int xgroup = Integer.parseInt(rwx_group[2]);
		
		String[] rwx_others = b_others.split("");
		int rothers = Integer.parseInt(rwx_others[0]);
		int wothers = Integer.parseInt(rwx_others[1]);
		int xothers = Integer.parseInt(rwx_others[2]);
				
		//convert back to string
		String user0 = Integer.toString(ruser);
		String user1 = Integer.toString(wuser);
		String user2 = Integer.toString(xuser);
		String group0 = Integer.toString(rgroup);
		String group1 = Integer.toString(wgroup);
		String group2 = Integer.toString(xgroup);
		String others0 = Integer.toString(rothers);
		String others1 = Integer.toString(wothers);
		String others2 = Integer.toString(xothers);

		//replace with stuff
		if(ruser == 0) user0 = user0.replaceAll("0", "-"); else user0 = user0.replaceAll("1", "r");
		if(wuser == 0) user1 = user1.replaceAll("0", "-"); else user1 = user1.replaceAll("1", "w");
		if(xuser == 0) user2 = user2.replaceAll("0", "-"); else user2 = user2.replaceAll("1", "x");
		if(rgroup == 0) group0 = group0.replaceAll("0", "-"); else group0 = group0.replaceAll("1", "r");
		if(wgroup == 0) group1 = group1.replaceAll("0", "-"); else group1 = group1.replaceAll("1", "w");
		if(xgroup == 0) group2 = group2.replaceAll("0", "-"); else group2 = group2.replaceAll("1", "x");
		if(rothers == 0) others0 = others0.replaceAll("0", "-"); else others0 = others0.replaceAll("1", "r");
		if(wothers == 0) others1 = others1.replaceAll("0", "-"); else others1 = others1.replaceAll("1", "w");
		if(xothers == 0) others2 = others2.replaceAll("0", "-"); else others2 = others2.replaceAll("1", "x");

		//check special permissions
		if(special==1) user2 = user2.replaceAll("x", "s");
		else if(special==2) group2 = group2.replaceAll("x", "s");
		else if(special==4) others2 = others2.replaceAll("x", "t");
		
		//print result in format
		String result = b_user + " " + b_group + " " + b_others	+ " and "
				+ user0 + user1 + user2 + " " + group0 + group1 + group2 + " "
				+ others0 + others1 + others2;	
		
		return result;
	}
}
