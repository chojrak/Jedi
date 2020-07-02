package Model;

public class Enigma {

    public static String Encryption(String text) {
        int chr = 0;
        char[][] tab = {{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'},
                {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z'},
                {'x', 'c', 'v', 'b', 'n', 'm', 'ę', 'ó', 'ą', 'ś'},
                {'ż', 'ź', 'ć', 'ń', 'ł', 'Q', 'W', 'E', 'R', 'T'},
                {'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G'},
                {'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N'},
                {'M', 'Ę', 'Ó', 'Ą', 'Ś', 'Ż', 'Ź', 'Ć', 'Ń', 'Ł'},
                {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'},
                {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')'},
                {'-', '_', '|', '?', 46, '`', ':', ',', ' ', '"'}};


        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < text.length(); c++) {
            for (int i = 0; i < tab.length; i++) {
                for (int j = 0; j < tab[i].length; j++) {
                    if (text.charAt(chr) == tab[i][j]) {
                        sb.append(i);
                        sb.append(j);
                    }
                }
            }
            chr++;
        }

        int start = 0;
        int stop = 2;
        int x = 0;
        String str = sb.toString();
        sb.delete(0, sb.length());
        StringBuilder liczba = new StringBuilder();
        for (int i = 1; i <= str.length() / 2; i++) {
            x = Integer.valueOf(str.substring(start, stop));
            do {
                if (x % 16 < 10) liczba.append(x % 16);
                else liczba.append(String.valueOf((char) (65 + (x % 16) - 10)));
                x /= 16;
            }
            while (x > 0);
            start += 2;
            stop += 2;
            if (liczba.length() == 1) sb.append('0');
            for (int j = liczba.length() - 1; j >= 0; j--)
                sb.append(liczba.charAt(j));
            liczba.delete(0, liczba.length());
        }


        return sb.toString();

    }

    public static String Decryption(String str) {


        int start = 0;
        int stop = 1;
        int x = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= str.length() / 2; i++) {
            if (str.charAt(start) >= 'A') x += (16 * (str.charAt(start) - 55));
            else x += (16 * Integer.valueOf(String.valueOf(str.charAt(start))));
            if (str.charAt(stop) >= 'A') x += (str.charAt(stop) - 55);
            else x += Integer.valueOf(String.valueOf(str.charAt(stop)));
            if (x < 10) {
                sb.append('0');
                sb.append(x);
            } else sb.append(x);
            x = 0;
            start += 2;
            stop += 2;
        }

        char[][] tab = {{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'},
                {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z'},
                {'x', 'c', 'v', 'b', 'n', 'm', 'ę', 'ó', 'ą', 'ś'},
                {'ż', 'ź', 'ć', 'ń', 'ł', 'Q', 'W', 'E', 'R', 'T'},
                {'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G'},
                {'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N'},
                {'M', 'Ę', 'Ó', 'Ą', 'Ś', 'Ż', 'Ź', 'Ć', 'Ń', 'Ł'},
                {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'},
                {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')'},
                {'-', '_', '|', '?', 46, '`', ':', ',', ' ', '"'}};

        str = sb.toString();
        sb.delete(0, sb.length());
        start = 0;
        stop = 1;
        for (int i = 0; i < str.length() / 2; i++) {
            sb.append(tab[Integer.valueOf(String.valueOf(str.charAt(start)))][Integer.valueOf(String.valueOf(str.charAt(stop)))]);
            start += 2;
            stop += 2;
        }
        return sb.toString();
    }
}
