package ua.in.sz.guavacache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.asm.ClassReader;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.FieldVisitor;
import org.springframework.asm.Opcodes;

@Slf4j
public class Application {
	public static void main(String[] args) throws Exception {
		ClassReader cr = new ClassReader("ua.in.sz.guavacache.Scannee");
		cr.accept(new AnnotationScanner(Opcodes.ASM4),0);
		log.info("end");
	}

	private static class AnnotationScanner extends ClassVisitor {
		public AnnotationScanner(int asm4) {
			super(asm4);
		}

		@Override
		public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
			log.info("field: {}", name);
			return super.visitField(access, name, desc, signature, value);
		}
	}
}
