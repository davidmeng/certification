package snail.certification.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
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

@Entity
@Table(name = "Book")
public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
	private BookType bookType;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID")
	private Person person;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "SUB_CATEGORY_ID")
	private SubCategory subCategory;
	
	
	private String number;
	private Timestamp issueDate;
	private Timestamp effictiveDate;
	private Timestamp lastExamDate;
	private String remark;
	private Short isNew;
	private Integer sequance;
	private String usedInfo;
	private String lendInfo;
	private Short lockInfo;
	
	@OneToMany(mappedBy="person")
	private Set<PersonBook> personBooks = new HashSet<>();

	public Book() {
	}

	public Book(BookType bookType, String number, Timestamp issueDate,
			String usedInfo, String lendInfo, Short lockInfo) {
		this.bookType = bookType;
		this.number = number;
		this.issueDate = issueDate;
		this.usedInfo = usedInfo;
		this.lendInfo = lendInfo;
		this.lockInfo = lockInfo;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BookType getBookType() {
		return this.bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	public SubCategory getSubCategory() {
		return this.subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Timestamp getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Timestamp issueDate) {
		this.issueDate = issueDate;
	}

	public Timestamp getEffictiveDate() {
		return this.effictiveDate;
	}

	public void setEffictiveDate(Timestamp effictiveDate) {
		this.effictiveDate = effictiveDate;
	}

	public Timestamp getLastExamDate() {
		return this.lastExamDate;
	}

	public void setLastExamDate(Timestamp lastExamDate) {
		this.lastExamDate = lastExamDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Short getIsNew() {
		return this.isNew;
	}

	public void setIsNew(Short isNew) {
		this.isNew = isNew;
	}

	public Integer getSequance() {
		return this.sequance;
	}

	public void setSequance(Integer sequance) {
		this.sequance = sequance;
	}

	public String getUsedInfo() {
		return this.usedInfo;
	}

	public void setUsedInfo(String usedInfo) {
		this.usedInfo = usedInfo;
	}

	public String getLendInfo() {
		return this.lendInfo;
	}

	public void setLendInfo(String lendInfo) {
		this.lendInfo = lendInfo;
	}

	public Short getLockInfo() {
		return this.lockInfo;
	}

	public void setLockInfo(Short lockInfo) {
		this.lockInfo = lockInfo;
	}

	public Set<PersonBook> getPersonBooks() {
		return this.personBooks;
	}

	public void setPersonBooks(Set<PersonBook> personBooks) {
		this.personBooks = personBooks;
	}

	public boolean isLock() {
		if (this.lockInfo == null)
			return false;
		return this.lockInfo.intValue() == 1;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
