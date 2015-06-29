package snail.certification.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import snail.certification.pojo.Book;
import snail.certification.pojo.Person;

public class PersonAssignVo implements Comparable<PersonAssignVo> {
	private Person person;
	private Map<Integer, AssignVo> map;
	private int subCategoryId;
	private String subCategoryDescr;
	private List<AssignBookVo> bookList = new ArrayList<>();
	private int size = 0;

	private double rate = 0.0D;

	public PersonAssignVo(Person person, Map<Integer, AssignVo> map) {
		this.person = person;
		this.map = map;
	}

	private void initRate() {
		for (Iterator<Book> itor = this.person.getBooks().iterator(); itor
				.hasNext();) {
			Book book = (Book) itor.next();
			AssignVo vo = (AssignVo) this.map
					.get(book.getSubCategory().getId());
			if (vo != null) {
				AssignBookVo bookVo = new AssignBookVo(book, Integer.valueOf(vo
						.getRemainQuantity()));
				this.bookList.add(bookVo);
				if (book.getSubCategory().getId().intValue() != this.subCategoryId)
					this.rate += ((AssignVo) this.map.get(book.getSubCategory()
							.getId())).getRate();
				else {
					bookVo.setSelect(true);
				}
			}

		}

		Collections.sort(this.bookList);
		this.size = this.bookList.size();
	}

	public double getRate() {
		return this.rate;
	}

	public int compareTo(PersonAssignVo o) {
		PersonAssignVo a = (PersonAssignVo) o;
		return new Double(getRate()).compareTo(new Double(a.getRate()));
	}

	public Map<Integer, AssignVo> getMap() {
		return this.map;
	}

	public void setMap(Map<Integer, AssignVo> map) {
		this.map = map;
	}

	public int getSubCategoryId() {
		return this.subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
		initRate();
	}

	public String getSubCategoryDescr() {
		return this.subCategoryDescr;
	}

	public void setSubCategoryDescr(String subCategoryDescr) {
		this.subCategoryDescr = subCategoryDescr;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<AssignBookVo> getBookList() {
		return this.bookList;
	}

	public void setBookList(List<AssignBookVo> bookList) {
		this.bookList = bookList;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
