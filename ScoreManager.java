import java.io.*;
import java.util.*;

public class ScoreManager{
    int score;

    static int[] scoreList = new int[5];

    public ScoreManager(int score){
	this.score = score;
	writing();
	reading();
    }

    public void writing(){
	try{
	    File file = new File("File.txt");
	    BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
	    bw.write(Integer.toString(score));
	    bw.newLine();
	    bw.close();
	}catch(FileNotFoundException e){
	    e.printStackTrace();
	}catch(IOException e){
	    e.printStackTrace();
	}
    }
    public void reading(){
	int i;
	ArrayList<Integer> List = new ArrayList<Integer>();
	try{
	    File file = new File("File.txt");
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    String line = "";
	    while((line = br.readLine()) != null){
		List.add(Integer.parseInt(line));
	    }
	    Collections.sort(List);
	    for(i = 0;i < 5;i++){
		scoreList[i] = List.get(i);
	    }
	    br.close();
	}catch(FileNotFoundException e){
	    e.printStackTrace();
	}catch(IOException e){
	    e.printStackTrace();
	}
    }
    public static int getScoreList(int j){
	return scoreList[j];
    }
}
