package snail.certification.dao;

import org.springframework.stereotype.Repository;

import snail.certification.pojo.BookType;

@Repository
public class BookTypeDao extends BaseDaoImpl<BookType>{

	protected BookTypeDao() {
		super(BookType.class);
	}

}
