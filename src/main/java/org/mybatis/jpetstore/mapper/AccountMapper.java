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

import org.mybatis.jpetstore.domain.Account;
import org.mybatis.jpetstore.domain.Alarm;

import java.util.List;

/**
 * The Interface AccountMapper.
 *
 * @author Eduardo Macarron
 */
public interface AccountMapper {

  Account getAccountByUsername(String username);

  Account getAccountByUsernameAndPassword(String username, String password);

  List<Account> searchAccountByUserId(String userId);
  List<Account> getAccountListByPermission(String permission);

  void insertAccount(Account account);

  void insertProfile(Account account);

  void insertSignon(Account account);

  void updateAccount(Account account);

  void updateProfile(Account account);

  void updateSignon(Account account);

  List<Alarm> getAlarmById(String id);
  void insertAlarm(Alarm alarm);

  void updateAlarm(Alarm alarm);
}
