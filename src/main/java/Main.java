import Model.Jedi;
import View.Panel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame ramka = new JFrame("System zarządzania Jedi");

        ramka.add(new Panel(ramka));
        ramka.setLocationByPlatform(true);
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setVisible(true);
        ramka.pack();


    }
}
