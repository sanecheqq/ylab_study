package io.ylab.intensive.task_lecture3.transliterator;

import java.util.HashMap;

public class TransliteratorImpl implements Transliterator {
    private final HashMap<Character, String> translitRules = new HashMap<Character, String>();

    public TransliteratorImpl() {
        initRuEngRules();
    }

    @Override
    public String transliterate(String source) {
        StringBuilder result = new StringBuilder();
        String[] words = source.split("\\s");
        for (int i = 0; i < words.length; i++) {
            String translitedWord = translitWord(words[i]);
            translitedWord = addSpaceIfNeeded(translitedWord, i, words.length);
            result.append(translitedWord);
        }
        return result.toString();
    }

    private String addSpaceIfNeeded(String word, int i, int n) {
        if (i < n-1) {
            return word + " ";
        } else {
            return word;
        }
    }
    private String translitWord(String word) {
        StringBuffer translitedWord = new StringBuffer();
        for (Character symbol : word.toCharArray()) {
            appendTranslitedSymbol(translitedWord, symbol);
        }
        return translitedWord.toString();
    }

    private void appendTranslitedSymbol(StringBuffer translitedWord, Character symbol) {
        if (translitRules.containsKey(symbol)) {
            translitedWord.append(translitRules.get(symbol));
        } else {
            translitedWord.append(symbol);
        }
    }

    private void initRuEngRules() {
        translitRules.put('А', "A");
        translitRules.put('Б', "B");
        translitRules.put('В', "V");
        translitRules.put('Г', "G");
        translitRules.put('Д', "D");
        translitRules.put('Е', "E");
        translitRules.put('Ё', "E");
        translitRules.put('Ж', "ZH");
        translitRules.put('З', "Z");
        translitRules.put('И', "I");
        translitRules.put('Й', "I");
        translitRules.put('К', "K");
        translitRules.put('Л', "L");
        translitRules.put('М', "M");
        translitRules.put('Н', "N");
        translitRules.put('О', "O");
        translitRules.put('П', "P");
        translitRules.put('Р', "R");
        translitRules.put('С', "S");
        translitRules.put('Т', "T");
        translitRules.put('У', "U");
        translitRules.put('Ф', "F");
        translitRules.put('Х', "KH");
        translitRules.put('Ц', "TS");
        translitRules.put('Ч', "CH");
        translitRules.put('Ш', "SH");
        translitRules.put('Щ', "SHCH");
        translitRules.put('Ы', "Y");
        translitRules.put('Ь', "");
        translitRules.put('Ъ', "IE");
        translitRules.put('Э', "E");
        translitRules.put('Ю', "IU");
        translitRules.put('Я', "IA");
    }
}
