package com.epam.jmp.tasks.mvc.domain;

import org.shop.data.Proposal;

public class ProposalDto {
	
	public ProposalDto(){super();}
	
	public ProposalDto(Proposal proposal){
		super();
		this.id = proposal.getId();
		this.productId = proposal.getProduct().getId();
		this.sellerId = proposal.getSeller().getId();
		this.price = proposal.getPrice();
	}
	
    private Long id;
    
    private Long productId;

    private Long sellerId;
    
    private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
    
    
}
