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
            return new Book("", "", 0, 0, "",
                    "", "000-00-000-0000-01", linenumber);
            // błędny ISBN, oznaczający obiekt pochodzący z pozycji z nieprawidłowymi danymi, 14 cyfr:
            // - ostatnia usuwana przy szukaniu po ISBN;
            // - niewykluczony prawidłowy numer 000-00-000-0000-0 w danych wejściowych
            // - dopuszczenie tytułu książki "!!data_error_in_input_file!!", dla zasady (nie jest to już znacznik złej
            // linii danych pliku wejściowego)
        }
    }

    private static int getCountableFromString(String value) throws NumberFormatException {
        return Integer.valueOf(value);
    }
}