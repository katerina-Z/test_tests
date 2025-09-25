import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class MainTest {

    @DataProvider
    public Object[][] vowelsData() {
        return new Object[][] {
                {"abc", 1},
                {"aabc", 2},
                {"Abc", 1},
                {"", 0},
                {"queue", 5},
                {"bcdfg", 0},
                {null, 0},
                {"0", 0},
                {"aie", 3},
                {"AIE", 3},
                {"abcu123o", 3},
                {"abcu!*3o", 3}
        };
    }

    @Test(dataProvider = "vowelsData")
    public void testVowels(String input, int expected) {
        assertEquals(Main.countVowels(input), expected, "Ошибка для строки: " + input);
    }
}