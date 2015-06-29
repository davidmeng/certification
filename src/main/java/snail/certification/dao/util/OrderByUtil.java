package snail.certification.dao.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;


public class OrderByUtil {

	public static void sortCriteria(Criteria criteria, Map<String,String> sortMap,
			Map<String,String> aliasMap) {
		if (!sortMap.isEmpty()) {
			for (Iterator<String> it = sortMap.keySet().iterator(); it.hasNext();) {
				Object o = it.next();
				String fieldName = o.toString();
				String orderType = sortMap.get(fieldName).toString();

				// ����Ϊ�´��� ������20101027����
				String[] fieldNameArray = fieldName.split("\\.");
				if (fieldNameArray.length > 1) {
					
					//���ų�Ϊ�յĴ���
					if(aliasMap == null) {
						aliasMap = new HashMap<String,String>();
					}
					
					int length = fieldNameArray.length;
					
					String[] aliasArray = new String[length];
					for (int i = 0; i < fieldNameArray.length - 1; i++) {
						String currentField = fieldNameArray[i];
						
						//��ѯaliasMap����û�и�currentField
						//�����,��aliasMap�е�Ϊ׼
						if(aliasMap.containsKey(currentField)) {
							aliasArray[i] = (String)aliasMap.get(currentField);
						}
						//���û�У��Ե�ǰcurrentFieldΪ׼
						else {
							aliasArray[i] = currentField;
							if( i == 0) {
								criteria.createAlias(currentField,currentField);
							}
							else {
								criteria.createAlias(aliasArray[i -1] + "." + currentField, currentField);
							}
						}
					}
					fieldName = aliasArray[length - 2] + "." + fieldNameArray[length - 1];
				}


				if ("asc".equalsIgnoreCase(orderType)) {
					criteria.addOrder(Order.asc(fieldName));
				} else {
					criteria.addOrder(Order.desc(fieldName));
				}
			}
		}
	}


	public static String sortSql(String hql, Map<String,String> sortMap) {
		StringBuffer buffer = new StringBuffer(hql);
		if (!sortMap.isEmpty()) {
			buffer.append(" order by ");
			int i = 0;
			for (Iterator<String> it = sortMap.keySet().iterator(); it.hasNext();) {
				Object o = it.next();
				String fieldName = o.toString();
				String orderType = sortMap.get(fieldName).toString();
				// ��ñ�ı���
				String tableName = getTableName(hql, fieldName);

				if ("asc".equalsIgnoreCase(orderType)) {
					if (i == 0)
						buffer.append(tableName).append(fieldName).append(
								" asc");
					else
						buffer.append(",").append(tableName).append(fieldName)
								.append(" asc");
				} else {
					if (i == 0)
						buffer.append(tableName).append(fieldName).append(
								" desc");
					else
						buffer.append(",").append(tableName).append(fieldName)
								.append(" desc");
				}
				i++;
			}
		}
		return buffer.toString();
	}


	public static String getTableName(String hql, String fieldName) {
		hql = StringUtils.substringBetween(hql.toLowerCase(), "select", "from");
		if (hql == null)
			hql = "";
		int endIndex = hql.indexOf(fieldName.toLowerCase());
		String tableName = "";
		if (endIndex != -1) {
			String subhql = hql.substring(0, endIndex);
			int beginIndex = subhql.lastIndexOf(",");
			tableName = hql.substring(beginIndex + 1, endIndex).trim();
		}
		return tableName;
	}

}
