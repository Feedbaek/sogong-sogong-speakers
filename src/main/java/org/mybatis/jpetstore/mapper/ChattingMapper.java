package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.Alarm;
import org.mybatis.jpetstore.domain.Chatting;
import org.mybatis.jpetstore.domain.ChattingRoom;
import org.mybatis.jpetstore.domain.Memo;

import java.util.List;

public interface ChattingMapper {

    List<ChattingRoom> getChatRoomByIdForManager(String managerId);

    List<ChattingRoom> getChatRoomByIdForUser(String customerId);

    List<ChattingRoom> getSearchedChatRoomList(ChattingRoom chattingRoom);

    List<Chatting> getChatLog(ChattingRoom chattingRoom);

    void insertChattingRoom(ChattingRoom chattingRoom);

    void insertChatting(Chatting chatting);

    void deleteChattingRoom(ChattingRoom chattingRoom);

    Memo getMemoById(String managerId, String customerId);

    void updateMemo(Memo memo);

    void insertMemo(Memo memo);

//    List<Alarm> getAlarmById(String id);
//    void insertAlarm(Alarm alarm);
//
//    void updateAlarm(Alarm alarm);
}
