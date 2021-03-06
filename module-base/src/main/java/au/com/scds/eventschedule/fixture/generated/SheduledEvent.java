//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.30 at 12:07:55 AM AEDT 
//


package au.com.scds.eventschedule.fixture.generated;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for SheduledEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SheduledEvent"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="waitList" type="{http://www.example.org/OneIdSchema}WaitList"/&gt;
 *         &lt;element name="booking" type="{http://www.example.org/OneIdSchema}Booking" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="attendance" type="{http://www.example.org/OneIdSchema}Attendance" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="eventFacilitator" type="{http://www.example.org/OneIdSchema}EventFacilitator" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SheduledEvent", propOrder = {
    "name",
    "date",
    "waitList",
    "booking",
    "attendance",
    "eventFacilitator"
})
public class SheduledEvent {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date date;
    @XmlElement(required = true)
    protected WaitList waitList;
    protected List<Booking> booking;
    protected List<Attendance> attendance;
    protected List<EventFacilitator> eventFacilitator;

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
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(Date value) {
        this.date = value;
    }

    /**
     * Gets the value of the waitList property.
     * 
     * @return
     *     possible object is
     *     {@link WaitList }
     *     
     */
    public WaitList getWaitList() {
        return waitList;
    }

    /**
     * Sets the value of the waitList property.
     * 
     * @param value
     *     allowed object is
     *     {@link WaitList }
     *     
     */
    public void setWaitList(WaitList value) {
        this.waitList = value;
    }

    /**
     * Gets the value of the booking property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the booking property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBooking().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Booking }
     * 
     * 
     */
    public List<Booking> getBooking() {
        if (booking == null) {
            booking = new ArrayList<Booking>();
        }
        return this.booking;
    }

    /**
     * Gets the value of the attendance property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attendance property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttendance().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Attendance }
     * 
     * 
     */
    public List<Attendance> getAttendance() {
        if (attendance == null) {
            attendance = new ArrayList<Attendance>();
        }
        return this.attendance;
    }

    /**
     * Gets the value of the eventFacilitator property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventFacilitator property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventFacilitator().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventFacilitator }
     * 
     * 
     */
    public List<EventFacilitator> getEventFacilitator() {
        if (eventFacilitator == null) {
            eventFacilitator = new ArrayList<EventFacilitator>();
        }
        return this.eventFacilitator;
    }

}
