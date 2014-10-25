/**
 * 
 */
package ca.lc.stimesheet.model.event;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Marc-Andre Lacroix
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:META-INF/context/applicationContext-stimesheet-*.xml" })
public class EventMarshallingTest {
    
    private Unmarshaller jaxbUnmarshaller;
    private Marshaller jaxbMarshaller;
    
    @Before
    public void setup() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Event.class, EventResult.class);
        jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        jaxbMarshaller = jaxbContext.createMarshaller();
    }

    @Test
    public void testEventUnMarshallingDummyCreate() throws Exception {
        Event event = (Event) jaxbUnmarshaller.unmarshal(Event.class.getResourceAsStream("dummyOrder.xml"));
        Assert.assertNotNull("The unmarshalled Event object should not be null", event);
    }
    
    @Test
    public void testEventResultMarshallingSuccess() throws Exception {

        EventResult result = new EventResult();
        result.setSuccess(true);

        jaxbMarshaller.marshal(result, System.out);

        //Assert.assertNotNull("The unmarshalled Event object should not be null", event);
    }
}
