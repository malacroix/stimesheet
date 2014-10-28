/**
 * 
 */
package ca.lc.stimesheet.marshall;

import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import ca.lc.stimesheet.marshall.exception.MarshallingException;
import ca.lc.stimesheet.model.event.Event;

/**
 * @author Marc-Andre Lacroix
 *
 */
@Component
public class ModelMarshallerImpl implements ModelMarshaller {

    private Unmarshaller jaxbUnmarshaller;
    
    @PostConstruct
    public void init() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Event.class);
        jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        // The Event XML does not have any namespaces, so disable validation
        jaxbUnmarshaller.setSchema(null);
    }

    @Override
    public Event unmarshallEvent(InputStream eventStream) throws MarshallingException {
        try {
            return (Event) jaxbUnmarshaller.unmarshal(eventStream);
        } catch (JAXBException jaxbe) {
            throw new MarshallingException("Could not unmarshall the event", jaxbe);
        }
    }
}
