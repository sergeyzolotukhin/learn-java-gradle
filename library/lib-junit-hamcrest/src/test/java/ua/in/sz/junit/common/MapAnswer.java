package ua.in.sz.junit.common;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import ua.in.sz.junit.dao.BookQuery;

import java.util.HashMap;
import java.util.Map;

class MapAnswer<T> implements Answer<T> {
	private Map<String, T> answers = new HashMap<>();

	MapAnswer<T> put(String key, T answer) {
		answers.put(key, answer);
		return this;
	}

	@Override
	public T answer(InvocationOnMock invocation) throws Throwable {
		BookQuery query = invocation.getArgument(0);
		return answers.get(query.getRole());
	}
}
