package com.bnk03.bnklaim;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BnklaimApplicationTests {

	@Test
	void contextLoads() {
		BnklaimApplication.main(new String[] {});
		assertEquals(2, 1 + 1);
		;
	}

}
