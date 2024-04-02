/*
*  Author of this project: Vinícius Cauã Pereira Martins
*
* */

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.Stack;

class MalformedHtml extends Exception{

}

class HTMLStructValidator{
    static void htmlStructValidator(String line,Stack<String> stack) throws MalformedHtml{
        line = line.replaceAll("//s+", "");

        if (line.startsWith("<")) {
            //if the tag is not closed an exception is thrown
            if (!line.endsWith(">")) {
                throw new MalformedHtml();
            }

            if(line.endsWith(">")) {
                //Gets the text contained in the tag
                String tag_name = line.substring(1, line.length()-1);

                //If the tag is a closing tag
                if (tag_name.startsWith("/")) {
                    //If a closing tag doesn't have it's respective opening tag, an exception is thrown
                    if (stack.isEmpty() || !stack.pop().equals(tag_name.substring(1))) {
                        throw new MalformedHtml();
                    }
                }

                //If the tag is an opening tag, the text contained in the tag is pushed to the stack
                if(!tag_name.startsWith("/")) {
                    stack.push(tag_name);
                }
            }
        }

    }
}

public class HtmlAnalyzer {

    public static void main(String[] args) {
        //Checks if the command line has the correct number of arguments
        if (args.length!=1){
            System.exit(1);
        }

        try {
            //Gets the url provided in the command line and opens a connection with it
            URL url = new URL(args[0]);
            URLConnection connection = url.openConnection();
            connection.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;
            String deepest_text="";
            StringBuilder current_text = new StringBuilder();

            int deepest_level=0;
            int current_level=0;

            Stack<String> stack=new Stack<>();

            //Reads the web page line by line
            while ((inputLine = in.readLine()) != null){

                inputLine=inputLine.trim();

                //Used to ignore empty lines
                if(inputLine.isEmpty()){
                    continue;
                }

                //Checks if the line has an opening tag
                if(!inputLine.startsWith("</") && inputLine.startsWith("<")){
                    current_level++;

                    HTMLStructValidator.htmlStructValidator(inputLine, stack);
                }

                //Checks if the line has a closing tag
                if(inputLine.startsWith("</")){
                    current_level--;

                    HTMLStructValidator.htmlStructValidator(inputLine, stack);

                }

                //Checks if the line has text in it
                if(!inputLine.startsWith("<") && !inputLine.startsWith("</")){
                    current_text.setLength(0);
                    current_text.append(inputLine);

                    //stores the deepest text
                    if(current_level>deepest_level){
                        deepest_text=current_text.toString();
                        deepest_level=current_level;
                        current_text.setLength(0);
                    }

                }
            }
            in.close();

            if(deepest_text.isEmpty()){
                System.exit(1);
            }

            if(!stack.isEmpty()) {
                throw new MalformedHtml();
            }

            System.out.println(deepest_text);

        }catch (MalformedURLException e){
            System.out.println("URL connection error");
        }catch (IOException e){
            System.out.println("URL connection error");
        }catch (MalformedHtml e){
            System.out.println("malformed HTML");
        }

    }
}