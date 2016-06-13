package jfxtras.labs.icalendarfx.components;

import jfxtras.labs.icalendarfx.components.revisors.Revisable;
import jfxtras.labs.icalendarfx.components.revisors.StandardTimeReviser;

public class StandardTime extends StandardOrDaylightBase<StandardTime>
{
    @Override
    public CalendarElementType componentType()
    {
        return CalendarElementType.STANDARD_TIME;
    }
    
    /*
     * CONSTRUCTORS
     */
    public StandardTime() { }
    
    public StandardTime(String contentLines)
    {
        super(contentLines);
    }

    public StandardTime(StandardTime source)
    {
        super(source);
    }
    
    @Override
    public Revisable<StandardTimeReviser, StandardTime> newRevisor() { return new StandardTimeReviser(this); }

    /** Parse content lines into calendar component object */
    public static StandardTime parse(String contentLines)
    {
        StandardTime component = new StandardTime();
        component.parseContent(contentLines);
        return component;
    }
}
