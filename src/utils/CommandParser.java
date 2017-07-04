package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class CommandParser
{
    public String[] parseArgs(String raw)
    {
        ArrayList<String> split = new ArrayList<String>();
        
        String beheaded = raw.replaceFirst("!", "");   // removes the actuation characters from the command string
        String [] splitBeheaded = beheaded.split(" ");  // splits the command string into individual words
        
        for (String s : splitBeheaded)
        {
            split.add(s);   // adds the individual words into an array list
        }
        //String invoke = split.get(0);   // gets the first word of the command string, which should be the command word proper
        String[] args = new String[split.size() - 1];
        split.subList(1, split.size()).toArray(args);
        
        //return new CommandContainer(raw, beheaded, splitBeheaded, invoke, args);
        return args;
    }
    
    public LocalDateTime parseDateString(String pattern, String raw)
    {
        LocalDateTime date = null;
        
        try
        {
            System.out.println(raw + "\n" +  pattern);
            DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
            date = LocalDateTime.parse(raw, format);
            
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return date;
    }
    
    public class CommandContainer
    {
        public final String raw;
        public final String beheaded;
        public final String[] splitBeheaded;
        public final String invoke;
        public final String[] args;
        
        public CommandContainer(String raw, String beheaded, String[] splitBeheaded, String invoke, String[] args)
        {
            this.raw = raw;
            this.beheaded = beheaded;
            this.splitBeheaded = splitBeheaded;
            this.invoke = invoke;
            this.args = args;
        }
        
        public void printToString()
        {
            System.out.printf("raw: %s \nbeheaded: %s \ninvoke: %s \n", raw, beheaded, invoke);
            System.out.println("splitBeheaded: ");
            for (String s : splitBeheaded)
            {
                System.out.printf("%s\n", s);
            }
            System.out.println("args: ");
            for (String s : args)
            {
                System.out.printf("%s\n", s);
            }
        }
    }
}