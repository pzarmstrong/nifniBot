package nifniBot;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;

import communityEvents.CommunityEvent;
import communityEvents.EventManager;
import net.dv8tion.jda.client.entities.Group;

// FROM https://github.com/DV8FromTheWorld/JDA/blob/master/src/examples/java/MessageListenerExample.java

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.requests.RestAction;
import utils.DefaultResponses;

public class MessageListener extends ListenerAdapter
{
    EventManager eMan;
    private final String eventsFileName = "events.xml";
    
    public MessageListener()
    {
        eMan = new EventManager(loadXMLList());
    }
    
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        JDA jda = event.getJDA();
        long responseNumber = event.getResponseNumber();
        
        User author = event.getAuthor();
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        
        String msg = message.getContent();
        
        boolean bot = author.isBot();
        
        if (event.isFromType(ChannelType.TEXT))
        {
            Guild guild = event.getGuild();
            TextChannel textChannel = event.getTextChannel();
            Member member = event.getMember();
            
            String name;
            if (message.isWebhookMessage())
            {
                name = author.getName();
            }
            else
            {
                name = member.getEffectiveName();
            }
            
            System.out.printf("(%s)[%s]<%s>: %s\n", guild.getName(), textChannel.getName(), name, msg);
        }
        else if (event.isFromType(ChannelType.PRIVATE))
        {
            //PrivateChannel privateChannel = event.getPrivateChannel();
            
            System.out.printf("[PRIV]<%s>: %s\n", author.getName(), msg);
        }
        else if (event.isFromType(ChannelType.GROUP))
        {
            Group group = event.getGroup();
            String groupName = group.getName() != null ? group.getName() : "";
            
            System.out.printf("[GRP: %s]<%s>: %s\n", groupName, author.getName(), msg);
        }
        
        if (!bot)
        {
            if (msg.contains("!hello"))
            {
                channel.sendMessage("Hello, " + author.getAsMention()).queue();
            }
            else if(msg.contains("!createEvent") || msg.contains("!addEvent"))
            {
                String creationMessage = eMan.createEvent(message);
                channel.sendMessage(creationMessage).queue();
                this.updateXMLList();
            }
            else if(msg.contains("!modifyEvent"))
            {
                String modifyMessage = eMan.modifyEventTime(message);
                channel.sendMessage(modifyMessage).queue();
                this.updateXMLList();
            }
            else if(msg.equals("!listEvents") || msg.equals("!events"))
            {
                channel.sendMessage("Upcoming Events").queue();
                for (CommunityEvent ce : eMan.getCommunityEvents())
                {
                    channel.sendMessage(ce.getEventName() + "\t-\t" + ce.getDateOfEvent().toString()).queue();
                }
            }
            else if(msg.contains("!deleteEvent") || msg.contains("!removeEvent"))
            {
                String deletionMessage = eMan.deleteEvent(message);
                channel.sendMessage(deletionMessage).queue();
                this.updateXMLList();
            }
            else if (message.isMentioned(event.getJDA().getSelfUser()))
            {
                channel.sendMessage(DefaultResponses.getRandomResponse()).queue();
            }
        }
    }
    
    private void updateXMLList()
    {
        XStream xstream = new XStream();
        //xstream.alias("CommunityEvents", EventManager.class);
        xstream.alias("CommunityEvent", CommunityEvent.class);
        xstream.alias("list", ArrayList.class);
        try
        {
            FileOutputStream fos = new FileOutputStream(eventsFileName);
//            for (CommunityEvent ce : CommunityEvents)
//            {
//                //System.out.println(xstream.toXML(ce));
//                xstream.toXML(ce, fos);
//            }
            xstream.toXML(eMan.getCommunityEvents(), fos);
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    private ArrayList<CommunityEvent> loadXMLList()
    {
        XStream xstream = new XStream();
        xstream.alias("CommunityEvent", CommunityEvent.class);
        xstream.alias("list", ArrayList.class);
        ArrayList<CommunityEvent> newEventList;
        
        try
        {
            FileInputStream fis = new FileInputStream(eventsFileName);
            newEventList = (ArrayList<CommunityEvent>) xstream.fromXML(fis);
            
            return newEventList;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
