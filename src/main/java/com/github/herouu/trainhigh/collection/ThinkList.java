package com.github.herouu.trainhigh.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @author alertcode
 * @date 2018-10-17
 * @copyright alertcode.top
 */
public class ThinkList {

  public static void main(String[] args) {
    ArrayList<StringAddress> strings = new ArrayList<>(Collections.nCopies(4, new StringAddress("hello")));
    System.out.println(strings);

    new ArrayList<>();
    new LinkedList<>();
    new HashMap<>();
    new LinkedHashMap<>();


  }
}


class StringAddress {

  private String s;

  public StringAddress(String s) {
    this.s = s;
  }

  @Override
  public String toString() {
    return super.toString() + " " + s;
  }

}
