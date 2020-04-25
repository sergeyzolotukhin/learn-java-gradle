package ua.in.sz.parameter.validation;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;


/**
 * @NotNull
 * javax.validation.constraints.NotNull
 * edu.umd.cs.findbugs.annotations.NonNull
 * org.jetbrains.annotations.NotNull
 *
 * @NonNull
 * import lombok.NonNull;
 * import org.checkerframework.checker.nullness.qual.NonNull;
 * org.eclipse.jdt.annotation.NonNull
 *
 * @Nonnull
 * import javax.annotation.Nonnull;
 * import org.springframework.lang.NonNull;
 */
@Slf4j
public class Main {

	public static void main(String[] args) {
		notNullParameters(null);
	}

	public static void notNullParameters(@NonNull Integer i) {
		log.info("i = {}", ++i);
	}
}
