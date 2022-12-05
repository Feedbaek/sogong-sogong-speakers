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

import org.mybatis.jpetstore.domain.Account;
import org.mybatis.jpetstore.domain.Alarm;
import org.mybatis.jpetstore.mapper.AccountMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * The Class AccountService.
 *
 * @author Eduardo Macarron
 */
@Service
public class AccountService {

  private final AccountMapper accountMapper;

  public AccountService(AccountMapper accountMapper) {
    this.accountMapper = accountMapper;
  }

  public Account getAccount(String username) {
    return accountMapper.getAccountByUsername(username);
  }

  public Account getAccount(String username, String password) {
    return accountMapper.getAccountByUsernameAndPassword(username, password);
  }

  public Account getPetManagerAccount(String username) {
    return accountMapper.getPetManagerAccountByID(username);
  }

  public Account getPetManagerAccount(String username, String password) {
    return accountMapper.getPetManagerAccount(username, password);
  }

  public List<Account> getAccountListByPermission(String permission){return accountMapper.getAccountListByPermission(permission);}
  public List<Account> searchAccountByUserId(String userid){
    return accountMapper.searchAccountByUserId("%"+userid+"%");
  }


  /**
   * Insert account.
   *
   * @param account
   *          the account
   */
  @Transactional
  public void insertAccount(Account account) {
    accountMapper.insertAccount(account);
    accountMapper.insertProfile(account);
    accountMapper.insertSignon(account);
  }


  @Transactional
  public void insertPetManagerAccount(Account account) {
    accountMapper.insertPetManagerAccount(account);
    accountMapper.insertSignon(account);
  }
  /**
   * Update account.
   *
   * @param account
   *          the account
   */
  @Transactional
  public void updateAccount(Account account) {
    accountMapper.updateAccount(account);
    accountMapper.updateProfile(account);

    Optional.ofNullable(account.getPassword()).filter(password -> password.length() > 0)
        .ifPresent(password -> accountMapper.updateSignon(account));
  }
  @Transactional
  public void   editPetManagerAccount(Account account) {
    accountMapper.updateAccount(account);

    Optional.ofNullable(account.getPassword()).filter(password -> password.length() > 0)
            .ifPresent(password -> accountMapper.updateSignon(account));
  }
  @Transactional
  public void deleteAccountByUserId(String userId) {
    accountMapper.deleteAccountByUserId(userId);
  }

  @Transactional
  public List<Alarm> getAlarmById(String id) {
    return accountMapper.getAlarmById(id);
  }
  @Transactional
  public void insertAlarm(Alarm alarm) {
    accountMapper.insertAlarm(alarm);
  }
  @Transactional
  public void updateAlarm(Alarm alarm) {
    accountMapper.updateAlarm(alarm);
  }

}
