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

    public FilterType analyze(String arg) {
        Pattern pattern = Pattern.compile(
                "[" +                   //������ ������ ���������� ��������
                        "�-��-߸�" +    //����� �������� ��������
                        "\\d" +         //�����
                        "\\s" +         //�����-����������� (������, ��������� � �.�.)
                        "\\p{Punct}" +  //����� ����������
                        "]" +           //����� ������ ���������� ��������
                        "*");           //����������� ������� ��������� �������� � ����� ����������

        Matcher matcher = pattern.matcher(arg);
        if (matcher.matches()) {
            return FilterType.GOOD;
        }
        return FilterType.NOT_CYRILLIC;
    }

    private int priority = 3;
    public int getPriority() {
        return priority;
    }
}
