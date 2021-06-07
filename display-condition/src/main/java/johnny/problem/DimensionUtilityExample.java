package johnny.problem;

/**
 * Copyright (c) 2021 Ariba, Inc.
 * All rights reserved. Patents pending.
 */
public class DimensionUtilityExample {
  public static void main(String[] args) {
    DimensionUtility3.parseBoolExpr("t");
    DimensionUtility3.parseBoolExpr("f");
    DimensionUtility3.parseBoolExpr("!(f)");
    DimensionUtility3.parseBoolExpr("|(f,t)");
    DimensionUtility3.parseBoolExpr("&(t,f)");
    DimensionUtility3.parseBoolExpr("|(&(t,f,t),!(t))");
  }
}
