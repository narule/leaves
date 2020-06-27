//package com.domoment.leaves.dao;
//
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.session.SqlSession;
//
//import com.domoment.leaves.vo.LeaveMessageVO;
//
////	@Override
////	@Insert(value="insert into t_user (id, userName, age, position) values ( #{id,jdbcType=INTEGER},#{userName,jdbcType=VARCHAR},#{age,jdbcType=INTEGER},#{position,jdbcType=VARCHAR})")
////	@Insert(value="INSERT INTO `domoment`.`leave_message` "
////			+ "(`title`, `description`, `content`, `to_whom`, `reserved_information`, `leave_name`,"
////			+ " `parent_id`, `attachment`, `public`, `view_certificate`,"
////			+ " `creator`, `creator_id`, `updater`, `updater_id`, `version`, `transaction_id`, `valid`, `remark`) "
////			+ "VALUES ("
////			+ "#{title}, #{description}, #{content}, #{to_whom}, #{reservedInformation}, 'leaveName',"
////			+ " #{parent_id}, #{attachment}, #{public}, #{view_certificate}, "
////			+ "'#{creator}, #{creatorId},#{updater}, #{updaterId},#{version},#{transactionId}, #{valid},#{remark})")
////	public Object insert(LeaveMessageVO data);
////@Mapper
//public interface LeaveMessageMapper extends SqlSession{
//	
//}
