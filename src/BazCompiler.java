import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BazCompiler {
    // 만들어야 하는 기if if! get show 'if - endif' end

    public static void main(String[] args) throws Exception {

        // input two arguments, filepath and outputpath
        if (args.length != 2) {
            System.out.println("[Error] input file path!!");
            throw new Exception();
        }

        // setting reader & writer
        File file = new File(args[0]);
        File output = new File(args[1]);

        Scanner scan = new Scanner(file);
        FileWriter fileWriter = new FileWriter(output);

        // initialize Tokenizer
        StringTokenizer stringTokenizer = null;



        /*
        앞에 기본적인 변수를 넣어준다.
        파일을 하나하나 읽어서 바꿔치기를 한다.
        end를 만날 때까지 while문을 돌리고 각 평션을 만나면 내부에서 반복문 하나 더 돌려서 펑션 내부를 작성하고
        hasNext 사용해서 있으면 에러
        endif 등을 찍고 계속 진행!!
         */

        // Convert baz to c
        String line;
        do {
            line = scan.nextLine();
            stringTokenizer = new StringTokenizer(line, " ");
            System.out.println(line);


        } while (!line.equals("end"));

        if (scan.hasNext()) {
            System.out.println("[Error] Compile Error!!");
            throw new Exception();
        }

    }

}
