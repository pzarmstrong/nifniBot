package communityEvents;

import java.util.Date;

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
    
    public CommunityEvent(Date dateOfEvent, String name, String author)
    {
        this.dateOfEvent = dateOfEvent;
        this.dateCreated = new Date();
        this.eventName = name;
        this.eventAuthor = author;
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
    
    public String getEventAuthor()
    {
        return eventAuthor;
    }
}
