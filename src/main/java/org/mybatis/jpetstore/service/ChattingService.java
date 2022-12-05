package org.mybatis.jpetstore.service;

import java.util.List;

import org.mybatis.jpetstore.domain.Chatting;
import org.mybatis.jpetstore.domain.ChattingRoom;
import org.mybatis.jpetstore.domain.Memo;
import org.mybatis.jpetstore.mapper.ChattingMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChattingService {

    private final ChattingMapper chattingMapper;

    public ChattingService(ChattingMapper chattingMapper){this.chattingMapper=chattingMapper;}


    public List<ChattingRoom> getChatRoomListForManager(String managerId) {
        return chattingMapper.getChatRoomByIdForManager(managerId);
    }

    public List<ChattingRoom> getChatRoomListForUser(String customerId) {
        return chattingMapper.getChatRoomByIdForUser(customerId);
    }

    public List<ChattingRoom> getAllChatRoom(){
        return chattingMapper.getAllChatRoom();
    }
    public List<ChattingRoom> getSearchedChatRoomList(ChattingRoom chattingRoom) {
        return chattingMapper.getSearchedChatRoomList(chattingRoom);
    }

    public List<Chatting> getChatLog(ChattingRoom chattingRoom) {
        return chattingMapper.getChatLog(chattingRoom);
    }
    public List<Chatting> getAdminChatLog(String userId) {
        return chattingMapper.getAdminChatLog(userId);
    }

    @Transactional
    public void insertChatting(Chatting chatting) {
        chattingMapper.insertChatting(chatting);
    }

    @Transactional
    public void insertAdminChatting(Chatting chatting){chattingMapper.insertAdminChatting(chatting);}

    @Transactional
    public void createChattingRoom(ChattingRoom chattingRoom) {
        chattingMapper.insertChattingRoom(chattingRoom);
    }

    public int updateChattingRoom(ChattingRoom fromChattingRoom,ChattingRoom toChattingRoom){
        return chattingMapper.updateChattingRoom(fromChattingRoom,toChattingRoom);
    }

    @Transactional
    public void destroyChattingRoom(ChattingRoom chattingRoom) {chattingMapper.deleteChattingRoom(chattingRoom);}

    @Transactional
    public Memo getMemoById(String managerId, String customerId) { return chattingMapper.getMemoById(managerId, customerId);}

    @Transactional
    public void updateMemo(Memo memo) {
        chattingMapper.updateMemo(memo);
    }

    @Transactional
    public void insertMemo(Memo memo) {
        chattingMapper.insertMemo(memo);
    }

}
