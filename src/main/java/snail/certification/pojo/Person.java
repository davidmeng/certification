package snail.certification.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import snail.certification.utils.Constants;

@Entity
@Table(name = "PERSON")
public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID")
	private Company company;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
	private Book book;
	private String name;
	private String personInfo;
	private String company_1;
	private Short gender;
	
	@OneToMany(mappedBy="person")
	private Set<PersonBook> personBooks = new HashSet<>(0);
	private boolean lockInfo;
	
	@OneToMany(mappedBy="person")
	private Set<Book> books = new HashSet<>(0);
	private String lendInfo;
	private String usedInfo;
	private Integer position;
	private Integer education;
	private Timestamp positionDate;
	private Timestamp educationDate;

	public boolean isLockInfo() {
		return this.lockInfo;
	}

	public void setLockInfo(boolean lockInfo) {
		this.lockInfo = lockInfo;
	}

	public Person() {
	}

	public Person(String name) {
		this.name = name;
	}

	public Person(Company company, String name, String personInfo,
			String company_1, Short gender, Set<PersonBook> personBooks) {
		this.company = company;
		this.name = name;
		this.personInfo = personInfo;
		this.company_1 = company_1;
		this.gender = gender;
		this.personBooks = personBooks;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonInfo() {
		return this.personInfo;
	}

	public void setPersonInfo(String personInfo) {
		this.personInfo = personInfo;
	}

	public String getCompany_1() {
		return this.company_1;
	}

	public void setCompany_1(String company_1) {
		this.company_1 = company_1;
	}

	public Short getGender() {
		return this.gender;
	}

	public void setGender(Short gender) {
		this.gender = gender;
	}

	public Set<PersonBook> getPersonBooks() {
		return this.personBooks;
	}

	public void setPersonBooks(Set<PersonBook> personBooks) {
		this.personBooks = personBooks;
	}

	public String getLendInfo() {
		return this.lendInfo;
	}

	public void setLendInfo(String lendInfo) {
		this.lendInfo = lendInfo;
	}

	public String getUsedInfo() {
		return this.usedInfo;
	}

	public void setUsedInfo(String usedInfo) {
		this.usedInfo = usedInfo;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Set<Book> getBooks() {
		return this.books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public Integer getPosition() {
		return position;
	}

	public String getPositionStr() {
		if (position == null) {
			return "";
		}
		if (position == 1)
			return "初级";
		else if (position == 2)
			return "中级";
		else if (position == 3)
			return "高级";
		else
			return "";
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getEducation() {
		return education;
	}

	public String getEducationStr() {
		if (education == null) {
			return "";
		}
		if (education == 1)
			return "初中";
		else if (education == 2)
			return "中专";
		else if (education == 3)
			return "高中";
		else if (education == 4)
			return "专科";
		else if (education == 5)
			return "本科";
		else if (education == 6)
			return "研究生";
		else
			return "";
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public Timestamp getPositionDate() {
		return positionDate;
	}

	public String getPositionDateStr() {
		return formatDate(positionDate);
	}

	public String getEducationDateStr() {
		return formatDate(educationDate);
	}

	public String formatDate(Timestamp date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (date.compareTo(Constants.DATE_FOR_NULL) == 0) {
			return "";
		}
		return sdf.format(date);
	}

	public void setPositionDate(Timestamp positionDate) {
		this.positionDate = positionDate;
	}

	public Timestamp getEducationDate() {
		return educationDate;
	}

	public void setEducationDate(Timestamp educationDate) {
		this.educationDate = educationDate;
	}
}
