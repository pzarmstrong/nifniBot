package communityEvents;

import java.util.Date;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommunityEvent
{
    /**
     * Date/Time of Event
     * Event Name
     * Event Description
     * 
     * Date/Time of Creation
     * Event Author
     */
    
    private Date dateOfEvent;
    private String eventName;
    private Date dateCreated;
    private String eventAuthor;
    
    public CommunityEvent(Date dateOfEvent, String name)
    {
        this.dateOfEvent = dateOfEvent;
        this.dateCreated = new Date();
        this.eventName = name;
    }
    
    public Date getDateOfEvent()
    {
        return dateOfEvent;
    }
    
    public Date getDateCreated()
    {
        return dateCreated;
    }

    public String getEventName()
    {
        return eventName;
    }
}
