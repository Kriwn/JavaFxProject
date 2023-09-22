package cs211.project.repository;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
class AccountEventRepositoryTest {
  AccountEventRepository accountEventRepository;

  @Test
    void testRepository(){
      accountEventRepository = new AccountEventRepository();
      System.out.println(accountEventRepository.getEventJoinByAccountId(1));
      System.out.println(accountEventRepository.getEventJoinByAccountId(2));
      System.out.println(accountEventRepository.getEventOwnerByAccountId(3));
    System.out.println(accountEventRepository.getAccount_join_event());
      ArrayList<Integer> a = new ArrayList<>();
      a.add(1);
      a.add(10);
      HashMap<Integer,ArrayList<Integer>> event = accountEventRepository.getAccount_join_event();
      event.put(4,a);
      accountEventRepository.saveEventJoin(event);
  }
}