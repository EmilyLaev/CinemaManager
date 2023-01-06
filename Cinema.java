package cinema;

import java.util.Scanner;
import java.text.DecimalFormat;

public class Cinema {
    
    //To start the user is asked to create the cinema by choosing number of rows and seats and are used as array dimensions
    //all values of array start as zero to show the seat is available, total number of seats is calculated
    //all statistic values are set to zero (total income, current income, tickets bought)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        int[][] cin = new int[rows][seats];
        int row = 0;
        int seat = 0;
        int totalSeats = rows * seats;
        int totalIncome = 0;
        int curIncome = 0;
        //total income is calculated based on number of seats and ticket prices
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else if (totalSeats > 60) {
            totalIncome = (rows/2 * 10 * seats) + ((rows/2 + rows%2) * 8 * seats);
        }
        int ticketsBought = 0;
        boolean seatsAvailable = true;
        
        //the program runs in a while loop based on boolean seatsAvailable
        //a menu is displayed with four options
        while (seatsAvailable) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            
           //depending on the user input one of three options are possible
            switch (choice) {
                 //if 1 is entered the cinema is drawn
                case 1: {
                    drawCin(cin, rows, seats);
                    continue;
                }
                //if 2 is entered the user is asked for locations (row, seat number)
                //if the input is the wrong form an error message is printed and the user re-asked for location
                case 2: {
                    boolean getnum = false;
                    while (!getnum) {
                        System.out.println("Enter a row number:");
                        row = scanner.nextInt() - 1;
                        System.out.println("Enter a seat number in that row:");
                        seat = scanner.nextInt() - 1;
                        if (row >= 0 && row < rows) {
                            if (seat >= 0 && seat < seats) {
                                getnum = true;
                            } else {
                                System.out.println("Wrong input!");
                                continue;
                            }
                        } else {
                            System.out.println("Wrong input!");
                            continue;
                        }
                        //When correct input is entered the availablity is checked
                        //if unavailable, user is asked for new location
                        //if available the price of the ticket for location is calculated and seat is now unavailable
                        //The current income (curIncome) and number of tickets bought are updated
                        if (cin[row][seat] == 0) {
                            if (totalSeats <= 60) {
                                System.out.println("Ticket price: $10");
                                cin[row][seat] = 1;
                                curIncome = curIncome + 10;
                            } else if (totalSeats > 60) {
                                if (row + 1 <= rows / 2) {
                                    System.out.println("Ticket price: $10");
                                    curIncome = curIncome + 10;
                                } else if (row + 1 > rows / 2) {
                                    System.out.println("Ticket price: $8");
                                    curIncome = curIncome + 8;
                                }
                                cin[row][seat] = 1;
                            }
                            ticketsBought++;
                            continue;
                        } else {
                            System.out.println("That ticket has already been purchased!");
                            getnum = false;
                            continue;
                        }
                    }
                }
                //if 2 is entered the current statistics are calculated and displayed
                //the number of tickets bought, the percentage of seats taken, the current income and the possible total income
                case 3: {
                    System.out.println(ticketsBought);
                    float a = ticketsBought;
                    float b = totalSeats;
                    float percent = a/b * 100;
                    DecimalFormat f = new DecimalFormat("0.00");
                    System.out.println("Number of purchased tickets: " + ticketsBought);
                    System.out.println("Percentage: " + f.format(percent) + "%");
                    System.out.println("Current income: $" + curIncome);
                    System.out.println("Total income: $" + totalIncome);
                    System.out.println();
                    continue;
                }
                //if 0 is entered the seatsAvailable is turned to false and the while loop is finished and program ends    
                case 0: {
                    seatsAvailable = false;
                    continue;
                }
                default: {
                    System.out.println("Wrong input!");
                }
            }
        }
    }
    
    //this is a method to draw the cinema given the integer array of values
    public static void drawCin(int[][] cin, int rows, int seats) {
        System.out.println("Cinema:");
        int[] firstLine = new int[seats + 1];
        int place = 1;
        //this loop just prints out the top row labeling the seat numbers
        for (int i = 0; i <= seats; i++) {
            if (i == 0) {
                System.out.print(" ");
            } else {
                System.out.print(" " + place);
                place++;
            }
        }
        System.out.println();
        place = 1;
        //this loop displays the cinema in terms of available (S) or unavailable (B) seats
        for(int i = 0; i < rows; i++) {
            System.out.print(place);
            for (int j = 0; j < seats; j++) {
                if (cin[i][j] == 0) {
                    System.out.print(" S");
                } else if (cin[i][j] == 1) {
                    System.out.print(" B");
                }
            }
            place++;
            System.out.println();
        }
    }
}
