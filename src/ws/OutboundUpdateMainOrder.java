package ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="index" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cBusType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cVenName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cMemo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cDepName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "index", "cCode", "dDate", "cBusType",
		"cVenName", "cMemo", "cDepName" })
@XmlRootElement(name = "outboundUpdateMainOrder")
public class OutboundUpdateMainOrder {

	protected int index;
	@XmlElement(required = true)
	protected String cCode;
	@XmlElement(required = true)
	protected String dDate;
	@XmlElement(required = true)
	protected String cBusType;
	@XmlElement(required = true)
	protected String cVenName;
	@XmlElement(required = true)
	protected String cMemo;
	@XmlElement(required = true)
	protected String cDepName;

	/**
	 * Gets the value of the index property.
	 * 
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the value of the index property.
	 * 
	 */
	public void setIndex(int value) {
		this.index = value;
	}

	/**
	 * Gets the value of the cCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCCode() {
		return cCode;
	}

	/**
	 * Sets the value of the cCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCCode(String value) {
		this.cCode = value;
	}

	/**
	 * Gets the value of the dDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDDate() {
		return dDate;
	}

	/**
	 * Sets the value of the dDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDDate(String value) {
		this.dDate = value;
	}

	/**
	 * Gets the value of the cBusType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCBusType() {
		return cBusType;
	}

	/**
	 * Sets the value of the cBusType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCBusType(String value) {
		this.cBusType = value;
	}

	/**
	 * Gets the value of the cVenName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCVenName() {
		return cVenName;
	}

	/**
	 * Sets the value of the cVenName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCVenName(String value) {
		this.cVenName = value;
	}

	/**
	 * Gets the value of the cMemo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCMemo() {
		return cMemo;
	}

	/**
	 * Sets the value of the cMemo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCMemo(String value) {
		this.cMemo = value;
	}

	/**
	 * Gets the value of the cDepName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCDepName() {
		return cDepName;
	}

	/**
	 * Sets the value of the cDepName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCDepName(String value) {
		this.cDepName = value;
	}

}
