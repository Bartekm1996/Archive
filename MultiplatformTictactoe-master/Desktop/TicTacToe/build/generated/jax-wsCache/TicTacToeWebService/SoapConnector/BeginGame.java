
package SoapConnector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for beginGame complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="beginGame">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="u" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="joiner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "beginGame", propOrder = {
    "u",
    "joiner"
})
public class BeginGame {

    protected String u;
    protected String joiner;

    /**
     * Gets the value of the u property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getU() {
        return u;
    }

    /**
     * Sets the value of the u property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setU(String value) {
        this.u = value;
    }

    /**
     * Gets the value of the joiner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJoiner() {
        return joiner;
    }

    /**
     * Sets the value of the joiner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJoiner(String value) {
        this.joiner = value;
    }

}
