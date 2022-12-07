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

import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;

import org.mybatis.jpetstore.domain.Account;
import org.mybatis.jpetstore.domain.Alarm;
import org.mybatis.jpetstore.domain.Product;
import org.mybatis.jpetstore.service.AccountService;
import org.mybatis.jpetstore.service.CatalogService;
import org.mybatis.jpetstore.service.ChattingService;

/**
 * The Class AccountActionBean.
 *
 * @author Eduardo Macarron
 */
@SessionScope
public class AccountActionBean extends AbstractActionBean {

  private static final long serialVersionUID = 5499663666155758178L;

  private static final String NEW_ACCOUNT = "/WEB-INF/jsp/account/NewAccountForm.jsp";
  private static final String EDIT_ACCOUNT = "/WEB-INF/jsp/account/EditAccountForm.jsp";
  private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";

  private static final String ALL_ACCOUNT_VIEW = "/WEB-INF/jsp/chatting/adminChattingRoom.jsp";

  private static final List<String> LANGUAGE_LIST;
  private static final List<String> CATEGORY_LIST;

  @SpringBean
  private transient AccountService accountService;
  @SpringBean
  private transient CatalogService catalogService;

  @SpringBean
  private transient ChattingService chattingService;

  private Account account = new Account();

  private List<Account> accountList;
  private List<Product> myList;

  private String keyword;
  private boolean authenticated;


  static {
    LANGUAGE_LIST = Collections.unmodifiableList(Arrays.asList("english", "japanese"));
    CATEGORY_LIST = Collections.unmodifiableList(Arrays.asList("FISH", "DOGS", "REPTILES", "CATS", "BIRDS"));
  }

  public Account getAccount() {
    return this.account;
  }

  public String getUsername() {
    return account.getUsername();
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  @Validate(required = true, on = { "signon", "newAccount", "editAccount" })
  public void setUsername(String username) {
    account.setUsername(username);
  }

  public String getPassword() {
    return account.getPassword();
  }

  @Validate(required = true, on = { "signon", "newAccount", "editAccount" })
  public void setPassword(String password) {
    account.setPassword(password);
  }

  public List<Product> getMyList() {
    return myList;
  }

  public void setMyList(List<Product> myList) {
    this.myList = myList;
  }

  public List<String> getLanguages() {
    return LANGUAGE_LIST;
  }

  public List<String> getCategories() {
    return CATEGORY_LIST;
  }

  public List<Account> getAccountList() {return accountList;}

  public void setAccountList(List<Account> accountList) {this.accountList = accountList;}

  public String getAdminName(){return accountService.getAccountListByPermission("admin").get(0).getFirstName()+
          " "+
          accountService.getAccountListByPermission("admin").get(0).getLastName();}
  public String getAdminId(){return accountService.getAccountListByPermission("admin").get(0).getUsername();}

  public List<Account> searchAccountByUserId(String userId){
    return accountService.searchAccountByUserId(userId);
  }

  public Resolution newAccountForm() {
    return new ForwardResolution(NEW_ACCOUNT);
  }

  /**
   * New account.
   *
   * @return the resolution
   */
  public Resolution newAccount() {
    try {
      accountService.insertAccount(account);
    }catch (Exception e){
      setMessage("ERROR: Fill in the blank or your ID is duplicated.");
      return new ForwardResolution(NEW_ACCOUNT);
    }
    account = accountService.getAccount(account.getUsername());
    myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
    authenticated = true;
    return new RedirectResolution(CatalogActionBean.class);
  }

  /**
   * Edits the account form.
   *
   * @return the resolution
   */
  public Resolution editAccountForm() {
    return new ForwardResolution(EDIT_ACCOUNT);
  }

  /**
   * Edits the account.
   *
   * @return the resolution
   */
  public Resolution editAccount() {
    try {
      accountService.updateAccount(account);
    }catch (Exception e){
      setMessage("ERROR: Fill in the blank Or You ID is Duplicated.");
      return new ForwardResolution(EDIT_ACCOUNT);
    }
    account = accountService.getAccount(account.getUsername());
    myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
    return new RedirectResolution(CatalogActionBean.class);
  }

  public ForwardResolution viewAllAccountExceptManager(){
    HttpSession session = context.getRequest().getSession();
    String permission = (String) session.getAttribute("permission");
    if(permission.equals("admin")) {
      accountList = accountService.getAccountListByPermission("user");
      return new ForwardResolution(ALL_ACCOUNT_VIEW);
    }
    return new ForwardResolution(ERROR);
  }

  /**
   * Signon form.
   *
   * @return the resolution
   */
  @DefaultHandler
  public Resolution signonForm() {
    return new ForwardResolution(SIGNON);
  }

  /**
   * Signon.
   *
   * @return the resolution
   */
  public Resolution signon() {

    String username = getUsername();
    String password = getPassword();
    account = accountService.getAccount(username, password);

    if (account == null) {

      account = accountService.getPetManagerAccount(username, password);
      if (account == null)
      {
        String value = "Invalid username or password.  Signon failed.";
        setMessage(value);
        clear();
        return new ForwardResolution(SIGNON);
      }
    }
    else
        myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
      account.setPassword(null);
      authenticated = true;
      HttpSession s = context.getRequest().getSession();
      // this bean is already registered as /actions/Account.action
      s.setAttribute("accountBean", this);
      s.setAttribute("permission", account.getPermission());
      List<Alarm> alarms = accountService.getAlarmById(account.getUsername());
      s.setAttribute("alarms", alarms);
      s.setAttribute("path", context.getRequest().getServletContext().getRealPath("images") + "/");
      return new RedirectResolution(CatalogActionBean.class);
  }

  /**
   * Signoff.
   *
   * @return the resolution
   */
  public Resolution signoff() {
    context.getRequest().getSession().invalidate();
    clear();
    return new RedirectResolution(CatalogActionBean.class);
  }

  /**
   * Checks if is authenticated.
   *
   * @return true, if is authenticated
   */
  public boolean isAuthenticated() {
    return authenticated && account != null && account.getUsername() != null;
  }

  /**
   * Clear.
   */
  public void clear() {
    account = new Account();
    myList = null;
    authenticated = false;
  }

}
