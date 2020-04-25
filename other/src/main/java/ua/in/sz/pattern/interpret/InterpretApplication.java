package ua.in.sz.pattern.interpret;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.interpret.api.Entity;
import ua.in.sz.pattern.interpret.expression.EntityExpression;
import ua.in.sz.pattern.interpret.vo.EntityVO;

import java.time.LocalDate;

import static ua.in.sz.pattern.interpret.expression.EntityExpressions.currentEntity;
import static ua.in.sz.pattern.interpret.expression.EntityExpressions.futureEntity;

@Slf4j
public class InterpretApplication {
	public static void main(String[] args) {
		LocalDate now = LocalDate.now();
		Entity entity = new EntityVO(now.minusDays(1), now.plusDays(1));

		EntityExpression currentOrFuture = currentEntity().or(futureEntity());
		boolean isCurrentOrFuture = currentOrFuture.evaluate(entity, now);

		log.info("Is current or future: {}", isCurrentOrFuture);
	}
}
