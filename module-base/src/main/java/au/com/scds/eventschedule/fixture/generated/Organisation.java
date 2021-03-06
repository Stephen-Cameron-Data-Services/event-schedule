//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.30 at 02:46:12 PM AEST 
//


package au.com.scds.eventschedule.fixture.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Organisation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Organisation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="sheduledEvent" type="{http://www.example.org/EventSchedule}ScheduledEvent"/&gt;
 *         &lt;element name="eventFacilitator" type="{http://www.example.org/EventSchedule}EventFacilitator"/&gt;
 *         &lt;element name="contactor" type="{http://www.example.org/EventSchedule}Contactor"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Organisation", propOrder = {
    "name",
    "sheduledEvent",
    "eventFacilitator",
    "contactor"
})
public class Organisation {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected ScheduledEvent sheduledEvent;
    @XmlElement(required = true)
    protected EventFacilitator eventFacilitator;
    @XmlElement(required = true)
    protected Contactor contactor;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the sheduledEvent property.
     * 
     * @return
     *     possible object is
     *     {@link ScheduledEvent }
     *     
     */
    public ScheduledEvent getSheduledEvent() {
        return sheduledEvent;
    }

    /**
     * Sets the value of the sheduledEvent property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduledEvent }
     *     
     */
    public void setSheduledEvent(ScheduledEvent value) {
        this.sheduledEvent = value;
    }

    /**
     * Gets the value of the eventFacilitator property.
     * 
     * @return
     *     possible object is
     *     {@link EventFacilitator }
     *     
     */
    public EventFacilitator getEventFacilitator() {
        return eventFacilitator;
    }

    /**
     * Sets the value of the eventFacilitator property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventFacilitator }
     *     
     */
    public void setEventFacilitator(EventFacilitator value) {
        this.eventFacilitator = value;
    }

    /**
     * Gets the value of the contactor property.
     * 
     * @return
     *     possible object is
     *     {@link Contactor }
     *     
     */
    public Contactor getContactor() {
        return contactor;
    }

    /**
     * Sets the value of the contactor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Contactor }
     *     
     */
    public void setContactor(Contactor value) {
        this.contactor = value;
    }

}
