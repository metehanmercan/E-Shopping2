package com.example.eShopping2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "product_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class ProductImage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private int id;
  @Column(name="url")
  private String url;
  @ManyToOne
  @JoinColumn(name="product_id")
  private Product product;
}
