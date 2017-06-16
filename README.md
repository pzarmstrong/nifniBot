# nifniBot
Discord bot written in Java using JDA - for the purpose of planning Discord community events

# JDA
Java Discord API - https://github.com/DV8FromTheWorld/JDA/

## Current Stage of Development
The bot can currently: 
* respond to users in a Discord text channel
* create upcoming events with a formatted date and event name e.g. !addEvent PlayGame 17:00-17/6/17
* modify upcoming events with the same format as creating, with a different command i.e. !modifyEvent
  at the moment this is only for changing the time/date of the event
* delete upcoming events
* list upcoming events
* automatically back-up upcoming events list to an XML file
* reload upcoming events list from the back-up file
