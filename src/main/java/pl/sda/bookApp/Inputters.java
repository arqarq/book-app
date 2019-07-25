package pl.sda.bookApp;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

class Inputters {
    static String giveFilePath() {
        System.out.print("Podaj ścieżkę do pliku z danymi [ENTER = \"dane.txt\"]: "); // welcome
        String inputpath = "";
        int i = 0;
        Scanner in = new Scanner(System.in);
        while ((i < 1) && in.hasNextLine()) {
            inputpath = in.nextLine();
            i++;
        }
        if (inputpath.equals("")) {
            return ".\\dane.txt";
        } else {
            return inputpath.trim().toLowerCase();
        }
    }

    static String giveFilePathWrite() {
        System.out.print("Podaj ścieżkę i nazwę pliku do zapisania [ENTER = \".\\dane2.txt\"]: ");
        String inputpath = "";
        int i = 0;
        Scanner in = new Scanner(System.in);
        while ((i < 1) && in.hasNextLine()) {
            inputpath = in.nextLine();
            i++;
        }
        if (inputpath.equals("")) {
            return ".\\dane2.txt";
        } else {
            return inputpath.trim().toLowerCase();
        }
    }

    static int giveMenuChoice() {
        System.out.print("Wybór: ");
        int i = 0;
        String menuChoice = "";
        Scanner in = new Scanner(System.in);
        while ((i < 1) && in.hasNextLine()) {
            menuChoice = in.nextLine();
            i++;
        }
        try {
            return parseInt(menuChoice);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    static String giveByWhat() {
        int i = 0;
        String what = "";
        Scanner in = new Scanner(System.in);
        while ((i < 1) && in.hasNextLine()) {
            what = in.nextLine();
            i++;
        }
        return what;
    }
}
