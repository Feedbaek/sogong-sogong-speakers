package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.Chatting;
import org.mybatis.jpetstore.domain.ChattingRoom;

import java.util.List;

public interface ChattingMapper {

    List<ChattingRoom> getAllChatRoom();

    List<ChattingRoom> getChatRoomByIdForManager(String managerId);

    List<ChattingRoom> getChatRoomByIdForUser(String customerId);

    List<Chatting> getChatLogById(String customerId,String managerId);

    void insertChattingRoom(ChattingRoom chattingRoom);

    void insertChatting(Chatting chatting);

    void deleteChattingRoom(ChattingRoom chattingRoom);
}
