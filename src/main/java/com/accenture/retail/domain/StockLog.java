package com.accenture.retail.domain;

import java.util.ArrayList;
import java.util.List;

public class StockLog extends StockCounter {
	private List<StockOperation> operations;
	private long count;
	private long counterNumber;
	
	public long getCounterNumber() {
		return counterNumber;
	}

	public void setCounterNumber(long counterNumber) {
		this.counterNumber = counterNumber;
	}

	public List<StockOperation> getOperations() {
		return operations;
	}

	public void setOperations(List<StockOperation>  operations) {
		this.operations = operations;
	}
	
	public void addOperation(StockOperation operation) {
		if(operations==null) {
			operations=new ArrayList<StockOperation>(1);
			count=0l;
		}
		operations.add(operation);
		count++;
	}
	
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	
}