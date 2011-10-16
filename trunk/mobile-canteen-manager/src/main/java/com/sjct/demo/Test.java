package com.sjct.demo;

public class Test {
	public static void main(String[] args) {
        Product proc = new Product();
        
        NameObserver nOberserver = new NameObserver();
        PriceObserver pOberserver = new PriceObserver();
        
        proc.addObserver(nOberserver);
        proc.addObserver(pOberserver);
        
        proc.setName("javaeye");
        proc.setPrice(21423f);
	}

}
