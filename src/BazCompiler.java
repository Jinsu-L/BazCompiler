import java.io.*;
import java.util.*;

public class BazCompiler {

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
        BufferedWriter writer = new BufferedWriter(new FileWriter(output));

        // initialize Tokenizer
        StringTokenizer stringTokenizer;


        // Print Front Code

        writer.write("#include <stdio.h>\n");
        writer.write("#include <stdlib.h>\n");
        writer.write("#include <string.h>\n");
        writer.write("#include <time.h>\n");
        writer.write("enum baz_val {true, false, baz};\n");

        writer.write("void baz_print(enum baz_val v) {\n");
        writer.write("if (v == true)\n");
        writer.write("printf(\"true\\n\");\n");
        writer.write("else if (v == false)\n");
        writer.write("printf(\"false\\n\");\n");
        writer.write("else if (v == baz)\n");
        writer.write("printf(\"baz\\n\");\n");
        writer.write("}\n");

        writer.write("void baz_input(enum baz_val* v) {\n");
        writer.write("char temp[5];\n");
        writer.write("scanf(\"%s\", temp);\n");
        writer.write("if (!strcmp(temp,\"true\"))\n");
        writer.write("*v = true;\n");
        writer.write("else if (!strcmp(temp,\"false\"))\n");
        writer.write("*v = false;\n");
        writer.write("else if (!strcmp(temp,\"baz\"))\n");
        writer.write("*v = baz;\n");
        writer.write("else {\n*v = 4;\nprintf(\"YOU ARE WRONG!\\n\");\nabort();\n}\n");
        writer.write("}\n");

        writer.write("int main(){\n");
        writer.write("\tsrand(time(NULL));\n");

        // Convert baz to c
        String line;
        HashMap<String, String> map = new HashMap<>();
        boolean indent = false;
        writer.write("\tint temp = 0;\n");

        while (true) {
            line = scan.nextLine();
            stringTokenizer = new StringTokenizer(line, " ");
            int numOfToken = stringTokenizer.countTokens();
            // Token이 4개 이상일 경우 문법으로 인하여 컴파일 불가 에러
            if (numOfToken > 3) {
                Error(writer, output);
            } else if (numOfToken == 2) {
                String firstToken = stringTokenizer.nextToken();

                if (firstToken.equals("show")) {
                    String variable = stringTokenizer.nextToken();
                    if (map.containsKey(variable) || isBazVariable(variable)) {
                        writer.write("baz_print(" + variable + ");\n");
                    } else {
                        Error(writer, output);
                    }
                } else if (firstToken.equals("get")) {
                    String variable = stringTokenizer.nextToken();
                    if (!map.containsKey(variable)) {
                        map.put(variable, variable);
                        writer.write("enum baz_val " + variable + ";\n");
                        writer.write("baz_input(&" + variable + ");\n");
                    } else {
                        writer.write("baz_input(&" + variable + ");\n");
                    }
                } else if (firstToken.equals("if")) {
                    indent = true;
                    String variable = stringTokenizer.nextToken();
                    writer.write("temp = rand()%2;\n");
                    writer.write("if (" + variable + " == true || ( temp && " + variable + " == baz)) {\n");
                    writer.write("temp = 0;\n");
                } else if (firstToken.equals("if!")) {
                    indent = true;
                    String variable = stringTokenizer.nextToken();
                    writer.write("temp = rand()%2;\n");
                    writer.write("if (" + variable + " == false || ( !temp && " + variable + " == baz)) {\n");
                    writer.write("temp = 0;\n");
                } else {
                    Error(writer, output);
                }
            } else if (numOfToken == 1) {
                String firstToken = stringTokenizer.nextToken();

                if (firstToken.equals("endif")) {
                    if (!indent) {
                        Error(writer, output);
                    }
                    indent = false;
                    writer.write("}\n");
                } else if (firstToken.equals("end")) {
                    writer.write("return 1;\n");
                    break;
                } else {
                    Error(writer, output);
                }
            } else {
                String firstToken = stringTokenizer.nextToken();
                String equal = stringTokenizer.nextToken();
                if (!equal.equals("="))
                    Error(writer, output);
                String variable = stringTokenizer.nextToken();
                if (!map.containsKey(variable) && isBazVariable(variable)) {
                    map.put(firstToken, firstToken);
                    writer.write("\tenum baz_val " + firstToken + " = " + variable + ";\n");
                } else {
                    Error(writer, output);
                }
            }


        }

        if (scan.hasNext()) {
            Error(writer, output);
        }

        writer.write("}\n");
        writer.flush();

    }

    private static boolean isBazVariable(String variable) {
        return variable.equals("true") || variable.equals("false") || variable.equals("baz");
    }


    private static void Error(BufferedWriter writer, File output) throws IOException {
        System.out.println("YOU ARE WRONG!");
        writer.close();
        writer = new BufferedWriter(new FileWriter(output));
        writer.write("#include <stdio.h>\n");
        writer.write("int main(){\n");
        writer.write("\tprintf(\"YOU ARE WRONG!\\n\");\n");
        writer.write("\treturn 0;\n");
        writer.write("}");
        writer.flush();
        System.exit(0);
    }

}
