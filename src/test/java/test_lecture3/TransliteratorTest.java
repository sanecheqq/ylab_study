package test_lecture3;

import org.junit.Test;
import io.ylab.intensive.task_lecture3.transliterator.Transliterator;
import io.ylab.intensive.task_lecture3.transliterator.TransliteratorImpl;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TransliteratorTest {

    @Test
    public void testSentenceTransliterate() {
        Transliterator transliterator = new TransliteratorImpl();
        String src = "Hello! Hi! ПРИВЕТ, ЕВПАТИЙ КОЛОВРАТ! ГДЕ ПОМОЩЬ? Bye-bye.";
        String expected = "Hello! Hi! PRIVET, EVPATII KOLOVRAT! GDE POMOSHCH? Bye-bye.";
        String result = transliterator.transliterate(src);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testAllAlphabeticTransliterate() {
        Transliterator transliterator = new TransliteratorImpl();
        StringBuffer src = new StringBuffer("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЫЬЪЭЮЯ");
        StringBuffer expected = new StringBuffer("ABVGDEEZHZIIKLMNOPRSTUFKHTSCHSHSHCHYIEEIUIA");
        String result = transliterator.transliterate(src.toString());

        assertThat(result).isEqualTo(expected.toString());
    }
}
