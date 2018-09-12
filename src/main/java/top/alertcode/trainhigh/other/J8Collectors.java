package top.alertcode.trainhigh.other;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;

public class J8Collectors {

  public static void main(String[] args) {
    toList();
    toSet();
    toMap();
  }

  public static void toList() {
    List<Integer> collect = Arrays.asList(1, 2, 3, 3, 3, 4, 4).stream()
        .collect(Collectors.toList());
    System.out.println(JSON.toJSONString(collect));
  }

  public static void toSet() {
    Set<Integer> collect = Arrays.asList(1, 2, 3, 3, 3, 4, 4).stream()
        .collect(Collectors.toSet());
    System.out.println(JSON.toJSONString(collect));
  }

  public static void toMap() {
    List<Demo> list = new ArrayList<>();
    list.add(new Demo("1", "1"));
    list.add(new Demo("2", "2"));
    list.add(new Demo("3", "3"));
    Map<String, String> collect = list.stream()
        .collect(Collectors.toMap(Demo::getId, Demo::getName));
    System.out.println(JSON.toJSONString(collect));
  }

  @Data
  static class Demo {

    private String id;
    private String name;

    public Demo() {
    }

    public Demo(String id, String name) {
      this.id = id;
      this.name = name;
    }
  }

}
