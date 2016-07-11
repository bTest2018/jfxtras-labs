package jfxtras.labs.icalendarfx;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javafx.util.Callback;

/**
 * Base class for parent calendar components.  Uses an {@link Orderer} to keep track of child object order.  Ordering
 * requires registering {@link Orderer} listeners to child properties.
 * 
 * @author David Bal
 */
public abstract class VParentBase implements VParent
{
    /*
     * SORT ORDER FOR CHILD ELEMENTS
     */
    final private Orderer orderer = new OrdererBase(this);
    public Orderer orderer() { return orderer; }
    
    /** Strategy to copy subclass's children */
    protected Callback<VChild, Void> copyChildCallback()
    {
        throw new RuntimeException("Copy child callback not overridden in subclass " + this.getClass());
    };

    /** returns read-only collection of child elements following the sort order controlled by {@link Orderer} */
    @Override
    public Collection<VChild> childrenUnmodifiable()
    {
        return Collections.unmodifiableList(
                orderer().elementSortOrderMap().entrySet().stream()
                .sorted((Comparator<? super Entry<VChild, Integer>>) (e1, e2) -> e1.getValue().compareTo(e2.getValue()))
                .map(e -> e.getKey())
                .collect(Collectors.toList())
                );
    }
    
    /** Copy parameters, properties, and subcomponents from source into this component,
    * essentially making a copy of source 
    * 
    * Note: this method only overwrites properties found in source.  If there are properties in
    * this component that are not present in source then those will remain unchanged.
    * */
    @Override
    public void copyChildrenFrom(VParent source)
    {
         source.childrenUnmodifiable().forEach((e) -> copyChildCallback().call(e));
    }
    
    @Override
    public List<String> errors()
    {
        return childrenUnmodifiable().stream()
                .flatMap(c -> c.errors().stream())
                .collect(Collectors.toList());
    }
}
