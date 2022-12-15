import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Program {

    public static void main(String[] args) {
        //�������� ������� ����
        RussianWordsParser fileParser = new RussianWordsParser("War and piece.txt");
        fileParser.parse();
        Map<String, Integer> frequency = fileParser.frequency();
        //���������� �� � ����
        try(FileWriter fileWriter = new FileWriter("statistics.txt")) {
            for(Map.Entry<String, Integer> entry : frequency.entrySet()) {
                String s = String.format("����� %s ����������� %d ���\n", entry.getKey(), entry.getValue());
                fileWriter.write(s);
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(fileParser.mostFrequentWord());
        System.out.println("������� ������� ������� ����� = "+ fileParser.averageFrequency());

    }
}
