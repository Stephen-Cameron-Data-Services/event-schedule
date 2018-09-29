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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Booking complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Booking"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="booker" type="{http://www.example.org/EventSchedule}Booker"/&gt;
 *         &lt;element name="bookable" type="{http://www.example.org/EventSchedule}Bookable" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Booking", propOrder = {
    "booker",
    "bookable"
})
@XmlSeeAlso({
    Participation.class
})
public class Booking {

    @XmlElement(required = true)
    protected Booker booker;
    protected Bookable bookable;

    /**
     * Gets the value of the booker property.
     * 
     * @return
     *     possible object is
     *     {@link Booker }
     *     
     */
    public Booker getBooker() {
        return booker;
    }

    /**
     * Sets the value of the booker property.
     * 
     * @param value
     *     allowed object is
     *     {@link Booker }
     *     
     */
    public void setBooker(Booker value) {
        this.booker = value;
    }

    /**
     * Gets the value of the bookable property.
     * 
     * @return
     *     possible object is
     *     {@link Bookable }
     *     
     */
    public Bookable getBookable() {
        return bookable;
    }

    /**
     * Sets the value of the bookable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bookable }
     *     
     */
    public void setBookable(Bookable value) {
        this.bookable = value;
    }

}
