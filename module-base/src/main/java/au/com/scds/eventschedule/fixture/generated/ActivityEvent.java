//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.02.13 at 09:40:06 PM AEDT 
//


package au.com.scds.eventschedule.fixture.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActivityEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActivityEvent"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.example.org/OneIdSchema}ScheduledEvent"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="participation" type="{http://www.example.org/OneIdSchema}Participation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="attendance" type="{http://www.example.org/OneIdSchema}Attendance" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActivityEvent", propOrder = {
    "participation",
    "attendance"
})
@XmlSeeAlso({
    RecurringActivityEvent.class,
    PartentedActivityEvent.class
})
public class ActivityEvent
    extends ScheduledEvent
{

    protected List<Participation> participation;
    protected List<Attendance> attendance;

    /**
     * Gets the value of the participation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the participation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParticipation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Participation }
     * 
     * 
     */
    public List<Participation> getParticipation() {
        if (participation == null) {
            participation = new ArrayList<Participation>();
        }
        return this.participation;
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

}
