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
package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * The Interface ItemMapper.
 *
 * @author Eduardo Macarron
 */
public interface ItemMapper {

  void updateInventoryQuantity(Map<String, Object> param);

  int getInventoryQuantity(String itemId);

  List<Item> getItemListByProduct(String productId);


  Item getItem(String itemId);
  void DeleteItem(String ProductId, String itemId);

  void UpdateItem(String itemId, String arg1 , BigDecimal listprice);
  void UpdateQTY(String itemId, int quantity);

  String getCategoryName(String productId);

  void AddItem( String itemId, String productId, BigDecimal listprice, String attri);
  void AddInventory(String itemId, int qty);

}
