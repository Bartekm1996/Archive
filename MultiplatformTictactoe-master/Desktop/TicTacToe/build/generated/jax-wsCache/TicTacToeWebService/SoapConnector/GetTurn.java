
package SoapConnector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getTurn complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getTurn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
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
@XmlType(name = "getTurn", propOrder = {
    "joiner"
})
public class GetTurn {

    protected String joiner;

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
