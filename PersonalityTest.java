import java.io.FileInputStream; 
import java.util.Scanner;
public class PersonalityTest {
	public static final String surveyQues = "1223344122334412233441223344122334412233441223344122334412233441223344";//type of survey questions	
	public static void main(String[] args) {//MAIN METHOD
				Scanner console = new Scanner(System.in);
				String filepath = console.nextLine();
				processFile(filepath);//process input file
				console.close();//close scanner object
	}//end of main method
	public static void processFile(String filepath) {//METHOD 1
		try(Scanner scan = new Scanner(new FileInputStream(filepath))){
			 int index=0;//Check number of lines in the file
			 System.out.print("Input file name: ");
			    while(scan.hasNextLine()){
			    	String test = scan.nextLine();//capture each line
			    	if(test.length()<50) {//check if it the person name or survey line
			    		System.out.println(test+":");
			    	}else {//process survey line
			    		personality(test);//process personality
			    		if(index!=17) System.out.println("");//eliminate the last next line
			    	}
			        index++;//increment index as the program reads the line
			    }
	         scan.close();//close the file   
		}
		catch(Exception ex) {
			ex.printStackTrace();//print any exception when reading the file
		}
	}//end of method processFile
	public static void personality(String survey) {//METHOD 2
		survey = survey.toUpperCase();//as some characters are upper and other lower case make them in the same case
		//1223344122334412233441223344122334412233441223344122334412233441223344
		//BAAAAA-BAAAABABAAAAAABA-AAAABABAAAABAABAA-BAAABAABAAAAAABA-BAAABA-BAAA
		String [] surveyQuesArry = new String[] {};//capture questions in an array
		String [] surveyAns = new String[survey.length()];//capture answers in an array
		for(int i = 0; i<survey.length();i++) {
			surveyAns[i] = survey.charAt(i)+"";//add answers to the array
		}
		double countA1 = 0, countB1 = 0, countC1 = 0, countA2 = 0, countB2 = 0, countC2 = 0, countA3 = 0;//A's
		double countB3 = 0, countC3 = 0, countA4 = 0, countB4 = 0, countC4 = 0;//dashes
		for(int i = 0; i<surveyQues.length();i++) {
			if(surveyQues.charAt(i) == '1') {//process introversion/extraversion (I/E) to be dimension 1
				if(surveyAns[i].equals("A")) countA1++;//increment if the personality file has A's for Question 1
				else if(surveyAns[i].equals("B")) countB1++;//increment if the personality file has B's for Question 1
			    else countC1++;//increment if the personality file has dashes for Question 1
			}else if(surveyQues.charAt(i) == '2') {//sensing/intuition (S/N) to be dimension 2
				if(surveyAns[i].equals("A")) countA2++;//increment if the personality file has A's for Question 2
				else if(surveyAns[i].equals("B")) countB2++;//increment if the personality file has B's for Question 2
				else countC2++;//increment if the personality file has dashes for Question 2
			}else if(surveyQues.charAt(i) == '3') {//thinking/feeling (T/F) to be dimension 3
				if(surveyAns[i].equals("A")) countA3++;//increment if the personality file has A's for Question 3
				else if(surveyAns[i].equals("B")) countB3++;//increment if the personality file has B's for Question 3
				else countC3++;//increment if the personality file has dashes for Question 3
			}else if(surveyQues.charAt(i) == '4') {//judging/perception (J/P) to be dimension 4
				if(surveyAns[i].equals("A")) countA4++;//increment if the personality file has A's for Question 4
				else if(surveyAns[i].equals("B")) countB4++;//increment if the personality file has B's for Question 4
				else countC4++;//increment if the personality file has dashes for Question 4
			}
		}//for loop
		processResult(countA1, countB1, countC1, countA2, countB2, countC2, countA3, countB3, countC3, countA4, countB4, countC4);
	}//end of personality method
	public static void processResult(double countA1, double countB1, double countC1, 
									double countA2, double countB2, double countC2, 
									double countA3, double countB3, double countC3,
									double countA4, double countB4, double countC4) {//METHOD 3
		double q1percent = (countB1 *100)/(countA1+countB1);//calculate percent of B
		double q2percent = (countB2 *100)/(countA2+countB2);//calculate percent of B
		double q3percent = (countB3 *100)/(countA3+countB3);//calculate percent of B
		double q4percent = (countB4 *100)/(countA4+countB4);//calculate percent of B
		String personalityCode = "";//check personality type
		if(q1percent>50) personalityCode+="I";
		else if(q1percent==50) personalityCode+="X";
		else personalityCode+="E";
		if(q2percent>50) personalityCode+="N";
	    else if(q2percent==50) personalityCode+="X";
		else personalityCode+="S";
		if(q3percent>50) personalityCode+="F";
		else if(q3percent==50) personalityCode+="X";
		else personalityCode+="T";
		if(q4percent>50) personalityCode+="P";
		else if(q4percent==50) personalityCode+="X";
		else personalityCode+="J";
		//print final output
		System.out.println("    "+Math.round(countA1)+"A"+"-"+Math.round(countB1)+"B "+Math.round(countA2)+"A"+"-"+Math.round(countB2)+"B "+Math.round(countA3)+"A"+"-"+Math.round(countB3)+"B "+Math.round(countA4)+"A"+"-"+Math.round(countB4)+"B");
		System.out.println("    "+"["+Math.round(q1percent)+"%, "+Math.round(q2percent)+"%, "+Math.round(q3percent) +"%, "+Math.round(q4percent)+"%] = "+personalityCode);
	}//end of method processResult
}//end of class
