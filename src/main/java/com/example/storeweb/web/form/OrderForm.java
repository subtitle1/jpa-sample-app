package com.example.storeweb.web.form;

import java.util.Arrays;

import com.example.storeweb.entity.Book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class OrderForm {

	private long[] ids;
	private int[] prices;
	private int[] quantities;
	private int totalBookPrice;
	private int totalOrderPrice;
	private int totalDiscountPrice;
	private int usePoint;
	private int totalPaymentPrice;

	public String getTitle(Book book) {
		if (ids.length > 1) {
			return book.getTitle() + " 외 " + (ids.length - 1) + "종";
		}
		return book.getTitle();
	}
	public int getTotalQuantity() {
		return Arrays.stream(quantities).sum();
	}
	public int getDepositPoint() {
		return (int) (totalPaymentPrice*0.03);
	}
}

