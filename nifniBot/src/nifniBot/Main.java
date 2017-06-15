package nifniBot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import utils.*;

public class Main
{

    public static void main(String[] args)
    {
        try
        {
            JDA jda = new JDABuilder(AccountType.BOT).setToken(Sneaky.DISCORD_TOKEN).addEventListener(new MessageListener()).buildBlocking();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
