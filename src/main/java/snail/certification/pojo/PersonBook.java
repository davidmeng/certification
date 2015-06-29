package snail.certification.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON_BOOK")
public class PersonBook implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID")
	private Person person;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
	private Book book;
	
	private String useInfo;
	private String isLend;
	private String lendInfo;

	public PersonBook() {
	}

	public PersonBook(Person person, Book book) {
		this.person = person;
		this.book = book;
	}

	public PersonBook(Person person, Book book, String useInfo, String isLend,
			String lendInfo) {
		this.person = person;
		this.book = book;
		this.useInfo = useInfo;
		this.isLend = isLend;
		this.lendInfo = lendInfo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getUseInfo() {
		return this.useInfo;
	}

	public void setUseInfo(String useInfo) {
		this.useInfo = useInfo;
	}

	public String getIsLend() {
		return this.isLend;
	}

	public void setIsLend(String isLend) {
		this.isLend = isLend;
	}

	public String getLendInfo() {
		return this.lendInfo;
	}

	public void setLendInfo(String lendInfo) {
		this.lendInfo = lendInfo;
	}
}
