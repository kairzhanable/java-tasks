package ru.mail.polis.homework.analyzer;

import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ��������� ������, ������� ���������, ������� �� ����� �� ���������
 * ������ GOOD, ���� � ������ ��� ��������� ����
 * ���� ���� ���� �� ���� ����� �� ��������, ������ NOT_CYRILLIC
 * ���� ����� ������ �� ���� ��� ��������� �� �������, �� �������� ����� - ���� GOOD
 */

public class IsInCyrillic implements TextAnalyzer {

    private static Pattern pattern = Pattern.compile(
            "[" +                   //������ ������ ���������� ��������
                    "�-��-߸�" +    //����� �������� ��������
                    "\\d" +         //�����
                    "\\s" +         //�����-����������� (������, ��������� � �.�.)
                    "\\p{Punct}" +  //����� ����������
                    "]" +           //����� ������ ���������� ��������
                    "*");           //����������� ������� ��������� �������� � ����� ����������

    @Override
    public FilterType analyze(String arg) {
        Matcher matcher = pattern.matcher(arg);
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
