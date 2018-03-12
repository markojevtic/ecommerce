package com.prodyna.ecommerce.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Category {

	private String categoryId;
	private String name;

}
