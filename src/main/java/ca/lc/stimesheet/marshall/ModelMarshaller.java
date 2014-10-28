/**
 * 
 */
package ca.lc.stimesheet.marshall;

import java.io.InputStream;

import ca.lc.stimesheet.marshall.exception.MarshallingException;
import ca.lc.stimesheet.model.event.Event;

/**
 * @author Marc-Andre Lacroix
 *
 */
public interface ModelMarshaller {

    /**
     * Unmarshals an {@link Event} XML from an {@link InputStream}
     * @param eventStream
     * @return the unmarshaled Event
     * @throws MarshallingException if any error happens during unmarshalling
     */
    Event unmarshallEvent(InputStream eventStream) throws MarshallingException;

}
