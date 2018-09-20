package pl.sda.bookApp;

class Converters {
    static Book setBookFromLine(String line, int linenumber) {
        String[] linee = line.split(";");
        linenumber++; // dla użytkownika pokazywany numer linii w pliku od 1, nie od 0
        if (linee.length == 7) {
            for (int i = 0; i < linee.length; i++) {
                linee[i] = linee[i].trim();
            }
            int linee2, linee3;
            try {
                linee2 = getCountableFromString(linee[2]);
            } catch (NumberFormatException e) {
                linee2 = 0;
                System.out.println("(!)Błędny rok wydania w linii: " + linenumber + ", treść: \"" + linee[2] + "\"");
                System.out.println("(!)Linia w pliku: \"" + line + "\"");
            }
            try {
                linee3 = getCountableFromString(linee[3]);
            } catch (NumberFormatException e) {
                linee3 = 0;
                System.out.println("(!)Błędna ilość stron w linii: " + linenumber + ", treść: \"" + linee[3] + "\"");
                System.out.println("(!)Linia w pliku: \"" + line + "\"");
            }
            return new Book(linee[0], linee[1], linee2, linee3, linee[4], linee[5], linee[6], linenumber);
        } else {
            System.out.println("(!)Zły format danych w linii: " + linenumber + ", z zawartością:");
            System.out.println("(!)\"" + line + "\"");
            return new Book("!!data_error_in_input_file!!", "", 0, 0, "",
                    "", "000-00-000-0000-01", linenumber);
            // " " dla błędnych linii, bo spacja jest potem trimowana(), żeby nie wyszukiwało dla "" (ENTERa)
            // błędny ISBN, poprawia wyszukiwanie przy błędnych wpisach w pliku wejściowym
        }
    }

    private static int getCountableFromString(String value) throws NumberFormatException {
        return Integer.valueOf(value);
    }
}