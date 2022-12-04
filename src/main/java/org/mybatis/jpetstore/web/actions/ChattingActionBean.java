package org.mybatis.jpetstore.web.actions;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.domain.*;
import org.mybatis.jpetstore.service.AccountService;
import org.mybatis.jpetstore.service.ChattingService;
import org.mybatis.jpetstore.service.PetManagerService;

import javax.servlet.http.HttpSession;
import java.util.List;

@SessionScope
public class ChattingActionBean extends AbstractActionBean {

    @SpringBean
    private transient ChattingService chattingService;
    @SpringBean
    private transient AccountService accountService;

    @SpringBean
    private transient PetManagerService petManagerService;

    private List<ChattingRoom> chattingRoomList;

    private List<ChattingRoom> adminChatList;

    private List<Chatting> chattingLog;

    private List<Account> accountList;
    private Chatting chatting;

    private String chattingLine;

    private ChattingRoom chattingRoom;

    private ChattingRoom updatedChattingRoom;

    private String customerId;
    private String managerId;

    private String keyword;

    private String senderId;

    private String adminName;

    private String adminId;

    private Memo memo;


    private List<Alarm> alarms;

    private List<PetManager> petManagerList;


    //-------------------------------------------------------------------------//
    private static final String VIEW_CHATTING_ROOM = "/WEB-INF/jsp/chatting/ChattingRoom.jsp";
    private static final String VIEW_PM_CHATTING_ROOM = "/WEB-INF/jsp/chatting/PetMangerChattingRoom.jsp";
    private static final String VIEW_CHATTINGROOM_MANAGER = "/WEB-INF/jsp/chatting/ChattingRoomManager.jsp";
    private static final String JOIN_CHATTING = "/WEB-INF/jsp/chatting/Chatting.jsp";
    private static final String VIEW_SEARCHED_CHATTING_ROOM = "/WEB-INF/jsp/chatting/searchChattingRoom.jsp";
    private static final String VIEW_SEARCHED_ADMIN_CHATTING_ROOM = "/WEB-INF/jsp/chatting/searchAdminChattingRoom.jsp";
    private static final String VIEW_CHATTING_MEMO = "/WEB-INF/jsp/chatting/memoChatting.jsp";
    private static final String VIEW_CHATTING_MEMO_READONLY = "/WEB-INF/jsp/chatting/memoChattingOnlyView.jsp";
    private static final String VIEW_UPDATE_CHATTING_ROOM_FORM = "/WEB-INF/jsp/chatting/UpdateChattingRoomForm.jsp";

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

    public List<PetManager> getPetManagerList() {return petManagerList;}

    public void setPetManagerList(List<PetManager> petManagerList) {this.petManagerList = petManagerList;}

    public ChattingRoom getUpdatedChattingRoom() {return updatedChattingRoom;}

    public void setUpdatedChattingRoom(ChattingRoom updatedChattingRoom) {this.updatedChattingRoom = updatedChattingRoom;}



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
            chattingRoomList = chattingService.getChatRoomListForManager(managerId);
            return new ForwardResolution(VIEW_CHATTINGROOM_MANAGER);
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
        if (permission == null || permission.equals("user")) {
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

        if(permission.equals("admin")) {
            return new ForwardResolution(VIEW_CHATTING_MEMO_READONLY);
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
        return new RedirectResolution(ChattingActionBean.class, "memoChatting");
    }

    public ForwardResolution viewUpdateChattingRoom(){
        HttpSession session = context.getRequest().getSession();
        String permission = (String) session.getAttribute("permission");
        if(!permission.equals("admin")){
            setMessage("You don't have permission to access");
            return new ForwardResolution(ERROR);
        }
        petManagerList=petManagerService.getPetMangerList();
        updatedChattingRoom = new ChattingRoom(customerId,managerId);
        return new ForwardResolution(VIEW_UPDATE_CHATTING_ROOM_FORM);
    }

    public Resolution updateChattingRoom(){
        HttpSession session = context.getRequest().getSession();
        String permission = (String) session.getAttribute("permission");
        if(!permission.equals("admin")){
            setMessage("You don't have permission to access");
            return new ForwardResolution(ERROR);
        }
        chattingRoomList = chattingService.getAllChatRoom();
        for(ChattingRoom chatroom : chattingRoomList){
            if(chatroom.getManagerId().equals(updatedChattingRoom.getManagerId())&&
                    chatroom.getCustomerId().equals(updatedChattingRoom.getCustomerId())) {
                setMessage("the ChattingRoom is duplicated. Try again with another value.");
                return new RedirectResolution(ChattingActionBean.class,"viewUpdateChattingRoom");
            }
            else{
                chattingService.updateChattingRoom(chattingRoom,updatedChattingRoom);
            }
        }
        return new RedirectResolution(ChattingActionBean.class,"viewChattingRoom");
    }
}

