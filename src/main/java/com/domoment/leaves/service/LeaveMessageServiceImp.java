package com.domoment.leaves.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.domoment.leaves.common.response.PageData;
import com.domoment.leaves.dao.CommonMapper;
import com.domoment.leaves.vo.LeaveMessageVO;

@Service
public class LeaveMessageServiceImp implements LeaveMessageService {
	
	@Autowired
	private CommonMapper mapper;
	
	@Override
	@Transactional
	public Object save(LeaveMessageVO data) {
		data.setValid(1);
		return mapper.insert("leaveMessageMapper.insert", data);
	}

	@Override
	@Transactional
	public Object delete(LeaveMessageVO data) {
		data.setValid(0);
		return update(data);
	}

	@Override
	@Transactional
	public Object update(LeaveMessageVO data) {
		return mapper.update("leaveMessageMapper.update", data);
	}

	@Override
	public Object query(LeaveMessageVO data) {
		PageData<LeaveMessageVO> pageData = new PageData<>();
		List<LeaveMessageVO> selectList = null;
		Integer pageNo = data.getPageNo();
		Integer pageSize = data.getPageSize();
		RowBounds rowBounds = null;
		if(!StringUtils.isEmpty(data.getCertificate())) {
			data.set_public(0);
		}else {
			data.set_public(1);
		}
		if(pageNo !=null && pageNo > 0 && pageSize != null && pageSize > 0) {
			rowBounds = new RowBounds((pageNo - 1)*pageSize,pageSize);
		}else {
			rowBounds = RowBounds.DEFAULT;
		}
		int totalCount = mapper.selectOne("leaveMessageMapper.selectListCount",data);
		pageData.setTotalCount(totalCount);
		if(totalCount > 0) {
			selectList = mapper.selectList("leaveMessageMapper.selectList", data, rowBounds);			
		}else {
			selectList = new ArrayList<LeaveMessageVO>(0);
		}
		pageData.setListData(selectList);
		return pageData;
	}

}
