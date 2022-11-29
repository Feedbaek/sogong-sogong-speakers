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
package org.mybatis.jpetstore.service;

import org.mybatis.jpetstore.domain.Category;
import org.mybatis.jpetstore.domain.Item;
import org.mybatis.jpetstore.domain.Product;
import org.mybatis.jpetstore.mapper.CategoryMapper;
import org.mybatis.jpetstore.mapper.ItemMapper;
import org.mybatis.jpetstore.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class CatalogService.
 *
 * @author Eduardo Macarron
 */
@Service
public class CatalogService {

  private final CategoryMapper categoryMapper;
  private final ItemMapper itemMapper;
  private final ProductMapper productMapper;

  public CatalogService(CategoryMapper categoryMapper, ItemMapper itemMapper, ProductMapper productMapper) {
    this.categoryMapper = categoryMapper;
    this.itemMapper = itemMapper;
    this.productMapper = productMapper;
  }

  // out : product list
  public List<Category> getCategoryList() {
    return categoryMapper.getCategoryList();
  }

  public List<Product> getProductListByCategory(String categoryId) {
    return productMapper.getProductListByCategory(categoryId);
  }
  public List<Product> getProductList() {
    return productMapper.getProductList();
  }

  public List<Product> searchProductList(String keywords) {
    List<Product> products = new ArrayList<>();
    for (String keyword : keywords.split("\\s+")) {
      products.addAll(productMapper.searchProductList("%" + keyword.toLowerCase() + "%"));
    }
    return products;
  }





  public List<Item> getItemListByProduct(String productId)
  {
    return itemMapper.getItemListByProduct(productId);
  }

  // OBJ

  public Category getCategory(String categoryId) {
    return categoryMapper.getCategory(categoryId);
  }

  public Product getProduct(String productId) {
    return productMapper.getProduct(productId);
  }
  public Item getItem(String itemId) {
    return itemMapper.getItem(itemId);
  }

  public String getCategoryName(String productId)
  {
    return itemMapper.getCategoryName(productId);
  }

  public boolean isItemInStock(String itemId) {
    return itemMapper.getInventoryQuantity(itemId) > 0;
  }


  // VOID
  public void UpdateItem(String itemId, String arg1 , BigDecimal listprice,int quantity)
  {
    itemMapper.UpdateItem(itemId,arg1, listprice);
    itemMapper.UpdateQTY( itemId,quantity);
  }

  public void DeleteItem(String productId, String itemId)
  {
    itemMapper.DeleteItem(productId,itemId);
  }


  public void AddItem(String itemId, String productId, BigDecimal listprice, String attri ,int qty) {

    itemMapper.AddItem( itemId,  productId,  listprice,  attri);
    itemMapper.AddInventory(itemId,qty);
  }
}