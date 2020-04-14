


package org.apache.naming;

import java.util.Iterator;

import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

/**
 * Naming enumeration implementation.
 *
 * @author Remy Maucherat
 */
public class NamingContextEnumeration
    implements NamingEnumeration<NameClassPair> {


    // ----------------------------------------------------------- Constructors


    public NamingContextEnumeration(Iterator<NamingEntry> entries) {
        iterator = entries;
    }


    // -------------------------------------------------------------- Variables


    /**
     * Underlying enumeration.
     */
    protected final Iterator<NamingEntry> iterator;


    // --------------------------------------------------------- Public Methods


    /**
     * Retrieves the next element in the enumeration.
     */
    @Override
    public NameClassPair next()
        throws NamingException {
        return nextElement();
    }


    /**
     * Determines whether there are any more elements in the enumeration.
     */
    @Override
    public boolean hasMore()
        throws NamingException {
        return iterator.hasNext();
    }


    /**
     * Closes this enumeration.
     */
    @Override
    public void close()
        throws NamingException {
    }


    @Override
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }


    @Override
    public NameClassPair nextElement() {
        NamingEntry entry = iterator.next();
        return new NameClassPair(entry.name, entry.value.getClass().getName());
    }


}

