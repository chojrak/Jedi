package Model;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.TreeSet;

public abstract class Zakon implements Serializable {
    private int idZakonu;
    private String nazwaZakonu;
    private static HashMap<Integer, String> Zakony;
    private static int nextId;

    static {
        nextId = 1;
        Zakony = new HashMap<Integer, String>();
        try {
            ResultSet rs = Polaczenie.Execute("select * from zakony");
            while (rs.next()) {
                Zakony.put(rs.getInt("id"), rs.getString("nazwa_zakonu"));
                if (rs.getInt("id") + 1 > nextId) nextId = rs.getInt("id") + 1;
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void dodajZakon(String nazwaZakonu) {
        if (!Zakony.containsValue(nazwaZakonu)) {
            Zakony.put(nextId, nazwaZakonu);
            dodajZakonSQL(nextId++, nazwaZakonu);
        }
    }


    public static void dodajZakonSQL(int idZakonu, String nazwaZakonu) {
        Polaczenie.Update("insert into zakony (id, nazwa_zakonu) values (" + idZakonu + ", '" + nazwaZakonu + "'); commit;");
    }

    public static String getNazwaZakonu(int idZakonu) {
        return Zakony.get(idZakonu);
    }

    public static int getIdZakonu(String NazwaZakonu) {
        for (int i : Zakony.keySet()) if (Zakony.get(i).equals(NazwaZakonu)) return i;
        return 1;
    }


    public static TreeSet<String> zakonySQL() {
        TreeSet<String> ts = new TreeSet<>();
        StringBuilder sb = new StringBuilder();
        try {
            ResultSet rs = Polaczenie.Execute("select z.nazwa_zakonu, sum(case when j.zakon_id is not null then 1 else 0 end) czlonki from zakony z left join jedis j on j.zakon_id = z.id where z.id > 1 group by z.nazwa_zakonu");

            while (rs.next()) {
                sb.append(rs.getString("nazwa_zakonu"));
                sb.append(" (");
                sb.append(rs.getInt("czlonki"));
                sb.append(")");
                ts.add(sb.toString());
                sb = new StringBuilder();
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        return ts;
    }

    public static void zapisDoPliku() {
        HashMap<String, String> encryptedData = new HashMap<String, String>();
        for (int i : Zakony.keySet())
            encryptedData.put(Enigma.Encryption(String.valueOf(i)), Enigma.Encryption(Zakony.get(i)));

        try {
            FileOutputStream fos = new FileOutputStream("src\\main\\resources\\Zakons.jed");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(encryptedData);
            oos.close();
            fos.close();

        } catch (Exception ex) {
        }
    }

    public static void odczytZPliku(File f) {

        HashMap<String, String> encryptedData = new HashMap<String, String>();

        try {

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            encryptedData = (HashMap<String, String>) ois.readObject();

            ois.close();
            fis.close();

            for (String s : encryptedData.keySet()) {
                int id = Integer.valueOf(Enigma.Decryption(s));
                String nazwa = Enigma.Decryption(encryptedData.get(s));
                if (!Zakony.containsKey(id)) {
                    Zakony.put(id, nazwa);
                    dodajZakonSQL(id, nazwa);
                }
            }
        } catch (Exception ex) {

        }
    }


}
