package com.org.peysen.bootrabbitmq.mapper;

import com.org.peysen.bootrabbitmq.entity.MessageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Description: MessageLog DAO
 * @Author: peysen
 * @CreateDate: 2019/7/8 21:36
 * @UpdateRemark: The modified content
 */
@Mapper
public interface MessageLogRepository {


    Integer insert(MessageInfo messageInfo);

    Integer updateStatus(@Param("messageId") int messageId,
                         @Param("status") int status,
                         @Param("updateTime") Date updateTime);


    List<MessageInfo> queryStatusAndTimeout();

    Integer updateRetryCount();

}
