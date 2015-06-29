package snail.certification.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import snail.certification.dao.BookDao;
import snail.certification.dao.BookTypeDao;
import snail.certification.dao.CategoryDao;
import snail.certification.dao.CompanyDao;
import snail.certification.dao.PersonBookDao;
import snail.certification.dao.PersonDao;
import snail.certification.dao.SubCategoryDao;
import snail.certification.dao.util.QueryPage;
import snail.certification.exception.ApplicateionException;
import snail.certification.pojo.Book;
import snail.certification.pojo.BookType;
import snail.certification.pojo.Category;
import snail.certification.pojo.Company;
import snail.certification.pojo.Person;
import snail.certification.pojo.SubCategory;
import snail.certification.utils.NameUtils;
import snail.certification.utils.Utils;
import snail.certification.vo.AssignVo;
import snail.certification.vo.PersonAssignVo;
import snail.certification.vo.SubCategoryStatisticsVo;

public class CertificateBiz {
	
	
	Short lock = new Short((short) 1);
	Short unlock = new Short((short) 2);

	Short otherType = new Short((short) 1);
	Short projectType = new Short((short) 2);
	
	BookDao bookDao;
	CategoryDao categoryDao;
	SubCategoryDao subCategoryDao;
	BookTypeDao bookTypeDao;
	CompanyDao companyDao;
	PersonDao personDao;
	PersonBookDao personBookDao;
	
	
	//private Person prePerson;
	BookType t1 = new BookType();
	BookType t2 = new BookType();

	Map<Integer, SubCategory> subCategoryMap = null;

	public void lockCertificate(int id) {
		Book c = bookDao.getById(new Integer(id));

		if (c.getBookType().getId().intValue() == projectType.intValue()) {
			Person p = c.getPerson();
			p.setBook(c);
			personDao.save(p);
		}
		c.setLockInfo(lock);
		bookDao.save(c);
	}

	public void unlockCertificate(int id) {
		Book c = bookDao.getById(new Integer(id));
		c.setLockInfo(unlock);

		if (c.getBookType().getId().intValue() == projectType.intValue()) {
			Person p = c.getPerson();
			p.setBook(null);
			personDao.save(p);
		}

		bookDao.save(c);
	}

	public void saveCertificate(Book c) {
		bookDao.save(c);
	}

	public Book getCertificateById(Integer id) {
		return bookDao.getById(id);
	}

	public List<Company> getAllCompanyList() {
		return companyDao.getAll();
	}

	public CertificateBiz() {
		t1.setId(new Integer(1));
		t2.setId(new Integer(2));
	}

	public List<SubCategory> getAllSubCategory() {
		if (subCategoryMap == null) {
			subCategoryMap = new HashMap<Integer, SubCategory>();
			List<SubCategory> list = subCategoryDao.getAll();
			for (Iterator<SubCategory> itor = list.iterator(); itor.hasNext();) {
				SubCategory sc = (SubCategory) itor.next();
				subCategoryMap.put(sc.getId(), sc);
			}
		}

		return new ArrayList<SubCategory>(subCategoryMap.values());
	}

	public List<Person> getAllPerson() {
		return personDao.getAll();
	}

	public List<Person> getPersonByPage(QueryPage page) {
		return personDao.getByPage(page);
	}
/*
	public void initData(int subCategoryId, FormFile workFile) throws Exception {
		String seq = null;

		int i = 2;
		try {
			Workbook wb = WorkbookFactory.create(workFile.getInputStream());
			Sheet sheet = wb.getSheetAt(0);

			Row r = null;
			while (true) {
				r = sheet.getRow(i++);
				System.out.println("error occurs at row :" + i);
				if ((r == null) || (r.getCell(6) == null)) {
					break;
				}
				String number = r.getCell(6).getStringCellValue();
				if ((number != null) && (!number.trim().equals(""))) {
					if (Utils.isNotNull(r.getCell(0).getStringCellValue())) {
						seq = r.getCell(0).getStringCellValue().trim();
					}
					String name = r.getCell(1).getStringCellValue();
					String id = r.getCell(3).getStringCellValue();
					String gender = r.getCell(2).getStringCellValue();
					String work = r.getCell(4).getStringCellValue();

					String issueStr = r.getCell(7).getStringCellValue();

					Person person = new Person();

					Company c = getComanyByName(work);
					Person p;
					if ((name != null) && (!name.equals(""))) {
						person.setCompany(c);
						person.setName(name);
						person.setPersonInfo(id);
						person.setGender(getGender(gender));

						p = getPerson(person);
						prePerson = p;
					} else {
						p = prePerson;
					}

					SubCategory sc = new SubCategory();
					sc.setId(new Integer(subCategoryId));

					Book certificate = new Book();
					certificate.setSequance(new Integer(seq));
					certificate.setIssueDate(new Timestamp(getDate(issueStr)
							.getTime()));
					certificate.setNumber(number);
					certificate.setSubCategory(sc);
					certificate.setBookType(getCertificateType(number));

					bookDao.save(certificate);

					PersonBook pc = new PersonBook();
					pc.setBook(certificate);
					pc.setPerson(p);

					personBookDao.save(pc);
				}
			}
		} catch (InvalidFormatException e) {
			System.out.println("error occurs at row :" + i);
		} catch (FileNotFoundException e) {
			System.out.println("error occurs at row :" + i);
		} catch (IOException e) {
			System.out.println("error occurs at row :" + i);
		}
	}*/

	public void test() throws Exception {
		Company c = new Company();
		c.setName("testing");
		companyDao.save(c);

		SubCategory sc = new SubCategory();
		sc.setDescr("test");
		Category ca = new Category();
		ca.setId(new Integer(9));

		subCategoryDao.save(sc);

		if (c != null)
			throw new Exception("testing");
	}

	public Company getComanyByName(String name) throws Exception {
		if (Utils.isNull(name)) {
			name = "天津公司";
		}

		Company c = companyDao.getByNameAndId(name);

		if (c == null) {
			c = new Company();
			c.setName(name);
			companyDao.save(c);
		}

		return c;
	}

	public void scramblePersonInfo() {
		List<Person> personList = personDao.getAll();
		for (Person p : personList) {
			p.setName(NameUtils.getName());
			p.setPersonInfo(p.getPersonInfo().substring(0, 10)
					+ NameUtils.getPersonId());
			personDao.save(p);
		}
	}

	public Person getPerson(Person person) throws Exception {
		Person p = personDao.getByNameAndId(person.getPersonInfo());

		if (p == null) {
			personDao.save(person);
			return person;
		}
		return p;
	}
/*
	private Short getGender(String g) {
		if ((g != null) && (g.equals("男"))) {
			return new Short((short) 1);
		}
		return new Short((short) 0);
	}

	private Date getDate(String date) throws ParseException {
		if ((date == null) || (date.trim().equals(""))) {
			return new Date();
		}

		String[] dateArr = date.split("\\.");
		if (dateArr.length == 2) {
			dateArr[2] = "01";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(dateArr[0]);
		sb.append(dateString(dateArr[1]));
		sb.append(dateString(dateArr[2]));

		String dateStr = sb.toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		return sdf.parse(dateStr);
	}*/

	public int savePerson(Person person, String companyName, Book b)
			throws Exception {
		Company c = getComanyByName(companyName);

		Person p = personDao.getByNameAndId(person.getPersonInfo());
		if (p != null)
			person.setId(p.getId());
		else {
			p = new Person();
		}
		p.setCompany(c);
		p.setName(person.getName());
		p.setPersonInfo(person.getPersonInfo());
		p.setEducation(person.getEducation());
		p.setEducationDate(person.getEducationDate());
		p.setPosition(person.getPosition());
		p.setPositionDate(person.getPositionDate());

		personDao.save(p);

		if (b != null) {
			b.setPerson(p);
			b.setBookType(getCertificateType(b.getNumber()));

			bookDao.save(b);
		}
		return p.getId().intValue();
	}
/*
	private String dateString(String d) {
		if (d.length() == 1) {
			return "0" + d;
		}
		return d;
	}*/

	public void deleteBook(Book book) {
		bookDao.delete(book);
	}

	private BookType getCertificateType(String c) {
		if (c.indexOf("项") > -1) {
			return t2;
		}
		return t1;
	}

	public List<Person> getPersonListBy(String name, String personInfo, String number,
			Integer categoryId, Integer subCategoryId, Integer education,
			Integer position, String educationStartDateStr,
			String educationEndDateStr, String positionStartDateStr,
			String positionEndDateStr, String companyName, Short typeId,
			String lendInfo, String usedInfo) {
		/*return personDao.getPersonListBy(name, personInfo, number,
				categoryId, subCategoryId, education, position,
				educationStartDateStr, educationEndDateStr,
				positionStartDateStr, positionEndDateStr, companyName, typeId,
				lendInfo, usedInfo);*/
		
		return new ArrayList<>();
	}

	public List<SubCategoryStatisticsVo> getStatisticsVo(Short typeId) {
		return subCategoryDao.getStatList(typeId.shortValue());
	}

	public List<PersonAssignVo> getAssignPersonList(String companyName,
			int[] subCategoryIds, int[] quantitys) throws ApplicateionException {
		/*getAllSubCategory();
		List<SubCategoryStatisticsVo> voList = getStatisticsVo(Short
				.valueOf((short) 2));
		Map<Integer,SubCategoryStatisticsVo> map = new HashMap<>();
		Map<Integer,AssignVo> assignVoMap = new HashMap<>();

		for (SubCategoryStatisticsVo vo : voList) {
			map.put(Integer.valueOf(vo.getSubCategoryId()), vo);
		}

		int length = quantitys.length;
		List<AssignVo> assignVoList = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			AssignVo vo = new AssignVo();
			vo.setRemainQuantity(((SubCategoryStatisticsVo) map.get(Integer
					.valueOf(subCategoryIds[i]))).getCount());
			vo.setRequireQuantity(quantitys[i]);
			vo.setSubCategoryId(subCategoryIds[i]);
			vo.setSubCategoryDescr(((SubCategoryStatisticsVo) map.get(Integer
					.valueOf(subCategoryIds[i]))).getDescr());

			assignVoMap.put(Integer.valueOf(subCategoryIds[i]), vo);
			if (quantitys[i] != 0)
				assignVoList.add(vo);
		}
		Collections.sort(assignVoList);
		List<PersonAssignVo> resultPersonList = new ArrayList<>();

		int subCategoryId = fetchSubcategory(assignVoList);
		while (subCategoryId != -1) {
			List<Person> personList = personDao.getAllBooksPersonListBy(
					Integer.valueOf(subCategoryId), companyName);
			removePersonList(personList, resultPersonList);

			List<PersonAssignVo> personVoList = new ArrayList<>();
			for (Person p : personList) {
				if (!p.isLockInfo()) {
					PersonAssignVo v = new PersonAssignVo(p, assignVoMap);
					v.setSubCategoryId(subCategoryId);
					v.setSubCategoryDescr(((SubCategory) subCategoryMap
							.get(Integer.valueOf(subCategoryId))).getDescr());
					personVoList.add(v);
				}
			}
			if (personVoList.size() == 0) {
				throw new ApplicateionException("系统无法找到最佳配证方式，请手动进行配证。");
			}

			Collections.sort(personVoList);
			resultPersonList.add((PersonAssignVo) personVoList.get(0));
			removePersonFromMap(
					((PersonAssignVo) personVoList.get(0)).getPerson(),
					assignVoMap);
			subCategoryId = fetchSubcategory(assignVoList);
		}

		return resultPersonList;*/
		
		return new ArrayList<>();
	}

	int fetchSubcategory(List<AssignVo> assignVoList) {
		if (assignVoList.size() == 0) {
			return -1;
		}

		AssignVo vo = (AssignVo) assignVoList.get(0);
		vo.setRequireQuantity(vo.getRequireQuantity() - 1);
		if (vo.getRequireQuantity() == 0) {
			assignVoList.remove(0);
		}
		return vo.getSubCategoryId();
	}

	void removePersonFromMap(Person person, Map<Integer, AssignVo> assignVoMap)
			throws ApplicateionException {
		for (Iterator<Book> itor = person.getBooks().iterator(); itor.hasNext();) {
			Book b = (Book) itor.next();
			removeSubCategoryFromMap(b.getSubCategory().getId().intValue(),
					assignVoMap);
		}
	}

	void removeSubCategoryFromMap(int subCategoryId,
			Map<Integer, AssignVo> assignVoMap) throws ApplicateionException {
		AssignVo vo = (AssignVo) assignVoMap
				.get(Integer.valueOf(subCategoryId));
		if (vo != null) {
			vo.setRemainQuantity(vo.getRemainQuantity() - 1);
			if (vo.getRemainQuantity() < 0)
				throw new ApplicateionException("系统无法找到最佳配证方式，请手动进行配证。");
		}
	}

	void removePersonList(List<Person> personList,
			List<PersonAssignVo> personVoList) {
		if ((personList == null) || (personList.size() == 0)) {
			return;
		}

		if ((personVoList == null) || (personVoList.size() == 0)) {
			return;
		}

		for (PersonAssignVo vo : personVoList)
			for (Person person : personList)
				if (person.getId().equals(vo.getPerson().getId())) {
					personList.remove(person);
					break;
				}
	}

	public void batchAssign(int[] books, String usedInfo) {
		for (int bookId : books) {
			Book book = bookDao.getById(Integer.valueOf(bookId));
			book.setUsedInfo(usedInfo);
			book.setLockInfo(lock);

			Person p = book.getPerson();
			p.setBook(book);
			personDao.save(p);

			bookDao.save(book);
		}
	}

	public Person getPersonById(Integer id) {
		return personDao.getById(id);
	}
}
