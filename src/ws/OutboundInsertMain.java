package ws;

import java.math.BigDecimal;
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
 *         &lt;element name="bRdFlag" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cBatch" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cInvName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cWhCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="iQuantity" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="cInvStd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cDepCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cBusType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cMaker" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="iPrice" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="iUnitCost" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "index", "bRdFlag", "dDate", "cBatch",
		"cInvName", "cWhCode", "iQuantity", "cInvStd", "cCode", "cDepCode",
		"cBusType", "cMaker", "iPrice", "iUnitCost" })
@XmlRootElement(name = "outboundInsertMain")
public class OutboundInsertMain {

	protected int index;
	protected int bRdFlag;
	@XmlElement(required = true)
	protected String dDate;
	@XmlElement(required = true)
	protected String cBatch;
	@XmlElement(required = true)
	protected String cInvName;
	@XmlElement(required = true)
	protected String cWhCode;
	protected double iQuantity;
	@XmlElement(required = true)
	protected String cInvStd;
	@XmlElement(required = true)
	protected String cCode;
	@XmlElement(required = true)
	protected String cDepCode;
	@XmlElement(required = true)
	protected String cBusType;
	@XmlElement(required = true)
	protected String cMaker;
	@XmlElement(required = true)
	protected BigDecimal iPrice;
	protected double iUnitCost;

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
	 * Gets the value of the bRdFlag property.
	 * 
	 */
	public int getBRdFlag() {
		return bRdFlag;
	}

	/**
	 * Sets the value of the bRdFlag property.
	 * 
	 */
	public void setBRdFlag(int value) {
		this.bRdFlag = value;
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
	 * Gets the value of the cBatch property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCBatch() {
		return cBatch;
	}

	/**
	 * Sets the value of the cBatch property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCBatch(String value) {
		this.cBatch = value;
	}

	/**
	 * Gets the value of the cInvName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCInvName() {
		return cInvName;
	}

	/**
	 * Sets the value of the cInvName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCInvName(String value) {
		this.cInvName = value;
	}

	/**
	 * Gets the value of the cWhCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCWhCode() {
		return cWhCode;
	}

	/**
	 * Sets the value of the cWhCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCWhCode(String value) {
		this.cWhCode = value;
	}

	/**
	 * Gets the value of the iQuantity property.
	 * 
	 */
	public double getIQuantity() {
		return iQuantity;
	}

	/**
	 * Sets the value of the iQuantity property.
	 * 
	 */
	public void setIQuantity(double value) {
		this.iQuantity = value;
	}

	/**
	 * Gets the value of the cInvStd property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCInvStd() {
		return cInvStd;
	}

	/**
	 * Sets the value of the cInvStd property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCInvStd(String value) {
		this.cInvStd = value;
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
	 * Gets the value of the cDepCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCDepCode() {
		return cDepCode;
	}

	/**
	 * Sets the value of the cDepCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCDepCode(String value) {
		this.cDepCode = value;
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
	 * Gets the value of the cMaker property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCMaker() {
		return cMaker;
	}

	/**
	 * Sets the value of the cMaker property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCMaker(String value) {
		this.cMaker = value;
	}

	/**
	 * Gets the value of the iPrice property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getIPrice() {
		return iPrice;
	}

	/**
	 * Sets the value of the iPrice property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setIPrice(BigDecimal value) {
		this.iPrice = value;
	}

	/**
	 * Gets the value of the iUnitCost property.
	 * 
	 */
	public double getIUnitCost() {
		return iUnitCost;
	}

	/**
	 * Sets the value of the iUnitCost property.
	 * 
	 */
	public void setIUnitCost(double value) {
		this.iUnitCost = value;
	}

}
