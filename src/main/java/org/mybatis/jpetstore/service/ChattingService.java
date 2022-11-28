package org.mybatis.jpetstore.service;

import org.mybatis.jpetstore.domain.Chatting;
import org.mybatis.jpetstore.domain.ChattingRoom;
import org.mybatis.jpetstore.mapper.ChattingMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChattingService {

    private final ChattingMapper chattingMapper;

    public ChattingService(ChattingMapper chattingMapper){this.chattingMapper=chattingMapper;}


    public List<ChattingRoom> getChatRoomListForManager(String managerId) {
        return chattingMapper.getChatRoomByIdForManager(managerId);
    }

    public List<ChattingRoom> getChatRoomListForUser(String userId) {
        return chattingMapper.getChatRoomByIdForUser(userId);
    }

    public List<ChattingRoom> getAllChatRoom() {
        return chattingMapper.getAllChatRoom();
    }

    public List<Chatting> getChatLog(String userId,String managerId) {
        return chattingMapper.getChatLogById(userId,managerId);
    }


    @Transactional
    public void insertChatting(Chatting chatting) {
        chattingMapper.insertChatting(chatting);
    }

    @Transactional
    public void createChattingRoom(ChattingRoom chattingRoom) {
        chattingMapper.insertChattingRoom(chattingRoom);
    }

    @Transactional
    public void destroyChattingRoom(ChattingRoom chattingRoom) {chattingMapper.deleteChattingRoom(chattingRoom);}

}
