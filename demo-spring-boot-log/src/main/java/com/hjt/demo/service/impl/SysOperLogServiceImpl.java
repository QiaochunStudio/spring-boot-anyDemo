//package com.hjt.demo.service.impl;
//
//import com.hjt.demo.mapper.SysOperLogMapper;
//import com.hjt.demo.service.SysOperLogService;
//import com.hjt.domain.SysOperLog;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///***
// * @author hjt
// *
// */
//@Service
//public class SysOperLogServiceImpl implements SysOperLogService {
//    @Autowired
//    private SysOperLogMapper operLogMapper;
//    @Override
//    public int insertOperlog(SysOperLog operLog) {
//        return operLogMapper.insertOperlog(operLog);
//    }
//
//    @Override
//    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
//        return null;
//    }
//
//    @Override
//    public int deleteOperLogByIds(Long[] operIds) {
//        return 0;
//    }
//
//    @Override
//    public SysOperLog selectOperLogById(Long operId) {
//        return null;
//    }
//
//    @Override
//    public void cleanOperLog() {
//
//    }
//}
