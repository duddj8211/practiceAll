package com.mg.otp.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class baseDao {
	protected Log log = LogFactory.getLog(baseDao.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	protected void printQueryId(String strQueryId) {
		if(log.isDebugEnabled()){ 
			log.debug("\t QueryId \t: " + strQueryId); 
		} 
	}
	
	/**
	  * @methodName  : insert
	  * @author      : 2018. 10. 12.
	  * @date        : KOIWARE-KJH
	  * @description : Author
	  */
	public Object insert(String strQueryId, Object objParams) {
		printQueryId(strQueryId);

		return sqlSession.insert(strQueryId, objParams);
	}

	/**
	  * @methodName  : update
	  * @author      : 2018. 10. 12.
	  * @date        : KOIWARE-KJH
	  * @description : Author
	  */
	public Object update(String strQueryId, Object objParams) {
		printQueryId(strQueryId);

		return sqlSession.update(strQueryId, objParams);
	}
	
	/**
	 * @methodName : printQueryId
	 * @author : 2018. 10. 12.
	 * @date : KOIWARE-KJH
	 * @description :
	 */
	public Object delete(String strQueryId, Object objParams) {
		printQueryId(strQueryId);

		return sqlSession.delete(strQueryId, objParams);
	}
	/**
	 * @methodName : printQueryId
	 * @author : 2018. 10. 12.
	 * @date : KOIWARE-KJH
	 * @description :
	 */
	public Object delete(String strQueryId) {
		printQueryId(strQueryId);

		return sqlSession.delete(strQueryId);
	}

	/**
	  * @methodName  : selectOne
	  * @author      : 2018. 10. 12.
	  * @date        : KOIWARE-KJH
	  * @description : 
	  */
	public Object selectOne(String strQueryId) {
		printQueryId(strQueryId);

		return sqlSession.selectOne(strQueryId);
	}

	/**
	  * @methodName  : selectOne
	  * @author      : 2018. 10. 12.
	  * @date        : KOIWARE-KJH
	  * @description : Author
	  */
	public HashMap<String, Object> selectOne(String strQueryId, Object objParams) {
		printQueryId(strQueryId);

		return sqlSession.selectOne(strQueryId, objParams);
	}
	
	/**
	  * @methodName  : selectList
	  * @author      : 2018. 10. 12.
	  * @date        : KOIWARE-KJH
	  * @description : Author
	  */
	@SuppressWarnings("rawtypes")
	public List selectList(String strQueryId) {
		printQueryId(strQueryId);

		return sqlSession.selectList(strQueryId);
	}
	
	/**
	  * @methodName  : selectList
	  * @author      : 2018. 10. 12.
	  * @date        : KOIWARE-KJH
	  * @description : Author
	  */
	@SuppressWarnings("rawtypes")
	public List selectList(String strQueryId, Object objParams) {
		printQueryId(strQueryId);

		return sqlSession.selectList(strQueryId, objParams);
	}

}
