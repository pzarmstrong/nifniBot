package communityEvents;

import java.time.LocalDateTime;
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
    
    private LocalDateTime dateOfEvent;
    private String eventName;
    private LocalDateTime dateCreated;
    private String eventAuthor;
    
    public CommunityEvent(LocalDateTime dateOfEvent, String name, String author)
    {
        this.dateOfEvent = dateOfEvent;
        this.dateCreated = LocalDateTime.now();
        this.eventName = name;
        this.eventAuthor = author;
    }
    
    public LocalDateTime getDateOfEvent()
    {
        return dateOfEvent;
    }
    
    public LocalDateTime getDateCreated()
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
