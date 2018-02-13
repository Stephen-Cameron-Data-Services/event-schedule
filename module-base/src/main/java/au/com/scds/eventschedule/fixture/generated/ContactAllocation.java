//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.02.13 at 09:40:06 PM AEDT 
//


package au.com.scds.eventschedule.fixture.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ContactAllocation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContactAllocation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="contactee" type="{http://www.example.org/OneIdSchema}Contactee"/&gt;
 *         &lt;element name="contactor" type="{http://www.example.org/OneIdSchema}Contactor"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContactAllocation", propOrder = {
    "contactee",
    "contactor"
})
public class ContactAllocation {

    @XmlElement(required = true)
    protected Contactee contactee;
    @XmlElement(required = true)
    protected Contactor contactor;

    /**
     * Gets the value of the contactee property.
     * 
     * @return
     *     possible object is
     *     {@link Contactee }
     *     
     */
    public Contactee getContactee() {
        return contactee;
    }

    /**
     * Sets the value of the contactee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Contactee }
     *     
     */
    public void setContactee(Contactee value) {
        this.contactee = value;
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
