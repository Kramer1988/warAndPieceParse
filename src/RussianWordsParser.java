import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

//���� ����� ����� ������������ ������� ����� � ������
public class RussianWordsParser implements Parser{

    private final String fileName;
    private final Map<String, Integer> wordsFrequency;
    private double count =0 ;

    public RussianWordsParser(String fileName) {
        //����� �������� �������� ������������� ����� fileName
        this.fileName = fileName;
        this.wordsFrequency = new TreeMap<>();

    }

    @Override
    public void parse() {
        if(!wordsFrequency.isEmpty()) {
            //���� �� �� ����� ��� ������� ������� ����, �� ������ �� ������
            return;
        }
        try(FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String s = null;
            while((s = bufferedReader.readLine()) != null) {
                String[] russianWords = s.split("[^�-��-�]+");
                for(String word : russianWords) {

                    if(!word.equals("")) {   //���� ����� �� �������� ������ �������
                        //����� ����� ���� ���� � ����� wordsFrequency, ���� �� ����.
                        //toLowerCase ����� ��� �������������� � ������� ��������
                        //���������� ��� ��� ����, ����� �������� ����� "������" � "������" ��������� �����������
                        String lowerCaseWord = word.toLowerCase();
                        count++;
                        if(wordsFrequency.get(lowerCaseWord) == null) {
                            //����� ����������� � ������ ���. �� ������ ������ ��� ������� ����� �������
                            wordsFrequency.put(lowerCaseWord, 1);
                        }
                        else {
                            //������ ������ ������� �����. ����������� �� �� �������.
                            int oldFrequency = wordsFrequency.get(lowerCaseWord);
                            wordsFrequency.replace(lowerCaseWord, oldFrequency + 1);

                        }
                    }

                }
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Map<String, Integer> frequency() {
        return wordsFrequency;
    }

//    �������
//1. ����������� ������ https://github.com/IlyaBikmeev/warAndPieceParse
    //           2. � ����� RussianWordsParser �������� ��������� ������:
    //           � public String mostFrequentWord() � ���������� ���������� � ���� ������ � ����� ����� ����������� �����.
    //   �������� ������� � ����������� 13064 ����.
//            � public double averageFrequency() � ���������� ������� ������� ������� �����.
//            ��������:
//    ��� ���������� ������� ������ ��� ����� ������ �������� �� Map�� � ����� ����� � ����� ������� ��������.
//    ��� ���������� ������� ������ ��� ����� �������������� ��� �������� Map�a,
//    � �������� ��� ����� �� ������ ����.

    public String mostFrequentWord(){
        Map.Entry<String, Integer> wf = null;
        String str = null;
        for(Map.Entry<String, Integer> wordsFrequency1  :wordsFrequency.entrySet()) {
            if(wf==null || wordsFrequency1.getValue()>wf.getValue()){
                wf=wordsFrequency1;
               str = "����� ����� ����������� �����:" + wf + " ���(�) �����������";
            }
        }return str;
    }
    public double averageFrequency(){
    double res = count/wordsFrequency.size();
     return res;
    }



}
