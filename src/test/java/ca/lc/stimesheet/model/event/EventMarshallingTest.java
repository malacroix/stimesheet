/**
 * 
 */
package ca.lc.stimesheet.model.event;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.lc.stimesheet.marshall.ModelMarshaller;

/**
 * @author Marc-Andre Lacroix
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:META-INF/context/applicationContext-stimesheet-test.xml" })
public class EventMarshallingTest {
    
    @Autowired
    private ModelMarshaller modelMarshaller;

    private Marshaller jaxbMarshaller;
    
    @Before
    public void setup() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Event.class, EventResult.class);
        jaxbMarshaller = jaxbContext.createMarshaller();
    }

    @Test
    public void testEventUnMarshallingDummyCreate() throws Exception {

        Event event = modelMarshaller.unmarshallEvent(Event.class.getResourceAsStream("dummyOrder.xml"));
        
        Assert.assertNotNull("The unmarshalled Event object should not be null", event);
    }

    @Test
    public void testEventResultMarshallingSuccess() throws Exception {
        // We let the Spring MVC mechanism convert the EventResult to XML, but here is simply
        // to validate the XML output it should produce using normal marshalling

        EventResult result = new EventResult();
        result.setSuccess(true);

        jaxbMarshaller.marshal(result, System.out);

        //Assert.assertNotNull("The unmarshalled Event object should not be null", event);
    }
}
