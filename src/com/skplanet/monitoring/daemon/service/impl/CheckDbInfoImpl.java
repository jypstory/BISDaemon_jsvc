package com.skplanet.monitoring.daemon.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skplanet.monitoring.cmmon.utils.DaemonProperty;
import com.skplanet.monitoring.daemon.dao.impl.DBConnectionImpl;
import com.skplanet.monitoring.daemon.service.CheckDbInfo;
import com.skplanet.monitoring.daemon.vo.CueOutputVo;

public class CheckDbInfoImpl implements CheckDbInfo {
	Logger logger = LoggerFactory.getLogger(MainDaemonImpl.class);

	private static DaemonProperty prof = new DaemonProperty();
	final int INTERVAL_TIME = Integer.parseInt(prof.getProperty("sleep_time(min)"));
	
	public void getDbInfo(String targetDb) throws SQLException{

		DBConnectionImpl dbc = new DBConnectionImpl();
		//Statement stmt = null;	//쿼리문
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;	//결과값
		Connection con =null;
		CueOutputVo cueVo = new CueOutputVo();

		ArrayList<CueOutputVo> cueArrayList;
		
		//PreparedStatement pstmt = null;
		try {
			
			cueArrayList = new ArrayList<CueOutputVo>();
			con = dbc.getConnection(targetDb);

			String sql=" select B.BM_NM||'_'||B.JOB_TYPE as CUEMSG_ID"
					+ "       ,NVL2(A.ERR_CD,'ERROR','OK') as ERROR_CODE"
					+ " ,A.ERR_MSG as MESSAGE"
					+ " ,A.ERR_CD as ERROR_TYPE"
					+ " ,A.JOB_ID as JOB_ID"
					+ " ,B.JOB_NAME as JOB_NAME"
					+ " ,A.JOB_ID||'_'||A.BASE_DT as ENTITY"
					+ " ,TO_CHAR(A.STRT_DTM, 'YYYYMMDDHH24MISS') as STRT_DTM"
					+ " ,TO_CHAR(A.END_DTM, 'YYYYMMDDHH24MISS') as END_DTM"
					+ " ,A.OPER_CNT as OPER_CNT"
					+ " ,A.INSERT_CNT as INSERT_CNT"
					+ " ,A.UPDATE_CNT as UPDATE_CNT"
					+ " ,A.DELETE_CNT as DELETE_CNT"
					+ " ,TO_CHAR(A.OPER_DTM, 'YYYYMMDDHH24MISS') as LOG_TIME"
					//+ " ,A.* "
					//+ " ,B.* "
					+ " from (select AA.* "
					+ "        from JOB_OPER_LOG AA "
					+ "            , (select max(oper_dtm) as oper_dtm "
					+ "                    , job_id "
					+ "                 from job_oper_log group by job_id) BB "
					+ " 	           where AA.job_id = BB.job_id and AA.oper_dtm = BB.oper_dtm) A  "
					+ " 	 , JOB_OPER_LOG_SUB_INFO B "
					+ " where A.JOB_ID = B.JOB_ID "
					+ " and ROUND((SYSDATE - A.OPER_DTM) * 24 * 60) <= ? ";

			
			//stmt = con.createStatement();
			//rs = stmt.executeQuery(sql);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, INTERVAL_TIME);
			rs = pstmt.executeQuery();
			
			while(rs.next()){ 

				cueVo = new CueOutputVo();
				
				cueVo.setCuemsgId( rs.getString("CUEMSG_ID"));
				cueVo.setErrorCode(rs.getString("ERROR_CODE"));
				cueVo.setMessage(rs.getString("MESSAGE"));
				cueVo.setErrorType(rs.getString("ERROR_TYPE"));
				cueVo.setOriginalMessage(rs.getString("MESSAGE"));
				cueVo.setJobID(rs.getString("JOB_ID"));
				cueVo.setJobName(rs.getString("JOB_NAME"));
				cueVo.setEntity(rs.getString("ENTITY"));
				cueVo.setStart(rs.getString("STRT_DTM"));
				cueVo.setEnd(rs.getString("END_DTM"));
				cueVo.setCount(rs.getString("OPER_CNT"));
				cueVo.setInsert(rs.getString("INSERT_CNT"));
				cueVo.setUpdate(rs.getString("UPDATE_CNT"));
				cueVo.setDelete(rs.getString("DELETE_CNT"));
				cueVo.setLogTime(rs.getString("LOG_TIME"));

				cueArrayList.add(cueVo);
			
			} 
			
			CueMsgWriteImpl cmw = new CueMsgWriteImpl();
			cmw.writeCue(cueArrayList);
			cueArrayList.clear();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally{
			rs.close(); 
			//stmt.close(); 
			pstmt.close();
			con.close();
		}

		return;
	}




}
