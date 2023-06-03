package ua.in.sz.junit.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class BookServiceImpl implements BookService {
	@Override
	public List<String> findTitles() {
		return Arrays.asList("1", "2", "3");
	}

	@Override
	public List<BookVO> find() {
		return Arrays.asList(
				BookVO.builder().title("T1").description("D4")
						.authors(Lists.newArrayList(
								AuthorVO.builder().name("A1").surname("S3").build(),
								AuthorVO.builder().name("A2").surname("S4").build()
						)).build(),
				BookVO.builder().title("T2").description("D5").build(),
				BookVO.builder().title("T3").description("D6").build()
		);
	}
}
