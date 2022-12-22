package com.example.shoppingmall.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "cart_item_table")
public class CartItemEntity {
}
