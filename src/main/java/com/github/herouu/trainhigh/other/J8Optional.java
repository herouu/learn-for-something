package com.github.herouu.trainhigh.other;

import com.alibaba.fastjson.JSON;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class J8Optional {


  public static void main(String[] args) {
    optionalBasic();
    System.out.println("===========================");
    optionalMapFlatMap();
    System.out.println("===========================");
    optioncalFilter();
    System.out.println("===========================");
    optionalIfPresent();
    System.out.println("===========================");
    optionalOrElse();
    System.out.println("===========================");
    optionalorElseGet();
    System.out.println("===========================");
    diffOrElseOrElseGet();
  }

  public static void optionalBasic() {
    String str = "user";
    String str1 = null;
    System.out.println(Optional.ofNullable(str).get());
    System.out.println(Optional.ofNullable(str1));
    /**
     * 下面这行代码报错
     */
    //System.out.println(Optional.ofNullable(str1).get());

  }

  public static void optionalMapFlatMap() {
    Optional<Optional<String>> nonEmptyOtionalGender = Optional.of(Optional.of("male"));
    System.out.println("Optional value   :: " + nonEmptyOtionalGender);
    System.out.println("Optional.map     :: " + nonEmptyOtionalGender
        .map(gender -> gender.map(String::toUpperCase)));
    System.out.println("Optional.flatMap :: " + nonEmptyOtionalGender
        .flatMap(gender -> gender.map(String::toUpperCase)));
  }

  public static void optioncalFilter() {
    Optional<String> gender = Optional.of("MALE");
    Optional<String> emptyGender = Optional.empty();

    //filter 过滤 返回满足条件的optional
    System.out.println(gender.filter(g -> g.equals("male")));
    System.out.println(gender.filter(g -> g.equalsIgnoreCase("MALE")));
    System.out.println(emptyGender.filter(g -> g.equalsIgnoreCase("MALE")));
  }

  public static void optionalIfPresent() {
    Optional<String> gender = Optional.of("MALE");
    Optional<String> emptyGender = Optional.empty();

    if (gender.isPresent()) {
      System.out.println("Value available.");
    } else {
      System.out.println("Value not available.");
    }

    gender.ifPresent(g -> System.out.println("条件满足，有输出"));

    //condition failed, no output print
    emptyGender.ifPresent(g -> System.out.println("条件失败，无输出"));

    /**
     * 满足条件，有输出
     */
    List<Integer> integers = Arrays.asList(1, 2, 2, 3, 4);
    Optional.ofNullable(integers).ifPresent(e -> {
      Set<Integer> collect = e.stream().collect(Collectors.toSet());
      System.out.println(JSON.toJSONString(collect));
    });
    /**
     * 下面代码不满足条件无输出
     */
    integers = null;
    Optional.ofNullable(integers).ifPresent(e -> {
      Set<Integer> collect = e.stream().collect(Collectors.toSet());
      System.out.println(JSON.toJSONString(collect));
    });
  }

  /**
   * orElseGet与orElse方法类似，区别在于得到的默认值。orElse方法将传入的参数字符串作为默认值,orElseGet方法可以接受Supplier接口的实现用来生成默认值。
   */

  public static void optionalorElseGet() {
    Optional<String> optional = Optional.ofNullable("123");
    System.out.println(optional.orElseGet(() -> "123456"));
    optional = Optional.ofNullable(null);
    System.out.println(optional.orElseGet(J8Optional::defaultOrElseGet));
  }

  static String defaultOrElseGet() {
    System.out.println("Setting default value...");
    return "defaultOrElseGet";
  }


  public static void optionalOrElse() {
    Optional<String> optional = Optional.ofNullable("123");
    System.out.println(optional.orElse("有没有"));
    optional = Optional.ofNullable(null);
    System.out.println(optional.orElse("有没有000"));
  }

  /**
   * 当option 为null时，两个方法无区别 当option 不为null时，orElse 中的方法体执行，没有返回值 orElseGet中的方法体不执行
   */
  public static void diffOrElseOrElseGet() {
    System.out.println("==================测试option 不为 null orElseGet与get方法区别========");
    Optional<String> nonEmptyOption = Optional.ofNullable("123");
    System.out.println(nonEmptyOption.orElseGet(() -> defaultOrElseGet()));
    System.out.println("--------------------");
    System.out.println(nonEmptyOption.orElse(defaultOrElseGet()));
    System.out.println("==================测试option 为 null orElseGet与get方法区别========");
    Optional<String> emptyOption = Optional.ofNullable(null);
    System.out.println(emptyOption.orElseGet(() -> defaultOrElseGet()));
    System.out.println(emptyOption.orElse(defaultOrElseGet()));
  }


}
