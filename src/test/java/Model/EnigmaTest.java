package Model;

import org.junit.Test;
import static org.junit.Assert.*;
public class EnigmaTest {

    @Test
    public void Decryption (){
        String szyfr = Enigma.Encryption("test");
        assertEquals("test", Enigma.Decryption(szyfr));
        assertEquals("Konstantynopolitańczykowianeczka", Enigma.Decryption(Enigma.Encryption("Konstantynopolitańczykowianeczka")));
    }

}
