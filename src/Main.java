import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {

        readCustomersFile("Customers.csv");
        readItemsFile("Items.csv");
    }


    private static Customer readCustomersFile(String fileName) throws IOException {

        Customer customer = new Customer();

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
                strArr = str.split(";");
                customer.setName(strArr[0]);
                customer.setDateOfBirth(convertStringToDate(strArr[1], "d MMMM yyyy"));
                customer.setAddress(strArr[2]);
                customer.setGender(strArr[3]);
                customer.setPhoneNumber(strArr[4]);
                customer.setLastPurchases(convertStringToArr(strArr[5]));
                //?

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
        inportCustomerToDB(customer);
        return customer;
    }

    private static void inportCustomerToDB(Customer customer) {
        //?
    }

    private static Item readItemsFile(String fileName) throws IOException {
        Item item = new Item();
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
                strArr = str.split(";");
                item.setId(Integer.parseInt(strArr[0]));
                item.setTitle(strArr[1]);
                item.setCode(Integer.parseInt(strArr[2]));
                item.setProducer(strArr[3]);
                item.setDateOfLastUpdate(convertStringToDate(strArr[4], "dd.MM.yyyy k:mm:ss")); // Не проставляет время
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
        importItemToDB(item);
        return item;
    }

    private static void importItemToDB(Item item) {
        //?
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
    private static LocalDate convertStringToDate(String strDate, String format) throws ParseException {
        LocalDate date;
        DateTimeFormatter formatter;

        try {
            if (format.equals("d MMMM yyyy")) {
                formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
                date = LocalDate.parse(strDate, formatter);
                return date;
            }
            if (format.equals("dd.MM.yyyy k:mm:ss")) {
                formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy k:mm:ss", Locale.ENGLISH);
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
