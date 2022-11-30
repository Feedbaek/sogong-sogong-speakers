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

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.domain.Category;
import org.mybatis.jpetstore.domain.Item;
import org.mybatis.jpetstore.domain.Product;
import org.mybatis.jpetstore.service.CatalogService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;


/**
 * The Class CatalogActionBean.
 *
 * @author Eduardo Macarron
 */
@SessionScope
public class CatalogActionBean extends AbstractActionBean {

  private static final long serialVersionUID = 5849523372175050635L;

  private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
  private static final String VIEW_CATEGORY = "/WEB-INF/jsp/catalog/Category.jsp";
  private static final String VIEW_PRODUCT = "/WEB-INF/jsp/catalog/Product.jsp";
  private static final String VIEW_ITEM = "/WEB-INF/jsp/catalog/Item.jsp";
  private static final String SEARCH_PRODUCTS = "/WEB-INF/jsp/catalog/SearchProducts.jsp";
  private static final String ITEM_ADD_PAGE = "/WEB-INF/jsp/catalog/ItemAdd.jsp";
  private static final String ITEM_UPDATE_PAGE = "/WEB-INF/jsp/catalog/ItemUpdate.jsp";
  private static final String ADMIN_VIEW_CATEGORY = "/WEB-INF/jsp/catalog/AdminCategory.jsp";
  private static final String ADMIN_VIEW_PRODUCT = "/WEB-INF/jsp/catalog/AdminProduct.jsp";

  @SpringBean
  private transient CatalogService catalogService;

  private String keyword;

  private String categoryId;
  private Category category;
  private List<Category> categoryList;

  private String productId;
  private Product product;
  private List<Product> productList;

  private String itemId;
  private Item item;
  private List<Item> itemList;

  private int quantity;


  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public BigDecimal getListPrice() {
    return item.getListPrice();
  }

  public void setListPrice(BigDecimal price) {
    this.item.setListPrice(price);
  }

  public String getAttribute1() {
    return item.getAttribute1();
  }

  public void setAttribute1(String attribute1) {
    item.setAttribute1(attribute1);
  }

  public int getQuantity() {
    return item.getQuantity();
  }

  public void setQuantity(int quantity) {
    this.item.setQuantity(quantity);
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public List<Category> getCategoryList() {
    return categoryList;
  }

  public void setCategoryList(List<Category> categoryList) {
    this.categoryList = categoryList;
  }

  public List<Product> getProductList() {
    return productList;
  }

  public void setProductList(List<Product> productList) {
    this.productList = productList;
  }

  public List<Item> getItemList() {
    return itemList;
  }

  public void setItemList(List<Item> itemList) {
    this.itemList = itemList;
  }

  @DefaultHandler
  public ForwardResolution viewMain() {
    HttpSession session = context.getRequest().getSession();
    return new ForwardResolution(MAIN);
  }

  /**
   * View category.
   *
   * @return the forward resolution
   */

  // =========================================================================================
  public ForwardResolution viewItem() {
    item = catalogService.getItem(itemId);
    product = item.getProduct();
    return new ForwardResolution(VIEW_ITEM);
  }

  public ForwardResolution searchProducts() {
    if (keyword == null || keyword.length() < 1) {
      setMessage("Please enter a keyword to search for, then press the search button.");
      return new ForwardResolution(ERROR);
    } else {
      productList = catalogService.searchProductList(keyword.toLowerCase());
      return new ForwardResolution(SEARCH_PRODUCTS);
    }
  }

  // =========================================================================================
  public ForwardResolution viewCategory() {
    productList = catalogService.getProductListByCategory(categoryId);
    category = catalogService.getCategory(categoryId);
    return new ForwardResolution(VIEW_CATEGORY);
  }

  public ForwardResolution adminViewCategory() {
    String permission = (String) context.getRequest().getSession().getAttribute("permission");
    if (categoryId == null) {
      if (permission == null || permission.equals("admin") == false) {
        setMessage("You do not have permission.");
        return new ForwardResolution(ERROR);
      }
      productList = catalogService.getProductList();
    }
    return new ForwardResolution(ADMIN_VIEW_CATEGORY);
  }

  public ForwardResolution viewProduct() {
    itemList = catalogService.getItemListByProduct(productId);
    product = catalogService.getProduct(productId);
    return new ForwardResolution(VIEW_PRODUCT);
  }

  public ForwardResolution adminViewProduct() {
    String permission = (String) context.getRequest().getSession().getAttribute("permission");
    if (permission == null || permission.equals("admin") == false) {
      setMessage("You do not have permission.");
      return new ForwardResolution(ERROR);
    }
    itemList = catalogService.getItemListByProduct(productId);
    product = catalogService.getProduct(productId);
    return new ForwardResolution(ADMIN_VIEW_PRODUCT);
  }


  ////////////////////////// ITEMUPDATE
  public ForwardResolution ItemUpdatePage() { // IN : Itemlist.JSP , OUT : Itemupdate.JSP
    String permission = (String) context.getRequest().getSession().getAttribute("permission");
    if (permission == null || permission.equals("admin") == false) {
      setMessage("You do not have permission.");
      return new ForwardResolution(ERROR);
    }
    item = catalogService.getItem(itemId);
    return new ForwardResolution(ITEM_UPDATE_PAGE);
  }

  public ForwardResolution DBItemUpdate() { // IN : Itemupdate.JSP , OUT : Itemlist.JSP
    String permission = (String) context.getRequest().getSession().getAttribute("permission");
    if (permission == null || permission.equals("admin") == false) {
      setMessage("You do not have permission.");
      return new ForwardResolution(ERROR);
    }
    String resulturl = ERROR;

    if (item == null)
      setMessage("item ERROR");
    else if (item != null) {
      if (getAttribute1() == null)
        setMessage("No Description. Please fill in the description");
      else if (getListPrice() == null)
        setMessage("No ListPrice. Please fill in the ListPrice");
      else if (getQuantity() < 0)
        setMessage("Quantity is negative. Please re-enter Quantity");
      else {
        catalogService.UpdateItem(itemId, getAttribute1(), getListPrice(), getQuantity());
        itemList = catalogService.getItemListByProduct(productId);
        product = catalogService.getProduct(productId);
        resulturl = ADMIN_VIEW_PRODUCT;
      }
    }
    return new ForwardResolution(resulturl);
  }

  /////////////////////////// ITEMADD
  public ForwardResolution ItemAddPage() {// IN : Itemlist.JSP , OUT : Itemadd.JSP
    String permission = (String) context.getRequest().getSession().getAttribute("permission");
    if (permission == null || permission.equals("admin") == false) {
      setMessage("You do not have permission.");
      return new ForwardResolution(ERROR);
    }
    item = new Item();
    item.setProductId(productId);
    itemId = null;
    return new ForwardResolution(ITEM_ADD_PAGE);
  }

  public ForwardResolution DBItemAdd() {// IN : itemadd.JSP , OUT : Itemlist.JSP
    String permission = (String) context.getRequest().getSession().getAttribute("permission");
    String resulturl = ERROR;
    if (permission == null || permission.equals("admin") == false)
      setMessage("You do not have permission.");
    else if (itemId == null)
      setMessage("There no itemId, write itemId please");
    else {
      Item tempitem = catalogService.getItem(itemId);
      if (tempitem != null)
        setMessage("An itemId already exists. Please enter another itemId");
      else {
        if (getAttribute1() == null)
          setMessage("No Description. Please fill in the description");
        else if (getListPrice() == null)
          setMessage("No ListPrice. Please fill in the ListPrice");
        else if (getQuantity() < 0)
          setMessage("Quantity is negative. Please re-enter Quantity");
        else {
          catalogService.AddItem(itemId, getProductId(), getListPrice(), getAttribute1(), getQuantity());
          itemList = catalogService.getItemListByProduct(productId);
          product = catalogService.getProduct(productId);
          resulturl = ADMIN_VIEW_PRODUCT;
        }
      }
    }
    return new ForwardResolution(resulturl);
  }

  /////////////////////// // ITEM DELETE
  public ForwardResolution ItemDelete() {
    String permission = (String) context.getRequest().getSession().getAttribute("permission");
    if (permission == null || permission.equals("admin") == false) {
      setMessage("You do not have permission.");
      return new ForwardResolution(ERROR);
    }
    Item tempitem = catalogService.getItem(itemId);
    if (tempitem != null)
      catalogService.DeleteItem(productId, itemId);
    itemList = catalogService.getItemListByProduct(productId);
    product = catalogService.getProduct(productId);
    return new ForwardResolution(ADMIN_VIEW_PRODUCT);
  }

  /**
   * Clear.
   */
  public void clear() {
    keyword = null;

    categoryId = null;
    category = null;
    categoryList = null;

    productId = null;
    product = null;
    productList = null;

    itemId = null;
    item = null;
    itemList = null;

    quantity = 0;
  }
}
