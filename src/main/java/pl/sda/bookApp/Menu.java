package pl.sda.bookApp;

class Menu {
    static void showMenu(){
        System.out.println("Wybierz opcję - wpisz cyfrę i naciśnij ENTER.");
        System.out.println("1. Wypisz książkę z największą liczbą stron");
        System.out.println("2. Posortuj i wypisz książki według roku wydania (od najnowszej)");
        System.out.println("3. Wypisz książki w zależności od podanego typu");
        System.out.println("4. Wypisz książkę w zależności od podanego numeru ISBN");
        System.out.println("5. Wypisz książki w zależności od podanego autora");
        System.out.println("6. Zapisz do wskazanego pliku posortowane alfabetycznie (według tytułu) książki");
    }

    static void showSpacer(){
        System.out.println("* * * * * * * * * * * * * * * * * * * * *");
    }
}