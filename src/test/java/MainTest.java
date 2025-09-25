import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class MainTest {

    @Test
    public void testAbc() {
        assertEquals(Main.countVowels("abc"), 1);
    }

    @Test
    public void testDoubleA() {
        assertEquals(Main.countVowels("aabc"), 2);
    }

    @Test
    public void testUppercase() {
        assertEquals(Main.countVowels("Abc"), 1);
    }

    @Test
    public void testEmpty() {
        assertEquals(Main.countVowels(""), 0);
    }

    @Test
    public void testNull() {
        assertEquals(Main.countVowels(null), 0);
    }

    @Test
    public void testDigits() {
        assertEquals(Main.countVowels("0"), 0);
    }

    @Test
    public void testNoVowels() {
        assertEquals(Main.countVowels("bc"), 0);
    }

    @Test
    public void testAllVowels() {
        assertEquals(Main.countVowels("aie"), 3);
    }

    @Test
    public void testUpperVowels() {
        assertEquals(Main.countVowels("AIE"), 3);
    }

    @Test
    public void testMixed() {
        assertEquals(Main.countVowels("abcu123o"), 3);
    }

    @Test
    public void testSpecialChars() {
        assertEquals(Main.countVowels("abcu!*3o"), 3);
    }

    @Test
    public void testQueue() {
        assertEquals(Main.countVowels("queue"), 5);
    }
}