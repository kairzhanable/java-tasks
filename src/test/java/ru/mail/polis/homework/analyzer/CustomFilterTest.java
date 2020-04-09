package ru.mail.polis.homework.analyzer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomFilterTest {
    @Test
    public void analyzeOnlyCyrillicFilter() {
        TextFilterManager manager = new TextFilterManager(
                new TextAnalyzer[]{TextAnalyzer.createCyrillicTextAnalyzer()});
        assertEquals("GOOD", manager.analyze("��� ������� �����!").toString());
        assertEquals("NOT_CYRILLIC", manager.analyze("Etot tekst napisan translitom!").toString());
        assertEquals("GOOD", manager.analyze(null).toString());
        assertEquals("GOOD", manager.analyze("���� ����� � �������� ,,,,,, ").toString());
        assertEquals("NOT_CYRILLIC", manager.analyze("� eto smeshannyi �����").toString());
        assertEquals("GOOD", manager.analyze("133521").toString());
        assertEquals("GOOD", manager.analyze("��� ������� ����� � ������� 1432").toString());
    }
}
