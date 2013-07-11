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
 *         &lt;element name="bRdFlag" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="iQuantity" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="inAutoID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="canOutQuantity" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "bRdFlag", "iQuantity", "inAutoID",
		"canOutQuantity" })
@XmlRootElement(name = "test")
public class Test {

	protected int bRdFlag;
	protected double iQuantity;
	protected int inAutoID;
	protected double canOutQuantity;

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
	 * Gets the value of the inAutoID property.
	 * 
	 */
	public int getInAutoID() {
		return inAutoID;
	}

	/**
	 * Sets the value of the inAutoID property.
	 * 
	 */
	public void setInAutoID(int value) {
		this.inAutoID = value;
	}

	/**
	 * Gets the value of the canOutQuantity property.
	 * 
	 */
	public double getCanOutQuantity() {
		return canOutQuantity;
	}

	/**
	 * Sets the value of the canOutQuantity property.
	 * 
	 */
	public void setCanOutQuantity(double value) {
		this.canOutQuantity = value;
	}

}
