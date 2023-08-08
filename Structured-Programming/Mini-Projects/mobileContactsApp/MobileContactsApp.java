package gr.aueb.cf.homework.mobileContacts;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Mobile contacts application which can store up
 * to 500 contacts, using firstname, lastname and phone number
 */

public class MobileContactsApp {
    final static Scanner in = new Scanner(System.in);
    final static String[][] contacts = new String[500][3];
    static int pivot = -1;
    final static Path path = Paths.get("/Users/anastasiospapargyropoulos/Documents/Java/log.txt");


    public static void main(String[] args) {
        boolean quit = false;
        String s;
        int choice;
        String phoneNumber;

        do {
            printMenu();
            s = getChoice();
            if (s.matches("[qQ]")) quit = true;
            else {
                try {
                    choice = Integer.parseInt(s);
                    if (!isValid(choice)) {
                        throw new IllegalArgumentException("Error - Choice must be between 1 - 5");
                    }

                    switch (choice) {
                        case 1:
                            printContactMenu();
                            insertController(getFirstname(), getLastname(), getPhoneNumber());
                            System.out.println("Successful insert");
                            break;
                        case 2:
                            phoneNumber = getPhoneNumber();
                            deleteController(phoneNumber);
                            System.out.println("Successful delete");
                            break;
                        case 3:
                            phoneNumber = getPhoneNumber();
                            printContactMenu();
                            updateController(phoneNumber, getFirstname(), getLastname(), getPhoneNumber());
                            System.out.println("Successful update");
                            break;
                        case 4:
                            phoneNumber = getPhoneNumber();
                            String[] contact = getOneController(phoneNumber);
                            printContact(contact);
                            break;
                        case 5:
                            String[][] allContacts = getAllContactsController();
                            printAllContacts(allContacts);
                            break;
                        default:
                            throw new IllegalArgumentException("Bad choice");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (!quit);
    }

    /**
     * Prints a single contact.
     * @param contact   The given contact.
     */
    public static void printContact(String[] contact) {
        for (String s : contact) {
            System.out.println(s + " ");
        }
    }

    /**
     * Print all saved contacts.
     * @param contacts  All contacts in an array.
     */
    public static void printAllContacts(String[][] contacts) {
        for (String[] contact : contacts) {
            printContact(contact);
            // System.out.printf("%s\t%s\t%s\n", contact[0], contact[1], contact[2]);
        }
    }


    /**
     * Checks if a choice is valid.
     * @param choice    The given choice.
     * @return          True if it fulfills the criteria (if it is between 1-5)
     *                  False otherwise.
     */
    public static boolean isValid(int choice) {
        return (choice >= 1 && choice <= 5);
    }

    /**
     * Prints a menu.
     */
    public static void printMenu() {
        System.out.println("Select a choice from the below");
        System.out.println("1. Insert contact");
        System.out.println("2. Delete contact");
        System.out.println("3. Update contact");
        System.out.println("4. Search contact");
        System.out.println("5. Print all contacts");
        System.out.println("Q/q. Exit");
    }

    /**
     * Prints a prompt to choose from a selection.
     * @return  The choice.
     */
    public static String getChoice() {
        System.out.println("Insert your choice");
        return in.nextLine().trim();
    }

    /**
     * Prints a menu.
     */
    public static void printContactMenu() {
        System.out.println("Insert firstname, lastname and phone number");
    }

    /**
     * Prints a prompt to choose from a selection.
     * @return The choice.
     */
    public static String getFirstname() {
        System.out.println("Insert firstname");
        return in.nextLine().trim();
    }

    /**
     * Prints a prompt to choose from a selection.
     * @return The choice.
     */
    public static String getLastname() {
        System.out.println("Insert lastname");
        return in.nextLine().trim();
    }

    /**
     * Prints a prompt to choose from a selection.
     * @return The choice.
     */
    public static String getPhoneNumber() {
        System.out.println("Insert phone number");
        return in.nextLine().trim();
    }



    /*
     *  Controllers
     */

    /**
     * Checks if parameters are null or firstname, lastname < 2 or phone number >50
     * and if not it calls the service layer to insert a contact.
     * @param firstname     Contact's firstname
     * @param lastname      Contact's lastname
     * @param phoneNumber   Contact's phone number
     */
    public static void insertController(String firstname, String lastname, String phoneNumber) {
        try {
            // Validation
            if (firstname == null || lastname == null || phoneNumber == null) {
                throw new IllegalArgumentException("Nulls are not allowed");
            }

            if (firstname.length() < 2 || firstname.length() > 50) {
                throw new IllegalArgumentException("Firstname is not valid");
            }

            if (lastname.length() < 2 || lastname.length() > 50) {
                throw new IllegalArgumentException("Lastname is not valid");
            }

            if (phoneNumber.length() < 2 || phoneNumber.length() > 12) {
                throw new IllegalArgumentException("Phone number is not valid");
            }

            // Service call
            insertContactService(firstname.trim(), lastname.trim(), phoneNumber.trim());
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            throw e;
        }
    }


    /**
     * Checks if parameters are null or firstname, lastname < 2 or phone number >50
     * and if not it calls the service layer to update a contact.
     * @param firstname         Contact's firstname
     * @param lastname          Contact's lastname
     * @param oldPhoneNumber    Contact's old phone number
     * @param newPhoneNumber    Contact's old phone number
     */
    public static void updateController(String oldPhoneNumber, String firstname,
                                        String lastname, String newPhoneNumber) {
        try {
            // Validation
            if (oldPhoneNumber == null || firstname == null || lastname == null || newPhoneNumber == null)  {
                throw new IllegalArgumentException("Nulls are not allowed");
            }

            if (oldPhoneNumber.length() < 2 || oldPhoneNumber.length() > 50) {
                throw new IllegalArgumentException("Old phone number is not valid");
            }

            if (firstname.length() < 2 || firstname.length() > 50) {
                throw new IllegalArgumentException("Firstname is not valid");
            }

            if (lastname.length() < 2 || lastname.length() > 50) {
                throw new IllegalArgumentException("Lastname is not valid");
            }

            if (newPhoneNumber.length() < 2 || newPhoneNumber.length() > 12) {
                throw new IllegalArgumentException("New phone number is not valid");
            }

            // Service call
            updateContactService(oldPhoneNumber.trim(), firstname.trim(),
                    lastname.trim(), newPhoneNumber.trim());
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            throw e;
        }
    }

    /**
     * Checks if parameter is null or < 2 or > 12 and if not it calls the service layer to delete a contact.
     * @param phoneNumber   Contact's phone number
     */
    public static String[] deleteController(String phoneNumber) {
        try {
            if (phoneNumber.length() < 2 || phoneNumber.length() > 12) {
                throw new IllegalArgumentException("Phone number is not valid");
            }
            return deleteContactService(phoneNumber);
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            throw e;
        }
    }

    /**
     * Checks if parameter is null or < 2 or > 12 and if not it calls the service layer to return one contact.
     * @param phoneNumber   Contact's phone number
     */
    public static String[] getOneController(String phoneNumber) {
        try {
            if (phoneNumber.length() < 2 || phoneNumber.length() > 12) {
                throw new IllegalArgumentException("Phone number is not valid");
            }
            return getOneContactService(phoneNumber);
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            throw e;
        }
    }

    /**
     * Calls service layer to return all contacts
     * @return
     */
    public static String[][] getAllContactsController() {
        try {
            return getAllContactsService();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        }
    }



    /*
     * Service Layer
     */

    /**
     * Checks if contact exists.
     * Returns contact if it exists
     * else throws error.
     *
     * @param phoneNumber   phone number of contact.
     * @return              contact.
     */
    public static String[] getOneContactService(String phoneNumber) {
        try {
            String[] contact = getContactByPhoneNumber(phoneNumber);
            if (contact.length == 0) {
                throw new IllegalArgumentException("Contact not found");
            }

            return contact;
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    /**
     * Checks if contact list is empty.
     * Returns array of contacts if not empty,
     * else throws error.
     *
     * @return  array of contacts.
     */
    public static String[][] getAllContactsService() throws IllegalArgumentException {
        try {
            String[][] contactsList = getAllContacts();
            if (contactsList.length == 0) {
                throw new IllegalArgumentException("List is empty");
            }
            return contactsList;
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    /**
     * Checks if contact is inserted
     * else throws error.
     *
     * @param firstname      Firstname of contact.
     * @param lastname       Surname of contact.
     * @param phoneNumber    phone number of contact.
     */
    public static void insertContactService(String firstname, String lastname, String phoneNumber) {
        try {
            if (!(insert(firstname, lastname, phoneNumber))) {
                throw new IllegalArgumentException("Error in insert");
            }
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    /**
     * Checks if contact is updated
     * else throws error.
     *
     * @param firstname         Firstname of contact.
     * @param lastname          Surname of contact.
     * @param oldPhoneNumber    Old phone number of contact.
     * @param newPhoneNumber    New phone number of contact.
     */
    public static void updateContactService(String oldPhoneNumber, String firstname,
                                            String lastname, String newPhoneNumber) {

        try {
            if (!(update(oldPhoneNumber, firstname, lastname, newPhoneNumber))) {
                throw new IllegalArgumentException("Error in update");
            }
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    /**
     * Checks if contact is deleted
     * else throws error.
     * @param phoneNumber    phone number of contact.
     */
    public static String[] deleteContactService(String phoneNumber) {
        String[] contact;

        try {
            contact = delete(phoneNumber);
            if (contact.length == 0) {
                throw new IllegalArgumentException("Error in Delete");
            }
            return contact;
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }



    /*
     *   CRUD Services that are provided to
     *   Service Layer.
     */

    /**
     * Searches all contacts until it finds a phone number match.
     *
     * @param phoneNumber   searched phone number.
     * @return              position of contact (index) if found.
     *                      -1 if not found.
     */
    public static int getIndexByPhoneNumber(String phoneNumber) {
        for (int i = 0; i <= pivot; i++) {
            if (contacts[i][2].equals(phoneNumber)) {
                return i;
            }
        }

        return -1;  // if not found
    }

    /**
     * Inserts new contact if not already existing and if array is not full.
     *
     * @param firstname      name of contact.
     * @param lastname       surname of contact.
     * @param phoneNumber    phone number of contact
     * @return
     */
    public static boolean insert(String firstname, String lastname, String phoneNumber) {
        if (isFull(contacts)) {
            return false;
        }

        if (getIndexByPhoneNumber(phoneNumber) != -1) {
            return false;
        }

        pivot++;
        contacts[pivot][0] = firstname;
        contacts[pivot][1] = lastname;
        contacts[pivot][2] = phoneNumber;

        return true;
    }

    /**
     * Updates contact details, if contact exists.
     *
     * @param firstname          name of contact.
     * @param lastname           surname of contact.
     * @param oldPhoneNumber     original phone number.
     * @param newPhoneNumber     new phone number to update.
     * @return
     */
    public static boolean update(String oldPhoneNumber, String firstname, String lastname, String newPhoneNumber) {
        int positionToUpdate = getIndexByPhoneNumber(oldPhoneNumber);
        String[] contact = new String[3];

        if (positionToUpdate == -1) {
            return false;
        }

        contacts[positionToUpdate][0] = firstname;
        contacts[positionToUpdate][1] = lastname;
        contacts[positionToUpdate][2] = newPhoneNumber;
        //return contact;
        return true;
    }

    /**
     * Deletes a contact, if contact exists.
     * Moves all contacts after the deleted contact's position 1 position prior.
     * @param phoneNumber       phone number of contact.
     * @return                  the contact.
     */
    public static String[] delete(String phoneNumber) {
        int positionToDelete = getIndexByPhoneNumber(phoneNumber);
        String[] contact = new String[3];

        if (positionToDelete == -1) {
            return new String[] {};
        }

        System.arraycopy(contacts[positionToDelete], 0, contact, 0, contact.length);

        if (!(positionToDelete == contacts.length - 1)) {
            System.arraycopy(contacts, positionToDelete + 1, contacts, positionToDelete, pivot - positionToDelete);
        }

        pivot--;
        return contact;
    }


    /**
     * Gets a contact based on the phone number of the contact.
     * @param phoneNumber   The given phone number.
     * @return              The contact.
     */
    public static String[] getContactByPhoneNumber(String phoneNumber) {
        int positionToReturn = getIndexByPhoneNumber(phoneNumber);

        if (positionToReturn == -1) {
            return new String[] {};
        }

        return contacts[positionToReturn];
    }

    /**
     * Gets all contacts from the contacts array.
     * @return
     */
    public static String[][] getAllContacts() {
        return Arrays.copyOf(contacts, pivot + 1);
    }

    /**
     * Checks if an array is full.
     * @param arr   The given array.
     * @return      True if array is full
     *              False otherwise.
     */
    public static boolean isFull(String[][] arr) {
        return pivot == arr.length - 1;
    }

    /*
     * Custom logger.
     * Varargs String[] message
     * log(Exception)
     */

    public static void log(Exception e, String... message) {
        try (PrintStream ps = new PrintStream(new FileOutputStream(path.toFile(), true))) {
            ps.println(LocalDateTime.now() + "\n" + e.toString());
            ps.printf("%s", (message.length == 1) ? message[0] : "");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
