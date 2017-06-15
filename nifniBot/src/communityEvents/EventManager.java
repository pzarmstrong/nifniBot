package communityEvents;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.thoughtworks.xstream.XStream;

import net.dv8tion.jda.core.entities.Message;
import utils.CommandParser;

public class EventManager
{
    private ArrayList<CommunityEvent> CommunityEvents;
    private CommandParser cp = new CommandParser();
    
    public EventManager(ArrayList<CommunityEvent> newEventList)
    {
        if (newEventList != null)
        {
            CommunityEvents = newEventList;
        }
        else
        {
            CommunityEvents = new ArrayList<CommunityEvent>();   
        }
        //CommunityEvents = new ArrayList<CommunityEvent>();
    }
    
    private boolean eventExists(String name)
    {
        for (CommunityEvent ce : CommunityEvents)
        {
            if (ce.getEventName().equals(name))
            {
                return true;
            }
            else
            {
                continue;
            }
        }
        return false;
    }
    
    public ArrayList<CommunityEvent> getCommunityEvents()
    {
        return CommunityEvents;
    }
    
    public String createEvent(Message m)
    {   
        final int NUM_ARGS = 2;
        String[] args = cp.parseArgs(m.getContent());
        if (args.length < NUM_ARGS || args.length > NUM_ARGS)
        {
            return "Too many or too few arguments!\nUsage: !createEvent EventName HH:mm-dd/MM/yy \nCurrently, the <EventName> cannot contain any spaces.\nExample: !createEvent SundayFunday 18:50-26/6/17";
        }
        
        String eventName = args[0];
        
        if (eventExists(args[0]))
        {
            return "An event already exists by that name. Please choose a different name.";
        }
        
        Date d = cp.parseDateString("HH:mm-dd/MM/yy", args[1]);
        if (d == null)
        {
            System.out.println("Date format was invalid.");
            return "Invalid date format. The format is HH:mm-dd/MM/yy, e.g. 18:50-26/6/17";
        }
        
        CommunityEvents.add(new CommunityEvent(d, eventName));
        return "Success.";
    }
    
    public String modifyEventTime(Message m)
    {
        final int NUM_ARGS = 2;
        String[] args = cp.parseArgs(m.getContent());
        if (args.length < NUM_ARGS || args.length > NUM_ARGS)
        {
            return "Too many or too few arguments!\nUsage: !modifyEvent EventName HH:mm-dd/MM/yy\nCurrently, the <EventName> cannot contain any spaces.\nExample: !modifyEvent SundayFunday 18:50-26/6/17";
        }
        
        String name = args[0];
        
        Date d = cp.parseDateString("HH:mm-dd/MM/yy", args[1]);
        if (d == null)
        {
            System.out.println("Date format was invalid.");
            return "Invalid date format. The format is HH:mm-dd/MM/yy, e.g. 18:50-26/6/17";
        }
        
        CommunityEvent newEvent = new CommunityEvent(d, name);
        ListIterator<CommunityEvent> it = CommunityEvents.listIterator();
        
        while(it.hasNext())
        {
            if (it.next().getEventName().equals(name))
            {
                it.set(newEvent);
                return "Successfully modified event time '" + name + "' to " + d.toString();
            }
        }
        
//        for (CommunityEvent ce : CommunityEvents)
//        {
//            if (ce.getEventName().equals(name))
//            {
//                this.updateXMLList();
//                return "Event removed.";
//            }
//            else
//            {
//                continue;
//            }
//        }
        
        return "Unable to modify event.";
    }
    
    public String deleteEvent(Message m)
    {
        final int NUM_ARGS = 1;
        String[] args = cp.parseArgs(m.getContent());
        if (args.length < NUM_ARGS || args.length > NUM_ARGS)
        {
            return "Too many or too few arguments!\nUsage: !deleteEvent EventName\nCurrently, the <EventName> cannot contain any spaces.\nExample: !deleteEvent SundayFunday";
        }
        
        String name = args[0];
        
        if (!eventExists(name))
        {
            return "No event exists by that name.";
        }
        
        for (CommunityEvent ce : CommunityEvents)
        {
            if (ce.getEventName().equals(name))
            {
                CommunityEvents.remove(ce);
                return "Event removed.";
            }
            else
            {
                continue;
            }
        }
        
        return "Event not deleted.";
    }
}
