package View;

import Model.Jedi;
import Model.Zakon;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    JFrame ramka;
    JTextField nazwaJedText;
    JTextField nazwaZakText;
    JComboBox pytanieOpcje;
    JSlider mocSlider;
    JRadioButton ciemna;
    JRadioButton jasna;
    static DefaultListModel listaZakonow;
    static DefaultListModel jedis;
    static DefaultListModel daneJedi;
    static JList lista;
    static JList lista3;


static {

    listaZakonow = new DefaultListModel();
    listaZakonow.addAll(Zakon.zakonySQL());
    jedis = new DefaultListModel();
    jedis.addAll(Jedi.getJedis());
    daneJedi = new DefaultListModel();
    daneJedi.addAll(Jedi.getLonelyJedis());


}
    public Panel(JFrame ramka) {
        this.ramka = ramka;
        setLayout(null);


        JLabel zakony = new JLabel("zakony jedi", SwingConstants.CENTER);
        zakony.setBounds(0, 0, 450, 50);
        zakony.setFont(new Font("Star Jedi", Font.PLAIN, 30));
        add(zakony);

        JLabel jedi = new JLabel("jedi", SwingConstants.CENTER);
        jedi.setBounds(450, 0, 450, 50);
        jedi.setFont(new Font("Star Jedi", Font.PLAIN, 30));
        add(jedi);

        lista = new JList();
        lista.setModel(listaZakonow);
        JScrollPane sp = new JScrollPane(lista);
        sp.setBounds(25, 50, 400, 400);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(sp);



        JList lista2 = new JList();
        lista2.setModel(this.jedis);

        JScrollPane sp2;

        sp2 = new JScrollPane(lista2);
        sp2.setBounds(475, 50, 400, 400);
        sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(sp2);

        JLabel rejZak = new JLabel("rejestracja zakonu jedi", SwingConstants.CENTER);
        rejZak.setBounds(0, 450, 450, 50);
        rejZak.setFont(new Font("Star Jedi", Font.PLAIN, 20));
        add(rejZak);

        JLabel rejJed = new JLabel("rejestracja jedi", SwingConstants.CENTER);
        rejJed.setBounds(450, 450, 450, 50);
        rejJed.setFont(new Font("Star Jedi", Font.PLAIN, 20));
        add(rejJed);

        JLabel nazwaZak = new JLabel("Nazwa:", SwingConstants.RIGHT);
        nazwaZak.setBounds(0, 500, 110, 50);
        add(nazwaZak);

        JLabel nazwaJedi = new JLabel("Nazwa:", SwingConstants.RIGHT);
        nazwaJedi.setBounds(450, 500, 110, 50);
        add(nazwaJedi);

        nazwaZakText = new JTextField();
        nazwaZakText.setBounds(130, 500, 295, 50);
        add(nazwaZakText);

        nazwaJedText = new JTextField();
        nazwaJedText.setBounds(580, 500, 295, 50);
        add(nazwaJedText);

        lista3 = new JList();
        lista3.setModel(daneJedi);
        JScrollPane sp3 = new JScrollPane(lista3);
        sp3.setBounds(130, 560, 295, 200);
        sp3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(sp3);

        JButton wybierzZak = new JButton("Wybierz");
        wybierzZak.setBounds(25, 570, 85, 30);
        wybierzZak.addActionListener(new Listenery.WybierzZakon(this));
        add(wybierzZak);

        JButton importZak = new JButton("Import");
        importZak.setBounds(25, 760, 85, 30);
        importZak.addActionListener(new Listenery.ImportZakons(this));
        add(importZak);

        JButton exportZak = new JButton("Export");
        exportZak.setBounds(25, 815, 85, 30);
        exportZak.addActionListener(new Listenery.ExportZakons(this));
        add(exportZak);

        JButton rejestrZak = new JButton("Zarejestruj");
        rejestrZak.setBounds(155, 845, 120, 30);
        rejestrZak.addActionListener(new Listenery.NewZakon(this));
        add(rejestrZak);

        JButton clearZak = new JButton("Wyczyść");
        clearZak.setBounds(280, 845, 120, 30);
        // wybierz.addActionListener(new Listenery.Wyczysc());
        add(clearZak);

        JTextField sciezkaZakText = new JTextField("D:\\projekty\\Jedi\\src\\main\\resources\\Zakons.jed");
        sciezkaZakText.setBounds(130, 770, 295, 50);
        sciezkaZakText.setEditable(false);
        add(sciezkaZakText);

        JSeparator linia = new JSeparator(JSeparator.VERTICAL);
        linia.setBounds(450, 10, 10, 880);
        add(linia);

        JLabel miecz = new JLabel("Kolor miecza:", SwingConstants.RIGHT);
        miecz.setBounds(450, 575, 110, 50);
        add(miecz);

        JLabel moc = new JLabel("Moc:", SwingConstants.RIGHT);
        moc.setBounds(450, 650, 110, 50);
        add(moc);

        JLabel moc2 = new JLabel("Strona mocy:", SwingConstants.RIGHT);
        moc2.setBounds(450, 700, 110, 50);
        add(moc2);

        JButton importJed = new JButton("Import");
        importJed.setBounds(475, 760, 85, 30);
        importJed.addActionListener(new Listenery.ImportJedi(this));
        add(importJed);

        JButton exportJed = new JButton("Export");
        exportJed.setBounds(475, 815, 85, 30);
        exportJed.addActionListener(new Listenery.ExportJedi(this));
        add(exportJed);

        JButton rejestrJed = new JButton("Zarejestruj");
        rejestrJed.setBounds(605, 845, 120, 30);
        rejestrJed.addActionListener(new Listenery.ZarejestrujJedi(this));
        add(rejestrJed);

        JButton clearJed = new JButton("Wyczyść");
        clearJed.setBounds(730, 845, 120, 30);
        // wybierz.addActionListener(new Listenery.Wyczysc());
        add(clearJed);

        pytanieOpcje = new JComboBox<String>(new String[]{
                "niebieski",
                "czerwony",
                "zielony"});
        pytanieOpcje.setBounds(580, 575, 295, 50);
        pytanieOpcje.setFont(new Font("Star Jedi", Font.PLAIN, 20));
        pytanieOpcje.setEditable(false);
        add(pytanieOpcje);

        JTextField sciezkaJedText = new JTextField("D:\\projekty\\Jedi\\src\\main\\resources\\Jedis.jed");
        sciezkaJedText.setBounds(580, 770, 295, 50);
        sciezkaJedText.setEditable(false);
        add(sciezkaJedText);

        mocSlider = new JSlider(0,1000,500);
        mocSlider.setBounds(580, 665, 295, 30);
        mocSlider.setMajorTickSpacing(1000);
        mocSlider.setPaintLabels(true);
        add(mocSlider);

        ButtonGroup group = new ButtonGroup();
        ciemna = new JRadioButton("ciemna");
        ciemna.setBounds(630, 700, 110, 50);

        jasna = new JRadioButton("jasna");
        jasna.setBounds(740, 700, 110, 50);
        group.add(ciemna);
        group.add(jasna);
        add(ciemna);
        add(jasna);


    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(900, 900);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        listaZakonow.clear();
        listaZakonow.addAll(Zakon.zakonySQL());
        jedis.clear();
        jedis.addAll(Jedi.getJedis());
        daneJedi.clear();
        daneJedi.addAll(Jedi.getLonelyJedis());
   }

}


