package org.mybatis.jpetstore.web.actions;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.domain.Alarm;
import org.mybatis.jpetstore.domain.Account;
import org.mybatis.jpetstore.domain.Chatting;
import org.mybatis.jpetstore.domain.ChattingRoom;
import org.mybatis.jpetstore.domain.Memo;
import org.mybatis.jpetstore.service.AccountService;
import org.mybatis.jpetstore.service.ChattingService;

import javax.servlet.http.HttpSession;
import java.util.List;

@SessionScope
public class ChattingActionBean extends AbstractActionBean {

    @SpringBean
    private transient ChattingService chattingService;
    @SpringBean
    private transient AccountService accountService;

    private List<ChattingRoom> chattingRoomList;

    private List<ChattingRoom> adminChatList;
    private List<ChattingRoom> adminChatList1;

    private List<ChattingRoom> adminChatList2;

    private List<ChattingRoom> adminChatList3;

    private List<Chatting> chattingLog;

    private List<Account> accountList;
    private Chatting chatting;

    private String chattingLine;

    private ChattingRoom chattingRoom;

    private String customerId;
    private String managerId;

    private String keyword;

    private String senderId;

    private String adminName;

    private String adminId;

    private Memo memo;


    private List<Alarm> alarms;


    //-------------------------------------------------------------------------//
    private static final String VIEW_CHATTING_ROOM = "/WEB-INF/jsp/chatting/ChattingRoom.jsp";
    private static final String VIEW_PM_CHATTING_ROOM = "/WEB-INF/jsp/chatting/PetMangerChattingRoom.jsp";
    private static final String VIEW_ALL_CHATTING_ROOM = "/WEB-INF/jsp/chatting/AllChattingRoom.jsp";
    private static final String JOIN_CHATTING = "/WEB-INF/jsp/chatting/Chatting.jsp";
    private static final String VIEW_SEARCHED_CHATTING_ROOM = "/WEB-INF/jsp/chatting/searchChattingRoom.jsp";
    private static final String VIEW_SEARCHED_ADMIN_CHATTING_ROOM = "/WEB-INF/jsp/chatting/searchAdminChattingRoom.jsp";
    private static final String VIEW_CHATTING_MEMO = "/WEB-INF/jsp/chatting/memoChatting.jsp";

    //------------------------getter & setter ---------------------------------//
    public List<Alarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }
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

    public void setAdminName(String adminName){this.adminName = adminName;}
    public String getAdminName(){return adminName;}

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public List<Account> getAccountList() {
        return accountList;
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
        alarms = (List<Alarm>) session.getAttribute("alarms");
        senderId = accountBean.getUsername();
        adminName=accountBean.getAdminName();
        adminId= accountBean.getAdminId();
        if (permission.equals("petmanager")) {
            chattingRoomList = chattingService.getChatRoomListForManager(senderId);
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
        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
        String permission = (String) session.getAttribute("permission");
        senderId = accountBean.getUsername();
        adminName=accountBean.getAdminName();
        adminId= accountBean.getAdminId();
        if (permission == null || permission.isEmpty()) {
            setMessage("로그인이 필요합니다.");
            return new ForwardResolution(ERROR);
        }

        chattingRoom = new ChattingRoom();
        chattingRoom.setCustomerId(customerId);
        chattingRoom.setManagerId(managerId);
        chattingLog = chattingService.getChatLog(chattingRoom);
        Alarm alarm = new Alarm();
        alarm.setAlarm("off");
        if (accountBean.getUsername().equals(customerId)) {
            alarm.setSenderId(managerId);
            alarm.setReceiverId(customerId);
        }
        else {
            alarm.setSenderId(customerId);
            alarm.setReceiverId(managerId);
        }
        accountService.insertAlarm(alarm);
        alarms = accountService.getAlarmById(accountBean.getUsername());
        session.setAttribute("alarms", alarms);

        if(managerId.equals(adminId)){
            chattingLog = chattingService.getAdminChatLog(customerId);
        }
        else{
            chattingRoom = new ChattingRoom(customerId,managerId);
            chattingLog = chattingService.getChatLog(chattingRoom);
        }

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

        if(managerId.equals(adminId)){
            chatting = new Chatting();
            chatting.setCustomerId(customerId);
            chatting.setChattingLog(chattingLine);
            chatting.setSenderId(senderId);
            chattingService.insertAdminChatting(chatting);
        }
        else {
            chatting = new Chatting();
            chatting.setCustomerId(customerId);
            chatting.setManagerId(managerId);
            chatting.setChattingLog(chattingLine);
            chatting.setSenderId(senderId);
            chattingService.insertChatting(chatting);
        }
        setChattingLine(null);
        Alarm alarm = new Alarm();
        if (senderId.equals(customerId)) {
            alarm.setSenderId(customerId);
            alarm.setReceiverId(managerId);
            alarm.setAlarm("on");
            accountService.insertAlarm(alarm);
        }
        else {
            alarm.setSenderId(managerId);
            alarm.setReceiverId(customerId);
            alarm.setAlarm("on");
            accountService.insertAlarm(alarm);
        }
        return new RedirectResolution(ChattingActionBean.class,"joinChatting");
    }

    public ForwardResolution searchUserID() {
        if (keyword == null || keyword.length() < 1) {
            setKeyword(null);
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
                    setKeyword(null);
                    setMessage("No Result!");
                    return new ForwardResolution(ERROR);
                }
                return new ForwardResolution(VIEW_SEARCHED_CHATTING_ROOM);
            }
        }
        setKeyword(null);
        return new ForwardResolution(ERROR);
    }

    public ForwardResolution searchUserIdOnAccount() {
        if (keyword == null || keyword.length() < 1) {
            setMessage("Please enter a keyword to search for, then press the search button.");
            return new ForwardResolution(ERROR);
        } else {
            HttpSession session = context.getRequest().getSession();
            AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
            String permission = (String) session.getAttribute("permission");
            if (permission.equals("admin")) {
                accountList = accountBean.searchAccountByUserId(keyword);
                return new ForwardResolution(VIEW_SEARCHED_ADMIN_CHATTING_ROOM);
            }
        }
        setMessage("You do not have permission");
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
        HttpSession session = context.getRequest().getSession();
        String permission = (String) session.getAttribute("permission");
        if (permission == null || permission.isEmpty() || permission.equals("petmanager") == false) {
            setMessage("잘못된 접근입니다.");
            return new ForwardResolution(ERROR);
        }
        chattingService.updateMemo(memo);
        System.out.println("[DEBUG] updateMemo");
        System.out.println("[DEBUG] Memo:" + memo.getEvalLog());
        return new RedirectResolution(ChattingActionBean.class, "memoChatting");
    }
}

