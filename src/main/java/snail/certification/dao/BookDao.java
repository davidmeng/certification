package snail.certification.dao;

import org.springframework.stereotype.Repository;

import snail.certification.pojo.Book;

@Repository
public class BookDao extends BaseDaoImpl<Book>{

	protected BookDao() {
		super(Book.class);
	}

}
