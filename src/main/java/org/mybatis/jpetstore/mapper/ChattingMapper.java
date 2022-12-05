package org.mybatis.jpetstore.mapper;

import java.util.List;

import org.mybatis.jpetstore.domain.Chatting;
import org.mybatis.jpetstore.domain.ChattingRoom;
import org.mybatis.jpetstore.domain.Memo;

public interface ChattingMapper {

    List<ChattingRoom> getAllChatRoom();
    List<ChattingRoom> getChatRoomByIdForManager(String managerId);

    List<ChattingRoom> getChatRoomByIdForUser(String customerId);

    List<ChattingRoom> getSearchedChatRoomList(ChattingRoom chattingRoom);

    List<Chatting> getChatLog(ChattingRoom chattingRoom);
    List<Chatting> getAdminChatLog(String userId);

    void insertChattingRoom(ChattingRoom chattingRoom);

    void insertChatting(Chatting chatting);

    void insertAdminChatting(Chatting chatting);

    int updateChattingRoom(ChattingRoom fromChattingRoom,ChattingRoom toChattingRoom);

    void deleteChattingRoom(ChattingRoom chattingRoom);

    Memo getMemoById(String managerId, String customerId);

    void updateMemo(Memo memo);

    void insertMemo(Memo memo);

//    List<Alarm> getAlarmById(String id);
//    void insertAlarm(Alarm alarm);
//
//    void updateAlarm(Alarm alarm);
}
