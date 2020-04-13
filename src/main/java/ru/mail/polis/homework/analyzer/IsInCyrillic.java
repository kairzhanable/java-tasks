package ru.mail.polis.homework.analyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ��������� ������, ������� ���������, ������� �� ����� �� ���������
 * ������ GOOD, ���� � ������ ��� ��������� ����
 * ���� ���� ���� �� ���� ����� �� ��������, ������ NOT_CYRILLIC
 * ���� ����� ������ �� ���� ��� ��������� �� �������, �� �������� ����� - ���� GOOD
 */

public class IsInCyrillic implements TextAnalyzer {

    private final static Pattern PATTERN = Pattern.compile(
            "[" +                   //������ ������ ���������� ��������
                    "�-��-߸�" +    //����� �������� ��������
                    "\\d" +         //�����
                    "\\s" +         //�����-����������� (������, ��������� � �.�.)
                    "\\p{Punct}" +  //����� ����������
                    "]" +           //����� ������ ���������� ��������
                    "*");           //����������� ������� ��������� �������� � ����� ����������

    @Override
    public FilterType analyze(String arg) {
        Matcher matcher = PATTERN.matcher(arg);
        if (matcher.matches()) {
            return FilterType.GOOD;
        }
        return FilterType.NOT_CYRILLIC;
    }

    @Override
    public FilterType getFilterAnswer() {
        return FilterType.NOT_CYRILLIC;
    }
}
