package org.mybatis.jpetstore.web.actions;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
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

    private List<Chatting> chattingLog;
    private Chatting chatting;

    private ChattingRoom chattingRoom;
    private String costumerId;

    private String managerId;

    //-------------------------------------------------------------------------//

    private static final String VIEW_CHATTING_ROOM = "/WEB-INF/jsp/Chatting/ChattingRoom.jsp";

    //------------------------getter & setter ---------------------------------//

    public List<ChattingRoom> getChattingRoomList() {return chattingRoomList;}
    public void setChattingRoomList(List<ChattingRoom> chattingRoomList) {this.chattingRoomList = chattingRoomList;}

    public List<Chatting> getChattingLog() {return chattingLog;}
    public void setChattingLog(List<Chatting> chattingLog) {this.chattingLog = chattingLog;}

    public Chatting getChatting() {return chatting;}
    public void setChatting(Chatting chatting) {this.chatting = chatting;}

    public ChattingRoom getChattingRoom() {return chattingRoom;}
    public void setChattingRoom(ChattingRoom chattingRoom) {this.chattingRoom = chattingRoom;}

    public String getCostumerId() {return costumerId;}
    public void setCostumerId(String costumerId) {this.costumerId = costumerId;}

    public String getManagerId() {return managerId;}
    public void setManagerId(String managerId) {this.managerId = managerId;}


    //============================================================================================

    @DefaultHandler
    public ForwardResolution viewChattingRoomForManager(){
        HttpSession session = context.getRequest().getSession();
        String permission = (String) session.getAttribute("permission");
        if (permission == null || permission.equals("petmanager") == false) {
            setMessage("펫 매니저가 아닙니다.");
            return new ForwardResolution(ERROR);
        }
        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
        managerId = accountBean.getUsername();
        chattingRoomList = chattingService.getChatRoomListForManager(managerId);
        return new ForwardResolution(VIEW_CHATTING_ROOM);
    }

}
