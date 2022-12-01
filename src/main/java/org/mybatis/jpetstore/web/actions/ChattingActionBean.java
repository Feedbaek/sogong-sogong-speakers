package org.mybatis.jpetstore.web.actions;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.domain.Chatting;
import org.mybatis.jpetstore.domain.ChattingRoom;
import org.mybatis.jpetstore.domain.Memo;
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

    private String senderId;

    private Memo memo;


    //-------------------------------------------------------------------------//
    private static final String VIEW_CHATTING_ROOM = "/WEB-INF/jsp/chatting/ChattingRoom.jsp";

    private static final String VIEW_PM_CHATTING_ROOM = "/WEB-INF/jsp/chatting/PetMangerChattingRoom.jsp";
    private static final String VIEW_ALL_CHATTING_ROOM = "/WEB-INF/jsp/chatting/AllChattingRoom.jsp";
    private static final String JOIN_CHATTING = "/WEB-INF/jsp/chatting/Chatting.jsp";
    private static final String VIEW_SEARCHED_CHATTING_ROOM = "/WEB-INF/jsp/chatting/searchChattingRoom.jsp";
    private static final String VIEW_CHATTING_MEMO = "/WEB-INF/jsp/chatting/memoChatting.jsp";
    private static final String VIEW_MANAGER_LIST = "/WEB-INF/jsp/chatting/allManagerList.jsp";

    //------------------------getter & setter ---------------------------------//
    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
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
    public Memo getMemo() {
        return memo;
    }

    public void setMemo(Memo memo) {
        this.memo = memo;
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
        senderId = accountBean.getUsername();
        if (permission.equals("petmanager")) {
            chattingRoomList = chattingService.getChatRoomListForManager(senderId);
            if (senderId.equals("manager1"))
                adminChatList = chattingService.getChatRoomListForManager("manager1");
            else if (senderId.equals("manager2"))
                adminChatList = chattingService.getChatRoomListForManager("manager2");
            else if (senderId.equals("manager3"))
                adminChatList = chattingService.getChatRoomListForManager("manager3");
            else if (senderId.equals("manager4"))
                adminChatList = chattingService.getChatRoomListForManager("manager4");
            else if (senderId.equals("manager5"))
                adminChatList = chattingService.getChatRoomListForManager("manager5");
            else if (senderId.equals("manager6"))
                adminChatList = chattingService.getChatRoomListForManager("manager6");
            else if (senderId.equals("manager7"))
                adminChatList = chattingService.getChatRoomListForManager("manager7");
            else if (senderId.equals("manager8"))
                adminChatList = chattingService.getChatRoomListForManager("manager8");
            else if (senderId.equals("manager9"))
                adminChatList = chattingService.getChatRoomListForManager("manager9");
            return new ForwardResolution(VIEW_PM_CHATTING_ROOM);
        } else if (permission.equals("user"))
            chattingRoomList = chattingService.getChatRoomListForUser(senderId);
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
        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
        senderId = accountBean.getUsername();
        chatting = new Chatting();
        chatting.setCustomerId(customerId);
        chatting.setManagerId(managerId);
        chatting.setChattingLog(chattingLine);
        chatting.setSenderId(senderId);
        System.out.println(chattingLine.replace("\n\r","<br>"));
        chattingService.insertChatting(chatting);
        setChattingLine(null);
        return new RedirectResolution(ChattingActionBean.class,"joinChatting");
    }

    public ForwardResolution searchUserID() {
        if (keyword == null || keyword.length() < 1) {
            setMessage("Please enter a keyword to search for, then press the search button.");
            return new ForwardResolution(ERROR);
        } else {
            HttpSession session = context.getRequest().getSession();
            AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
            String permission = (String) session.getAttribute("permission");
            if (permission.equals("petmanager")) {
                String id = accountBean.getUsername();
                chattingRoom = new ChattingRoom();
                chattingRoom.setManagerId(id);
                chattingRoom.setCustomerId("%" + keyword + "%");
                chattingRoomList = chattingService.getSearchedChatRoomList(chattingRoom);
                if (chattingRoomList.size() == 0) {
                    setMessage("No Result!");
                    return new ForwardResolution(ERROR);
                }
                return new ForwardResolution(VIEW_SEARCHED_CHATTING_ROOM);
            }
        }
        return new ForwardResolution(ERROR);
    }
    public ForwardResolution memoChatting() {
        HttpSession session = context.getRequest().getSession();
        String permission = (String) session.getAttribute("permission");
        if (permission == null || permission.isEmpty() || permission.equals("petmanager") == false) {
            setMessage("잘못된 접근입니다.");
            return new ForwardResolution(ERROR);
        }
        memo = chattingService.getMemoById(managerId, customerId);
        if (memo == null) {
            memo = new Memo();
            memo.setManagerId(managerId);
            memo.setCustomerId(customerId);
            chattingService.insertMemo(memo);
        }
        return new ForwardResolution(VIEW_CHATTING_MEMO);
    }
    public Resolution saveMemo() {
//        HttpSession session = context.getRequest().getSession();
//        String permission = (String) session.getAttribute("permission");
//        if (permission == null || permission.isEmpty() || permission.equals("petmanager") == false) {
//            setMessage("잘못된 접근입니다.");
//            return new ForwardResolution(ERROR);
//        }
        chattingService.updateMemo(memo);
        System.out.println("[DEBUG] updateMemo");
        System.out.println("[DEBUG] Memo:" + memo.getEvalLog());
        return new RedirectResolution(ChattingActionBean.class, "memoChatting");
    }
    public ForwardResolution allManagerList() {
        return new ForwardResolution(VIEW_MANAGER_LIST);
    }
}

