package snail.certification.dao;

import java.util.ArrayList;
import java.util.List;

import snail.certification.pojo.SubCategory;
import snail.certification.vo.SubCategoryStatisticsVo;

public class SubCategoryDao extends BaseDaoImpl<SubCategory> {

	protected SubCategoryDao() {
		super(SubCategory.class);
	}

	 public List<SubCategoryStatisticsVo> getStatList(int typeId) {
		 
		 return new ArrayList<>();
		 /*
		     List list = new ArrayList();
		     PreparedStatement ps = null;
		     ResultSet rs = null;
		     Map<Integer,SubCategoryStatisticsVo> map = new HashMap<>();
		     try
		     {
		    	 List resultList = sessionFactory.getCurrentSession().createSQLQuery("SELECT COUNT(DISTINCT B.ID) as count , SC.DESCR , SC.ID FROM PERSON P, BOOK B, SUB_CATEGORY SC WHERE P.id = B.person_id AND B.SUB_CATEGORY_ID = SC.ID AND B.BOOK_TYPE_ID = ?  GROUP BY SC.DESCR , SC.ID ORDER BY SC.ID")
		    	 	.setInteger(1, typeId).list();
		       ps = sessionFactory.getCurrentSession().getSessionFactory().connection().prepareStatement("SELECT COUNT(DISTINCT B.ID) as count , SC.DESCR , SC.ID FROM PERSON P, BOOK B, SUB_CATEGORY SC WHERE P.id = B.person_id AND B.SUB_CATEGORY_ID = SC.ID AND B.BOOK_TYPE_ID = ?  GROUP BY SC.DESCR , SC.ID ORDER BY SC.ID");
		       ps.setInt(1, typeId);
		       rs = ps.executeQuery();
		       while (rs.next()) {
		         SubCategoryStatisticsVo vo = new SubCategoryStatisticsVo();
		         vo.setTotalCount(rs.getInt("count"));
		         vo.setDescr(rs.getString("DESCR"));
		         vo.setSubCategoryId(rs.getInt("id"));
		         map.put(Integer.valueOf(vo.getSubCategoryId()), vo);
		       }
		     } catch (DataAccessResourceFailureException e) {
		       e.printStackTrace();
		     } catch (HibernateException e) {
		       e.printStackTrace();
		     } catch (IllegalStateException e) {
		       e.printStackTrace();
		     } catch (SQLException e) {
		       e.printStackTrace();
		     }
		     finally {
		       if (rs != null)
		         try {
		           rs.close();
		         } catch (SQLException e) {
		           e.printStackTrace();
		         }
		       if (ps != null)
		         try {
		           ps.close();
		         } catch (SQLException e) {
		           e.printStackTrace();
		         }
		     }
		     try
		     {
		       String mySql = "SELECT COUNT(DISTINCT B.ID) as count , SC.DESCR , SC.ID FROM PERSON P, BOOK B, SUB_CATEGORY SC WHERE P.id = B.person_id AND B.SUB_CATEGORY_ID = SC.ID AND B.BOOK_TYPE_ID = ? and p.BOOK_ID is null  GROUP BY SC.DESCR , SC.ID ORDER BY SC.ID";
		       if (typeId == 1) {
		         mySql = "SELECT COUNT(DISTINCT B.ID) as count , SC.DESCR , SC.ID FROM PERSON P, BOOK B, SUB_CATEGORY SC WHERE (b.lock_info is null or b.lock_info = 2 ) and P.id = B.person_id AND B.SUB_CATEGORY_ID = SC.ID AND B.BOOK_TYPE_ID = ?  GROUP BY SC.DESCR , SC.ID ORDER BY SC.ID";
		       }
		 
		       ps = super.getSession().connection().prepareStatement(mySql);
		 
		       ps.setInt(1, typeId);
		       rs = ps.executeQuery();
		       while (rs.next())
		       {
		         int id = rs.getInt("id");
		         if (map.containsKey(Integer.valueOf(id))) {
		           SubCategoryStatisticsVo vo = (SubCategoryStatisticsVo)map.get(Integer.valueOf(id));
		           vo.setCount(rs.getInt("count"));
		         } else {
		           SubCategoryStatisticsVo vo = (SubCategoryStatisticsVo)map.get(Integer.valueOf(id));
		           vo.setCount(0);
		         }
		       }
		     } catch (DataAccessResourceFailureException e) {
		       e.printStackTrace();
		     } catch (HibernateException e) {
		       e.printStackTrace();
		     } catch (IllegalStateException e) {
		       e.printStackTrace();
		     } catch (SQLException e) {
		       e.printStackTrace();
		     }
		     finally {
		       if (rs != null)
		         try {
		           rs.close();
		         } catch (SQLException e) {
		           e.printStackTrace();
		         }
		       if (ps != null) {
		         try {
		           ps.close();
		         } catch (SQLException e) {
		           e.printStackTrace();
		         }
		       }
		     }
		     return new ArrayList(map.values());
		   }
		   */
		 
		 
		 }
}
