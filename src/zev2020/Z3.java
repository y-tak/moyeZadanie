package zev2020;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Z3 {

    /*
     * напишите метод для определения относительной частоты вхождения слов в книге.
     * Алгоритм может выполняться многоркратно
     *
     * */

    public static void main(String[] args) {


       File file = new File( "file.txt" );
         ///запишем в файл информацю
        try {
            writeToFile( "src/file.txt", true, Charset.forName( "UTF-8" ) );
        } catch (IOException e) {
            e.printStackTrace();
        }

        String text;
        try {
           text=readFromFile( new File( "src/file.txt" ) );
            System.out.println(text);

         //   ClassLoader loader = Z3.class.getClassLoader();
          //  File file1 = new File(loader.getResource("file.txt").getFile());
        //    List<String> lines = Files.readAllLines(file.toPath());
            List<String> words = new ArrayList<>();
            for (String line: text.split( " " )){
                String[] wordSplit = line.toLowerCase() // приведение к нижнему регистру
                        .replaceAll("\\p{Punct}", " ") // знаки препинания на пробел
                        .trim() // убираем пробелы
                        .split("\\s");
                for (String s: wordSplit){
                    if (s.length() > 0) {
                        words.add(s.trim());
                    }
                }
            }
           ////-----------первый -посчитать  сколько раз каждое слово встречается, вывесть нужное
           getFrequency1(words,"line");

             ///----второй способ-----------
            HashMap<String,Integer> dictionary=setupDictionary(words);
            System.out.println(" 2. способ слово ["+"line"+"] встречается "+getFrequency(dictionary, "line")+" раз ");

        } catch (IOException e) {
            e.printStackTrace();
        }

   }

//-----------------------

    ////--------   Сосчитать частоту встречаемости слов в Line  (файл file.txt).
    ///----------способ 1----------
        public static  int getFrequency1(List<String> book,String wordSearch)
        {
         HashMap<String, Integer> wordMap = new HashMap<>();
            for (String word: book) {
                if (wordMap.containsKey(word)){
                    wordMap.put(word, wordMap.get(word)+1);
                } else {
                    wordMap.put(word, 1);
                }
            }
             System.out.println(" сколько раз всего каждое слово встречается "+wordMap);
             System.out.println(" 1. способ слово ["+wordSearch+"] встречется "+ wordMap.get(wordSearch)+"  раз ");

             return 0;
        }

    ////------------------способ 2 многоразовый------------------------------------------
    ///---------заполнили таблицу--------
    public static   HashMap<String,Integer> setupDictionary(List<String> book)
    {

        HashMap<String,Integer> table=new HashMap<>(  );
        for (String word: book) {
            word = word.toLowerCase(); ///на всякий случай
            if (word.trim() != "") //если не пустое
            {
               if (! table.containsKey( word )) //елси таких не было слов раньще то присвоить 0
               {
                   table.put(word,0 );
               }

                table.put( word,table.get( word )+1);
            }
        }
        return table;
    }

///--------------обратились  катблицы посмтреть частоту------------------------
    public static int getFrequency(HashMap<String,Integer> table, String word)
    {
        if (table==null||word==null)return -1;
        word=word.toLowerCase();
        if (table.containsKey( word ))
        {
            return table.get(word);
        }
        return 0;
    }
//----------------------записать в файл-----------------------

    public static void writeToFile(String fileName, boolean append, Charset charset)
            throws IOException {
//        FileOutputStream - запись в файл
        try (FileOutputStream outputStream =
                     new FileOutputStream( fileName, append )) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                sb.append( "line " ).append( i ).append( "\n" );
            }

            byte[] bytes = sb.toString().getBytes( charset );
            outputStream.write( bytes );
        }
    }

///---------------------------прочитать-- из файла----------------------------
    public static String readFromFile(File file) throws IOException {

//        FileInputStream
        long sum = 0;
        StringBuilder sb = new StringBuilder();
        try (FileInputStream inputStream = new FileInputStream( file )) {
            System.out.println( inputStream.available() );
            byte[] bytes;

            while (inputStream.available() > 0) {
                int data = inputStream.read();
                sum += data;
                sb.append((char)data);
              //  System.out.print( (char) data );
            }

            return sb.toString();
        }
    }
//----------------------------------------------------------

}
