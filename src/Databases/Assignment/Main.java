package Ergasia.VaseisDedomenwn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BOLD = "\u001B[1m";

    public static void main(String[] args) throws IOException {
        System.out.println(ANSI_GREEN + ANSI_BOLD + "Enter the sub-question you want to be executed (a-g):");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String ypoerotima = reader.readLine();
        while (ypoerotima == null || ypoerotima.length() != 1 || !ypoerotima.matches("[a-gA-G]+")) {
            System.out.println(ANSI_RED + ANSI_BOLD + "Invalid input! Please try again. You need to enter a value from English alphabet A-G");
            ypoerotima = reader.readLine();
        }
        if (ypoerotima.equalsIgnoreCase("a")) {
            try {
                Connection conn = null;
                PreparedStatement pst = null;
                ResultSet rs = null;

                conn = Connect_Database.getConnection();
                pst = conn.prepareStatement("SELECT T.category, COUNT(CT.transaction) AS Quantity " +
                        "FROM company_transactions AS CT, tickets AS T " +
                        "WHERE T.ticket_id = CT.ticket_id " +
                        "GROUP BY T.category O" +
                        "RDER BY Quantity DESC;");
                rs = pst.executeQuery();
                System.out.print(ANSI_RED + "--------------------------------- \n"
                        + ANSI_YELLOW + ANSI_BOLD + " QUESTION 2 (A) \n"
                        + ANSI_RED + "---------------------------------");
                while (rs.next()) {
                    String category = rs.getString(1);
                    int quantity = rs.getInt(2);
                    System.out.print("\n" + ANSI_CYAN + ANSI_BOLD + category + ANSI_WHITE + ": "
                            + ANSI_BOLD + ANSI_GREEN + " Quantity(" + ANSI_CYAN + quantity + ANSI_GREEN + ")");
                }
                System.out.println("\n" + ANSI_RED + "--------------------------------- \n");
            }
            catch (Exception e) {
                System.out.println("Something went wrong!");
                e.printStackTrace();
            }
        }
        else if (ypoerotima.equalsIgnoreCase("b")) {
            try {
                Connection conn = null;
                PreparedStatement pst = null;
                ResultSet rs = null;

                conn = Connect_Database.getConnection();
                pst = conn.prepareStatement("SELECT category, SUM(transaction) AS Sales " +
                        "FROM tickets AS T, company_transactions AS CT " +
                        "WHERE T.ticket_id = CT.ticket_id " +
                        "GROUP BY category " +
                        "HAVING SUM(transaction) >= ALL (SELECT SUM(transaction) " +
                        "FROM company_transactions AS CT, tickets AS T " +
                        "WHERE T.ticket_id = CT.ticket_id " +
                        "GROUP BY category);");
                rs = pst.executeQuery();
                System.out.print(ANSI_RED + "--------------------------------------- \n"
                        + ANSI_YELLOW + ANSI_BOLD + " QUESTION 2 (B) \n"
                        + ANSI_RED + "---------------------------------------");
                while (rs.next()) {
                    String category = rs.getString(1);
                    double sales = rs.getDouble(2);
                    System.out.print("\n" + ANSI_CYAN + category + ANSI_WHITE + ": "
                            + ANSI_BOLD + ANSI_YELLOW + "Sales(" + ANSI_CYAN + sales + ANSI_YELLOW + ")"
                    );
                }
                System.out.println("\n" + ANSI_RED + "--------------------------------------- \n");
            }
            catch (Exception e) {
                System.out.println("Something went wrong!");
                e.printStackTrace();
            }
        }
        else if (ypoerotima.equalsIgnoreCase("c")) {
            try {
                Connection conn = null;
                PreparedStatement pst = null;
                ResultSet rs = null;

                conn = Connect_Database.getConnection();
                pst = conn.prepareStatement("SELECT category, AVG(transaction) AS Average_Profit " +
                        "FROM tickets AS T, customers AS C, company_transactions AS CT " +
                        "WHERE (C.age >= 16 AND C.age <= 44) " +
                        "AND T.ticket_id = CT.ticket_id " +
                        "AND C.customer_id = CT.customer_id " +
                        "GROUP BY CATEGORY " +
                        "ORDER BY Average_Profit DESC;");
                rs = pst.executeQuery();
                System.out.print(ANSI_RED + "--------------------------------------------------- \n"
                        + ANSI_YELLOW + ANSI_BOLD + " QUESTION 2 (C) \n"
                        + ANSI_RED + "---------------------------------------------------");
                while (rs.next()) {
                    String category = rs.getString(1);
                    double avg_profit = rs.getDouble(2);
                    System.out.print("\n" + ANSI_CYAN + category + ANSI_WHITE + ": "
                            + ANSI_BOLD + ANSI_YELLOW + "Average Profit(" + ANSI_CYAN + avg_profit + ANSI_YELLOW + ")");
                }
                System.out.println("\n" + ANSI_RED + "--------------------------------------------------- \n");
            }
            catch (Exception e) {
                System.out.println("Something went wrong!");
                e.printStackTrace();
            }
        }
        else if (ypoerotima.equalsIgnoreCase("d")) {
            try {
                Connection conn = null;
                PreparedStatement pst = null;
                ResultSet rs = null;

                conn = Connect_Database.getConnection();
                pst = conn.prepareStatement("SELECT category AS Spectacle, COUNT(CT.ticket_id) AS Quantity " +
                        "FROM tickets, company_transactions AS CT " +
                        "WHERE tickets.ticket_id = CT.ticket_id " +
                        "GROUP BY Spectacle " +
                        "HAVING COUNT(CT.ticket_id) >= ALL (SELECT COUNT(CT.ticket_id) " +
                        "FROM tickets, company_transactions AS CT " +
                        "WHERE tickets.ticket_id = CT.ticket_id " +
                        "GROUP BY category);");
                rs = pst.executeQuery();
                System.out.print(ANSI_RED + "--------------------------------------------------- \n"
                        + ANSI_YELLOW + ANSI_BOLD + " QUESTION 2 (D) \n"
                        + ANSI_RED + "---------------------------------------------------");
                while (rs.next()) {
                    String category = rs.getString(1);
                    int quantity = rs.getInt(2);
                    System.out.print("\n" + ANSI_CYAN + category + ANSI_WHITE + ": "
                            + ANSI_BOLD + ANSI_YELLOW + "Quantity(" + ANSI_CYAN + quantity + ANSI_YELLOW + ")");
                }
                System.out.println("\n" + ANSI_RED + "--------------------------------------------------- \n");
            }
            catch (Exception e) {
                System.out.println("Something went wrong!");
                e.printStackTrace();
            }
        }
        else if (ypoerotima.equalsIgnoreCase("e")) {
            try {
                Connection conn = null;
                PreparedStatement pst = null;
                ResultSet rs = null;

                conn = Connect_Database.getConnection();
                pst = conn.prepareStatement("SELECT C.customer_id, full_name, category, COUNT(CT.ticket_id) AS Quantity " +
                        "FROM company_transactions AS CT, customers AS C, tickets AS T " +
                        "WHERE category = 'Presentation' " +
                        "AND CT.ticket_id = T.ticket_id " +
                        "AND C.customer_id = CT.customer_id " +
                        "GROUP BY C.customer_id, full_name, category " +
                        "HAVING COUNT(CT.ticket_id) = (SELECT MAX(maxQuantity)" +
                        "FROM ( SELECT full_name, category, COUNT(CT.ticket_id) maxQuantity " +
                        "FROM company_transactions AS CT, customers AS C, tickets AS T " +
                        "WHERE category = 'Presentation' AND CT.ticket_id = T.ticket_id AND C.customer_id = CT.customer_id " +
                        "GROUP BY C.customer_id, full_name, category) " +
                        "AS maxPresentation) " +
                        "UNION ALL " +
                        "SELECT C.customer_id, full_name, category, COUNT(CT.ticket_id) AS Quantity " +
                        "FROM company_transactions AS CT, customers AS C, tickets AS T " +
                        "WHERE category = 'Concert' AND CT.ticket_id = T.ticket_id AND C.customer_id = CT.customer_id " +
                        "GROUP BY C.customer_id, full_name, category " +
                        "HAVING COUNT(CT.ticket_id) = " +
                        "(SELECT MAX(maxQuantity)" +
                        "FROM (" +
                        "SELECT full_name, category, COUNT(CT.ticket_id) maxQuantity " +
                        "FROM company_transactions AS CT, customers AS C, tickets AS T " +
                        "WHERE category = 'Concert' " +
                        "AND CT.ticket_id = T.ticket_id " +
                        "AND C.customer_id = CT.customer_id "  +
                        "GROUP BY C.customer_id, full_name, category) " +
                        "AS maxConcert) " +
                        "UNION ALL " +
                        "SELECT C.customer_id, full_name, category, COUNT(CT.ticket_id) AS Quantity " +
                        "FROM company_transactions AS CT, customers AS C, tickets AS T " +
                        "WHERE category = 'Conference' AND CT.ticket_id = T.ticket_id AND C.customer_id = CT.customer_id " +
                        "GROUP BY C.customer_id, full_name, category " +
                        "HAVING COUNT(CT.ticket_id) = " +
                        "(SELECT MAX(maxQuantity) " +
                        "FROM (" +
                        "SELECT full_name, category, COUNT(CT.ticket_id) maxQuantity " +
                        "FROM company_transactions AS CT, customers AS C, tickets AS T " +
                        "WHERE category = 'Conference' " +
                        "AND CT.ticket_id = T.ticket_id " +
                        "AND C.customer_id = CT.customer_id " +
                        "GROUP BY C.customer_id, full_name, category) " +
                        "AS maxConference) " +
                        "" +
                        "UNION ALL " +
                        "" +
                        "SELECT C.customer_id, full_name, category, COUNT(CT.ticket_id) AS Quantity " +
                        "FROM company_transactions AS CT, customers AS C, tickets AS T " +
                        "WHERE category = 'Seminar' AND CT.ticket_id = T.ticket_id AND C.customer_id = CT.customer_id " +
                        "GROUP BY C.customer_id, full_name, category " +
                        "HAVING COUNT(CT.ticket_id) = (SELECT MAX(maxQuantity) " +
                        "FROM ( SELECT full_name, category, COUNT(CT.ticket_id) maxQuantity " +
                        "FROM company_transactions AS CT, customers AS C, tickets AS T " +
                        "WHERE category = 'Seminar' " +
                        "AND CT.ticket_id = T.ticket_id " +
                        "AND C.customer_id = CT.customer_id " +
                        "GROUP BY C.customer_id, full_name, category) AS maxSeminar) " +
                        "UNION ALL " +
                        "SELECT C.customer_id, full_name, category, COUNT(CT.ticket_id) AS Quantity " +
                        "FROM company_transactions AS CT, customers AS C, tickets AS T " +
                        "WHERE category = 'Theatrical Show' " +
                        "AND CT.ticket_id = T.ticket_id " +
                        "AND C.customer_id = CT.customer_id " +
                        "GROUP BY C.customer_id, full_name, category " +
                        "HAVING COUNT(CT.ticket_id) = (SELECT MAX(maxQuantity) " +
                        "FROM ( SELECT full_name, category, COUNT(CT.ticket_id) maxQuantity " +
                        "FROM company_transactions AS CT, customers AS C, tickets AS T " +
                        "WHERE category = 'Theatrical Show' AND CT.ticket_id = T.ticket_id " +
                        "AND C.customer_id = CT.customer_id " +
                        "GROUP BY C.customer_id, full_name, category) AS maxTheaterical_Show) " +
                        "ORDER BY Quantity DESC;");
                rs = pst.executeQuery();
                System.out.print(ANSI_RED + "-------------------------------------------------------------------------------------- \n"
                        + ANSI_YELLOW + ANSI_BOLD + " QUESTION 2 (E) \n"
                        + ANSI_RED + "--------------------------------------------------------------------------------------");
                while (rs.next()) {
                    int customer_id = rs.getInt(1);
                    String full_name = rs.getString(2);
                    String category = rs.getString(3);
                    int quantity =  rs.getInt(4);

                    System.out.print("\n" + ANSI_CYAN + full_name + ANSI_WHITE + ": "
                            + ANSI_BOLD + ANSI_YELLOW + "Customer-ID(" + ANSI_CYAN + customer_id + ANSI_YELLOW + ")"
                            + ANSI_BOLD + ANSI_BLUE + " Category(" + ANSI_CYAN + category + ANSI_BLUE + ")"
                            + ANSI_BOLD + ANSI_YELLOW + " Quantity(" + ANSI_CYAN + quantity + ANSI_YELLOW + ")"
                    );
                }
                System.out.println("\n" + ANSI_RED + "-------------------------------------------------------------------------------------- \n");
            }
            catch (Exception e) {
                System.out.println("Something went wrong!");
                e.printStackTrace();
            }
        }
        else if (ypoerotima.equalsIgnoreCase("f")) {
            try {
                Connection conn = null;
                PreparedStatement pst = null;
                ResultSet rs = null;

                conn = Connect_Database.getConnection();
                pst = conn.prepareStatement("SELECT company_name AS Company_Supplier, COUNT(transaction) AS Quantity " +
                        "FROM supplier AS SL, company_transactions AS CT " +
                        "WHERE SL.supplier_id = CT.supplier_id " +
                        "GROUP BY Company_Supplier " +
                        "HAVING COUNT(transaction) >= ALL (SELECT COUNT(transaction) " +
                        "FROM company_transactions " +
                        "GROUP BY supplier_id);");
                rs = pst.executeQuery();
                System.out.print(ANSI_RED + "--------------------------------------------------- \n"
                        + ANSI_YELLOW + ANSI_BOLD + " QUESTION 2 (F) \n"
                        + ANSI_RED + "---------------------------------------------------");
                while (rs.next()) {
                    String company_name = rs.getString(1);
                    int quantity = rs.getInt(2);
                    System.out.print("\n" + ANSI_CYAN + company_name + ANSI_WHITE + ": "
                            + ANSI_BOLD + ANSI_YELLOW + "Quantity(" + ANSI_CYAN + quantity + ANSI_YELLOW + ")");
                }
                System.out.println("\n" + ANSI_RED + "--------------------------------------------------- \n");
            }
            catch (Exception e) {
                System.out.println("Something went wrong!");
                e.printStackTrace();
            }
        }
        else if (ypoerotima.equalsIgnoreCase("g")) {
            try {
                Connection conn = null;
                PreparedStatement pst = null;
                ResultSet rs = null;

                conn = Connect_Database.getConnection();
                pst = conn.prepareStatement("SELECT purchase_date, CT.ticket_id, type, category, transaction_id " +
                        "FROM company_transactions AS CT, tickets " +
                        "WHERE (purchase_date >= '2019-05-01'AND purchase_date <= '2019-05-17')" +
                        "AND CT.ticket_id = tickets.ticket_id " +
                        "ORDER BY purchase_date, ticket_id;");
                rs = pst.executeQuery();
                System.out.print(ANSI_RED + "-------------------------------------------------------------------------------------- \n"
                        + ANSI_YELLOW + ANSI_BOLD + " QUESTION 2 (G) \n"
                        + ANSI_RED + "--------------------------------------------------------------------------------------");
                while (rs.next()) {
                    String purchase_date = rs.getString(1);
                    int ticket_id = rs.getInt(2);
                    String type = rs.getString(3);
                    String category = rs.getString(4);
                    int transaction_id = rs.getInt(5);
                    System.out.print("\n" + ANSI_CYAN + purchase_date + ANSI_WHITE + ": "
                            + ANSI_BOLD + ANSI_YELLOW + "Ticket-ID(" + ANSI_CYAN + ticket_id + ANSI_YELLOW + ")"
                            + ANSI_BOLD + ANSI_PURPLE + " Type(" + ANSI_CYAN + type + ANSI_PURPLE + ")"
                            + ANSI_BOLD + ANSI_BLUE + " Category(" + ANSI_CYAN + category + ANSI_BLUE + ")"
                            + ANSI_BOLD + ANSI_YELLOW + " Transaction-ID(" + ANSI_CYAN + transaction_id + ANSI_YELLOW + ")"
                    );
                }
                System.out.println("\n" + ANSI_RED + "-------------------------------------------------------------------------------------- \n");
            }
            catch (Exception e) {
                System.out.println("Something went wrong!");
                e.printStackTrace();
            }
        }
    }

}
