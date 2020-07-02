package View;

import Model.Jedi;
import Model.Zakon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Listenery {


    static class ZarejestrujJedi implements ActionListener {
        Panel panel;

        public ZarejestrujJedi(Panel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!Jedi.chckJedi(panel.nazwaJedText.getText())) {
                String strona;
                if (panel.jasna.isSelected()) strona = "jasna";
                else strona = "ciemna";
                Jedi jedi = new Jedi(panel.nazwaJedText.getText(), panel.pytanieOpcje.getSelectedItem().toString(), panel.mocSlider.getValue(), strona);

            } else {
                JOptionPane.showMessageDialog(panel, "Jest już taki Jedi");
            }
            panel.repaint();
        }
    }


    static class LonelyJedis implements ActionListener {
        Panel panel;

        public LonelyJedis(Panel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Jedi.getLonelyJedis();
        }
    }


    static class WybierzZakon implements ActionListener {
        Panel panel;

        public WybierzZakon(Panel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (panel.lista.getSelectedValuesList().size() != 1)
                JOptionPane.showMessageDialog(panel, "wybierz dokładnie jeden zakon");
            else if (panel.lista3.getSelectedValuesList().isEmpty())
                JOptionPane.showMessageDialog(panel, "wybierz przynajmniej jednego Jedi");
            else {
                Jedi.przypiszZakon(panel.lista3.getSelectedValuesList(), panel.lista.getSelectedValuesList().get(0).toString());
                JOptionPane.showMessageDialog(panel, "Jedi wstąpił do zakonu");
                panel.repaint();
            }

        }
    }


    static class NewZakon implements ActionListener {
        Panel panel;

        public NewZakon(Panel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (panel.nazwaZakText.getText().isEmpty())
                JOptionPane.showMessageDialog(panel, "podaj nazwę nowego zakonu lub wybierz zakon z listy");
            else if (!panel.lista3.getSelectedValuesList().isEmpty()) {
                Zakon.dodajZakon(panel.nazwaZakText.getText());
                Jedi.przypiszZakon(panel.lista3.getSelectedValuesList(), panel.nazwaZakText.getText() + " (");
                JOptionPane.showMessageDialog(panel, "Dodano nowy zakon i przypisano zaznaczonych Jedi");
            } else {
                Zakon.dodajZakon(panel.nazwaZakText.getText());
                JOptionPane.showMessageDialog(panel, "Dodano nowy zakon, \n Zapisz do niego niezrzeszonych Jedi");
            }
            panel.repaint();

        }
    }


    static class ExportJedi implements ActionListener {
        Panel panel;

        public ExportJedi(Panel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Jedi.zapisDoPliku();
            JOptionPane.showMessageDialog(panel, "Wyeksportowano zaszyfrowane dane do pliku", "Brawo", JOptionPane.INFORMATION_MESSAGE);
            panel.repaint();
        }
    }

    static class ExportZakons implements ActionListener {
        Panel panel;

        public ExportZakons(Panel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Zakon.zapisDoPliku();
            JOptionPane.showMessageDialog(panel, "Wyeksportowano zaszyfrowane dane do pliku", "Brawo", JOptionPane.INFORMATION_MESSAGE);
            panel.repaint();
        }
    }


    static class ImportJedi implements ActionListener {
        Panel panel;

        public ImportJedi(Panel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser("src\\main\\resources");
            int odp = fc.showDialog(panel, "Wybierz");
            File file = fc.getSelectedFile();
            Jedi.odczytZPliku(file);
            JOptionPane.showMessageDialog(panel, "Wczytano i rozszyfrowano dane", "Brawo", JOptionPane.INFORMATION_MESSAGE);
            panel.repaint();
        }
    }

    static class ImportZakons implements ActionListener {
        Panel panel;

        public ImportZakons(Panel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser("src\\main\\resources");
            int odp = fc.showDialog(panel, "Wybierz");
            File file = fc.getSelectedFile();
            Zakon.odczytZPliku(file);
            JOptionPane.showMessageDialog(panel, "Wczytano i rozszyfrowano dane", "Brawo", JOptionPane.INFORMATION_MESSAGE);
            panel.repaint();
        }
    }
}
