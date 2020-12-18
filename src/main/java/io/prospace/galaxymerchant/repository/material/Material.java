package io.prospace.galaxymerchant.repository.material;
/**
 * @author azzuarezh
 *
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Material {

	private @Id @GeneratedValue Long id;
	private String name;
	private String description;
	private double price;
	public String priceUnit ="Credits";
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Material() {
		// TODO Auto-generated constructor stub
	}

	public Material(String name, String description, double price, String priceUnit) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.priceUnit = priceUnit;
	}
	
	public Material(String name, String description, double price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}

}
