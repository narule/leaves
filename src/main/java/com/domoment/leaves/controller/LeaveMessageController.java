package com.domoment.leaves.controller;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domoment.leaves.common.response.PageData;
import com.domoment.leaves.common.response.ResponseResult;
import com.domoment.leaves.service.LeaveMessageService;
import com.domoment.leaves.vo.LeaveMessageVO;

@SuppressWarnings("all")
@RestController
@RequestMapping("msg")
public class LeaveMessageController {

	@Autowired
	private LeaveMessageService service;
	
	@Autowired
	HttpServletRequest _request;
	
	@Autowired
	HttpServletResponse _response;
	
	
	/**
	 * description 保存留言
	 * @param vo
	 * @return
	 */
	@PostMapping("/save")
	public ResponseResult save(@RequestBody LeaveMessageVO vo) {
		ResponseResult response = null;
		try {
			int insertNum = (int)service.save(vo);
			if(insertNum > 0) {
				response = ResponseResult.success(insertNum);
			}else {
				response = ResponseResult.failed(insertNum);
			}
		} catch (Exception e) {
			response = ResponseResult.error(e.getMessage());
		}
		return response;
	}
	
	/**
	 * description 保存留言
	 * @param vo
	 * @return
	 */
	@RequestMapping("/query")
	public ResponseResult query(@RequestBody LeaveMessageVO vo) {
		ResponseResult result = null;
		try {
			PageData<LeaveMessageVO> pageData = (PageData)service.query(vo);
			result = ResponseResult.success(pageData);
		} catch (Exception e) {
			result = ResponseResult.error(e.getMessage());
		}
		return result;			
	}
}
