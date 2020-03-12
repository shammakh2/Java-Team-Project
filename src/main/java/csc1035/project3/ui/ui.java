package csc1035.project3.ui;

import csc1035.project3.HibernateUtil;
import csc1035.project3.insert.Input;
import csc1035.project3.lookup.stockLookup;
import csc1035.project3.receipt.PrintReceipt;
import csc1035.project3.tables.Exchanges;
import csc1035.project3.tables.Transactions;
import csc1035.project3.tables.warning_quantity;
import csc1035.project3.transactions.Exchange;
import csc1035.project3.transactions.Purchase;
import csc1035.project3.transactions.Refund;
import csc1035.project3.update.update;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ui {
    private static Scanner scanner = new Scanner(System.in);
    public static ArrayList<String> options = new ArrayList<>(Arrays.asList("lookup", "insert - unique", "insert - csv", "update", "purchase", "exchange - return", "exchange - repurchase", "refund", "exit"));

    public static void start() {
        boolean running = true;
        System.out.println("Welcome");
        while (running) {
            System.out.println("Type \"help\" to see options");
            System.out.println("What would you like to do?");
            Scanner scanner = new Scanner(System.in);
            boolean valid = false;
            String choice = "";
            while (!valid) {
                System.out.print(">>>  ");
                choice = scanner.nextLine().toLowerCase();
                if (options.contains(choice)) {
                    valid = true;
                } else if (choice.equals("help")) {
                    help();
                } else {
                    System.out.println("Invalid Choice, please try again");
                }
            }

            switch (choice) {
                case "lookup":
                    stockLookup stock = new stockLookup();
                    stock.start();
                    break;
                case "insert - unique":
                    Input in_user = new Input();
                    in_user.user();
                    break;
                case "insert - csv":
                    Input in_csv = new Input();
                    in_csv.CSV();
                    break;
                case "update":
                    update update = new update();
                    update.modify();
                    break;
                case "purchase":
                    try {
                        Purchase purch = new Purchase();
                        while (true) {
                            System.out.println("Please enter the id of the items you want to purchase or for multiple items," +
                                    " enter the item id and the amount separated by a comma (item_id,amount). When you are" +
                                    " done entering all items, type in 'done'");
                            String l = scanner.nextLine();
                            if (l.equalsIgnoreCase("done")) {
                                break;
                            } else if (l.split(",").length == 2) {
                                String[] items = l.split(",");
                                purch.queue(Integer.parseInt(items[0]), Integer.parseInt(items[1]));
                            } else if (l.split(",").length == 1) {
                                purch.queue(Integer.parseInt(l.split(",")[0]));
                            }
                        }
                        int receipt = purch.handshake();
                        PrintReceipt.pullUpItems(choice, receipt);
                        System.out.println("Transaction complete");
                    } catch (Exception e) {
                        System.out.println("Invalid entry please try again.");
                    }
                    break;
                case "exchange - return":
                    try {
                        Exchange e = new Exchange();
                        e.validate = false;
                        while (true) {
                            System.out.println("Please enter the id of the items you want to return or to return multiple items," +
                                    " enter the item id and the amount separated by a comma (item_id,amount). When you are" +
                                    " done entering all items, type in 'done'");
                            String l = scanner.nextLine();
                            if (l.equalsIgnoreCase("done")) {
                                break;
                            } else if (l.split(",").length == 2) {
                                String[] items = l.split(",");
                                e.queue(Integer.parseInt(items[0]), Integer.parseInt(items[1]));
                            } else if (l.split(",").length == 1) {
                                e.queue(Integer.parseInt(l.split(",")[0]));
                            }
                        }
                        int i =  e.handshake();
                        PrintReceipt.pullUpItems(choice, i);

                    } catch (Exception e) {
                        System.out.println("Invalid entry please try again.");
                    }
                    break;
                case "exchange - repurchase":
                    try {
                        Session session = HibernateUtil.getSessionFactory().openSession();

                        System.out.println("Enter transaction id");
                        Integer id = Integer.parseInt(scanner.nextLine());
                        Transactions t = session.get(Transactions.class, id);
                        Exchanges reEx = t.getExchange();
                        double cost = t.getTotalCost();
                        Exchange re = new Exchange();
                        re.validate = true;
                        while (true) {
                            System.out.println("Please enter the id of the items you want to purchase using your return transaction or to enter multiple items," +
                                    " enter the item id and the amount separated by a comma (item_id,amount). When you are" +
                                    " done entering all items, type in 'done'");
                            String l = scanner.nextLine();
                            if (l.equalsIgnoreCase("done")) {
                                break;
                            } else if (l.split(",").length == 2) {
                                String[] items = l.split(",");
                                re.queue(Integer.parseInt(items[0]), Integer.parseInt(items[1]));
                            } else if (l.split(",").length == 1) {
                                re.queue(Integer.parseInt(l.split(",")[0]));
                            }
                        }
                        int receipt = re.exchangePurchase(reEx, session, cost);
                        PrintReceipt.pullUpItems(choice, receipt, id);
                        System.out.println("Exchange complete");
                    } catch (Exception e) {
                        System.out.println("Invalid entry please try again.");
                    }
                    break;
                case "refund":
                    try {
                        Refund r = new Refund();
                        while (true) {
                            System.out.println("Please enter the id of the items you want to refund or for multiple items," +
                                    " enter the item id and the amount separated by a comma (item_id,amount). When you are" +
                                    " done entering all items, type in 'done'");
                            String l = scanner.nextLine();
                            if (l.equalsIgnoreCase("done")) {
                                break;
                            } else if (l.split(",").length == 2) {
                                String[] items = l.split(",");
                                r.queue(Integer.parseInt(items[0]), Integer.parseInt(items[1]));
                            } else if (l.split(",").length == 1) {
                                r.queue(Integer.parseInt(l.split(",")[0]));
                            }
                        }
                        int receipt = r.handshake();
                        PrintReceipt.pullUpItems(choice, receipt);
                        System.out.println("Refund complete");
                    }catch (Exception e){
                        System.out.println("Invalid entry please try again.");
                    }
                    break;
                case "exit":
                    running = false;
                    warning_quantity.warning();
                    System.out.println("Exiting program, goodbye.");
                    break;
            }
        }

    }

    /**
     * Outputs a help message for the ui
     */
    private static void help() {
        for (String option : options) {
            System.out.println(option);
        }
    }
}
