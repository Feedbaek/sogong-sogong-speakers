package org.mybatis.jpetstore.web.actions;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.domain.Chatting;
import org.mybatis.jpetstore.domain.ChattingRoom;
import org.mybatis.jpetstore.service.ChattingService;

import java.util.List;

public class ChattingActionBean extends AbstractActionBean {

    @SpringBean
    private transient ChattingService chattingService;

    private List<ChattingRoom> chattingRoomList;

    private List<Chatting> chattingLog;
    private Chatting chatting;

    private ChattingRoom chattingRoom;
    private String userId;

    private String managerId;

    //-------------------------------------------------------------------------//

    private static final String VIEW_CHATTING_ROOM = "/WEB-INF/jsp/catalog/ChattingRoom.jsp";

    //------------------------getter & setter ---------------------------------//

    public List<ChattingRoom> getChattingRoomList() {return chattingRoomList;}
    public void setChattingRoomList(List<ChattingRoom> chattingRoomList) {this.chattingRoomList = chattingRoomList;}

    public List<Chatting> getChattingLog() {return chattingLog;}
    public void setChattingLog(List<Chatting> chattingLog) {this.chattingLog = chattingLog;}

    public Chatting getChatting() {return chatting;}
    public void setChatting(Chatting chatting) {this.chatting = chatting;}

    public ChattingRoom getChattingRoom() {return chattingRoom;}
    public void setChattingRoom(ChattingRoom chattingRoom) {this.chattingRoom = chattingRoom;}

    public String getUserId() {return userId;}
    public void setUserId(String userId) {this.userId = userId;}

    public String getManagerId() {return managerId;}
    public void setManagerId(String managerId) {this.managerId = managerId;}


    //============================================================================================

    public ForwardResolution viewChattingRoomForManager(){
        chattingRoomList = chattingService.getChatRoomListForManager(managerId);
        return new ForwardResolution(VIEW_CHATTING_ROOM);
    }

}
