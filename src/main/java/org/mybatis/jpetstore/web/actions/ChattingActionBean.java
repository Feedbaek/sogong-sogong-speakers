package org.mybatis.jpetstore.web.actions;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.domain.Chatting;
import org.mybatis.jpetstore.domain.ChattingRoom;
import org.mybatis.jpetstore.service.ChattingService;

import javax.servlet.http.HttpSession;
import java.util.List;

@SessionScope
public class ChattingActionBean extends AbstractActionBean {

    @SpringBean
    private transient ChattingService chattingService;

    private List<ChattingRoom> chattingRoomList;

    private List<ChattingRoom> adminChatList1;

    private List<ChattingRoom> adminChatList2;

    private List<ChattingRoom> adminChatList3;

    private List<Chatting> chattingLog;
    private Chatting chatting;

    private ChattingRoom chattingRoom;

    private String customerId;
    private String managerId;


    //-------------------------------------------------------------------------//
    private static final String VIEW_CHATTING_ROOM = "/WEB-INF/jsp/Chatting/ChattingRoom.jsp";
    private static final String VIEW_ALL_CHATTING_ROOM = "/WEB-INF/jsp/Chatting/AllChattingRoom.jsp";
    private static final String JOIN_CHATTING="/WEB-INF/jsp/Chatting/Chatting.jsp";


    //------------------------getter & setter ---------------------------------//
    public List<ChattingRoom> getChattingRoomList() {return chattingRoomList;}

    public void setChattingRoomList(List<ChattingRoom> chattingRoomList) {this.chattingRoomList = chattingRoomList;}
    public List<Chatting> getChattingLog() {return chattingLog;}

    public void setChattingLog(List<Chatting> chattingLog) {this.chattingLog = chattingLog;}
    public Chatting getChatting() {return chatting;}

    public void setChatting(Chatting chatting) {this.chatting = chatting;}

    public ChattingRoom getChattingRoom() {return chattingRoom;}

    public void setChattingRoom(ChattingRoom chattingRoom) {this.chattingRoom = chattingRoom;}

    public List<ChattingRoom> getAdminChatList1() {
        return adminChatList1;
    }

    public void setAdminChatList1(List<ChattingRoom> adminChatList1) {
        this.adminChatList1 = adminChatList1;
    }

    public List<ChattingRoom> getAdminChatList2() {
        return adminChatList2;
    }

    public void setAdminChatList2(List<ChattingRoom> adminChatList2) {
        this.adminChatList2 = adminChatList2;
    }

    public List<ChattingRoom> getAdminChatList3() {
        return adminChatList3;
    }

    public void setAdminChatList3(List<ChattingRoom> adminChatList3) {
        this.adminChatList3 = adminChatList3;
    }

    public String getCustomerId() {return customerId;}

    public void setCustomerId(String customerId) {this.customerId = customerId;}

    public String getManagerId() {return managerId;}

    public void setManagerId(String managerId) {this.managerId = managerId;}


    //============================================================================================

    @DefaultHandler
    public ForwardResolution viewChattingRoom(){
        HttpSession session = context.getRequest().getSession();
        String permission = (String) session.getAttribute("permission");
        if (permission == null || permission.isEmpty()) {
            setMessage("접근 권한이 없습니다.");
            return new ForwardResolution(ERROR);
        }
        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
        String userId =  accountBean.getUsername();
		if (permission.equals("petmanager"))
            chattingRoomList = chattingService.getChatRoomListForManager(userId);
		else if (permission.equals("user"))
			chattingRoomList = chattingService.getChatRoomListForUser(userId);
		else if (permission.equals("admin")) {
            adminChatList1 = chattingService.getChatRoomListForManager("manager1");
            adminChatList2 = chattingService.getChatRoomListForManager("manager2");
            adminChatList3 = chattingService.getChatRoomListForManager("manager3");
            return new ForwardResolution(VIEW_ALL_CHATTING_ROOM);
        }
        return new ForwardResolution(VIEW_CHATTING_ROOM);
    }

    public ForwardResolution joinChatting(){
        HttpSession session = context.getRequest().getSession();
        String permission = (String) session.getAttribute("permission");
        if(permission==null || permission.isEmpty()){
            setMessage("로그인이 필요합니다.");
            return new ForwardResolution(ERROR);
        }
        chattingRoom = new ChattingRoom();
        chattingRoom.setCustomerId(customerId);
        chattingRoom.setManagerId(managerId);
        chattingLog=chattingService.getChatLogById(chattingRoom);
        return new ForwardResolution(JOIN_CHATTING);
    }

    public Resolution insertChatting(){
        HttpSession session = context.getRequest().getSession();
        String userId = (String) session.getAttribute("userId");
        if(userId==null || userId.isEmpty()){
            setMessage("로그인이 필요합니다.");
            return new RedirectResolution(ERROR);
        }
        chattingService.insertChatting(chatting);
        return new RedirectResolution(ChattingActionBean.class);
    }
}

