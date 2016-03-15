import java.io.*;
import java.util.*;
import java.lang.*;
import java.lang.Math.*;
import java.lang.Object;
import java.text.*;
import java.text.DecimalFormat;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.lang.Integer;
import java.lang.Integer.*;
import java.util.concurrent.ConcurrentHashMap;

public class makeInsert{
	private String[] weddingSupplies={"tables", "table cloth", "wedding dress", "markers", "cards", "mason jars", "paper straws", "dj", "alcohol", "food", "lights", 
		"curtains", "chandeliers", "paper lanterns", "tiki torches", "silk rose petals", "bird cages", "guest book", "aisle runners", "flower girl baskets", "sparklers", "wedding cake", "silverware","gift bags",
		"balloons","table overlays","weddingbells","cardholders","garlands","candles","centerpiece lighting","diamond rhinestone ribbon"};
		private String[] vendors={"Walmart","Target","Liquor Express","WeddingsRUs","BabiesRUs","Taco Bell","Michael's","Heavenly Ham","Krispy Kreme","WeddingDay","24/7Weddings",
		"Tiffany & Co."};
		private String[] weddings={"Suess & Manion","Louie & Ebarb", "Brock & Dingle", "Sebring & Gucci", "West & Collins", "Randy & Lahey","Tippie & Parker","Piazza & Pemberton", 
		"Loux & Tuner", "Heinze & Kerry", "Bacher & Summerfield", "Peaden & Degroot", "Aschenbrenner & Plowman","Gros & Bertucci"};
		private String[] currentWeddings={"Suess & Manion","Louie & Ebarb","Brock & Dingle","Sebring & Gucci","West & Collins","Randy & Lahey","Tippie & Parker","Piazza & Pemberton",
		"Loux & Tuner","Heinze & Kerry","Bacher & Summerfield","Peaden & Degroot","Aschenbrenner & Plowman","Gros & Bertucci","Johnson & Johnson","Corey & Trevor","Trin & Jacob"};
		private Double[]amntPaid ={50404.70,87394.23,39475.59,94568.01,72391.47,72933.73,50513.32,8626.69,38975.80,64714.16,44049.54,80831.92,74973.91,100741.86,10000.00,12000.00,0.00 };
		private Double[]weddingCst={64788.82,76199.03,42000.03,28674.79,92427.90,43778.68,48587.51,40948.97,9099.23,20741.30,83998.46,77231.23,68017.07,94174.73,10000.00,12000.00,100.00};
		private String[] wedDate = {"2015-03-21","2012-04-23","2015-10-03","2014-12-12","2015-12-23","2015-12-19","2015-05-24","2013-11-12","2012-07-14","2015-12-31","2015-12-31","2015-12-15","2015-02-16","2015-07-22","2015-07-04","2015-08-24","2015-12-22"};
		private int weddingID;
		private int vendorID;
		private int suppliesID;
		private double random;
		private DecimalFormat df;
		private File file;
		private BufferedWriter bw;
		private FileWriter writer;
		private Random rn;	
		private ArrayList<Integer> duplicates;
		private ArrayList<Integer> supplyIDs;
		private ArrayList<Integer>weddingIDs;
		private List<Integer>tempHolder;
		private ArrayList<Integer>compare;
		private List temp;
		private StringBuilder sbW;
		private StringBuilder sbS;
		private int returnSupplies;
		private Integer nums;
		
		
		private boolean flag;
		
	
	public makeInsert(){
		weddingID= 0;
		vendorID=0;		
		returnSupplies = 0;		
		nums = 0;	
		rn = new Random();
		supplyIDs = new ArrayList<Integer>();
	}
	
	public static void main(String [] args)throws IOException {
		makeInsert make = new makeInsert();
		make.getSuppliesInsert();
		make.getVendorsInsert();
		make.getPriceInsert();
		make.getWeddingsInsert();
		make.makeSupplieSoldInsert();
	
		make.insertRandomExtraCost();	
		make.returnNumberOfWeddings();			
	}
	
	public void getSuppliesInsert()throws IOException{
		file = new File("InsertSupplies.txt");
		file.createNewFile();
		writer = new FileWriter(file);
		bw = new BufferedWriter(writer);
		for(int i =0;i<30;i++){
			//write to file			
			bw.write("INSERT INTO SUPPLIES VALUES("+suppliesID+", "+"'"+weddingSupplies[i]+"'"+ ")");
			bw.newLine();			
			suppliesID++;			
		}
		bw.close();
		writer.close();		
	}
	
	
	
	public void getVendorsInsert() throws IOException{
		file = new File("InsertVendors.txt");
		file.createNewFile();
		writer = new FileWriter(file);
		bw = new BufferedWriter(writer);		
		for(int i =0;i<12;i++){			
		//write to file			
			bw.write("INSERT INTO VENDORS VALUES("+vendorID+", "+"'"+vendors[i]+"'"+")");
			bw.newLine();
			vendorID++;
		}
		bw.close();
		writer.close();
	}
	
	public void getSuppliesSoldInsert() throws IOException{
		//need to write to a file supply id (foreign key), amt sold, wedding id, vendor id
	}
	
	public void getPriceInsert()throws IOException{
		String newCheckDupe;
		String oldCheckDupe;
		file = new File("price.txt");
		file.createNewFile();
		writer = new FileWriter(file);
		bw = new BufferedWriter(writer);
		int newRandVendorID;
		
		suppliesID=0;
		for(int i =0;i<60;i++){
			newRandVendorID = randomID(11,0);
			
			
				bw.write("INSERT INTO PRICE VALUES("+newRandVendorID+","+suppliesID+", "+makeRandomPrice(500,1)+")");
				bw.newLine();
				suppliesID++;
				if(suppliesID==31){
					suppliesID=0;
				}
		 
		}	
		bw.close();
		writer.close();
	}
	
	public void insertRandomExtraCost()throws IOException{
		file = new File("updateWeddingsExtraCost.txt");
		file.createNewFile();
		writer = new FileWriter(file);
		bw = new BufferedWriter(writer);		
		double extraCosts=0;
		for(int i =0;i<currentWeddings.length;i++){
			extraCosts=makeRandomPrice(5000,0);
			bw.write("UPDATE WEDDINGS SET EXTRACOSTS="+extraCosts+" WHERE WEDDINGID="+weddingID+";");			
			bw.newLine();
			weddingID++;		
		}
		bw.close();
		writer.close();
	}
	

	
	public void getWeddingsInsert()throws IOException{		
		file = new File("InsertWeddings.txt");
		file.createNewFile();
		writer = new FileWriter(file);
		bw = new BufferedWriter(writer);		
		double weddingPrice = 0;
		double amountPaidWedding=0;
		double extraCosts=0;
		for(int i =0;i<currentWeddings.length;i++){
			//weddingPrice = makeRandomPrice(90000,5000);
			//amountPaidWedding = makeRandomPrice(90000,5000);	
			extraCosts=makeRandomPrice(5000,0);			
			bw.write("INSERT INTO WEDDINGS VALUES("+weddingID+", "+"'"+currentWeddings[i]+"'"+", "+"'"+wedDate[i]+"'"+","+weddingCst[i]+", "+amntPaid[i]+","+extraCosts+")");		
			bw.newLine();
			weddingID++;
			
			
			
		}
		bw.close();
		writer.close();
	}
	

	
	public void makeSupplieSoldInsert()throws IOException{
		
		int count  =0;
		int countWeddingIDs = 0;
		int countSupplyIDs = 0;
		int getWeddingID=0;
		
		
		file = new File("suppliesSold.txt");
		
		file.createNewFile();
		writer = new FileWriter(file);
		bw = new BufferedWriter(writer);	
		for(int i =0;i<1000;i++){
						
				
			int randomSupplyID = randomID(29,0);
			
			int randomNumberChecker=randomID(30,10);
			
		
			
			
			
			
			
		
			
			
			
		
			bw.write("INSERT INTO WEDDINGSUPPLIES VALUES("+getWeddingID+", "+randomSupplyID+")");
			bw.newLine();
			count++;
			
			
			
			
			if(count ==randomNumberChecker||count>=30){
				

				getWeddingID++;
				count=0;				
			}else if(getWeddingID>17|| getWeddingID==18){
				
				break;
			}		
		}
		bw.close();
		writer.close();
		
	}
	
	

	
	
	
	public void returnNumberOfWeddings(){
		int count = 0;
		for(int i =0; i<supplyIDs.size();i++){
			count++;			
		}
		//System.out.println(count);
		
		
	}

		
		
		
		
		
	

	
	
	public int randomID(int maximum, int minimum){
		int range = maximum - minimum + 1;
		int randomNum =  rn.nextInt(range) + minimum;
		return randomNum;
	}
	public String randomFromStringArray(String[] array){
		int idx = rn.nextInt(array.length);
		String randomVendor = (array[idx]);
		return randomVendor;
	}
	public double makeRandomPrice(int max,int min){
		df=new DecimalFormat(".##");	
		random = Math.random()*max+min;
		String randomPrice =df.format(random);
		double finalPrice = Double.parseDouble(randomPrice);
		return finalPrice;
	}
	

	
	
	
	


}
