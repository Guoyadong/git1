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
 *         &lt;element name="outboundDeleteMainReturn" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "outboundDeleteMainReturn" })
@XmlRootElement(name = "outboundDeleteMainResponse")
public class OutboundDeleteMainResponse {

	protected int outboundDeleteMainReturn;

	/**
	 * Gets the value of the outboundDeleteMainReturn property.
	 * 
	 */
	public int getOutboundDeleteMainReturn() {
		return outboundDeleteMainReturn;
	}

	/**
	 * Sets the value of the outboundDeleteMainReturn property.
	 * 
	 */
	public void setOutboundDeleteMainReturn(int value) {
		this.outboundDeleteMainReturn = value;
	}

}
