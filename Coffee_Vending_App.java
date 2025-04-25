import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Coffee_Vending_App{
	public static void readCSV(ArrayList<String> coffee, ArrayList<Double> price, String fileName){
		try{
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while((line = br.readLine())!=null){
				String[] data = line.split(",");
				coffee.add(data[0]);
				price.add(Double.parseDouble(data[1]));
			}
		}
		catch(IOException e){
			System.out.println("Error: Unable to read the data from file: "+ fileName);
		}
	}

	public static void clearConsole(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void wait(int seconds){
		try{
			TimeUnit.SECONDS.sleep(seconds);
		}
		catch(InterruptedException e){
			System.out.println("Error: Failed to wait");
		}
	}

	public static void drawSeparators(int ammount, String separator){
		for(int i=0; i<ammount; i++ ){
			System.out.print(separator);
		}
		System.out.println();
	}

	public static void drawMainMenu(ArrayList<String> coffee){
		int option = 1;
		clearConsole();
		System.out.println("Select Coffee:");
		drawSeparators(20,"*");

		for(int i = 0; i<coffee.size();i++){
			System.out.printf("%2s) %2s\n", option,coffee.get(i));
			option++;
		}
		System.out.printf("%2d) Exit\n", option++);
		drawSeparators(20,"*");

	}

	public static void drawSizeMenu(){
		clearConsole();
		System.out.println("Select coffee size:");
		drawSeparators(20,"*");
		System.out.println(" 1) Small");
		System.out.println(" 2) Medium");
		System.out.println(" 3) Large");
		drawSeparators(20,"*");
	}

	public static void drawExtrasMenu(){
		clearConsole();
		System.out.println("Select extra:");
		drawSeparators(20,"*");
		System.out.println(" 1) Vanilla syrup");
		System.out.println(" 2) Hazelnut syrup");
		System.out.println(" 3) Caramel syrup");
		System.out.println(" 4) None");
		drawSeparators(20,"*");
	}

	public static String getExtra(int selectedExtra){
		String extra = "";
		if(selectedExtra==1){
			extra= "Vanilla syrup";
		}
		else if(selectedExtra==2){
			extra= "Hazelnut syrup";
		}
		else if(selectedExtra==3){
			extra= "Caramel syrup";
		}
		return extra;
	}

	public static Double getExtraPrice(int selectedExtra){
		double extraPrice = 0;
		if(selectedExtra==1){
			extraPrice = 2.5;
		}
		else if(selectedExtra==2){
			extraPrice = 3;
		}
		else if(selectedExtra==3){
			extraPrice = 2;
		}
		return extraPrice;
	}

	public static String getSize(int selectedSize){
		String size = "";
		if(selectedSize==1){
			size= "Small";
		}
		else if(selectedSize==2){
			size= "Medium";
		}

		else if(selectedSize==3){
			size= "Large";
		}
		return size;
	}

	public static Double getSizePrice(int selectedSize){
		double sizePrice = 0;
		if(selectedSize==1){
			sizePrice = 1;
		}
		else if(selectedSize==2){
			sizePrice = 2.5;
		}

		else if(selectedSize==3){
			sizePrice = 2.8;
		}
		return sizePrice;
	}

	public static void drawOrderMenu(ArrayList<String> coffee, ArrayList<Double> price, int selectedCoffee,int selectedSize, int selectedExtra, double finalPrice){
		clearConsole();
		System.out.println("Your order: ");
		drawSeparators(20,"*");
		System.out.printf("Selected coffee:%2s\n",coffee.get(selectedCoffee-1));
		System.out.printf("Size:%2s\n",getSize(selectedSize));
		if(selectedExtra<4){
			System.out.printf("Extra:%2s\n",getExtra(selectedExtra));
		}
		System.out.printf("Price: €%s\n",finalPrice);


		System.out.println("Proceed?");
		System.out.println(" 1) Yes");
		System.out.println(" 2) No");
		drawSeparators(20,"*");
	}

	public static void drawPaymentMenu(double finalPrice){
		clearConsole();
		System.out.println("Select payment type:");
		drawSeparators(20,"*");
		System.out.printf("To pay: €%s\n",finalPrice);
		System.out.println(" 1) Cash");
		System.out.println(" 2) Card");
		drawSeparators(20,"*");
	}

	public static void drawCardMenu(){
		clearConsole();
		System.out.println("Select card type:");
		drawSeparators(20,"*");
		System.out.println(" 1) Visa");
		System.out.println(" 2) MasterCard");
		drawSeparators(20,"*");
	}

	public static void drawReceipt(ArrayList<String> coffee, ArrayList<Double> price, int selectedCoffee, int selectedSize, int selectedExtra, int selectedPayment, int selectedCard, double finalPrice,  double tendered, double change){
		clearConsole();
		System.out.println("Your receipt:");
		drawSeparators(20,"*");
		System.out.printf("Coffee Selected:%2s\n", coffee.get(selectedCoffee-1));
		if(selectedPayment == 1){
			System.out.println("Payment method: Cash");
			System.out.printf("Coffee price: €%s\n",price.get(selectedCoffee-1));
			System.out.printf("Size price: €%s\n",getSizePrice(selectedSize));
			System.out.printf("Extra topping price: €%s\n",getExtraPrice(selectedExtra));
			System.out.printf("Total: €%s\n",finalPrice);
			System.out.printf("Cash tendered: €%s\n", tendered);
			System.out.printf("Change: €%s\n", change);
		}
		else{
			System.out.println("Payment method: Card");
			if(selectedCard==1){
				System.out.println("Card type: Visa");
			}
			else if(selectedCard==2){
				System.out.println("Card type: MasterCard");
			}
			System.out.printf("Coffee price: €%s\n",price.get(selectedCoffee-1));
			System.out.printf("Size price: €%s\n",getSizePrice(selectedSize));
			System.out.printf("Extra topping price: €%s\n",getExtraPrice(selectedExtra));
			System.out.printf("Total: €%s\n",finalPrice);
		}
		drawSeparators(20,"*");
		System.out.println("Enjoy your "+coffee.get(selectedCoffee-1)+"!");
		drawSeparators(20,"*");
		wait(10);
	}

	public static void saveReceipt(ArrayList<String> coffee, int selectedCoffee, int selectedSize, int selectedExtra, int selectedPayment, int selectedCard, double finalPrice,  double tendered, double change, String saveFile){
		try{
			FileWriter fw = new FileWriter(saveFile, true);
			PrintWriter pw = new PrintWriter(fw, true);
			if(selectedPayment==1){
				pw.print(coffee.get(selectedCoffee-1)+",");
				pw.print(getSize(selectedSize)+",");
				pw.print(getExtra(selectedExtra)+",");
				pw.print(finalPrice+",");
				pw.print(tendered+",");
				pw.print(change);
			}
			else{
				pw.print(coffee.get(selectedCoffee-1)+",");
				pw.print(getSize(selectedSize)+",");
				pw.print(getExtra(selectedExtra)+",");
				pw.print(finalPrice+",");
				if(selectedCard==1){
					pw.print("Visa");
				}
				else if(selectedCard==2){
					pw.print("MasterCard");
				}

			}
			pw.println();
			pw.close();
		}
		catch(IOException e){
			System.out.println("Error: Unable to write into file "+ saveFile);
		}
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		ArrayList<String> coffee = new ArrayList<String>();
		ArrayList<Double> price = new ArrayList<Double>();
		String dataFile = "Inventory.csv";
		String saveFile = "Transactions.csv";
		boolean isTerminated = false;

		int selectedCoffee = 0;
		int selectedSize = 0;
		int selectedExtra = 0;
		int selectedPayment = 0;
		int selectedCard = 0;
		int proceed = 0;

		double extraPrice = 0;
		double sizePrice = 0;
		double tendered = 0;
		double change = 0;
		double finalPrice = 0;

		readCSV(coffee,price,dataFile);

		while(isTerminated == false){
			drawMainMenu(coffee);
			try {
				selectedCoffee = in.nextInt();

				if(selectedCoffee > 0 && selectedCoffee <= coffee.size()+1){
					if(selectedCoffee == coffee.size()+1){
						isTerminated = true;
					}
					else{

						boolean endSizeCycle = false;
						while(endSizeCycle == false){
							drawSizeMenu();
							selectedSize = in.nextInt();
							if(selectedSize > 0 && selectedSize < 4){
								endSizeCycle = true;
							}
							else{
								System.out.println("Error: Select a valid number");
								wait(1);
							}
						}


						boolean endExtraCycle = false;
						while(endExtraCycle == false){
							drawExtrasMenu();
							selectedExtra = in.nextInt();
							if(selectedExtra > 0 && selectedExtra < 5){
								endExtraCycle = true;
							}
							else{
								System.out.println("Error: Select a valid number");
								wait(1);
							}
						}


						finalPrice = price.get(selectedCoffee-1) + getSizePrice(selectedSize) + getExtraPrice(selectedExtra);
						boolean endProceedCycle = false;
						while(endProceedCycle == false){
							drawOrderMenu(coffee, price, selectedCoffee, selectedSize, selectedExtra, finalPrice);
							proceed = in.nextInt();
							if(proceed > 0 && proceed < 3){
								endProceedCycle = true;
							}
							else{
								System.out.println("Error: Select a valid number");
								wait(1);
							}
						}

						if(proceed == 1){

							boolean endPaymentCycle = false;
							while(endPaymentCycle == false){
								drawPaymentMenu(finalPrice);
								selectedPayment = in.nextInt();
								if(selectedPayment > 0 && selectedPayment < 3){
									if(selectedPayment == 1){
										System.out.print("Input ammount of cash: ");
										tendered = in.nextDouble();
										if(tendered > finalPrice){
											change = tendered - finalPrice;
											saveReceipt(coffee, selectedCoffee, selectedSize, selectedExtra, selectedPayment, selectedCard, finalPrice, tendered, change, saveFile);
											drawReceipt(coffee, price, selectedCoffee, selectedSize, selectedExtra, selectedPayment, selectedCard, finalPrice, tendered, change);
											endPaymentCycle = true;
										}
										else if(tendered == finalPrice){
											change = 0;
											saveReceipt(coffee, selectedCoffee, selectedSize, selectedExtra, selectedPayment, selectedCard, finalPrice, tendered, change, saveFile);
											drawReceipt(coffee, price, selectedCoffee, selectedSize, selectedExtra, selectedPayment, selectedCard, finalPrice, tendered, change);
											endPaymentCycle = true;
										}
										else{
											System.out.println("Error: Insufficient money");
											wait(1);
											endPaymentCycle = true;
										}
									}
									else if(selectedPayment == 2){
										drawCardMenu();
										selectedCard = in.nextInt();
										if(selectedCard > 0 && selectedCard < 3){
											saveReceipt(coffee, selectedCoffee, selectedSize, selectedExtra, selectedPayment, selectedCard, finalPrice, tendered, change, saveFile);
											drawReceipt(coffee, price, selectedCoffee, selectedSize, selectedExtra, selectedPayment, selectedCard, finalPrice, tendered, change);
										}
										else{
											System.out.println("Error: Select a valid number");
											wait(1);
										}
										endPaymentCycle = true;
									}
								}
								else{
									System.out.println("Error: Select a valid number");
									wait(1);
								}
							}
						}
					}
				}
				else{
					System.out.println("Error: Select a valid number");
					wait(1);
				}
			}
			catch(Exception e){
				System.out.println("Error: Type in an integer number");
				in.nextLine();
				wait(1);
			}
		}
		System.out.println("Program was terminated");
	}
}