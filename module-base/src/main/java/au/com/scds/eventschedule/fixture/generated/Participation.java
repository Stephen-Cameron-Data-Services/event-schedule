//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.29 at 06:39:32 PM AEST 
//


package au.com.scds.eventschedule.fixture.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Participation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Participation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.example.org/EventSchedule}Booking"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="attendee" type="{http://www.example.org/EventSchedule}Attendee"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Participation", propOrder = {
    "attendee"
})
public class Participation
    extends Booking
{

    @XmlElement(required = true)
    protected Attendee attendee;

    /**
     * Gets the value of the attendee property.
     * 
     * @return
     *     possible object is
     *     {@link Attendee }
     *     
     */
    public Attendee getAttendee() {
        return attendee;
    }

    /**
     * Sets the value of the attendee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Attendee }
     *     
     */
    public void setAttendee(Attendee value) {
        this.attendee = value;
    }

}
