package utils;

import java.util.concurrent.ThreadLocalRandom;

public final class DefaultResponses
{
    public final static String[] response = 
            {
                    "You rang?",
                    "Hello, yes, I am here.",
                    "Yup, that's my name!",
                    "Please speak clearly after the beep. \n\n[beep]",
                    "I am a bot. Bleep bloop."
            };
    
    public static String getRandomResponse()
    {
        int rNum = ThreadLocalRandom.current().nextInt(0, DefaultResponses.response.length);
        return response[rNum];
    }
}
