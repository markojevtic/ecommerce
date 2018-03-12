package com.prodyna.ecommerce.resource.dto;

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
public class CategoryDTO {

	private String categoryId;
	private String name;

}
