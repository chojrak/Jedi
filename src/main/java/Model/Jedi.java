package Model;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Jedi<TreeList> implements Serializable {
    private int idJedi;
    private String nazwaJedi;
    private String kolorMiecza;
    private int moc;
    private String stronaMocy;
    private int zakonId;
    private String encryptedData;
    private static HashSet<String> tajne = new HashSet<String>();
    private static HashMap<String, Jedi> Jedis = new HashMap<String, Jedi>();

    static {
        importDanychSQL();
    }

    public Jedi(String nazwaJedi, String kolorMiecza, int moc, String stronaMocy) {
        try {
            ResultSet rs =Polaczenie.Execute("select * from jedis where nazwa_jedi like '"+nazwaJedi+"'");

            if (!rs.next()) {
                this.nazwaJedi = nazwaJedi;
                this.kolorMiecza = kolorMiecza;
                this.moc = moc;
                this.stronaMocy = stronaMocy;
                this.zakonId = 1;
                exportJediSQL(this);
                importDanychSQL();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Jedi(int idJedi, String nazwaJedi, String kolorMiecza, int moc, String stronaMocy, int zakonId) {
        this.idJedi = idJedi;
        this.nazwaJedi = nazwaJedi;
        this.kolorMiecza = kolorMiecza;
        this.moc = moc;
        this.stronaMocy = stronaMocy;
        this.zakonId = zakonId;
        Jedis.put(nazwaJedi, this);

        StringBuilder sb = new StringBuilder();
        sb.append(Enigma.Encryption(String.valueOf(idJedi)));
        sb.append(";");
        sb.append(Enigma.Encryption(nazwaJedi));
        sb.append(";");
        sb.append(Enigma.Encryption(kolorMiecza));
        sb.append(";");
        sb.append(Enigma.Encryption(String.valueOf(moc)));
        sb.append(";");
        sb.append(Enigma.Encryption(stronaMocy));
        sb.append(";");
        sb.append(Enigma.Encryption(String.valueOf(zakonId)));
        sb.append(";");
        this.encryptedData = sb.toString();
        tajne.add(this.encryptedData);

    }

    public static boolean chckJedi(String login) {
        return (Jedis.containsKey(login));
    }

    public static TreeSet<String> getJedis() {
        TreeSet<String> ts = new TreeSet<String>();
        StringBuilder sb = new StringBuilder();
        for (String s : Jedis.keySet()) {
            sb.append(s);
            sb.append("(");
            sb.append(Zakon.getNazwaZakonu(Jedis.get(s).getZakonId()));
            sb.append(")");

            ts.add(sb.toString());
            sb = new StringBuilder();
        }
        return ts;
    }

    public static TreeSet<String> getLonelyJedis() {
        TreeSet<String> ts = new TreeSet<String>();
        StringBuilder sb = new StringBuilder();
        try {
            ResultSet rs = Polaczenie.Execute("select * from jedis where zakon_id = 1");

             while (rs.next()) {
                sb.append(rs.getString("nazwa_jedi"));
                sb.append(": ");
                sb.append(rs.getString("strona_mocy"));
                sb.append(" strona mocy, ");
                sb.append(rs.getString("kolor_miecza"));
                sb.append(" miecz (");
                sb.append(rs.getString("moc"));
                sb.append(")");
                ts.add(sb.toString());
                sb = new StringBuilder();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ts;
    }

    public int getZakonId() {
        return zakonId;
    }

    public String getNazwaJedi() {
        return nazwaJedi;
    }

    public String getKolorMiecza() {
        return kolorMiecza;
    }

    public int getMoc() {
        return moc;
    }

    public String getStronaMocy() {
        return stronaMocy;
    }

    public static void zapisDoPliku() {

        try {
            FileOutputStream fos = new FileOutputStream("src\\main\\resources\\Jedis.jed");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(tajne);
            oos.close();
            fos.close();

        } catch (Exception ex) {
        }
    }

    public static void odczytZPliku(File f) {
        try {

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            HashSet<String> list = (HashSet<String>) ois.readObject();

            ois.close();
            fis.close();

            for (String s : list) if (!tajne.contains(s)) {tajne.add(s);
                rozkodowywanieCzlonkow(s);}

        } catch (Exception ex) {

        }
    }

    public void exportJediSQL(Jedi jedi) {

            StringBuilder query = new StringBuilder("insert into jedis (nazwa_Jedi, kolor_Miecza, moc, strona_Mocy, zakon_id) values ('");
            query.append(jedi.getNazwaJedi());
            query.append("', '");
            query.append(jedi.getKolorMiecza());
            query.append("', '");
            query.append(jedi.getMoc());
            query.append("', '");
            query.append(jedi.getStronaMocy());
            query.append("', '");
            query.append(jedi.getZakonId());
            query.append("')");

            Polaczenie.Update(query.toString());

    }

    public static void importDanychSQL() {
        try {
            ResultSet rs = Polaczenie.Execute("select * from jedis");

            while (rs.next())
                new Jedi(rs.getInt("ID"), rs.getString("nazwa_jedi"), rs.getString("kolor_miecza"), rs.getInt("moc"),
                        rs.getString("strona_mocy"), rs.getInt("zakon_id"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void przypiszZakon(List<String> listaJedi, String nazwaZakonu) {
        for (String s : listaJedi) {

                StringBuilder query = new StringBuilder("update jedis set zakon_id = ");
                query.append(Zakon.getIdZakonu(nazwaZakonu.substring(0, nazwaZakonu.indexOf('(') - 1)));
                query.append(" where nazwa_jedi like '");
                query.append(s.substring(0, s.indexOf(':')));
                query.append("'; commit;");

                Polaczenie.Update(query.toString());

        }
    }

    public static void rozkodowywanieCzlonkow(String j) {
        ArrayList<String> czlon = new ArrayList<String>();
        int lastInd = 0;
        for (int i = 1; i <=6; i++){
        int nextInd = j.indexOf(';', lastInd);

        czlon.add(j.substring(lastInd, nextInd));
        lastInd = ++nextInd;}
        StringBuilder query = new StringBuilder();
        try {
            ResultSet rs =Polaczenie.Execute("select id from jedis where id ="+Enigma.Decryption(czlon.get(0)));

            if (!rs.next()) {

            query.append("insert into jedis (id, nazwa_Jedi, kolor_Miecza, moc, strona_Mocy, zakon_id) values (");
            query.append(Enigma.Decryption(czlon.get(0)));
            query.append(", '");
            query.append(Enigma.Decryption(czlon.get(1)));
            query.append("', '");
            query.append(Enigma.Decryption(czlon.get(2)));
            query.append("', ");
            query.append(Enigma.Decryption(czlon.get(3)));
            query.append(", '");
            query.append(Enigma.Decryption(czlon.get(4)));
            query.append("', ");
            query.append(Enigma.Decryption(czlon.get(5)));
            query.append(")");

            Polaczenie.Update(query.toString());
            importDanychSQL();}
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}
