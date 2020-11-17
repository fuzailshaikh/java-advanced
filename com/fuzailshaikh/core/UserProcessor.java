package com.fuzailshaikh.core;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.fuzailshaikh.model.User;

public class UserProcessor {

	public List<User> process(List<User> users) throws InterruptedException, ExecutionException {
		CompletableFuture<Map<String, Double>> fxRates = CompletableFuture.supplyAsync(this::getFxRates);
		CompletableFuture<List<User>> usersList = CompletableFuture.completedFuture(users);

		CompletableFuture<List<User>> result = usersList
																									.thenApply(this::filterMinors)
																									.thenApply(this::onlyBalanceGreaterThan500)
																									.thenCombine(fxRates, this::correctBalance);
								
		return result.get();
	}

	public Map<String, Double> getFxRates(){
		return Map.of("United States", 1.0, "India", 74.46, "Canada", 1.31, "Japan", 104.19);
	}

	public List<User> filterMinors(List<User> users){
		return users.stream().filter(i -> i.age > 18).collect(Collectors.toList());
	}

	public List<User> onlyBalanceGreaterThan500(List<User> users){
		return users.stream().filter(i -> i.balance > 500).collect(Collectors.toList());
	}

	public List<User> correctBalance(List<User> users, Map<String, Double> rates) {
		return users.stream().map(u -> {
			u.balance = u.balance * rates.get(u.country);
			return u;
		}).collect(Collectors.toList());
	}
	
}
