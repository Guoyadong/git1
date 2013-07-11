package ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="outboundUpdateMainReturn" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "outboundUpdateMainReturn" })
@XmlRootElement(name = "outboundUpdateMainResponse")
public class OutboundUpdateMainResponse {

	protected int outboundUpdateMainReturn;

	/**
	 * Gets the value of the outboundUpdateMainReturn property.
	 * 
	 */
	public int getOutboundUpdateMainReturn() {
		return outboundUpdateMainReturn;
	}

	/**
	 * Sets the value of the outboundUpdateMainReturn property.
	 * 
	 */
	public void setOutboundUpdateMainReturn(int value) {
		this.outboundUpdateMainReturn = value;
	}

}
