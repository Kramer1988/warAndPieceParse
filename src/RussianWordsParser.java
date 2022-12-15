import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

//Этот класс умеет обрабатывать русские слова в файлах
public class RussianWordsParser implements Parser{

    private final String fileName;
    private final Map<String, Integer> wordsFrequency;
    private double count =0 ;

    public RussianWordsParser(String fileName) {
        //Можно добавить проверку существования файла fileName
        this.fileName = fileName;
        this.wordsFrequency = new TreeMap<>();

    }

    @Override
    public void parse() {
        if(!wordsFrequency.isEmpty()) {
            //Если мы до этого уже считали частоту слов, то ничего не делаем
            return;
        }
        try(FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String s = null;
            while((s = bufferedReader.readLine()) != null) {
                String[] russianWords = s.split("[^А-яА-я]+");
                for(String word : russianWords) {

                    if(!word.equals("")) {   //Если слово не является пустой строкой
                        //Слово может либо быть в нашем wordsFrequency, либо не быть.
                        //toLowerCase нужен для преобразования к нижнему регистру
                        //Используем его для того, чтобы например слова "яблоко" и "Яблоко" считались одинаковыми
                        String lowerCaseWord = word.toLowerCase();
                        count++;
                        if(wordsFrequency.get(lowerCaseWord) == null) {
                            //Слово встретилось в первый раз. На данный момент его частота равна единице
                            wordsFrequency.put(lowerCaseWord, 1);
                        }
                        else {
                            //Достаём старую частоту слова. Увеличиваем ее на единицу.
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

//    Задание
//1. Склонируйте проект https://github.com/IlyaBikmeev/warAndPieceParse
    //           2. В класс RussianWordsParser добавьте следующий методы:
    //           • public String mostFrequentWord() – возвращает информацию в виде строки о самом часто встречаемом слове.
    //   Например «платок – встречается 13064 раза».
//            • public double averageFrequency() – возвращает среднюю частоту каждого слова.
//            Указания:
//    Для реализации первого метода вам нужно просто пройтись по Map’у и найти слово с самой большой частотой.
//    Для реализации второго метода вам нужно просуммировать все значения Map’a,
//    и поделить эту сумму на размер мапа.

    public String mostFrequentWord(){
        Map.Entry<String, Integer> wf = null;
        String str = null;
        for(Map.Entry<String, Integer> wordsFrequency1  :wordsFrequency.entrySet()) {
            if(wf==null || wordsFrequency1.getValue()>wf.getValue()){
                wf=wordsFrequency1;
               str = "САМОЕ ЧАСТО ВСТРЕЧАЕМОЕ СЛОВО:" + wf + " РАЗ(А) ВСТРЕЧАЕТСЯ";
            }
        }return str;
    }
    public double averageFrequency(){
    double res = count/wordsFrequency.size();
     return res;
    }



}
