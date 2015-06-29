package snail.certification.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	private String descr;
	
	@OneToMany(mappedBy="category")
	private Set<SubCategory> subCategories = new HashSet<>(0);

	public Category() {
	}

	public Category(String descr) {
		this.descr = descr;
	}

	public Category(String descr, Set<SubCategory> subCategories) {
		this.descr = descr;
		this.subCategories = subCategories;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Set<SubCategory> getSubCategories() {
		return this.subCategories;
	}

	public void setSubCategories(Set<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}
}
