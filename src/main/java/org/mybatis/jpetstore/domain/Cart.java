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
package org.mybatis.jpetstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * The Class Cart.
 *
 * @author Eduardo Macarron
 */
public class Cart implements Serializable {

  private static final long serialVersionUID = 8329559983943337176L;

  private final Map<String, CartItem> itemMap = Collections.synchronizedMap(new HashMap<>());
  private final List<CartItem> itemList = new ArrayList<>();

  public Iterator<CartItem> getCartItems() {
    return itemList.iterator();
  }

  public List<CartItem> getCartItemList() {
    return itemList;
  }

  public int getNumberOfItems() {
    return itemList.size();
  }

  public Iterator<CartItem> getAllCartItems() {
    return itemList.iterator();
  }

  private boolean catDog;
  private boolean repFish;
  private boolean bird;

  public boolean getCatDog() { return catDog; }
  public void setCatDog(boolean catDog) {
    this.catDog = catDog;
  }


  public boolean getRepFish() { return repFish; }

  public void setRepFish(boolean repFish) {
    this.repFish = repFish;
  }

  public boolean getBird() { return bird; }

  public void setBird(boolean bird) {
    this.bird = bird;
  }


  public boolean containsItemId(String itemId) {
    return itemMap.containsKey(itemId);
  }

  /**
   * Adds the item.
   *
   * @param item
   *          the item
   * @param isInStock
   *          the is in stock
   */
  public void addItem(Item item, boolean isInStock) {
    CartItem cartItem = itemMap.get(item.getItemId());
    if (cartItem == null) {
      cartItem = new CartItem();
      cartItem.setItem(item);
      cartItem.setQuantity(0);
      cartItem.setInStock(isInStock);
      itemMap.put(item.getItemId(), cartItem);
      itemList.add(cartItem);
    }
    cartItem.incrementQuantity();
  }

  /**
   * Removes the item by id.
   *
   * @param itemId
   *          the item id
   *
   * @return the item
   */
  public Item removeItemById(String itemId) {
    CartItem cartItem = itemMap.remove(itemId);
    if (cartItem == null) {
      return null;
    } else {
      itemList.remove(cartItem);
      return cartItem.getItem();
    }
  }

  /**
   * Increment quantity by item id.
   *
   * @param itemId
   *          the item id
   */
  public void incrementQuantityByItemId(String itemId) {
    CartItem cartItem = itemMap.get(itemId);
    cartItem.incrementQuantity();
  }

  public void setQuantityByItemId(String itemId, int quantity) {
    CartItem cartItem = itemMap.get(itemId);
    cartItem.setQuantity(quantity);
  }

  /**
   * Gets the sub total.
   *
   * @return the sub total
   */

  public BigDecimal getSubTotal() {
    BigDecimal cost;
    BigDecimal total = itemList.stream()
            .map(cartItem -> cartItem.getItem().getListPrice().multiply(new BigDecimal(cartItem.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
      if (catDog) {
        cost = new BigDecimal("30");
        total = total.add(cost);
      }
      if (repFish) {
        cost = new BigDecimal("30");
        total = total.add(cost);
      }
      if (bird) {
        cost = new BigDecimal("30");
        total = total.add(cost);
      }
    return total;
  }
}
