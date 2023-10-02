package com.example.sneakers.enums;

import com.example.sneakers.entity.Sneaker;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public enum Model {
  JORDAN_1,
  JORDAN_2,
  JORDAN_3,
  JORDAN_4,
  JORDAN_5,
  JORDAN_6,
  JORDAN_7,
  JORDAN_8,
  JORDAN_9,
  JORDAN_10,
  JORDAN_11,
  JORDAN_12,
  JORDAN_13,
  JORDAN_14,
  JORDAN_15,
  JORDAN_16,
  JORDAN_17,
  JORDAN_18,
  JORDAN_19,
  JORDAN_20,
  JORDAN_21,
  JORDAN_22,
  JORDAN_23,
  JORDAN_24,
  JORDAN_25,
  JORDAN_26,
  JORDAN_27,
  JORDAN_28,
  JORDAN_29,
  JORDAN_30,
  JORDAN_31,
  JORDAN_32,
  JORDAN_33,
  JORDAN_34,
  JORDAN_35,
  JORDAN_36,

  // ADIDAS

  SUPERSTAR,
  STAN_SMITH,
  CAMPUS,
  SAMBA,

  // NIKE

  DUNK,
  DUNK_SB,
  AIR_FORCE_1,

  // NEW BALANCE

  N530,
  N550,
  N9060,
  N2002R;


  public boolean correctModel(Sneaker sneaker) {
    Model[] models = values();
    List<Model> list = Arrays.asList(models);
    Model sneakerModel = sneaker.getModel();
    Brand brand = sneaker.getBrand();
    if (brand == Brand.JORDAN) {
      list = list.subList(0, 35);
      return list.contains(sneakerModel);
    } else if (brand == Brand.ADIDAS) {
      list = list.subList(36, 39);
      return list.contains(sneakerModel);
    } else if (brand == Brand.NIKE) {
      list = list.subList(40, 42);
      return list.contains(sneakerModel);
    } else if (brand == Brand.NEW_BALANCE) {
      list = list.subList(43, 46);
      return list.contains(sneakerModel);
    }
    return false;
  }

}
