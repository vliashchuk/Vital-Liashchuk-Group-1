package com.epam.jmp.tasks.mvc.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.shop.data.Proposal;

@XmlRootElement(name="proposals")
public class ProposalList {

	private List<Proposal> proposals;

	public ProposalList(){super();}
	
	public ProposalList(List<Proposal> proposals) {
		super();
		this.proposals = proposals;
	}

	@XmlElement(name="proposals")
	public List<Proposal> getProposals() {
		return proposals;
	}

	public void setProposals(List<Proposal> proposals) {
		this.proposals = proposals;
	}
}
