package pl.sda.bookApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static pl.sda.bookApp.Converters.setBookFromLine;
import static pl.sda.bookApp.Inputters.*;
import static pl.sda.bookApp.Menu.showSpacer;

class Runner {
    public static void main(String[] args) {
        System.runFinalizersOnExit(true);

        List<String> daneklienta = new ArrayList<>();
        List<Book> booklist = new ArrayList<>();
        try {
            Path inputpath = Paths.get(giveFilePath());
            if (Files.exists(inputpath) && (Files.size(inputpath) != 0)) {
                BufferedReader bufferedReader = Files.newBufferedReader(inputpath);
                daneklienta = bufferedReader.lines().collect(Collectors.toList());
                bufferedReader.close();
                System.out.format("Plik \"%s\" istnieje i wczytuję dane...%n", inputpath.toString()); // to po odczycie, ze względu na łapanie IOException
//            for (String s : daneklienta) {
//            booklist.add(setBookFromLine(s, daneklienta.lastIndexOf(s)));
                for (int ii = 0; ii < daneklienta.size(); ii++) {
                    Book temp = setBookFromLine(daneklienta.get(ii), ii);
                    booklist.add(temp);
                    if (temp.getTitle().equals("!!data_error_in_input_file!!")) {
                        daneklienta.set(ii, "(!)Błąd w linii numer: " + (ii + 1) + " pliku wejściowego.");
                    }
                }
            } else if (Files.exists(inputpath) && (Files.size(inputpath) == 0)) {
                System.out.println("(!)Podano pusty plik i wychodzimy z kodem 12!");
                System.exit(12); // do uzgodnienia z klientem
            } else {
                System.out.println("(!)Podano nieistniejący plik i wychodzimy z kodem 11!");
                System.exit(11); // do uzgodnienia
            }
        } catch (IOException | InvalidPathException e) {
            System.out.println("(!)Podano nieistniejący plik i wychodzimy z kodem 11!");
            System.exit(11); // do uzgodnienia z klientem
        }

        showSpacer();
        List<Book> operated = booklist.stream()
                .filter(book -> !book.getTitle().equals("!!data_error_in_input_file!!"))
                .collect(Collectors.toList());
        if (operated.size() == 0) {
            System.out.println("(!)Brak jakichkolwiek prawidłowych danych w pliku wejściowym i wychodzimy z kodem 13!");
            System.exit(13);
        }

        Menu.showMenu();

        List<Book> operated2;
        String what;
        switch (giveMenuChoice()) {
            case 1:
                showSpacer();
                operated2 = operated.stream()
                        .sorted(Comparator.comparing(Book::getPagecount).reversed())
                        .collect(Collectors.toList());
                int mostpages = operated2.get(0).getPagecount();
                int mostpagesline = operated2.get(0).getLineNumberInFile();
                System.out.println("Najwięcej stron (" + mostpages + ") ma pozycja z linii: " + mostpagesline + " o treści:");
                System.out.println("\"" + daneklienta.get(mostpagesline - 1) + "\"");
                break;
            case 2:
                showSpacer();
//                Optional<Integer> max = booklist.stream()
//                        .map(x -> x.getPagecount())
//                        .max((x, y) -> Integer.compare(x, y));
//                List<Book> sortedByRelyear = booklist.stream()
                operated2 = operated.stream()
                        .sorted(Comparator.comparing(Book::getReldate).reversed())
                        .collect(Collectors.toList());
//                sortedByRelyear.forEach(book -> System.out.println("\"" + daneklienta.get(book.getLineNumberInFile() - 1) + "\""));
                for (Book book : operated2) {
                    System.out.println("\"" + daneklienta.get(book.getLineNumberInFile() - 1) + "\"");
                }
                break;
            case 3:
                System.out.print("Podaj typ książki do wypisania: ");
                what = giveByWhat().trim().toLowerCase();
                showSpacer();
                operated2 = operated.stream()
                        .filter(book -> book.getType().toLowerCase().equals(what))
                        .collect(Collectors.toList());
                if (operated2.size() == 0) {
                    System.out.println("(!)Brak pozycji");
                } else {
                    for (Book book : operated2) {
                        System.out.println("\"" + daneklienta.get(book.getLineNumberInFile() - 1) + "\"");
                    }
                }
                break;
            case 4:
                System.out.print("Podaj ISBN [000-00-000-0000-0 lub 0000000000000] do wypisania: ");
                what = giveByWhat().trim().replaceAll("-", "");
                String what13;
                try {
                    what13 = what.substring(0, 13);
                } catch (StringIndexOutOfBoundsException e) {
                    what13 = what;
                }
                String what2 = what13; // dodatkowa zmienna bo w lambdzie musi być final lub effectively final
                operated2 = operated.stream()
                        .filter(book -> book.getISBN().replaceAll("-", "").equals(what2))
                        .collect(Collectors.toList());
                showSpacer();
                if (operated2.size() == 0) {
                    System.out.println("(!)Brak pozycji");
                } else {
                    for (Book book : operated2) {
                        System.out.println("\"" + daneklienta.get(book.getLineNumberInFile() - 1) + "\"");
                    }
                }
                break;
            case 5:
                System.out.print("Podaj autora do wypisania [ENTER = wszyscy]: ");
                what = giveByWhat().trim().toLowerCase();
                showSpacer();
                operated2 = operated.stream()
                        .filter(book -> book.getAuthor().toLowerCase().contains(what))
                        .collect(Collectors.toList());
                if (operated2.size() == 0) {
                    System.out.println("(!)Brak pozycji");
                } else {
                    for (Book book : operated2) {
                        System.out.println("\"" + daneklienta.get(book.getLineNumberInFile() - 1) + "\"");
                    }
                }
                break;
            case 6:
                Path outputpath = null;
                try {
                    outputpath = Paths.get(giveFilePathWrite());
                    BufferedWriter bufferedWriter = Files.newBufferedWriter(outputpath);
                    bufferedWriter.close();
                } catch (InvalidPathException | IOException e) {
                    System.out.println("(!)Podano niewłaściwą scieżkę zapisu i wychodzimy z kodem 15!");
                    System.exit(15);
                }
                showSpacer();
                operated2 = operated.stream()
                        .sorted(Comparator.comparing(Book::getTitle))
                        .collect(Collectors.toList());
                System.out.format("Posortowano według tytułów...%n%n");
                for (Book book : operated2) {
                    System.out.println("\"" + daneklienta.get(book.getLineNumberInFile() - 1) + "\"");
                }
                try {
                    BufferedWriter bufferedWriter = Files.newBufferedWriter(outputpath);
                    for (Book book : operated2) {
                        bufferedWriter.write(daneklienta.get(book.getLineNumberInFile() - 1));
                        bufferedWriter.newLine();
                    }
                    System.out.format("%n...i zapisano w \"%s\".%n", outputpath.toString());
                    bufferedWriter.close();
                } catch (IOException e) {
                    System.out.format("%n(!)...i coś poszło źle z zapisem, dostałeś komunikat systemowy: \"%s\",%n", e.getCause());
                    System.out.println("(!)czyli wychodzimy z kodem 16!");
                    System.exit(16);
                }
                break;
            default:
                System.out.println("(!)Podano niewłaściwy wybór i wychodzimy z kodem 14!");
                System.exit(14); // do uzgodnienia z klientem
        }

        showSpacer();
        System.out.println("Program kończy pracę bez problemów (kod 10).");
        System.exit(10);
    }
}