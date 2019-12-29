package ua.in.sz.neuralnetworks;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
@Builder
public class Synapse implements Supplier<Float> {
	private Neuron source;
	private float weight;

	@Override
	public Float get() {
		return weight * source.get();
	}
}
