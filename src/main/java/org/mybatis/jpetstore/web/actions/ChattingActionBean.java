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

    private List<ChattingRoom> adminChatList;
    private List<ChattingRoom> adminChatList1;

    private List<ChattingRoom> adminChatList2;

    private List<ChattingRoom> adminChatList3;

    private List<Chatting> chattingLog;
    private Chatting chatting;

    private String chattingLine;

    private ChattingRoom chattingRoom;

    private String customerId;
    private String managerId;

    private String keyword;

    //-------------------------------------------------------------------------//
    private static final String VIEW_CHATTING_ROOM = "/WEB-INF/jsp/chatting/ChattingRoom.jsp";

    private static final String VIEW_PM_CHATTING_ROOM = "/WEB-INF/jsp/chatting/PetMangerChattingRoom.jsp";
    private static final String VIEW_ALL_CHATTING_ROOM = "/WEB-INF/jsp/chatting/AllChattingRoom.jsp";
    private static final String JOIN_CHATTING = "/WEB-INF/jsp/chatting/Chatting.jsp";

    private static final String VIEW_SEARCHED_CHATTING_ROOM = "/WEB-INF/jsp/chatting/searchChattingRoom.jsp";

    //------------------------getter & setter ---------------------------------//
    public List<ChattingRoom> getChattingRoomList() {
        return chattingRoomList;
    }

    public List<ChattingRoom> getAdminChatList() {
        return adminChatList;
    }

    public void setAdminChatList(List<ChattingRoom> adminChatList) {
        this.adminChatList = adminChatList;
    }

    public void setChattingRoomList(List<ChattingRoom> chattingRoomList) {
        this.chattingRoomList = chattingRoomList;
    }

    public List<Chatting> getChattingLog() {
        return chattingLog;
    }

    public String getKeyword() { return keyword;}

    public void setKeyword(String keyword) { this.keyword = keyword;}

    public void setChattingLog(List<Chatting> chattingLog) {
        this.chattingLog = chattingLog;
    }

    public Chatting getChatting() {
        return chatting;
    }

    public void setChatting(Chatting chatting) {
        this.chatting = chatting;
    }

    public ChattingRoom getChattingRoom() {
        return chattingRoom;
    }

    public void setChattingRoom(ChattingRoom chattingRoom) {
        this.chattingRoom = chattingRoom;
    }

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getChattingLine() {
        return chattingLine;
    }

    public void setChattingLine(String chattingLine) {
        this.chattingLine = chattingLine;
    }


    //============================================================================================

    @DefaultHandler
    public ForwardResolution viewChattingRoom() {
        HttpSession session = context.getRequest().getSession();
        String permission = (String) session.getAttribute("permission");
        if (permission == null || permission.isEmpty()) {
            setMessage("접근 권한이 없습니다.");
            return new ForwardResolution(ERROR);
        }
        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
        String userId = accountBean.getUsername();
        if (permission.equals("petmanager")) {
            chattingRoomList = chattingService.getChatRoomListForManager(userId);
            if (userId.equals("manager1"))
                adminChatList = chattingService.getChatRoomListForManager("manager1");
            else if (userId.equals("manager2"))
                adminChatList = chattingService.getChatRoomListForManager("manager2");
            else if (userId.equals("manager3"))
                adminChatList = chattingService.getChatRoomListForManager("manager3");
            return new ForwardResolution(VIEW_PM_CHATTING_ROOM);
        } else if (permission.equals("user"))
            chattingRoomList = chattingService.getChatRoomListForUser(userId);
        else if (permission.equals("admin")) {
            adminChatList1 = chattingService.getChatRoomListForManager("manager1");
            adminChatList2 = chattingService.getChatRoomListForManager("manager2");
            adminChatList3 = chattingService.getChatRoomListForManager("manager3");
            return new ForwardResolution(VIEW_ALL_CHATTING_ROOM);
        }
        return new ForwardResolution(VIEW_CHATTING_ROOM);
    }

    public ForwardResolution joinChatting() {
        HttpSession session = context.getRequest().getSession();
        String permission = (String) session.getAttribute("permission");
        if (permission == null || permission.isEmpty()) {
            setMessage("로그인이 필요합니다.");
            return new ForwardResolution(ERROR);
        }
        chattingRoom = new ChattingRoom();
        chattingRoom.setCustomerId(customerId);
        chattingRoom.setManagerId(managerId);
        chattingLog = chattingService.getChatLog(chattingRoom);
        return new ForwardResolution(JOIN_CHATTING);
    }

    public RedirectResolution insertChatting() {
        HttpSession session = context.getRequest().getSession();
        String permission = (String) session.getAttribute("permission");
        if (permission == null || permission.isEmpty()) {
            setMessage("로그인이 필요합니다.");
        }
        if(chattingLine==null || chattingLine.isEmpty()){
            return new RedirectResolution(ChattingActionBean.class,"joinChatting");
        }
        chatting = new Chatting();
        chatting.setCustomerId(customerId);
        chatting.setManagerId(managerId);
        chatting.setChattingLog(chattingLine);
        chattingService.insertChatting(chatting);
        return new RedirectResolution(ChattingActionBean.class,"joinChatting");
    }

    public ForwardResolution searchUserID() {
        if (keyword == null || keyword.length() < 1) {
            setMessage("Please enter a keyword to search for, then press the search button.");
            return new ForwardResolution(ERROR);
        } else
        {
            HttpSession session = context.getRequest().getSession();
            AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
            String permission = (String) session.getAttribute("permission");
            if (permission.equals("petmanager"))
            {
                String id = accountBean.getUsername();
                chattingRoom = new ChattingRoom();
                chattingRoom.setManagerId(id);
                chattingRoom.setCustomerId("%" + keyword + "%");
                chattingRoomList = chattingService.getSearchedChatRoomList(chattingRoom);
                return new ForwardResolution(VIEW_SEARCHED_CHATTING_ROOM);
            }
        }
        return new ForwardResolution(ERROR);
    }
}

