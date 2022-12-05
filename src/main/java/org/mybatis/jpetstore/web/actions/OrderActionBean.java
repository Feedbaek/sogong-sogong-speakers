/*
 *    Copyright 2010-2022 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.jpetstore.web.actions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.mybatis.jpetstore.domain.*;
import org.mybatis.jpetstore.service.AccountService;
import org.mybatis.jpetstore.service.ChattingService;
import org.mybatis.jpetstore.service.OrderService;
import org.mybatis.jpetstore.service.PetManagerService;

/**
 * The Class OrderActionBean.
 *
 * @author Eduardo Macarron
 */
@SessionScope
public class OrderActionBean extends AbstractActionBean {

  private static final long serialVersionUID = -6171288227470176272L;

  private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
  private static final String LIST_ORDERS = "/WEB-INF/jsp/order/ListOrders.jsp";
  private static final String NEW_ORDER = "/WEB-INF/jsp/order/NewOrderForm.jsp";
  private static final String SHIPPING = "/WEB-INF/jsp/order/ShippingForm.jsp";
  private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
  private static final String CHOOSE_PM = "/WEB-INF/jsp/order/ChoosePetManager.jsp";

  private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
  private static final List<String> CARD_TYPE_LIST;

  @SpringBean
  private transient OrderService orderService;

  private Order order = new Order();
  @SpringBean
  private transient PetManagerService petManagerService;

  @SpringBean
  private transient ChattingService chattingService;

  @SpringBean
  private transient AccountService accountService;

  private ChattingRoom chattingRoom = new ChattingRoom();

  private List<ChattingRoom> chattingRoomList;

  private List<PetManager> petManagerList;

  private boolean shippingAddressRequired;
  private boolean confirmed;

  private boolean _showChooseButton;
  private List<Order> orderList;

  static {
    CARD_TYPE_LIST = Collections.unmodifiableList(Arrays.asList("Visa", "MasterCard", "American Express"));
  }

  public boolean is_showChooseButton() {
    return _showChooseButton;
  }

  public void set_showChooseButton(boolean _showChooseButton) {
    this._showChooseButton = _showChooseButton;
  }

  public List<PetManager> getPetManagerList() {
    return petManagerList;
  }

  public void setPetManagerList(List<PetManager> petManagerList) {
    this.petManagerList = petManagerList;
  }

  public int getOrderId() {
    return order.getOrderId();
  }

  public void setOrderId(int orderId) {
    order.setOrderId(orderId);
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public boolean isShippingAddressRequired() {
    return shippingAddressRequired;
  }

  public void setShippingAddressRequired(boolean shippingAddressRequired) {
    this.shippingAddressRequired = shippingAddressRequired;
  }

  public boolean isConfirmed() {
    return confirmed;
  }

  public void setConfirmed(boolean confirmed) {
    this.confirmed = confirmed;
  }

  public List<String> getCreditCardTypes() {
    return CARD_TYPE_LIST;
  }

  public List<Order> getOrderList() {
    return orderList;
  }

  public ChattingRoom getChattingRoom() {
    return chattingRoom;
  }

  public void setChattingRoom(ChattingRoom chattingRoom) {
    this.chattingRoom = chattingRoom;
  }

  /**
   * List orders.
   *
   * @return the resolution
   */
  public Resolution listOrders() {
    HttpSession session = context.getRequest().getSession();
    AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
    orderList = orderService.getOrdersByUsername(accountBean.getAccount().getUsername());
    return new ForwardResolution(LIST_ORDERS);
  }

  /**
   * New order form.
   *
   * @return the resolution
   */
  public Resolution newOrderForm() {
    HttpSession session = context.getRequest().getSession();
    AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
    CartActionBean cartBean = (CartActionBean) session.getAttribute("/actions/Cart.action");

    clear();
    if (accountBean == null || !accountBean.isAuthenticated()) {
      setMessage("You must sign on before attempting to check out.  Please sign on and try checking out again.");
      return new ForwardResolution(AccountActionBean.class);
    } else if (cartBean != null) {
      order.initOrder(accountBean.getAccount(), cartBean.getCart());
      return new ForwardResolution(NEW_ORDER);
    } else {
      setMessage("An order could not be created because a cart could not be found.");
      return new ForwardResolution(ERROR);
    }
  }

  /**
   * New order.
   *
   * @return the resolution
   */
  public Resolution newOrder() {
    HttpSession session = context.getRequest().getSession();

    if (shippingAddressRequired) {
      shippingAddressRequired = false;
      return new ForwardResolution(SHIPPING);
    } else if (!isConfirmed()) {
      return new ForwardResolution(CONFIRM_ORDER);
    } else if (getOrder() != null) {

      orderService.insertOrder(order);

      CartActionBean cartBean = (CartActionBean) session.getAttribute("/actions/Cart.action");
      cartBean.clear();

      setMessage("Thank you, your order has been submitted.");
      set_showChooseButton(true);
      return new ForwardResolution(VIEW_ORDER);
    } else {
      setMessage("An error occurred processing your order (order was null).");
      return new ForwardResolution(ERROR);
    }
  }

  /**
   * View order.
   *
   * @return the resolution
   */
  public Resolution viewOrder() {
    HttpSession session = context.getRequest().getSession();

    AccountActionBean accountBean = (AccountActionBean) session.getAttribute("accountBean");

    order = orderService.getOrder(order.getOrderId());
    if (accountBean.getAccount().getUsername().equals(order.getUsername())) {
      String senderId = accountBean.getUsername();
      chattingRoomList = chattingService.getChatRoomListForUser(senderId);
      boolean catDog_select = order.getCatDog();
      boolean repFish_select = order.getRepFish();
      boolean bird_select = order.getBird();
      set_showChooseButton(true);
      if (catDog_select)
      {
        for (int i = 0;i < chattingRoomList.size();i++)
        {
          String managerID = chattingRoomList.get(i).getManagerId();
          PetManager petManager = petManagerService.getPetMangerByID(managerID);
          if (petManager.getCatdog())
              set_showChooseButton(false);
        }
      }
      if (repFish_select)
      {
        for (int i = 0;i < chattingRoomList.size();i++)
        {
          String managerID = chattingRoomList.get(i).getManagerId();
          PetManager petManager = petManagerService.getPetMangerByID(managerID);
          if (petManager.getRepfish())
            set_showChooseButton(false);
        }
      }
      if (bird_select)
      {
        for (int i = 0;i < chattingRoomList.size();i++)
        {
          String managerID = chattingRoomList.get(i).getManagerId();
          PetManager petManager = petManagerService.getPetMangerByID(managerID);
          if (petManager.getBird())
            set_showChooseButton(false);
        }
      }
      return new ForwardResolution(VIEW_ORDER);
    } else {
      order = null;
      setMessage("You may only view your own orders.");
      return new ForwardResolution(ERROR);
    }
  }

  public Resolution choosePetManager()
  {
    order = orderService.getOrder(order.getOrderId());
    if (petManagerList != null)
      petManagerList.clear();
    if (order.getCatDog())
    {
      petManagerList = petManagerService.getCatDogManagerList();
      System.out.println(petManagerList.get(0).getCatdog());
    }
    if (order.getRepFish())
    {
      if (petManagerList == null || petManagerList.size() == 0)
        petManagerList = petManagerService.getRepFishManagerList();
      else
        petManagerList.addAll(petManagerService.getRepFishManagerList());
    }
    if (order.getBird())
    {
      if (petManagerList == null || petManagerList.size() == 0)
        petManagerList = petManagerService.getBirdManagerList();
      else
        petManagerList.addAll(petManagerService.getBirdManagerList());
    }
    return new ForwardResolution(CHOOSE_PM);
  }

  public Resolution submitPetManager() {
    HttpServletRequest request = context.getRequest();
    HttpSession session = context.getRequest().getSession();
    AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");

    String userId = accountBean.getUsername();
    String managerId;

    if((managerId = request.getParameter("catDog")) != null) {
      chattingRoom = new ChattingRoom(userId, managerId);
      chattingService.createChattingRoom(chattingRoom);
//      알람추가
      Alarm manager = new Alarm();
      Alarm user = new Alarm();
      manager.setSenderId(managerId);
      manager.setReceiverId(userId);
      manager.setAlarm("off");
      user.setSenderId(userId);
      user.setReceiverId(managerId);
      user.setAlarm("off");
      accountService.insertAlarm(manager);
      accountService.insertAlarm(user);
    }
    if((managerId = request.getParameter("repFish")) != null) {
      chattingRoom = new ChattingRoom(userId, managerId);
      chattingService.createChattingRoom(chattingRoom);
      //      알람추가
      Alarm manager = new Alarm();
      Alarm user = new Alarm();
      manager.setSenderId(managerId);
      manager.setReceiverId(userId);
      manager.setAlarm("off");
      user.setSenderId(userId);
      user.setReceiverId(managerId);
      user.setAlarm("off");
      accountService.insertAlarm(manager);
      accountService.insertAlarm(user);
    }
    if((managerId = request.getParameter("bird")) != null) {
      chattingRoom = new ChattingRoom(userId, managerId);
      chattingService.createChattingRoom(chattingRoom);
      //      알람추가
      Alarm manager = new Alarm();
      Alarm user = new Alarm();
      manager.setSenderId(managerId);
      manager.setReceiverId(userId);
      manager.setAlarm("off");
      user.setSenderId(userId);
      user.setReceiverId(managerId);
      user.setAlarm("off");
      accountService.insertAlarm(manager);
      accountService.insertAlarm(user);
    }
    return new ForwardResolution(MAIN);
  }

  /**
   * Clear.
   */
  public void clear() {
    order = new Order();
    shippingAddressRequired = false;
    confirmed = false;
    orderList = null;
  }

}
