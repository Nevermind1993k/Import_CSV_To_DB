import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws Exception {

        Connection connection = MyConnection.getConnection();

        readCustomersFile("Customers.csv");
        readItemsFile("Items.csv");

        //createEmptyTables(connection);

        importItemToDB(readItemsFile("Items.csv"), connection);
        importCustomerToDB(readCustomersFile("Customers.csv"), connection);

    }

    private static void createEmptyTables(Connection connection) throws Exception {
        final String sqlDropTable = "DROP TABLE PURCHASES\n" +
                "drop table CUSTOMERS\n" +
                "drop table ITEMS;";

        try {
            PreparedStatement stmt = connection.prepareStatement(sqlDropTable);
            stmt.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    /**
     * Reads CSV file, parses it to fields, creates object with fields and adds it to Collection
     *
     * @param fileName - file that you want to read
     * @return Arraylist of customers
     * @throws IOException
     */
    private static ArrayList<Customer> readCustomersFile(String fileName) throws IOException {

        ArrayList<Customer> customersList = new ArrayList<>();
        Customer customer;

        BufferedReader read = null;
        String[] strArr;

        exists(fileName);

        try {
            read = new BufferedReader(new FileReader(fileName));
            String str;
            while ((str = read.readLine()) != null) {

                //Используем костыли ( т.к. Не знаю как скипнуть 1ю строку)
                if (str.contains("Name") || str.contains("DateOfBirth") || str.contains("Address") || str.contains("Gender") ||
                        str.contains("PhoneNumber") || str.contains("LastPurchases") || str.contains("DateOfLastPurchase")) {
                    continue;
                }
                customer = new Customer();
                strArr = str.split(";");

                customer.setName(strArr[0]);
                customer.setDateOfBirth(convertStringToDate(strArr[1]));
                customer.setAddress(strArr[2]);
                customer.setGender(strArr[3]);
                customer.setPhoneNumber(strArr[4]);
                customer.setLastPurchases(convertStringToArr(strArr[5]));
                customer.setDateOfLastPurchase(convertStringToDate(strArr[6]));

                customersList.add(customer);
            }


        } catch (IOException exc) {
            System.out.println("File Read Error");
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {
                read.close();
            }
        }
        return customersList;
    }

    /**
     * Reads CSV file, parses it to fields, creates object with fields and adds it to Collection
     *
     * @param fileName - file that you want to read
     * @return Arralist of items
     * @throws IOException
     */
    private static ArrayList<Item> readItemsFile(String fileName) throws IOException {
        ArrayList<Item> itemsList = new ArrayList<>();
        Item item;
        BufferedReader read = null;
        String[] strArr;
        exists(fileName);

        try {
            read = new BufferedReader(new FileReader(fileName));
            String str;
            while ((str = read.readLine()) != null) {

                //Используем костыли ( т.к. Не знаю как скипнуть 1ю строку)
                if (str.contains("id") || str.contains("title") || str.contains("code")
                        || str.contains("producer") || str.contains("dateOfLastUpdate")) {
                    continue;
                }
                item = new Item();
                strArr = str.split(";");

                item.setId(Integer.parseInt(strArr[0]));
                item.setTitle(strArr[1]);
                item.setCode(Integer.parseInt(strArr[2]));
                item.setProducer(strArr[3]);
                item.setDateOfLastUpdate(convertStringToDate(strArr[4])); // Не проставляет время

                itemsList.add(item);
            }
        } catch (IOException exc) {
            System.out.println("File Read Error");
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {
                read.close();
            }
        }

        return itemsList;
    }

    /**
     * Imports collections of customers to DB
     *
     * @param customersList - ArraList of customers
     * @param con           - connection
     * @throws Exception
     */
    private static void importCustomerToDB(ArrayList<Customer> customersList, Connection con) throws Exception {
        final String sql = "INSERT INTO CUSTOMERS (NAME, date_Of_Birth, address, gender, phone_number, " +
                "date_Of_Last_Purchase) VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            for (Customer customer : customersList) {

                stmt.setString(1, customer.getName());
                stmt.setDate(2, Date.valueOf(customer.getDateOfBirth()));
                stmt.setString(3, customer.getAddress());
                stmt.setString(4, customer.getGender());
                stmt.setString(5, customer.getPhoneNumber());
                //stmt.setArray(6,customer.getLastPurchases());
                stmt.setDate(6, Date.valueOf(customer.getDateOfLastPurchase()));

                stmt.executeUpdate();
                con.commit();
            }


        } catch (SQLException exc) {
            throw new Exception(exc);
        } finally {
            System.out.println("Table Customers was imported.");
        }
    }

    /**
     * Imports collection of items to DB
     *
     * @param itemsList - ArraList of items
     * @param con       - connection
     * @throws Exception
     */
    private static void importItemToDB(ArrayList<Item> itemsList, Connection con) throws Exception {
        final String sql = "INSERT INTO ITEMS (ID, TITLE, CODE, PRODUCER, DATE_OF_LAST_UPDATE) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            for (Item item : itemsList) {

                stmt.setInt(1, item.getId());
                stmt.setString(2, item.getTitle());
                stmt.setInt(3, item.getCode());
                stmt.setString(4, item.getProducer());
                stmt.setDate(5, Date.valueOf(item.getDateOfLastUpdate()));

                stmt.executeUpdate();
                con.commit();
            }

        } catch (SQLException exc) {
            throw new Exception(exc);
        } finally {
            System.out.println("Table items was imported into DB");
        }

    }

    /**
     * Checks if file exist
     *
     * @param fileName - name of file
     * @throws FileNotFoundException
     */
    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }

    /**
     * Reads string, converts it to date, and returns date
     *
     * @param strDate - String that contains Date
     * @return Localdate
     * @throws ParseException
     */
    private static LocalDate convertStringToDate(String strDate) throws ParseException {
        LocalDate date;
        DateTimeFormatter formatter;

        try {
            if (strDate.contains(".") && strDate.contains(":")) {
                formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss");
                date = LocalDate.parse(strDate, formatter);
                return date;
            }
            if (strDate.contains("/")) {
                formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                date = LocalDate.parse(strDate, formatter);
                return date;
            }
            if (strDate.contains(" ")) {
                formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
                date = LocalDate.parse(strDate, formatter);
                return date;
            }


        } catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", strDate);
            throw exc;      // Rethrow the exception.
        }
        return null;
    }

    /**
     * Reads string, converts it to array of integers, and returns this array
     *
     * @param strArrList - string that contains numbers
     * @return result
     */
    private static int[] convertStringToArr(String strArrList) {
        String[] items = strArrList.split(",");
        int[] result = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            result[i] = Integer.parseInt(items[i]);
        }
        return result;
    }

}
