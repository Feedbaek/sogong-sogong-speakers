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

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.mybatis.jpetstore.domain.*;
import org.mybatis.jpetstore.service.CatalogService;
import org.mybatis.jpetstore.service.ChattingService;
import org.mybatis.jpetstore.service.OrderService;
import org.mybatis.jpetstore.service.PetManagerService;

/**
 * The Class CartActionBean.
 *
 * @author Eduardo Macarron
 */
@SessionScope
public class CartActionBean extends AbstractActionBean {

  private static final long serialVersionUID = -4038684592582714235L;

  private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
  private static final String CHECK_OUT = "/WEB-INF/jsp/cart/Checkout.jsp";

  @SpringBean
  private transient CatalogService catalogService;

  @SpringBean
  private transient OrderService orderService;

  @SpringBean
  private transient ChattingService chattingService;

  @SpringBean
  private transient PetManagerService petManagerService;
  //추가

  private Cart cart = new Cart();

  private List<ChattingRoom> chattingRoomList;
  private String workingItemId;

  private  String catdog;

  private  String repfish;

  private  String bird;
  public Cart getCart() {
    return cart;
  }

  public String getCatdog() {
    return catdog;
  }

  public void setCatdog(String catdog) {
    this.catdog = catdog;
  }

  public String getRepfish() {
    return repfish;
  }

  public void setRepfish(String repfish) {
    this.repfish = repfish;
  }

  public String getBird() {
    return bird;
  }

  public void setBird(String bird) {
    this.bird = bird;
  }

  public List<ChattingRoom> getChattingRoomList() {
    return chattingRoomList;
  }

  public void setChattingRoomList(List<ChattingRoom> chattingRoomList) {
    this.chattingRoomList = chattingRoomList;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

  public void setWorkingItemId(String workingItemId) {
    this.workingItemId = workingItemId;
  }

  /**
   * Adds the item to cart.
   *
   * @return the resolution
   */
  public Resolution addItemToCart() {
    if (cart.containsItemId(workingItemId)) {
      cart.incrementQuantityByItemId(workingItemId);
    } else {
      // isInStock is a "real-time" property that must be updated
      // every time an item is added to the cart, even if other
      // item details are cached.
      boolean isInStock = catalogService.isItemInStock(workingItemId);
      Item item = catalogService.getItem(workingItemId);
      cart.addItem(item, isInStock);
    }
    handle_duplicate();
    return new ForwardResolution(VIEW_CART);
  }

  /**
   * Removes the item from cart.
   *
   * @return the resolution
   */
  public Resolution removeItemFromCart() {

    Item item = cart.removeItemById(workingItemId);

    if (item == null) {
      setMessage("Attempted to remove null CartItem from Cart.");
      return new ForwardResolution(ERROR);
    } else {
      handle_duplicate();
      return new ForwardResolution(VIEW_CART);
    }
  }

  /**
   * Update cart quantities.
   *
   * @return the resolution
   */
  public Resolution updateCartQuantities() {
    HttpServletRequest request = context.getRequest();

    Iterator<CartItem> cartItems = getCart().getAllCartItems();
    if(request.getParameter("catDog") != null)
      getCart().setCatDog(true);
    else
      getCart().setCatDog(false);
    if(request.getParameter("repFish") != null)
      getCart().setRepFish(true);
    else
      getCart().setRepFish(false);
    if(request.getParameter("bird") != null)
      getCart().setBird(true);
    else
      getCart().setBird(false);
    while (cartItems.hasNext()) {
      CartItem cartItem = cartItems.next();
      String itemId = cartItem.getItem().getItemId();
      try {
        int quantity = Integer.parseInt(request.getParameter(itemId));
        getCart().setQuantityByItemId(itemId, quantity);
        if (quantity < 1) {
          cartItems.remove();
        }
      } catch (Exception e) {
        // ignore parse exceptions on purpose
      }
    }
    handle_duplicate();
    return new ForwardResolution(VIEW_CART);
  }

  public ForwardResolution viewCart() {
    handle_duplicate();
    return new ForwardResolution(VIEW_CART);
  }

  public ForwardResolution checkOut() {
    return new ForwardResolution(CHECK_OUT);
  }

  public void clear() {
    cart = new Cart();
    workingItemId = null;
  }

  public void handle_duplicate()
  {
    HttpSession session = context.getRequest().getSession();
    String permission = (String) session.getAttribute("permission");
    if (permission == null)
      return ;
    if (permission.equals("user"))
    {
      AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
      String userId = accountBean.getUsername();
      String managerId;
      chattingRoomList = chattingService.getChatRoomListForUser(userId);
      for (int i = 0;i < chattingRoomList.size();i++)
      {
        managerId = chattingRoomList.get(i).getManagerId();
        PetManager petManager= petManagerService.getPetMangerByID(managerId);
        if (petManager.getPetType().equals("CAT/DOG"))
          setCatdog("catdog");
        else if (petManager.getPetType().equals("REPTILE/FISH"))
          setRepfish("repfish");
        else if (petManager.getPetType().equals("BIRD"))
          setBird("bird");
      }
      List<Order> orderList = orderService.getOrdersByUsername(userId);
      if (orderList != null && orderList.size() > 0)
      {
        for (int i = 0;i < orderList.size();i++)
        {
          Order order = orderList.get(i);
          if (order.getCatDog())
            setCatdog("catdog");
          else if (order.getRepFish())
            setRepfish("repfish");
          else if (order.getBird())
            setBird("bird");
        }
      }
    }
    else
    {
      setCatdog("catdog");
      setRepfish("repfish");
      setBird("bird");
    }
  }

}
