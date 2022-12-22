package com.example.shoppingmall.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "order_item_table")
public class OrderItemEntity {
}
